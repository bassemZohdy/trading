package com.trade.data.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class tradingPortfolio {
    private String name;
    private int ammount;
    private int target;
    private LocalDateTime targetDate;
    private int stopLoss;
}