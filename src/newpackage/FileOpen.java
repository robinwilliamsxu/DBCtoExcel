/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackage;

/**
 *
 * @author doxu
 */
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
public class FileOpen{
 public File getfile(String[] args) 
 {
        //
        File file=null;
        // 创建文件选择器
        JFileChooser fileChooser = new JFileChooser();
        // 设置当前目录
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setAcceptAllFileFilterUsed(false);
        final String[][] fileENames = 
                {   
                    { ".java", "JAVA源程序 文件(*.java)" },
                    { ".doc", "MS-Word 2003 文件(*.doc)" },
                    { ".xls", "MS-Excel 2003 文件(*.xls)" },
                    { ".dbc", "dbc 文件(*.dbc)" }
                 };

        // 显示所有文件
        fileChooser.addChoosableFileFilter(new FileFilter() 
        {
            
                public boolean accept(File file) {
                 return true;
                }
                public String getDescription() {
                 return "所有文件(*.*)";
                }
        });

        // 循环添加需要显示的文件
        for (final String[] fileEName : fileENames) 
        {

                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() 
                {

                    public boolean accept(File file) { 

                    if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) 
                    {
                         return true;
                     }
                         return false;
                    }

                    public String getDescription() 
                    {
                         return fileEName[1];
                    }

                });
        }

        fileChooser.showDialog(null, null);
        return fileChooser.getSelectedFile();
 }
}