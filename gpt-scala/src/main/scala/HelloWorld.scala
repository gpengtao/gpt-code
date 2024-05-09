
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("hello world")
    println(multiplier1(10))
    println(multiplier2(10))
    println(multiplier3(10))
  }

  // 方法1，闭包
  var factor = 10
  val multiplier1 = (i: Int) => i * factor

  // 方法2
  def multiplier2(i: Int) = i + 10

  // 方法3
  def multiplier3(i: Int): Int = {
    i * 10
    return i * 20
    i * 30
  }


}