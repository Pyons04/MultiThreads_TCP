puts"受信を行う場合はs,送信を行う場合はcを押してください。"
require "socket"

command=gets.chomp.to_s

if command=="s"

while(true) do
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

# クライアントとの接続ソケットを閉じる
sock.close

# 待ちうけソケットを閉じる
s0.close

puts "#{sock_message}と受信しました。"

puts"送信側です。"
# 127.0.0.1(localhost)の20000番へ接続
sock = TCPSocket.open("localhost",20000)

# HELLOという文字列を送信
sock.write("first move")
puts "first move と送信しました。"
# 送信が終わったらソケットを閉じる
sock.close

end

elsif command=="c"

while(true) do
puts"送信側です。"
# 127.0.0.1(localhost)の20000番へ接続
sock = TCPSocket.open("localhost",20000)

# HELLOという文字列を送信
sock.write("second move")
puts "second move と送信しました。"

# 送信が終わったらソケットを閉じる
sock.close


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

puts "#{sock_message}と受信しました。"

# クライアントとの接続ソケットを閉じる
sock.close

# 待ちうけソケットを閉じる
s0.close
end
end