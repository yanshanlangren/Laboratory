package elvis.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.*;

public class ConsumerDemo {
	
	private static KafkaConsumer<String,String> consumer;

	public static void main(String[] args) {
	    Properties props = KafkaConfiguration.getConsumerProperties();
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test_Elvis"));
        int timeouts = 0;
        
        while (true) {
            // read records with a short timeout. If we time out, we don't really care.
            ConsumerRecords<String, String> records = consumer.poll(100);
            if (records.count() == 0) {
                timeouts++;
            } else {
                System.out.printf("Got %d records after %d timeouts\n", records.count(), timeouts);
                timeouts = 0;
            }
            for (ConsumerRecord<String, String> record : records) {
            	System.out.println("Message Received:" + record.toString());
            	System.out.println(">> offset:" + record.offset()); 
            	System.out.println(">> partition:" + record.partition());   
            	System.out.println(">> key:" + record.key());
            	System.out.println(">> value:"  + record.value());
            }
        }
	  }

}
