import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import java.lang.*;
import java.io.*;
import java.net.*;

public class Javatcp{
   public Javatcp(){
     
        JFrame frame=new JFrame("Hello Swing!!"); //上部のタイトル
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押すとプログラムを自動で終了する。
        frame.setSize(400,400);
        Container contentPane=frame.getContentPane();//必要ない　
        frame.setVisible(true);//windowを見せるor見せない（必須）
    }

    public static void main (String [] args){
        MultiThread mt = new MultiThread();
        Thread thread = new Thread(mt);
        thread.start();
     Javatcp s1=new Javatcp();
    }
}

class MultiThread implements Runnable {
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("スレッド2の" + (i + 1) + "度目の処理");
            } catch (InterruptedException e) {
                System.out.println("例外処理が実施された。");
                e.printStackTrace();
            }
        }
    }
}