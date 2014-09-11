/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import GUI.FindDBC;
import GUI.PopMe1;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

/**
 *
 * @author doxu
 */
public class ReadDbc {

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    
    public String   PropertyName = "";
    public String   Property = "";
    public String   PropertyValue = "";
    public Desktop  desktop;
    public String  Object;
    public static String outputFile ="C:\\Users\\doxu\\Desktop\\test.xls";
    public static String fileName ="C:\\Users\\doxu\\Desktop\\test.xls";
    public void readFileByLines() {
        
        FindDBC dialog = new FindDBC(new javax.swing.JFrame(), true);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        outputFile = dialog.Excelpath+dialog.ExcelName;
        fileName=dialog.DBCpath+"\\"+dialog.DBCFileName;
        
        //        FileOpen2 file= new FileOpen2();
//        
//        String fileName = file.getCurrentDirectory().getPath()+"\\"+file.getCurrentFile().getName();
        
        
        FileOutputStream fOut = null;//this is for excle out flow
        File file = new File(fileName);
        BufferedReader reader = null;
       List TitleList = new ArrayList(){{add("Msg\n" +"ID\n" +"(hex)");add("Msg\n" +"Name");
       add("Msg\n" +"Length\n" +"(bytes)");add("ECU \n" +"(Tx)");add("SignalName:Identifier");
       add("SignalOffset:Integer");add("SignalSize:Integer");add("ByteOrder");
       add("Signed");add("RangeScale:Integer ");add(" RangeOffset:Integer");
       add("RangeLow:Float");add("RangeHigh:Float");add(" RangeUnit:String");
       add("ReceiverNodeName:Identifier");}};
        
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("demo");     
            
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            String tempString1 = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            int MessagerIdNumber = 0;
            
            int SingalNumber=6000;
         
                String[] SingalName =new String[SingalNumber];
                String[] SingalOffset =new String[SingalNumber];
                String[] SingalSize =new String[SingalNumber];
                String[] ByteOrder =new String[SingalNumber];
                String[] Signed =new String[SingalNumber];
                String[] RangeScale =new String[SingalNumber];
                String[] RangeOffset =new String[SingalNumber];
                String[] RangeLow =new String[SingalNumber];
                String[] RangeHigh =new String[SingalNumber];
                String[] RangeUnit=new String[SingalNumber];
                String[] Receiver=new String[SingalNumber];
            int SingalOrder=0;
            int HowManySingalinOneMessage=0;
            int BasicSingal=0;
            boolean Ecustatus=false;
            String status1="";
            String status2="";
            String status3="";
            String[] MessagerInfo = new String[4];
            while ((tempString = reader.readLine()) != null)
            {
                // 显示行号
                String[] LineData = tempString.split(" ");
                String[] LineUnit = tempString.split("] \"");
                String[] LineReceiver = tempString.split("\" ");
                String[] ECUroot = new String[LineData.length - 1];
                if (LineData.length >= 4 && LineData[0].equals("BU_:")) 
                {
                   // System.out.println("line " + line + ": " + LineData[0]);
                    int EcuNumber = LineData.length - 1;
                    for (int i = 0; i < EcuNumber; i++) 
                    {
                        ECUroot[i] = LineData[i + 1];
                        //自动的把所有的ECU添加到标题上面。
                        //TitleList.add(ECUroot[i].toString());                       
                    }
                }
                if (LineData.length >= 4 && LineData[0].equals("BO_")) 
                {
                   // System.out.println("line " + line + ": " + LineData[0]);
                    int EcuInfoNumber = LineData.length - 1;
                    for (int i = 0; i < EcuInfoNumber; i++) {
                        MessagerInfo[i] = LineData[i + 1];
                        //System.out.println(MessagerInfo[i]);
                    }
                    Ecustatus=true;
                    MessagerIdNumber++;
                    BasicSingal=BasicSingal+HowManySingalinOneMessage;
                    HowManySingalinOneMessage=0;                   
                }
                if (LineData.length >= 4 && LineData[1].equals("SG_")) {
                    //用于表示当前信号属于第一个消息
                   Ecustatus=false;
                    //信号数加1，每次遇到新的消息，就清零一次                
                   HowManySingalinOneMessage++;                   
                    //System.out.println("line " + line + ": " + LineData[4]);
                   SingalName[SingalOrder]= LineData[2];
                    //System.out.println(SingalName[SingalOrder]);
                   SingalOffset[SingalOrder]= LineData[4].split("\\|")[0];
                    //System.out.println(SingalOffset[SingalOrder]);
                   SingalSize[SingalOrder]= LineData[4].split("@")[0].split("\\|")[1];
                    //System.out.println(SingalSize[SingalOrder]);
                   ByteOrder[SingalOrder]= LineData[4].split("@")[1].substring(0, 1);
                   // System.out.println(ByteOrder[SingalOrder]);
                   Signed[SingalOrder]= LineData[4].split("@")[1].substring(1, 2);
                   // System.out.println(Signed[SingalOrder]);                   
                   RangeScale[SingalOrder]= LineData[5].split(",")[0].substring(1);
                   // System.out.println(RangeScale[SingalOrder]);
                   RangeOffset[SingalOrder]= LineData[5].split(",")[1].substring(0,(LineData[5].split(",")[1].length()-1));
                   //System.out.println(RangeOffset[SingalOrder]);                
                   RangeLow[SingalOrder]= LineData[6].split("\\|")[0].substring(1);;
                    //System.out.println(RangeLow[SingalOrder]);
                   RangeHigh[SingalOrder]= LineData[6].split("\\|")[1].substring(0,(LineData[6].split("\\|")[1].length()-1));;
                    //System.out.println(RangeHigh[SingalOrder]);                   
                   RangeUnit[SingalOrder]= LineUnit[1].split("\"")[0];
                   //System.out.println( RangeUnit[SingalOrder]); 
                   Receiver[SingalOrder]=LineReceiver[1];
                   System.out.println( Receiver[SingalOrder]); 
                   SingalOrder++;
                   // System.out.println((LineData.length));
                  
                }
                if(LineData.length>1)
                {
                    status1=LineData[0]+LineData[1];
                    status3=status2+status1;
                }
                else
                        {
                    status1=LineData[0];
                    status3=status2+status1;
                     }
                status2=status1;
                //System.out.println(status3);
                if(status3.equalsIgnoreCase("SG_"))
                {
                 if( HowManySingalinOneMessage!=0)
                    {                      
                        for (int j = 0;j <MessagerIdNumber; j++) 
                        {
                            for (int i = 0;i <4; i++) 
                            {
                            //Region region = new Region((short)rowFrom,(short)columnFrom,(short)rowTo,(short)columnTo);   
                            Region region = new Region((short) (BasicSingal+1),(short)i,(short)(BasicSingal+HowManySingalinOneMessage),(short)i);   
                            sheet.addMergedRegion(region);
                             //System.out.println(MessagerInfo[i]);
                            HSSFCell cell =sheet.createRow((short) (BasicSingal+1)).createCell((short)i);
                            cell.setCellValue(MessagerInfo[i]); 
                            }
                        }    
                    }
                
                
                }   
                line++;
            }
            
            //写入标题
            Object Title[]= TitleList.toArray();
            for (int i = 0; i <Title.length; i++) 
            {
                        //在索引0的位置创建行（最顶端的行）
                        HSSFRow row = sheet.createRow((short) 0);                            
                        //在索引0的位置创建单元格(左上端)
                        HSSFCell cell = row.createCell((short) i);
                        //定义单元格为字符串类型
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
                        //在单元格中输入一些内容
                        cell.setCellValue(Title[i].toString());
            }
            //写入单元格数据
            for (int i = 0; i <SingalOrder; i++) 
            {
                //在索引0的位置创建行（最顶端的行）
                HSSFRow row = sheet.createRow((short) (i+1));
                //System.out.println(SingalName[0]);
                for(int j = 0;j<11;j++)
                {
                        
                        //在索引0的位置创建单元格(左上端)
                        HSSFCell cell = row.createCell((short) (j+4));
                        //定义单元格为字符串类型
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        //cell.setCellValue(SingalName[i]);
                        //在单元格中输入一些内容
                        switch(j)
                                {
                                    case 0: cell.setCellValue(SingalName[i]);break;
                                    case 1: cell.setCellValue(SingalOffset[i]);break;
                                    case 2: cell.setCellValue(SingalSize[i]);break;
                                    case 3: cell.setCellValue(ByteOrder[i]);break;
                                    case 4: cell.setCellValue(Signed[i]);break;
                                    case 5: cell.setCellValue(RangeScale[i]);break;
                                    case 6: cell.setCellValue(RangeOffset[i]);break;
                                    case 7: cell.setCellValue(RangeLow[i]);break;
                                    case 8: cell.setCellValue(RangeHigh[i]);break;
                                    case 9: cell.setCellValue(RangeUnit[i]);break;
                                    case 10: cell.setCellValue(Receiver[i]);break;
                                    default :cell.setCellValue("");
                              }            
                }
       }
            
            fOut = new FileOutputStream(outputFile);
            //把相应的Excel工作薄存盘
            workbook.write(fOut);
            fOut.flush();
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) 
            {
                try {
                     if (fOut != null) 
                    {
                        fOut.close();
                    }
            reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args) {
//   以下代码可以在程序中打开打开窗口
//        FileOpen2 file= new FileOpen2();
//        
//        String fileName = file.getCurrentDirectory().getPath()+"\\"+file.getCurrentFile().getName();
         
        //String fileName = "C:\\Users\\doxu\\Desktop\\ComfortCAN.dbc";
        //ReadDbc.readFileByLines(fileName);
        ReadDbc a=new ReadDbc();
        a.readFileByLines();
    }

    
}


