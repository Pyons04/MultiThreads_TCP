import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import java.lang.*;
import java.io.*;
import java.net.*;

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
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener{

   JPanel panel;
   static JTextArea textarea;
   JLabel lab,lab2;

   static String p="No_data";
   static String recieve="No_data";
   JTextField t;

 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    //try {
     Main frame = new Main();
     frame.setVisible(true);
     MultiThread1 mt = new MultiThread1();
     Thread thread5 = new Thread(mt);
     thread5.start();
    //} catch (Exception e) {
     //e.printStackTrace();
    //}
   }
  });
 }


 public Main() {
  JFrame frame=new JFrame("マルチスレッドでGUIに反映させるためのテスト。"); //上部のタイトル
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押すとプログラムを自動で終了する。
        frame.setSize(400,400);
        Container contentPane=frame.getContentPane();//必要ない
        JPanel panel=new JPanel();//新しいパネルオブジェクト
        panel.setLayout(new FlowLayout());//Layoutの設定（部品の乗せ方の設定、上下左右）
        panel.setBackground(Color.GREEN);
        contentPane.add(panel);

        String message="";
        JTextArea textarea = new JTextArea(message);//デフォルト文字
        textarea.setPreferredSize(new Dimension(300, 300));

        t = new JTextField("10",15);
        t.addActionListener(this);//TextFiledにアクションリスナー
        lab=new JLabel("送信される文字:");
        lab2=new JLabel("受信した文字:");
        panel.setLayout(new FlowLayout());

        panel.add(t);
        panel.add(lab);
        panel.add(lab2);

        panel.add(textarea);
        frame.setVisible(true);

  //新しいスレッドの中で無限ループ
  Thread thread = new Thread(){
   //@Override
   public void run() {
    //無限ループ
    while(true){

     //イベントディスパッチスレッドでUIを更新
     SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try{
  Thread.sleep(3000);
}catch (InterruptedException e){
}
        if(recieve!="No_data"){
       textarea.append("INCOMING:"+recieve+"\n");
     }
      }
     });
    }
   }
  };
  thread.start();
 }
   public void actionPerformed(ActionEvent event){

            String x=t.getText();
            //int x=Integer.parseInt(t.getText());//文字でも数字に変換してくれるInterger.parsint
            lab.setText("入力されている文字:"+x);
            lab2.setText("受信した文字:"+recieve);
            p=x;
            t.setText("");
}
}



class MultiThread1 implements Runnable {
    public void run() {
      try{
//接続設定・通信ループ
InputStreamReader c =
          new InputStreamReader(System.in);
System.out.print("送信先行側の設定を行います。");
System.out.print("portを設定します:");

       BufferedReader a=
          new BufferedReader(c);
       String port = a.readLine();
       int port_int = Integer.parseInt(port);
       System.out.println(port);

       System.out.print("hostを設定します:");
       InputStreamReader i =
          new InputStreamReader(System.in);
      
       BufferedReader b=
          new BufferedReader(i);
       String host = b.readLine();
       System.out.println(host);

     String data="サーバーへの接続完了。";

     while(true){
     try{
        //送信を担う処理
        ServerSocket srvr=new ServerSocket(port_int);
        Socket skt=srvr.accept();//接続するまでここで止まる
        PrintWriter out=new PrintWriter(skt.getOutputStream(),true);//メッセージを送信
        System.out.print("送信されたメッセージは"+Main.p+"です\n");
        out.print(Main.p);
        out.close();
        skt.close();
        srvr.close();

        if(Main.p!="No_data"){
            Main.p="No_data";
        }


//3秒のスリープ
try{
  Thread.sleep(3000);
}catch (InterruptedException e){
}

//受信を担う処理
        Socket mysocket = new Socket(host,port_int);//相手のIPアドレス,書かなくてもよい（クライアント側のみ）
        BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));//inはサーバーから受信するためのメソッド

    //System.out.print("受信したメッセージ:"+in.readLine()+"\n"); //ここはちゃんと表示できている。
    //System.out.println(in.readLine());
   String new_message=in.readLine();
   //System.out.println(new_message+"\n");

    if(new_message!="No_data"){
      System.out.println("受信したメッセージ:"+new_message);
      Main.recieve=new_message;
    }
    in.close();

    }

catch(Exception e){
    System.out.print("エラーが発生しています\n");
    }
  }
//接続設定接続設定・通信ループ
    }
     catch(Exception a){
      System.out.println("例外処理、入力エラー");
     }
    }
}