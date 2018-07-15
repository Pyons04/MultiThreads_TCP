import java.lang.*;
import java.io.*;
import java.net.*;

class SendFirst extends Thread{
	private String host;
	private int port_int;
  private boolean ahead;

	public SendFirst(String h,int p,boolean a){
		host      = h;
		port_int  = p;
    ahead     = a;
	}

	public void run(){
   if(ahead == true){
          while(true){
                   try{
                        ServerSocket server = new ServerSocket(port_int);
                        Socket socket = server.accept();//接続するまでここで止まる
                        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);//メッセージを送信
                        System.out.print("送信されたメッセージはHelloです\n");
                        out.print("Good Bay");
                        out.close();
                        socket.close();
                        server.close();
                        Thread.sleep(3000);
                      }

                    catch(Exception e){
                        System.out.print("送信用tryで例外が発生しています。\n");
                      }

                     try{
                      //相手のIPアドレス,書かなくてもよい（クライアント側のみ）
                          Socket mysocket = new Socket(host,port_int);
                          BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
                          String new_message = in.readLine();
                          System.out.print("受信したメッセージは"+ new_message + "です\n");
                          in.close();
                          Thread.sleep(3000);
                        }

                   catch(Exception e){
                        System.out.print("受信用tryで例外が発生しています。\n");
                        }

	                   }
                    }

   else{
          while(true){
                     try{
                      //相手のIPアドレス,書かなくてもよい（クライアント側のみ）
                          Socket mysocket = new Socket(host,port_int);
                          BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
                          String new_message = in.readLine();
                          System.out.print("受信したメッセージは"+ new_message + "です\n");
                          in.close();
                          Thread.sleep(3000);
                        }

                   catch(Exception e){
                        System.out.print("受信用tryで例外が発生しています。\n");
                        }

                   try{
                        ServerSocket server = new ServerSocket(port_int);
                        Socket socket = server.accept();//接続するまでここで止まる
                        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);//メッセージを送信
                        System.out.print("送信されたメッセージはHelloです\n");
                        out.print("Good Bay");
                        out.close();
                        socket.close();
                        server.close();
                        Thread.sleep(3000);
                      }

                    catch(Exception e){
                        System.out.print("送信用tryで例外が発生しています。\n");
                      }

                     }
        }
    }
}


public class Thread_App {
	public static void main(String args[]){
		SendFirst thread1;
    int port_int;
    String host;
    boolean ahead;

   try{
       System.out.println("送信先行なら1を受信先行なら0を入力してください。\n");
       InputStreamReader ahead_input = new InputStreamReader(System.in);
       BufferedReader reader_ahead = new BufferedReader(ahead_input);
       String ahead_str = reader_ahead.readLine();

       if(ahead_str == "0"){
          ahead = true;
       }
       else{
          ahead = false;
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

		thread1 = new SendFirst(host,port_int,ahead);
		thread1.start();
	}

}
