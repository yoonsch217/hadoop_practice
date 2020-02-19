public class FSDoubleCat{

    public static void main(String[] args) throws Exception{
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FSDataInputStream in = null;
        try{
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
            in.seek(0); //return to the start of the file. the parameter cannot exceed the file length. But this takes pretty big computations
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally{
            IOUtils.closeStream(in);
        }
    }
}

//두번 읽는다
