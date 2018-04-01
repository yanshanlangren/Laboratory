package hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class Test {
	public static void main(String[] args) throws IOException{
		Configuration config=HBaseConfiguration.create();
		
		Connection connection = ConnectionFactory.createConnection(config);
		
		Table tab=connection.getTable(TableName.valueOf("table0"));
		
		ResultScanner sc=tab.getScanner(Bytes.toBytes("familyName")); 
		
		Scan s=new Scan();
		s.setStartRow(Bytes.toBytes("2016-3-22"));
		s.setStopRow(Bytes.toBytes("2016-3-23"));
		ResultScanner rs=tab.getScanner(s);
		
		
//		tab.che
		
		
		tab.close();
		connection.close();
		
	}
	
}
