package edu.co.cedesistemas.reactiva.modulo2.streaming;

import edu.co.cedesistemas.reactiva.modulo2.streaming.model.Movie;
import edu.co.cedesistemas.reactiva.modulo2.streaming.services.KafkaProducerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamingApplication implements CommandLineRunner {

	private final KafkaProducerService kafkaProducerService;

	public StreamingApplication(KafkaProducerService kafkaProducerService){
		this.kafkaProducerService = kafkaProducerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(StreamingApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		producerData();
	}

	private void producerData(){
		Movie movie1 = new Movie(15, "El Padrino", "Drama", "Francis Ford Coppola", 175, "1972", true, 180);
		Movie movie2 = new Movie(27, "Parasite", "Drama", "Bong Joon-ho", 132, "2019", true, 120);

		String topic = "Peliculas-drama-cedesistemas";
		kafkaProducerService.sendKey(topic, movie1.getId(), movie1);
		kafkaProducerService.sendKey(topic, movie2.getId(), movie2);
	}
}
