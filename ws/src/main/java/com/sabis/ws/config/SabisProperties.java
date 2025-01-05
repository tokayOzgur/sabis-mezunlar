package com.sabis.ws.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "sabis")
public class SabisProperties {

        private Email email;
        private Client client;
        private Storage storage = new Storage();

        public static record Email(
                        String host,
                        // String username,
                        // String password,
                        String from,
                        int port

        ) {
        }

        public static record Client(
                        String host) {
        }

        @Data
        public static class Storage {
                String root = "uploads";
                String profile = "profiles";
                String post = "posts";

        }

}
