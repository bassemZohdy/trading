package io.trade.data.common;

import java.util.function.Function;

public class TradeResToTrade implements Function<TradeResponse,Trade> {
    @Override
    public Trade apply(TradeResponse tradeResponse) {
        return new Trade(tradeResponse.getData().get("s"));
    }
}
