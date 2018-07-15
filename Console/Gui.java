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

public class Gui implements ActionListener{

        static JTextField textfield;
        static JTextArea textarea;

   public static void main(String args[]){

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