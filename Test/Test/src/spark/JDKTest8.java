package spark;

import org.apache.parquet.it.unimi.dsi.fastutil.Arrays;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class JDKTest8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JavaSparkContext sc=new JavaSparkContext("local","test");
//		JavaRDD<String> distFile=sc.textFile("D:\\spark-2.0.0-bin-hadoop2.7\\README.md");
//		System.out.println(distFile.flatMap(line -> Arrays.asList(line.split(" "))));
		
		JavaRDD<String> distFile = sc.textFile("README.md");
//		JavaRDD<String> words =distFile.flatMap(line -> Arrays.asList(line.split(" ")));
	}

}
