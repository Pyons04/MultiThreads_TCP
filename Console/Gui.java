//GUI関係ライブラリ
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
//入出力・通信関係ライブラリ
import java.lang.*;
import java.io.*;
import java.net.*;

   class SendFirst extends Thread implements ActionListener{

        static JTextField textfield;
        static JTextArea textarea;

   public void run() {

        JFrame frame = new JFrame("TCP/IP Chat");            //上部のタイトル
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押すとプログラムを自動で終了する。
        frame.setSize(400,300);
        frame.setResizable(false);

        Container contentPane = frame.getContentPane();
        JPanel panel = new JPanel();                        //新しいパネルオブジェクト
        panel.setLayout(new FlowLayout());                  //Layoutの設定（部品の乗せ方の設定、上下左右）
        panel.setBackground(Color.GRAY);
        contentPane.add(panel);                             //Paneにpanelを追加

        JTextArea textarea = new JTextArea();
        textarea.setPreferredSize(new Dimension(300, 200));

        textfield = new JTextField(30);
        textfield.addActionListener(this);//TextFiledにアクションリスナー
        panel.setLayout(new FlowLayout());

        panel.add(textfield);
        panel.add(textarea);

        frame.setVisible(true);                             //windowを見せるor見せない。一番最後が良い（必須）

        }

        public void actionPerformed(ActionEvent event){
            String x = textfield.getText();
            //int x=Integer.parseInt(t.getText());//文字でも数字に変換してくれるInterger.parsint
            textfield.setText("");
        }

}


public class Gui {
    public static void main(String args[]){
        SendFirst thread1;
    int port_int;
    String host;
    boolean ahead;

   try{
       InputStreamReader ahead_input = new InputStreamReader(System.in);
       System.out.print("送信先行ならAを受信先行ならBを入力してください。");
       BufferedReader reader_ahead = new BufferedReader(ahead_input);
       String ahead_str = reader_ahead.readLine();


       if(ahead_str.equals("A")){
          ahead = true;//[送信先行:]
          System.out.println("送信先行に設定されました。\n");
              }
       else if (ahead_str.equals("B")){
          ahead = false;
          System.out.println("受信先行に設定されました。\n");
              }
       else{
          ahead = false;
          System.out.println("不正な入力です。\n");
          System.exit(0);
              }

       InputStreamReader c = new InputStreamReader(System.in);
       System.out.print("portを設定します:");

       BufferedReader reader_port = new BufferedReader(c);
       String port_str = reader_port.readLine();
       port_int = Integer.parseInt(port_str);

       System.out.print("hostを設定します:");
       InputStreamReader i = new InputStreamReader(System.in);

       BufferedReader reader_host = new BufferedReader(i);
       host = reader_host.readLine();
       //thread2.start();
      }

   catch (IOException e){
       System.out.println("Interfaceクラスに例外が発生しています。" );
       port_int = 0;
       host     = "blank";
       ahead    = false;
       System.exit(0);
       }

        thread1 = new SendFirst();
        thread1.start();
    }

}