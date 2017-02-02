package com.task.excel;

import com.task.dao.AbstractDAOFactory;
import com.task.model.UserTaskInfo;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * todo
 */
public class ExcelBuilder extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        HashMap<String, Float> taskTiming = (HashMap<String, Float>) model.get("taskDelay");
        List<UserTaskInfo> userTaskInfos = (List<UserTaskInfo>) model.get("userTaskInfo");

        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Roles List");
        sheet.setDefaultColumnWidth(20);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.PINK.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        HSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellValue("Timing statuses");
        title.createCell(3).setCellValue("Employee`s projects, sprints, tasks");

        HSSFRow header = sheet.createRow(1);

        header.createCell(0).setCellValue("Task");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Timing");
        header.getCell(1).setCellStyle(style);

        header.createCell(3).setCellValue("Employee");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Project");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Sprint");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("Task");
        header.getCell(6).setCellStyle(style);


        // create data rows
        int rowCount = 2;
        for (Map.Entry<String, Float> item : taskTiming.entrySet()) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(item.getKey());
            aRow.createCell(1).setCellValue(item.getValue() > 0 ? "" : "Failed");
        }

        rowCount = 2;
        for (UserTaskInfo item : userTaskInfos){
            HSSFRow aRow = sheet.getRow(rowCount++);
            aRow.createCell(3).setCellValue(item.getUserLogin());
            aRow.createCell(4).setCellValue(item.getProject());
            aRow.createCell(5).setCellValue(item.getSprint());
            aRow.createCell(6).setCellValue(item.getTask());
        }
    }

}
