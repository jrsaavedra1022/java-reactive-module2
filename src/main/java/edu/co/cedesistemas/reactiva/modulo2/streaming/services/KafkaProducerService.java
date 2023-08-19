package edu.co.cedesistemas.reactiva.modulo2.streaming.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendKey(String topic, Integer key, Object object){
        var future = kafkaTemplate.send(topic, key.toString(), getJsonFromObject(object));

        future.whenComplete((responseSend, ex) -> {
           if(ex != null){
               LOGGER.error(ex.getMessage());
               future.completeExceptionally(ex);
           } else {
               future.complete(responseSend);
           }
           LOGGER.info("Objecto enviado al topico de kafka con el id: " + object);
        });
    }

    public void send(String topic, Object object){
        var future = kafkaTemplate.send(topic, getJsonFromObject(object));

        future.whenComplete((responseSend, ex) -> {
            if(ex != null){
                LOGGER.error(ex.getMessage());
                future.completeExceptionally(ex);
            } else {
                future.complete(responseSend);
            }
            LOGGER.info("Objecto enviado al topico de kafka con id: " + object);
        });
    }

    private String getJsonFromObject(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
