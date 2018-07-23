import javax.swing.SwingWorker;
//GUI関係ライブラリ
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.SwingUtilities;

public class Gui {

        static JTextField textfield;
 public static JTextArea  textarea;
 public static String     fieldInput;

    public Gui() {

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

        //textarea.addActionListener(this);//textareaにactionlisnaerを追加

        textfield = new JTextField(30);
        //textfield.addActionListener(this);//TextFiledにアクションリスナー
        panel.setLayout(new FlowLayout());

        panel.add(textfield);
        panel.add(textarea);

        frame.setVisible(true);
        fieldInput = "NoInput";
        textarea.setText("TextFieldのInput\n");

        SwingWorker worker = new DetectInput(textarea);
        worker.execute();
    }

    //無限ループで受信した文字列を監視し、InputがあればGUIスレッドに反映
    class DetectInput extends SwingWorker<Object, Object> {
        public JTextArea  textarea;

        public DetectInput(JTextArea textarea) {
            this.textarea = textarea;
        }

        public Object doInBackground() {
            while(true){
            try {
                Thread.sleep(3000);
                textarea.append("HelloWorld\n");
                }
            catch (Exception e) {System.out.println("doInBackgroundでエラーが発生しています。");}
            }
      }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui();
            }
        });
    }
}