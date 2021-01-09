package com.trade.data.model;

import java.net.URL;

import lombok.Data;

@Data
public class TradingAccount {
    private int id;
    private String name;
    private URL apiBaseURL;
    private String apiKey;
    private String secretKey;
}