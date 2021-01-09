package io.trade.data.common;

import lombok.Data;

@Data
public class TradeRequest {
    private String type;
    private String symbol;
}
