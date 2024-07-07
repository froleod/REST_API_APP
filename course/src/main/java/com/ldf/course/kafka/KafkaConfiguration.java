//package com.ldf.course.kafka;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaConfiguration {
//
//    @Bean
//    public NewTopic topic1() {
//        return new NewTopic("kafkaTest1", 1, (short) 1);
//        // партиции - часть топика (для сохранности данных и параллелизации)
//        // хранят копии сообщений в порядке потока
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
//        return kafkaTemplate;
//    }
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put("bootstrap.servers", "localhost:9092");
//        config.put("key.serializer", StringSerializer.class);
//        config.put("value.serializer", StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//}
