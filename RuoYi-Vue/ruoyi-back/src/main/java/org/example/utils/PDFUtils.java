package org.example.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

public class PDFUtils<T> {
    private static final Logger log = LoggerFactory.getLogger(PDFUtils.class);
    private static final BaseFont CHINESE_BASE_FONT;
    private static final Font TITLE_FONT;
    private static final Font HEADER_FONT;
    private static final Font DATA_FONT;
    private static final Font SUBTITLE_FONT;

    static {
        try {
            // 方法一：使用 iText 自带的 FreeSans 字体（支持中文）
//            CHINESE_BASE_FONT = BaseFont.createFont("FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            // 方法二：从类路径加载字体资源（需将字体文件放入项目 resources 目录）
            CHINESE_BASE_FONT = loadFontFromResource("/fonts/simhei.ttf");

            TITLE_FONT = new Font(CHINESE_BASE_FONT, 16, Font.BOLD);
            HEADER_FONT = new Font(CHINESE_BASE_FONT, 10, Font.BOLD, BaseColor.BLACK);
            DATA_FONT = new Font(CHINESE_BASE_FONT, 10);
            SUBTITLE_FONT = new Font(CHINESE_BASE_FONT, 12, Font.BOLD, BaseColor.BLACK);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Failed to load Chinese font", e);
        }
    }

    // 从类路径加载字体资源的辅助方法
    private static BaseFont loadFontFromResource(String fontPath) throws DocumentException, IOException {
        try (InputStream fontStream = PDFUtils.class.getResourceAsStream(fontPath)) {
            if (fontStream == null) {
                throw new FileNotFoundException("Font resource not found: " + fontPath);
            }

            // 创建临时文件存储字体
            File tempFontFile = File.createTempFile("font", ".ttf");
            tempFontFile.deleteOnExit();

            try (OutputStream out = new FileOutputStream(tempFontFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fontStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            return BaseFont.createFont(tempFontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }
    }


    // 颜色配置
    private static final BaseColor HEADER_BG_COLOR = new BaseColor(240, 240, 240); // 浅灰色
    private static final BaseColor ROW_BG_COLOR_1 = new BaseColor(255, 255, 255); // 白色
    private static final BaseColor ROW_BG_COLOR_2 = new BaseColor(249, 249, 249); // 浅灰色

    private Class<T> clazz;
    private List<T> list;
    private String sheetName;

    public PDFUtils(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 导出竖型PDF到HTTP响应
     */
    public void exportPDF(HttpServletResponse response, List<T> list, String sheetName) throws IOException {
        this.list = list;
        this.sheetName = sheetName;

        response.setContentType("application/pdf");
        response.setCharacterEncoding("utf-8");

        // 生成安全的文件名
        String filename = encodingFilename(sheetName) + ".pdf";
        String encodedFilename = encodeFilename(response, filename);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFilename + "\"");

        try (OutputStream os = response.getOutputStream()) {
            generateVerticalPDF(os);
        } catch (Exception e) {
            log.error("导出PDF异常", e);
            throw new IOException("导出PDF失败");
        }
    }

    private void generateVerticalPDF(OutputStream os) throws DocumentException, IOException {
        // 创建纵向文档
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, os);

        // 设置文档属性
        document.addTitle(sheetName + " - 详细数据");
        document.addCreator("Ruoyi管理系统");

        document.open();

        // 添加标题
        addTitle(document);

        // 获取所有列信息
        List<ColumnInfo> columns = getColumnInfos();

        // 添加数据表格
        addDataTable(document, columns);

        // 添加页脚
        addFooter(writer, document);

        document.close();
    }

    /**
     * 添加标题
     */
    private void addTitle(Document document) throws DocumentException {
        // 主标题
        Paragraph title = new Paragraph(sheetName, TITLE_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);

        // 副标题（日期信息）
        Paragraph subtitle = new Paragraph("导出时间: " + DateUtils.getTime(), SUBTITLE_FONT);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        subtitle.setSpacingAfter(20);
        document.add(subtitle);
    }

    private void addDataTable(Document document, List<ColumnInfo> columns) throws DocumentException {
        // 创建单个表格（两列：表头+数据）
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(95);
        table.setWidths(new float[]{1, 4});
        table.setSpacingBefore(10f);

        // 设置表格样式
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.getDefaultCell().setPadding(8);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        // 设置表格跨页时保持表头
        table.setHeaderRows(0); // 不使用内置表头
        table.setSplitLate(false); // 避免行被分割
        table.setSplitRows(true); // 允许行跨页

        // 添加所有数据项
        for (T item : list) {
            // 添加数据项标题行
            addItemTitle(table, item);

            // 添加数据行
            for (ColumnInfo column : columns) {
                addDataRow(table, item, column);
            }

            // 添加分隔行
            addItemSeparator(table);
        }

        document.add(table);
    }

    /**
     * 添加数据项标题行
     */
    private void addItemTitle(PdfPTable table, T item) {

        PdfPCell titleCell = new PdfPCell(new Phrase("", HEADER_FONT));
        titleCell.setBackgroundColor(HEADER_BG_COLOR);
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        titleCell.setPadding(10);
        titleCell.setColspan(2); // 跨两列
        table.addCell(titleCell);
    }

    /**
     * 添加数据行
     */
    private void addDataRow(PdfPTable table, T item, ColumnInfo column) {
        try {
            column.field.setAccessible(true);
            Object value = column.field.get(item);
            String formattedValue = formatCellValue(value, column.excel);

            // 根据行索引设置背景色（交替颜色）
            int rowIndex = table.getRows().size() % 4;
            BaseColor bgColor = (rowIndex < 2) ? ROW_BG_COLOR_1 : ROW_BG_COLOR_2;

            // 左侧：字段名
            PdfPCell headerCell = new PdfPCell(new Phrase(column.excel.name(), HEADER_FONT));
            headerCell.setBackgroundColor(bgColor);
            headerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            headerCell.setBorder(Rectangle.NO_BORDER);
            headerCell.setPadding(8);
            table.addCell(headerCell);

            // 右侧：字段值
            PdfPCell valueCell = new PdfPCell(new Phrase(formattedValue, DATA_FONT));
            valueCell.setBackgroundColor(bgColor);
            valueCell.setBorder(Rectangle.NO_BORDER);
            valueCell.setPadding(8);
            table.addCell(valueCell);

        } catch (Exception e) {
            log.error("处理字段失败: {}", column.field.getName(), e);
            // 添加空单元格保持表格结构
            table.addCell("");
            table.addCell("");
        }
    }

    /**
     * 添加分隔行
     */
    private void addItemSeparator(PdfPTable table) {
        // 添加空行作为分隔
        PdfPCell spacerCell = new PdfPCell(new Phrase(""));
        spacerCell.setBorder(Rectangle.NO_BORDER);
        spacerCell.setFixedHeight(15f);
        spacerCell.setColspan(2);
        table.addCell(spacerCell);

        // 添加分隔线
        PdfPCell lineCell = new PdfPCell();
        lineCell.setBorder(Rectangle.BOTTOM);
        lineCell.setBorderColor(BaseColor.LIGHT_GRAY);
        lineCell.setFixedHeight(1f);
        lineCell.setColspan(2);
        table.addCell(lineCell);
    }

    /**
     * 添加对象分隔符
     */
    private void addItemSeparator(PdfPTable table, Document document) throws DocumentException {
        if (table.getRows().size() > 0) {
            // 添加空行
            PdfPCell spacerCell = new PdfPCell(new Phrase(""));
            spacerCell.setBorder(Rectangle.NO_BORDER);
            spacerCell.setFixedHeight(15f);
            spacerCell.setColspan(2);
            table.addCell(spacerCell);

            // 添加分隔线
            PdfPCell lineCell = new PdfPCell();
            lineCell.setBorder(Rectangle.BOTTOM);
            lineCell.setBorderColor(BaseColor.LIGHT_GRAY);
            lineCell.setFixedHeight(1f);
            lineCell.setColspan(2);
            table.addCell(lineCell);
        }
    }


    /**
     * 添加页脚
     */
    private void addFooter(PdfWriter writer, Document document) {
        // 创建页脚事件
        HeaderFooter event = new HeaderFooter();
        writer.setPageEvent(event);
    }

    /**
     * 页脚事件处理类
     */
    static class HeaderFooter extends PdfPageEventHelper {
        public void onEndPage(PdfWriter writer, Document document) {
            // 创建页脚表格
            PdfPTable footer = new PdfPTable(1);
            footer.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());

            // 页脚文本
            Phrase footerText = new Phrase(
                    "第 " + writer.getPageNumber() + " 页 | " +
                            new java.util.Date().toString(),
                    new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.GRAY)
            );

            // 页脚单元格
            PdfPCell cell = new PdfPCell(footerText);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(10);
            footer.addCell(cell);

            // 将页脚放在页面底部
            footer.writeSelectedRows(
                    0, -1,
                    document.leftMargin(),
                    document.bottomMargin() - 10,
                    writer.getDirectContent()
            );
        }
    }

    /**
     * 列信息封装类
     */
    private static class ColumnInfo {
        Field field;
        Excel excel;

        ColumnInfo(Field field, Excel excel) {
            this.field = field;
            this.excel = excel;
        }
    }

    /**
     * 获取所有列信息
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

        // 处理当前类的字段
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Excel.class)) {
                columns.add(new ColumnInfo(field, field.getAnnotation(Excel.class)));
            } else if (field.isAnnotationPresent(Excels.class)) {
                for (Excel excel : field.getAnnotation(Excels.class).value()) {
                    columns.add(new ColumnInfo(field, excel));
                }
            }
        }

        // 递归处理父类
        addFieldsRecursive(clazz.getSuperclass(), columns);
    }

    /**
     * 格式化单元格值
     */
    private String formatCellValue(Object value, Excel excel) {
        if (value == null) {
            return StringUtils.isNotEmpty(excel.defaultValue()) ?
                    excel.defaultValue() : "N/A";
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

        // 6. 处理布尔值
        if (value instanceof Boolean) {
            return (Boolean) value ? "是" : "否";
        }

        // 7. 默认处理
        return value.toString() + excel.suffix();
    }

    /**
     * 格式化数字类型
     */
    private String formatNumber(Number value, Excel excel) {
        // 处理小数位数
        if (excel.scale() > 0) {
            BigDecimal bd = new BigDecimal(value.toString());
            bd = bd.setScale(excel.scale(), RoundingMode.valueOf(excel.roundingMode()));
            return bd.toString() + excel.suffix();
        }

        // 整数处理
        if (value instanceof Double || value instanceof Float) {
            if (value.doubleValue() % 1 == 0) {
                return String.valueOf(value.intValue()) + excel.suffix();
            }
        }
        return value.toString() + excel.suffix();
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

    private String encodingFilename(String sheetName) {
        // 移除文件名中的非法字符
        String safeName = sheetName.replaceAll("[\\\\/:*?\"<>|]", "");
        // 截取前50个字符防止文件名过长
        return safeName.length() > 50 ? safeName.substring(0, 50) : safeName;
    }

    private String encodeFilename(HttpServletResponse response, String filename) {
        String userAgent = response.getHeader("User-Agent");
        try {
            // 处理中文文件名
            if (userAgent != null && userAgent.contains("MSIE")) {
                // IE浏览器
                return java.net.URLEncoder.encode(filename, "UTF-8");
            } else if (userAgent != null && userAgent.contains("Firefox")) {
                // Firefox浏览器
                return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            } else {
                // 其他浏览器
                return java.net.URLEncoder.encode(filename, "UTF-8");
            }
        } catch (Exception e) {
            return filename;
        }
    }
}