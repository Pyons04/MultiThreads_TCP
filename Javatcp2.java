import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import java.lang.*;
import java.io.*;
import java.net.*;

public class Javatcp2 implements ActionListener{

        JLabel lab;
        JTextField t;
        static int p=10;
   public Javatcp2(){
     
        JFrame frame=new JFrame("受信側"); //上部のタイトル
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
    Javatcp2 s1=new Javatcp2();
    MultiThread2 mt = new MultiThread2();
    Thread thread = new Thread(mt);
    thread.start();
    }

}


class MultiThread2 implements Runnable {
    public void run() {
             try{

    InputStreamReader i =
          new InputStreamReader(System.in);
      
System.out.print("Client側の設定を行います。");
System.out.print("hostを設定します:");
       BufferedReader b=
          new BufferedReader(i);
       String host = b.readLine();
       System.out.println(host);

System.out.print("portを設定します:");

       BufferedReader a=
          new BufferedReader(i);
       String port = a.readLine();
       int port_int = Integer.parseInt(port);
       System.out.println(port);
    

while(true){
     try{
    Socket mysocket = new Socket(host,port_int);//相手のIPアドレス,書かなくてもよい（クライアント側のみ）
        BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));//inはサーバーから受信するためのメソッド

System.out.print("受信したメッセージ:");
    while(!in.ready()){}
    System.out.println(in.readLine());
    System.out.print("\n");
    in.close();
   
     }
catch(Exception e){
    System.out.print("待機中\n");
    }
}




}catch(IOException a){
      System.out.println("例外処理、入力エラー");
     }
    }
}