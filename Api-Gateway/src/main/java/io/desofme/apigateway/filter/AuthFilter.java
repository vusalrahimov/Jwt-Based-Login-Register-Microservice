package io.desofme.apigateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.desofme.apigateway.response.ResponseStatus;
import io.desofme.apigateway.response.SingleResponseStatus;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.SerializationUtils;
import org.reactivestreams.Publisher;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;


@Component
@RequiredArgsConstructor
@Slf4j
@RefreshScope
public class AuthFilter implements GlobalFilter {

    private final JwtManager jwtManager;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        if(request.getURI().toString().contains("/auth")){
            return chain.filter(exchange);
        }else {
            String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (header == null) {
                SingleResponseStatus status = new SingleResponseStatus();
                status.setResponseStatus(new ResponseStatus("Header is empty", 103));
                DataBuffer dataBuffer = response.bufferFactory().wrap(objectToJson(status).getBytes(StandardCharsets.UTF_8));
                response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                return response.writeWith(Flux.just(dataBuffer));
            } else {
                if (header.contains("Bearer ")) {
                    try {
                        String token = header.substring(7);
                        Long userId = jwtManager.getIdFromToken(token);
                        ServerHttpRequest req =
                                request.mutate().header("userId", String.valueOf(userId)).build();
                        return chain.filter(exchange.mutate().request(req).build());
                    } catch (JwtException ex) {
                        SingleResponseStatus status = new SingleResponseStatus();
                        status.setResponseStatus(new ResponseStatus(ex.getMessage(), 106));
                        DataBuffer dataBuffer = response.bufferFactory().wrap(objectToJson(status).getBytes(StandardCharsets.UTF_8));
                        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                        return response.writeWith(Flux.just(dataBuffer));
                    }
                } else {
                    SingleResponseStatus status = new SingleResponseStatus();
                    status.setResponseStatus(new ResponseStatus("Token is empty", 105));
                    DataBuffer dataBuffer = response.bufferFactory().wrap(objectToJson(status).getBytes(StandardCharsets.UTF_8));
                    response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    return response.writeWith(Flux.just(dataBuffer));
                }
            }
        }
    }


    @SneakyThrows
    private String  objectToJson(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

}
