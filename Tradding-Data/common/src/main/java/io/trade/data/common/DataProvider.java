package io.trade.data.common;

import java.io.IOException;
import java.util.concurrent.Flow;
import reactor.core.publisher.Flux;

public interface DataProvider {

    public SocketProfile getStock(String sympol) throws IOException, InterruptedException;

    public Flux<Trade> getTradeHandeler(String sympol);
}
