package com.covid19.recovereds;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PreloadDatabase {
    private static final Logger log = LoggerFactory.getLogger(PreloadDatabase.class);

    @Autowired
    private RecoveredRepository recoveredRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            recoveredRepository.deleteAll();
            log.info("Precargando data: " + recoveredRepository.save(Recovered.builder().id(1).age(25).name("Pepito Perez").build()));
            log.info("Precargando data: " + recoveredRepository.save(Recovered.builder().id(2).age(18).name("Mario Morales").build()));
            log.info("Precargando data: " + recoveredRepository.save(Recovered.builder().id(3).age(45).name("Maria Ramirez").build()));
            log.info("Precargando data: " + recoveredRepository.save(Recovered.builder().id(4).age(36).name("Rosa Garcia").build()));
            log.info("Precargando data: " + recoveredRepository.save(Recovered.builder().id(5).age(70).name("Jose Javier").build()));
        };
    }
}
