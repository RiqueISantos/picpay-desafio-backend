package com.picpay.picpay_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PicpayChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpayChallengeApplication.class, args);
	}

}
