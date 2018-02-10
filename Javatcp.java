import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import java.lang.*;
import java.io.*;
import java.net.*;

public class Javatcp implements ActionListener{

        JLabel lab;
        JTextField t;
        static int p=10;
   public Javatcp(){
     
        JFrame frame=new JFrame("Text1"); //上部のタイトル
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押すとプログラムを自動で終了する。
        frame.setSize(400,400);
        Container contentPane=frame.getContentPane();//必要ない
        JPanel panel=new JPanel();//新しいパネルオブジェクト
        panel.setLayout(new FlowLayout());//Layoutの設定（部品の乗せ方の設定、上下左右）
        panel.setBackground(Color.RED);
        contentPane.add(panel);//Paneにpanelを追加

        t = new JTextField("10",15);
        t.addActionListener(this);//TextFiledにアクションリスナー
        lab=new JLabel("*2=");
        panel.setLayout(new FlowLayout());
        
        panel.add(t);
        panel.add(lab);

        frame.setVisible(true);//windowを見せるor見せない。一番最後が良い（必須）
    }

        public void actionPerformed(ActionEvent event){
            int x=Integer.parseInt(t.getText());//文字でも数字に変換してくれるInterger.parsint
            lab.setText("*2="+(x*2));
            p=x;
}


    public static void main (String [] args){
    Javatcp s1=new Javatcp();
    MultiThread mt = new MultiThread();
    Thread thread = new Thread(mt);
    thread.start();
    }

}


class MultiThread implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("スレッド2の" + (i + 1) + "度目の処理");
                System.out.println("GUIのスレッドではpの値は"+Javatcp.p+"を示している");
            }

                        catch (InterruptedException e) {
                System.out.println("例外処理が実施された。");
                e.printStackTrace();
            }
        }
    }
}