package com.trade.data.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TradingOffer {
    private int id;
    private int price;
    private TradingOfferType type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int quantity;

}