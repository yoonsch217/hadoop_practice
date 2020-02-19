import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxTemp{
    public static void main(String[] args) throws Exception{
        if(args.length != 2) {
            System.err.println("Usage: MaxTemperature inputpath outputpath");
            System.exit(-1);
        }

        Job job = new Job();
        job.setJarByClass(MaxTemp.class);  //하둡은 이 클래스를 포함하는 JAR 파일을 찾아서 클러스터에 배치한다.
        job.setJobName("Max temperature");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));  //이미 있는 디렉토리면 실행하지 않는다. 데이터의 유실을 막기 위한 의도이다.

        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}


