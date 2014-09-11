/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

/**
 *
 * @author doxu
 */
import java.awt.*;
import java.awt.event.*;
public class OpenANDsave
{
 Frame f = new Frame("测试");
 // 创建两个文件对话框
 FileDialog d1 = new FileDialog(f 
  , "选择需要打开文件" , FileDialog.LOAD);
 FileDialog d2 = new FileDialog(f 
  , "选择保存文件的路径" , FileDialog.SAVE);
 Button b1 = new Button("Open DBC file");
 Button b2 = new Button("Save to Excel");
 public void init()
 {
  b1.addActionListener(new ActionListener()
  {
   public void actionPerformed(ActionEvent e)
   {
    d1.setVisible(true);
    // 打印出用户选择的文件路径和文件名
    System.out.println(d1.getDirectory() 
     + d1.getFile());
   }
  });
  b2.addActionListener(new ActionListener()
  {
   public void actionPerformed(ActionEvent e)
   {
    d2.setVisible(true);
    // 打印出用户选择的文件路径和文件名
    System.out.println(d2.getDirectory() 
     + d2.getFile());
    
   }
  });
  f.add(b1);
  f.add(b2 , BorderLayout.SOUTH);
  f.pack();
  f.setVisible(true);
 }
 public static void main(String[] args) 
 {
  new OpenANDsave().init();
 }
}