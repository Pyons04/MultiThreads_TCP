package jp.fujiu.swing_sample;

import java.awt.EventQueue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

public class Main extends JFrame {
 //日付の書式 YYYY/MM/DD hh:mm:ss
 private static final String DATE_FORMAT = "%1$d/%2$02d/%3$02d %4$02d:%5$02d:%6$02d";

 private static final long serialVersionUID = 1L;
 private JPanel contentPane;
 private JLabel lblNewLabel;

 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     Main frame = new Main();
     frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 public Main() {
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 450, 300);
  contentPane = new JPanel();
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
  SpringLayout sl_contentPane = new SpringLayout();
  contentPane.setLayout(sl_contentPane);
  
  lblNewLabel = new JLabel("New label");
  sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, contentPane);
  sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, contentPane);
  sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, contentPane);
  contentPane.add(lblNewLabel);
  
  //新しいスレッドの中で無限ループ
  Thread thread = new Thread(){
   @Override
   public void run() {
    //無限ループ
    while(true){
     Calendar calendar = Calendar.getInstance();
     final ByteArrayOutputStream str = new ByteArrayOutputStream();
     PrintStream ps = new PrintStream(str);
     ps.printf(DATE_FORMAT, 
      calendar.get(Calendar.YEAR), 
      calendar.get(Calendar.MONTH) + 1, 
      calendar.get(Calendar.DATE), 
      calendar.get(Calendar.HOUR_OF_DAY), 
      calendar.get(Calendar.MINUTE), 
      calendar.get(Calendar.SECOND)
     );
     final String strDate = str.toString();
     ps.close();
     
      str.close();
     
     
     //イベントディスパッチスレッドでUIを更新
     SwingUtilities.invokeLater(new Runnable() {
      public void run() {
       lblNewLabel.setText(strDate);
      }
     });

     try {
      sleep(1000);
     } catch (InterruptedException e) {
     }
    }
   }
  };
  thread.start();
 }
}