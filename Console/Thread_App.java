import java.lang.*;
import java.io.*;
import java.net.*;

class SendFirst extends Thread{
	String host;
	int port_int;
  boolean ahead;
  String data1 = "ずんずん";
  String data2 = "どこどこ";

	public SendFirst(String h,int p,boolean a){
		host      = h;
		port_int  = p;
    ahead     = a;
	}

	public void run(){
   if(ahead == true){
          while(true){
                   try{
                        System.out.print("[送信先行:]サーバーとクライアントの接続を待機しています\n");
                        ServerSocket srvr=new ServerSocket(port_int);
                        Socket skt=srvr.accept();//接続するまでここで止まる
                        System.out.print("[送信先行:]サーバーとクライアントの接続を構築しました\n");
                        PrintWriter out=new PrintWriter(skt.getOutputStream(),true);//メッセージを送信
                        System.out.print("[送信先行:]送信されたメッセージは"+data1+"です\n");
                        out.print(data1);
                        out.close();
                        skt.close();
                        srvr.close();
                      }

                    catch(Exception e){
                        System.out.print("[送信先行:]受信用tryで例外が発生しています。\n");
                      }

                     try{
                          Socket mysocket = new Socket(host,port_int);//相手のIPアドレス,書かなくてもよい（クライアント側のみ）
                          BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));//inはサーバーから受信するためのメソッド

                          System.out.print("[送信先行:]受信したメッセージ:");
                          while(!in.ready()){}
                          System.out.println(in.readLine());
                          System.out.print("\n");
                          in.close();
                        }

                   catch(Exception e){
                        System.out.print("[送信先行:]受信用tryで例外が発生しています。\n");
                        }

	                   }
                    }

   else{
          while(true){
                     try{
                          Socket mysocket = new Socket(host,port_int);//相手のIPアドレス,書かなくてもよい（クライアント側のみ）
                          BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));//inはサーバーから受信するためのメソッド

                         System.out.print("[受信先行:]受信したメッセージ:");
                         while(!in.ready()){}
                         System.out.println(in.readLine());
                         System.out.print("\n");
                         in.close();
                        }

                   catch(Exception e){
                        System.out.print("[受信先行:]受信用tryで例外が発生しています。\n");
                        }

                   try{
                        System.out.print("[受信先行:]サーバーとクライアントの接続を待機しています\n");
                        ServerSocket srvr=new ServerSocket(port_int);
                        Socket skt=srvr.accept();//接続するまでここで止まる
                        System.out.print("[受信先行:]サーバーとクライアントの接続を構築しました\n");
                        PrintWriter out=new PrintWriter(skt.getOutputStream(),true);//メッセージを送信
                        System.out.print("[受信先行:]送信されたメッセージは"+data2+"です\n");
                        out.print(data2);
                        out.close();
                        skt.close();
                        srvr.close();
                      }

                    catch(Exception e){
                        System.out.print("[受信先行:]送信用tryで例外が発生しています。\n");
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

		thread1 = new SendFirst(host,port_int,ahead);
		thread1.start();
	}

}
