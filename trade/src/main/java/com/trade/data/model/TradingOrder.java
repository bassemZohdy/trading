package com.trade.data.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TradingOrder {
    private int id;
    private long orderId;
    private TradingOrderType type;
    private TradingOffer offer; 
    private int stopLoss;
    private int limit;
}