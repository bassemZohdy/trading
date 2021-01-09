package com.trade.trade;

import org.apache.tomcat.websocket.WsWebSocketContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class TradingManager {
    @Value("trading.baseURL")
    private String baseURL;
    @Value("trading.order.url")
    private String orderURI;
    @Value("credintial.api.key")
    private String key;
    @Value("credintial.api.secret")
    private String secret;
    private RestTemplate restTemplate;
    private WebSocketClient client;

    @Async
    public void trade(){
        System.out.println(baseURL);
        System.out.println(orderURI);
        restTemplate=new RestTemplate();
        client= new StandardWebSocketClient(new WsWebSocketContainer());
        TextWebSocketHandler webSocketHandler = new TextWebSocketHandler(){
            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
                System.out.println("message:"+message);
                super.handleTextMessage(session, message);
            }
        };
        
		client.doHandshake(webSocketHandler, "wss://data.alpaca.markets/stream");
        

    }
    
}