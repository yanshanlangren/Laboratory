package elvis.kafka;

import java.util.Properties;


public class KafkaConfiguration {
	static Properties properties = new Properties();
	static {
        properties.put("bootstrap.servers", "192.168.0.112:9092");
	}
	KafkaConfiguration(){
	}

	public static Properties getProducerProperties() {
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return properties;
	}
	
	public static Properties getConsumerProperties() {
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "latest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
}
