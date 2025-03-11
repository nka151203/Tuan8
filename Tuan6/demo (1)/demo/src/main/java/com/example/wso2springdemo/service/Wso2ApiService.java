//package com.example.wso2springdemo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Service
//public class Wso2ApiService {
//
//    private final WebClient webClient;
//    private final OAuth2AuthorizedClientService clientService;
//
//    @Autowired
//    public Wso2ApiService(WebClient.Builder webClientBuilder, OAuth2AuthorizedClientService clientService) {
//        this.webClient = webClientBuilder.baseUrl("https://localhost:9443").build();
//        this.clientService = clientService;
//    }
//
//    public String callWso2Api(String path, OAuth2AuthenticationToken authentication) {
//        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
//                authentication.getAuthorizedClientRegistrationId(),
//                authentication.getName()
//        );
//
//        return webClient.get()
//                .uri(path)
//                .headers(headers -> headers.setBearerAuth(client.getAccessToken().getTokenValue()))
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//}
