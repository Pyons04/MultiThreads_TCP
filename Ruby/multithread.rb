puts"受信を行う場合はs,送信を行う場合はcを押してください。"
require "socket"
require "pry"

command=gets.chomp.to_s

if command=="s"#先手側の処理

  input="No_data"

      t1 = Thread.start {
        puts"送受信を行うスレッドです。"
        loop do
        sleep(1)
        #break if input=="end"
        puts"受信側です。"
        s0 = TCPServer.open(20000)
        # クライアントからの接続を受け付ける
        sock = s0.accept
        sock_message = sock.gets
       # クライアントからのデータを最後まで受信する
          # 受信したデータはコンソールに表示される
        while sock_message.empty?
          sleep(1)
          puts"待機中"
          sock_message = sock.gets
        end

        buf=sock_message

          if buf=="No_data"
          puts"なにも受信しませんでした。"
          else
          puts"#{buf}と受信しました。"
          end
       # クライアントとの接続ソケットを閉じる
          sock.close
       # 待ちうけソケットを閉じる
            s0.close

       puts"送信側です。"
       puts "二つ目のスレッドのinputの値は#{input}です。"
        # 127.0.0.1(localhost)の20000番へ接続
        sock = TCPSocket.open("localhost",20000)
      #input="Japan"
      if input=="No_data"
      sock.write(input)
          puts "なにも送信されませんでした。"
        # 送信が終わったらソケットを閉じる
         sock.close
      else
        sock.write(input)
        puts "#{input}と送信しました。"
        # 送信が終わったらソケットを閉じる
         sock.close
         input="No_data"
      end
    end
        }


loop do
      #こちらのループ処理で入力待ちを行う。
      #input="AoyamaGakuin"
      #puts input
      #input=gets.to_s.chomp
      #sleep(3)
      #input="Meiji University"
      #puts input
      #sleep(3)
      #t1.join
      input=gets.to_s.chomp
    end

elsif command=="c"#後手側の処理

  input="No_data"
      t1 = Thread.start {
        puts"送受信を行うスレッドです。"
        loop do
          sleep(1)

         puts"送信側です。"
         puts "二つ目のスレッドのinputの値は#{input}です。"
        # 127.0.0.1(localhost)の20000番へ接続
        sock = TCPSocket.open("localhost",20000)
        #input="British"
     if input=="No_data"
      sock.write(input)
          puts "なにも送信されませんでした。"
        # 送信が終わったらソケットを閉じる
         sock.close
      else
        sock.write(input)
        puts "#{input}と送信しました。"
        # 送信が終わったらソケットを閉じる
         sock.close
         input="No_data"
      end
       puts"受信側です。"



        s0 = TCPServer.open(20000)
        # クライアントからの接続を受け付ける
        sock = s0.accept
        sock_message = sock.gets
       # クライアントからのデータを最後まで受信する
          # 受信したデータはコンソールに表示される
        while sock_message.empty?
          sleep(1)
          puts"待機中"
          sock_message = sock.gets
        end

        buf=sock_message
          if buf=="No_data"
          puts"なにも受信しませんでした。"
          else
          puts"#{buf}と受信しました。"
          end
       # クライアントとの接続ソケットを閉じる
          sock.close
       # 待ちうけソケットを閉じる
            s0.close
    end
        }

     loop do
      #こちらのループ処理で入力待ちを行う。
      #input="Chuo University"
      #puts input
      #sleep(3)
      #input="Hosei University"
      #puts input
      #sleep(3)
      #t1.join
      input=gets.to_s.chomp
    end


end

