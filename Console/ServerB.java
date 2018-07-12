import java.lang.*;
import java.io.*;
import java.net.*;


class ServerB{
  public static void main (String [] args){

   try{
       System.out.println("受信先行\n");
       InputStreamReader c = new InputStreamReader(System.in);
       System.out.print("portを設定します:");

       BufferedReader reader_port = new BufferedReader(c);
       String port_str = reader_port.readLine();
       int port_int = Integer.parseInt(port_str);

       System.out.print("hostを設定します:");
       InputStreamReader i = new InputStreamReader(System.in);

       BufferedReader reader_host = new BufferedReader(i);
       String host = reader_host.readLine();
      }

   catch (IOException e){
       System.out.println("Interfaceクラスに例外が発生しています。" );
      }

       //MultiThread7 mt = new MultiThread7();
       MultiThread8 lt = new MultiThread8();
       //Thread thread1 = new Thread(mt);
       Thread thread2 = new Thread(lt);
       //thread1.start();
       thread2.start();
 }
}

//送信先行のスレッド
class MultiThread7 implements Runnable {
        public void run() {
         while(true){
                try{
                        ServerSocket server = new ServerSocket(3000);

                        Socket socket = server.accept();//接続するまでここで止まる
                        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);//メッセージを送信
                        System.out.print("送信されたメッセージはHelloです\n");
                        Thread.sleep(3000);
                        out.print("Good Bay");
                        out.close();
                        socket.close();
                        server.close();
                   }
                catch(Exception e){
                    System.out.print("送信用スレッドで例外が発生しています。\n");
                   }

               try{
                //相手のIPアドレス,書かなくてもよい（クライアント側のみ）
                    Socket mysocket = new Socket("localhost",3000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
                    String new_message = in.readLine();
                    System.out.print("受信したメッセージは"+ new_message + "です\n");
                    in.close();
                  }
               catch(IOException e){
                    System.out.print("受信用スレッドで例外が発生しています。\n");
                  }
          }

     }
   }


//受信先行のスレッド
class MultiThread8 implements Runnable {
        public void run() {
          while(true){

                try{
                //相手のIPアドレス,書かなくてもよい（クライアント側のみ）
                    Socket mysocket = new Socket("localhost",3000);
                    BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
                    String new_message = in.readLine();
                    System.out.print("受信したメッセージは"+ new_message + "です\n");
                    in.close();
                   }
               catch(IOException e){
                    System.out.print("受信用スレッドで例外が発生しています。\n");
                   }


                try{
                        ServerSocket server = new ServerSocket(3000);

                        Socket socket = server.accept();//接続するまでここで止まる
                        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);//メッセージを送信
                        System.out.print("送信されたメッセージはHelloです\n");
                        Thread.sleep(3000);
                        out.print("Good Bay");
                        out.close();
                        socket.close();
                        server.close();
                   }
                catch(Exception e){
                    System.out.print("送信用スレッドで例外が発生しています。\n");
                   }
              }

    }
  }