import javax.swing.*;
import java.awt.*;

public class Test extends JFrame implements Runnable {
  public static void main(String args[]) {
    JFrame frame = new Test();
    frame.setBounds(10 , 10 , 400 , 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.show();
  }

  private int color;
  public Test() { new Thread(this).start(); }
  public void run() {
    while(true) {
      color += 0x050505;
      if (color == 0xFFFFFF) color = 0;

      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          setForeground(new Color(color));
        }
      });
      try{
        Thread.sleep(100);}
      catch(Exception e)
         { System.out.println("Interfaceクラスに例外が発生しています。" );}
    }
  }
  public void paint(Graphics g) {
    g.fillRect(0 , 0 , getWidth() , getHeight());
  }
}