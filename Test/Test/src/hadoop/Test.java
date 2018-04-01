package hadoop;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import java.io.IOException;
//
public class Test {
	
	public static void init() throws IOException{
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(conf);
		fs.exists(new Path("D:\\hadoop-2.7.3\\etc\\hadoop\\core-site.xml"));
	}
	public static void main(String[] args) throws IOException {
		init();
	}

}
