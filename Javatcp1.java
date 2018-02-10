import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import java.lang.*;
import java.io.*;
import java.net.*;

public class Javatcp1 implements ActionListener{

        JLabel lab;
        JTextField t;
        static int p=10;
   public Javatcp1(){
     
        JFrame frame=new JFrame("送信側"); //上部のタイトル
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押すとプログラムを自動で終了する。
        frame.setSize(400,400);
        Container contentPane=frame.getContentPane();//必要ない
        JPanel panel=new JPanel();//新しいパネルオブジェクト
        panel.setLayout(new FlowLayout());//Layoutの設定（部品の乗せ方の設定、上下左右）
        panel.setBackground(Color.GREEN);
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
    Javatcp1 s1=new Javatcp1();
    MultiThread1 mt = new MultiThread1();
    Thread thread = new Thread(mt);
    thread.start();
    }

}


class MultiThread1 implements Runnable {
    public void run() {
        try{

    InputStreamReader c =
          new InputStreamReader(System.in);

System.out.print("portを設定します:");

       BufferedReader a=
          new BufferedReader(c);
       String port = a.readLine();
       int port_int = Integer.parseInt(port);
       System.out.println(port);

     String data="サーバーへの接続完了。";

     while(true){
     try{
        System.out.print("サーバーとクライアントの接続を待機しています\n");
    ServerSocket srvr=new ServerSocket(port_int);
        Socket skt=srvr.accept();//接続するまでここで止まる
    System.out.print("サーバーとクライアントの接続を構築しました\n送信するメッセージを入力してください。");
    PrintWriter out=new PrintWriter(skt.getOutputStream(),true);//メッセージを送信
        System.out.print("送信されたメッセージは"+Javatcp1.p+"です\n");
        out.print(Javatcp1.p);
        out.close();
        skt.close();
        srvr.close();

try{   System.out.print("送信したい文字を入力してください。");
       InputStreamReader i =
          new InputStreamReader(System.in);
       BufferedReader b=
          new BufferedReader(i);
        data = b.readLine();
       System.out.println(data);
       }catch  (IOException f){
        System.out.println("入力エラー");
     }



    }
catch(Exception e){
    System.out.print("エラーが発生しています\n");
    }
  }

}catch(IOException a){
      System.out.println("例外処理、入力エラー");
     }
    }
}