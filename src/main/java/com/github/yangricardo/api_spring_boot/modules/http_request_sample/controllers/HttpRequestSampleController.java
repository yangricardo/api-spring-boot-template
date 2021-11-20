package com.github.yangricardo.api_spring_boot.modules.http_request_sample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/http-request-sample")
public class HttpRequestSampleController {

  WebClient webClient;

  public HttpRequestSampleController() {
    String baseUrl = "http://localhost:8080/api";
    webClient = WebClient.create(baseUrl);
  }

  @GetMapping("/get-client-server-hello")
  public String getClientServerHello() {
    Mono<String> response = webClient
      .get()
      .uri("/http-request-sample/get-server-hello")
      .retrieve()
      .bodyToMono(String.class);
    return response.block();
  }

  @GetMapping("/get-server-hello")
  public String getServerHello() {
    return new String("Hello");
  }
}
