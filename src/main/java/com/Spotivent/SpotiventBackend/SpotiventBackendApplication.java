package com.Spotivent.SpotiventBackend;

import com.Spotivent.SpotiventBackend.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class SpotiventBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpotiventBackendApplication.class, args);
	}

}
