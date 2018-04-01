package spark;

import java.beans.Transient;
import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class RDDClass implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = -6521707687113358208L;

	//	static public final String filePosition="hdfs://log.txt";
	static public final String filePosition="D:\\spark-2.0.0-bin-hadoop2.7\\README.md";

//	JavaSparkContext sc=new JavaSparkContext("local", "test");
//	sc=new JavaSparkContext();

	@SuppressWarnings("serial")
	JavaRDD<String> lines = new JavaSparkContext("local", "test").textFile(filePosition).filter(new Function<String, Boolean>(){
		@Transient
		public Boolean call(String s) throws Exception {
//			return s.contains("Spark");
			return false;
		}
		
	});

	long numErrors=lines.count();
	
	
	public static void main(String[] args){
		RDDClass rddClass=new RDDClass();
		System.out.println(rddClass.numErrors);
	}
			
}
