require "pry"

num=0

t1 = Thread.start {
  loop do
    puts "Bにメールする"
    sleep(3)
    if num>10
      puts "Aに10回以上メールしている。"
    end
  end
}

loop do
  puts "Aにメールする"
  sleep(2)
  num=num+1
end
