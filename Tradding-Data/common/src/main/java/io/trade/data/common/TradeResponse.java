package io.trade.data.common;

import lombok.Data;

import java.util.Map;

@Data
public class TradeResponse {
    private String type;
    private Map<String,String> data;
}
