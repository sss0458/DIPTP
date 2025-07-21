package org.example;

import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;

public class POIBasicTest {
    public static void main(String[] args) {
        try {
            // 1. 创建空白文档
            XWPFDocument doc = new XWPFDocument();

            // 2. 添加标题段落
            XWPFParagraph title = doc.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("POI 基础测试");
            titleRun.setBold(true);
            titleRun.setFontSize(16);

            // 3. 添加表格
            XWPFTable table = doc.createTable(3, 2);
            table.getRow(0).getCell(0).setText("Header 1");
            table.getRow(0).getCell(1).setText("Header 2");

            // 4. 输出到文件
            try (FileOutputStream out = new FileOutputStream("poi-test.docx")) {
                doc.write(out);
            }

            System.out.println("测试文件生成成功：poi-test.docx");
        } catch (Exception e) {
            System.err.println("POI 测试失败:");
            e.printStackTrace();
        }
    }
}