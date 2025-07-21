package org.example.utils;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class WordUtils<T> {
    private static final Logger log = LoggerFactory.getLogger(WordUtils.class);

    // 默认样式设置
    private static final int TITLE_FONT_SIZE = 16;
    private static final int HEADER_FONT_SIZE = 11;
    private static final int DATA_FONT_SIZE = 10;
    private static final int ITEM_TITLE_FONT_SIZE = 14;
    private static final String HEADER_BG_COLOR = "447FBD"; // 表头背景色
    private static final String ITEM_TITLE_BG_COLOR = "D9E1F2"; // 数据项标题背景色
    private static final String HEADER_FONT_COLOR = "FFFFFF";
    private static final String ITEM_TITLE_FONT_COLOR = "000000";
    private static final String HEADER_COLUMN_WIDTH = "20%"; // 表头列宽度
    private static final String DATA_COLUMN_WIDTH = "80%";  // 数据列宽度

    private Class<T> clazz;
    private List<T> list;
    private String sheetName;
    private boolean showItemTitles = true; // 控制是否显示数据项标题

    public WordUtils(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 导出Word到HTTP响应（纵向布局）
     *
     * @param response HTTP响应对象
     * @param list 数据列表
     * @param sheetName 表格名称
     */
    public void exportWord(HttpServletResponse response, List<T> list, String sheetName) throws IOException {
        this.list = list;
        this.sheetName = sheetName;

        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setCharacterEncoding("utf-8");

        String filename = encodingFilename(sheetName) + ".docx";
        String encodedFilename = encodeFilename(response, filename);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFilename + "\"");

        try (OutputStream os = response.getOutputStream()) {
            generateVerticalLayoutWord(os);
        } catch (Exception e) {
            log.error("导出Word异常", e);
            throw new IOException("导出Word失败");
        }
    }

    /**
     * 设置是否显示数据项标题
     * @param show true显示，false不显示
     */
    public void setShowItemTitles(boolean show) {
        this.showItemTitles = show;
    }

    /**
     * 生成纵向布局的Word文档
     */
    private void generateVerticalLayoutWord(OutputStream os) throws Exception {
        try (XWPFDocument document = new XWPFDocument()) {
            // 添加文档标题
            addTitle(document);

            // 获取所有列信息
            List<ColumnInfo> columns = getColumnInfos();

            // 为每个数据项创建独立表格
            for (int itemIndex = 0; itemIndex < list.size(); itemIndex++) {
                T item = list.get(itemIndex);

                // 创建表格：行数=字段数量 + 标题行(如果需要)
                int rowCount = columns.size() + (showItemTitles ? 1 : 0);
                XWPFTable table = document.createTable(rowCount, 2);
                table.setWidth("100%");

                // 正确设置列宽
                CTTblGrid grid = table.getCTTbl().getTblGrid();
                if (grid == null) {
                    grid = table.getCTTbl().addNewTblGrid();
                }

                // 创建第一列（表头列）
                CTTblGridCol col1 = grid.addNewGridCol();
                col1.setW(BigInteger.valueOf(2000)); // 直接设置列宽，单位为二十分之一磅

                // 创建第二列（数据列）
                CTTblGridCol col2 = grid.addNewGridCol();
                col2.setW(BigInteger.valueOf(8000)); // 直接设置列宽，单位为二十分之一磅

                // 添加数据项标题行（如果需要）
                int dataRowStartIndex = 0;
                if (showItemTitles) {
                    addItemTitleRow(table, item, itemIndex);
                    dataRowStartIndex = 1; // 数据行从第二行开始
                }

                // 填充表格内容
                for (int i = 0; i < columns.size(); i++) {
                    ColumnInfo column = columns.get(i);
                    XWPFTableRow row = table.getRow(i + dataRowStartIndex);

                    // 设置表头单元格（左侧）
                    setHeaderCell(row.getCell(0), column.excel.name());

                    // 设置数据单元格（右侧）
                    setDataCell(row.getCell(1), item, column);
                }

                // 在表格之间添加分隔
                if (itemIndex < list.size() - 1) {
                    document.createParagraph().createRun().addBreak(BreakType.TEXT_WRAPPING);
                }
            }

            // 写入输出流
            document.write(os);
        }
    }

    /**
     * 添加数据项标题行
     */
    private void addItemTitleRow(XWPFTable table, T item, int itemIndex) {
        // 获取第一行
        XWPFTableRow titleRow = table.getRow(0);

        // 合并第一行的两个单元格
        mergeCellsHorizontally(table, 0, 0, 1);

        // 获取合并后的单元格（第一个单元格）
        XWPFTableCell titleCell = titleRow.getCell(0);

        // 设置单元格样式
        titleCell.setColor(ITEM_TITLE_BG_COLOR);
        titleCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

        // 设置段落和文本样式
        XWPFParagraph para = titleCell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.CENTER);
        para.setSpacingAfter(0);
        para.setSpacingBefore(0);

        XWPFRun run = para.createRun();
        run.setBold(true);
        run.setFontSize(ITEM_TITLE_FONT_SIZE);
        run.setColor(ITEM_TITLE_FONT_COLOR);

        // 移除第二个单元格（已被合并）
        titleRow.removeCell(1);
    }

    /**
     * 水平合并单元格
     *
     * @param table 表格对象
     * @param row 行索引
     * @param startCol 起始列索引
     * @param endCol 结束列索引
     */
    private void mergeCellsHorizontally(XWPFTable table, int row, int startCol, int endCol) {
        for (int colIndex = startCol; colIndex <= endCol; colIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(colIndex);
            if (cell == null) continue;

            CTTc ctTc = cell.getCTTc();
            if (ctTc == null) {
                ctTc = cell.getCTTc();
            }

            CTTcPr tcPr = ctTc.isSetTcPr() ? ctTc.getTcPr() : ctTc.addNewTcPr();
            if (!tcPr.isSetHMerge()) {
                tcPr.addNewHMerge();
            }

            // 第一个单元格设置合并开始，其他单元格设置合并继续
            if (colIndex == startCol) {
                tcPr.getHMerge().setVal(STMerge.RESTART);
            } else {
                tcPr.getHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    /**
     * 列信息封装类
     */
    private static class ColumnInfo {
        Field field;
        Excel excel;
        String fieldName;

        ColumnInfo(Field field, Excel excel) {
            this.field = field;
            this.excel = excel;
            this.fieldName = field.getName();
        }
    }

    /**
     * 添加文档标题
     */
    private void addTitle(XWPFDocument document) {
        XWPFParagraph titlePara = document.createParagraph();
        titlePara.setAlignment(ParagraphAlignment.CENTER);
        titlePara.setSpacingAfter(400); // 20磅

        XWPFRun titleRun = titlePara.createRun();
        titleRun.setText(sheetName);
        titleRun.setBold(true);
        titleRun.setFontSize(TITLE_FONT_SIZE);
    }

    /**
     * 获取所有列信息（支持嵌套结构）
     */
    private List<ColumnInfo> getColumnInfos() {
        List<ColumnInfo> columns = new ArrayList<>();
        addFieldsRecursive(clazz, columns);

        // 按sort排序
        columns.sort(Comparator.comparingInt(c -> c.excel.sort()));
        return columns;
    }

    /**
     * 递归获取所有字段（包括父类）
     */
    private void addFieldsRecursive(Class<?> clazz, List<ColumnInfo> columns) {
        if (clazz == null || clazz == Object.class) return;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Excel.class)) {
                columns.add(new ColumnInfo(field, field.getAnnotation(Excel.class)));
            } else if (field.isAnnotationPresent(Excels.class)) {
                for (Excel excel : field.getAnnotation(Excels.class).value()) {
                    columns.add(new ColumnInfo(field, excel));
                }
            }
        }

        addFieldsRecursive(clazz.getSuperclass(), columns);
    }

    /**
     * 获取单元格对齐方式
     */
    private ParagraphAlignment getAlignment(Excel excel) {
        switch (excel.align()) {
            case LEFT: return ParagraphAlignment.LEFT;
            case RIGHT: return ParagraphAlignment.RIGHT;
            case CENTER: return ParagraphAlignment.CENTER;
            default: return ParagraphAlignment.LEFT;
        }
    }

    /**
     * 格式化单元格值
     */
    private String formatCellValue(Object value, Excel excel) {
        if (value == null) {
            return StringUtils.isNotEmpty(excel.defaultValue()) ?
                    excel.defaultValue() : "";
        }

        // 1. 处理日期格式
        if (StringUtils.isNotEmpty(excel.dateFormat())) {
            return parseDateToStr(excel.dateFormat(), value);
        }

        // 2. 处理字典转换
        if (StringUtils.isNotEmpty(excel.dictType())) {
            return DictUtils.getDictLabel(excel.dictType(), value.toString(), excel.separator());
        }

        // 3. 处理转换表达式
        if (StringUtils.isNotEmpty(excel.readConverterExp())) {
            return convertByExp(value.toString(), excel.readConverterExp(), excel.separator());
        }

        // 4. 处理BigDecimal精度
        if (value instanceof BigDecimal && excel.scale() >= 0) {
            BigDecimal bd = (BigDecimal) value;
            bd = bd.setScale(excel.scale(), RoundingMode.valueOf(excel.roundingMode()));
            return bd.toString() + excel.suffix();
        }

        // 5. 处理数字格式
        if (value instanceof Number) {
            return formatNumber((Number) value, excel);
        }

        // 6. 默认处理
        return value.toString() + excel.suffix();
    }

    /**
     * 格式化数字类型
     */
    private String formatNumber(Number value, Excel excel) {
        if (excel.scale() > 0) {
            BigDecimal bd = new BigDecimal(value.toString());
            bd = bd.setScale(excel.scale(), RoundingMode.valueOf(excel.roundingMode()));
            return bd.toString() + excel.suffix();
        }

        if (value instanceof Double || value instanceof Float) {
            if (value.doubleValue() % 1 == 0) {
                return String.valueOf(value.intValue()) + excel.suffix();
            }
        }
        return value.toString() + excel.suffix();
    }

    private String encodingFilename(String sheetName) {
        String safeName = sheetName.replaceAll("[\\\\/:*?\"<>|]", "");
        return safeName.length() > 50 ? safeName.substring(0, 50) : safeName;
    }

    private String encodeFilename(HttpServletResponse response, String filename) {
        String userAgent = response.getHeader("User-Agent");
        try {
            if (userAgent != null && userAgent.contains("MSIE")) {
                return java.net.URLEncoder.encode(filename, "UTF-8");
            } else if (userAgent != null && userAgent.contains("Firefox")) {
                return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            } else {
                return java.net.URLEncoder.encode(filename, "UTF-8");
            }
        } catch (Exception e) {
            return filename;
        }
    }

    private String parseDateToStr(String format, Object value) {
        if (value instanceof Date) {
            return DateUtils.parseDateToStr(format, (Date) value);
        } else if (value instanceof LocalDateTime) {
            return DateUtils.parseDateToStr(format, DateUtils.toDate((LocalDateTime) value));
        } else if (value instanceof LocalDate) {
            return DateUtils.parseDateToStr(format, DateUtils.toDate((LocalDate) value));
        }
        return value.toString();
    }

    private String convertByExp(String value, String converterExp, String separator) {
        String[] items = converterExp.split(",");
        for (String item : items) {
            String[] kv = item.split("=");
            if (kv.length == 2 && kv[0].equals(value)) {
                return kv[1];
            }
        }
        return value;
    }

    /**
     * 设置表头单元格样式
     */
    private void setHeaderCell(XWPFTableCell cell, String headerText) {
        cell.setColor(HEADER_BG_COLOR);
        cell.setWidth(HEADER_COLUMN_WIDTH);

        XWPFParagraph para = cell.getParagraphs().get(0);
        para.setAlignment(ParagraphAlignment.LEFT);

        XWPFRun run = para.createRun();
        run.setText(headerText);
        run.setBold(true);
        run.setFontSize(HEADER_FONT_SIZE);
        run.setColor(HEADER_FONT_COLOR);
    }

    /**
     * 设置数据单元格样式和内容
     */
    private void setDataCell(XWPFTableCell cell, T item, ColumnInfo column) {
        try {
            column.field.setAccessible(true);
            Object value = column.field.get(item);
            String cellValue = formatCellValue(value, column.excel);

            XWPFParagraph para = cell.getParagraphs().get(0);
            para.setAlignment(getAlignment(column.excel));
            para.setSpacingAfterLines(1);

            XWPFRun run = para.createRun();
            run.setText(cellValue);
            run.setFontSize(DATA_FONT_SIZE);

            cell.setWidth(DATA_COLUMN_WIDTH);
        } catch (Exception e) {
            log.error("反射获取字段值失败", e);
            cell.setText("");
        }
    }
}