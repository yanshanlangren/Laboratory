package elvis.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;

import kafka.producer.KeyedMessage;
import kafka.producer.Partitioner;
import kafka.producer.ProducerConfig;

public class PartitionerDemo implements Partitioner {

	@Override
	public int partition(Object key, int numPartitions) {
		try {
			int partitionNum=Integer.parseInt((String)key);
			return Math.abs(partitionNum % numPartitions);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	public static void main(String[] args) {
		Properties props=new Properties();
		props.put("metadata.broker.list", "192.168.0.112:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
		Producer producer=new Producer(new ProducerConfig(props));
		
		for(int i=1;i<=5;i++) {
			List messageList = new ArrayList<KeyedMessage<String,String>>();
			for(int j=0;j<4;j++) {
				messageList.add(new KeyedMessage<String, String>("test_Elvis",String.valueOf(j),String.format("The %d message for key %d", i,  j)));
			}
			producer.send(messageList);
		}
	}
}
