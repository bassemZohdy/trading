package com.trade.data.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TradingCredintial {

    private int id;
    private String name;
    private CerdintialType type;
    private String username;
    private String password;
    @Value("credintial.api.key")
    private String key;
    @Value("credintial.api.secret")
    private String secret;
    
}