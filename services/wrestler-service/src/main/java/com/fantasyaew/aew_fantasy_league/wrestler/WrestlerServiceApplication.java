package com.fantasyaew.aew_fantasy_league.wrestler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WrestlerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WrestlerServiceApplication.class, args);
    }

}




