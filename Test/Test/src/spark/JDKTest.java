package spark;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

public class JDKTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JavaSparkContext sc=new JavaSparkContext("local","test");
		JavaRDD<String> distFile = sc.textFile("D:\\spark-2.0.0-bin-hadoop2.7\\README.md");
		// Map each line to multiple words!
		JavaRDD<String> words = distFile.flatMap(new FlatMapFunction<String, String>() {
			public Iterator<String> call(String line) {
					return Arrays.asList(line.split(" ")).iterator() ;
			}
		});
		
		
		System.out.println(words.collect());
		
	}

}
