import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author: RookieX
 * @Date: 2021/9/14 9:30 下午
 * @Description: 使用 MR 编写 WordCount, 本地环境测试
 *  KIN   行的偏移量  Long
 *  VIN   行数据     String
 *  KOUT  单词       String
 *  VOUT   1个       Integer
 *
 */
public class WordCount {
    static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        //重写一个 map 方法
        //map 方法一行执行一次, 循环执行, 当前任务多少行, 执行多少次

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            /**
             * @Author: RookieX
             * @Date: 2021/9/14 9:47 下午
             * @param key
             * @param value
             * @param context
             * @return: void
             * @Description: map 阶段, wordAndOne
             */

            //获取一行数据处理
            String line = value.toString();
            String[] words = line.split("\\s+");
            for (String word : words) {
                Text keyOut = new Text(word);
                IntWritable valueOut = new IntWritable(1);

                //context 上下文对象, 可以衔接 map 和 reduce 两个阶段
                //输出单词, 1
                context.write(keyOut, valueOut);
            }
        }
    }

    static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
        //reduce 方法一个单词执行一次

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable value : values) {
                count++;
            }
            context.write(key, new IntWritable(count));
        }
    }

    //运行程序
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();

        //创建一个 job
        Job job = Job.getInstance(conf, "wordCount");
        //设置 mapper 类
        job.setMapperClass(WordCountMapper.class);
        //设置 reducer 类
        job.setReducerClass(WordCountReducer.class);
        //指定 map 端的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //指定最终结果的数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置 reduce 的个数
        job.setNumReduceTasks(2);
        //5 输入路径
        FileInputFormat.setInputPaths(job ,new Path("/Users/xuyuanhang/Desktop/temp/data/words.txt"));
        // 6 输出路径
        FileOutputFormat.setOutputPath(job,new Path("/Users/xuyuanhang/Desktop/temp/11_res"));
        //7 job提交  等待程序执行完毕
        boolean b = job.waitForCompletion(true);
    }
}
