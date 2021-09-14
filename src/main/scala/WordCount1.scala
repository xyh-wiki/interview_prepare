import org.apache.flink.streaming.api.scala._
/**
 * @Author RookieX
 * @Date 2021/9/14 8:57 下午
 * @Description:
 */
object WordCount1 {

  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    //调用 Source创建 DataStream
    val lines: DataStream[String] = env.socketTextStream("localhost", 8888)
    val words: DataStream[String] = lines.flatMap(_.split(" "))
    val wordAndOne: DataStream[(String, Int)] = words.map((_, 1))
    val keyBy: KeyedStream[(String, Int), String] = wordAndOne.keyBy(_._1)
    val summed: DataStream[(String, Int)] = keyBy.sum(1)
    summed.print()
    env.execute()
  }
}
