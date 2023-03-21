package com.undina.mainserver.client;

import com.undina.mainserver.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Service
public class MessageClient extends BaseClient {

    @Autowired
    public MessageClient(@Value("${MESSAGE_SERVER_URL}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> createMessage(MessageDto messageDto) {
        return post("/messages", messageDto);
    }

    public ResponseEntity<Object> getMessages(String userId) {
        Map<String, Object> parameters = Map.of(
                "userId", userId
        );
        return get("/messages/{userId}", parameters);
    }
}
