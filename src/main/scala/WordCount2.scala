import org.apache.flink.streaming.api.scala._

/**
 * @Author RookieX
 * @Date 2021/9/14 9:24 下午
 * @Description:
 */
object WordCount2 {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val lines: DataStream[String] = env.socketTextStream("localhost", 8888)
    //使用 spark
    //val conf: SparkConf = new SparkConf()
    //val sc: SparkContext = new SparkContext(conf)
    //sc.textFile(args[0]).flatMap(_.split(" "))
    lines.flatMap(_.split(" "))
      .map((_, 1))
      .keyBy(_._1)
      .sum(1)
      .print()

    env.execute()
  }

}
