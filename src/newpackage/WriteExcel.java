/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author doxu
 */
//java 创建excel

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcel {
    /** Excel 文件要存放的位置，假定在D盘JTest目录下*/
    public static String outputFile = "E:\\快盘\\capital\\NetBeansProjects\\xmlcomplie -last revision\\ExcelDemo.xls";
    
    public static void PutData(String args) {
        FileOutputStream fOut = null;
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值
            // 如要新建一名为"效益指标"的工作表，其语句为：
            // HSSFSheet sheet = workbook.createSheet("效益指标");
            HSSFSheet sheet = workbook.createSheet("demo");
            //在索引0的位置创建行（最顶端的行）
            HSSFRow row = sheet.createRow((short) 1);
            //在索引0的位置创建单元格(左上端)
            HSSFCell cell = row.createCell((short) 0);
            //定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            //在单元格中输入一些内容
            cell.setCellValue(args);
            //新建一输出文件流
            fOut = new FileOutputStream(outputFile);
            //把相应的Excel工作薄存盘
            workbook.write(fOut);
            fOut.flush();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("testExcel wrong");
        } finally {
            try {
                if (fOut != null) {
                    fOut.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
