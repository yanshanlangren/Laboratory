package elvis.kafka;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


/**
 * Author  : RandySun
 * Date    : 2017-08-13  16:23
 * Comment :
 */
public class ProducerDemo {

    public static void main(String[] args){
        Properties properties = KafkaConfiguration.getProducerProperties();
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; ; i++) {
                String msg = "Message " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                producer.send(new ProducerRecord<String, String>("test_Elvis","message", msg));
                System.out.println("Sent:" + msg);
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }

    }
}