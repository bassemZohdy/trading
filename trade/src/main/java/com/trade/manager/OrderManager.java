package com.trade.manager;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import com.trade.data.model.TradingOrder;

import org.springframework.stereotype.Service;

//@Service
public class OrderManager implements Subscriber<TradingOrder> {

    private OrderGateway orderGateway;
    private Map<Integer, TradingOrder> currentOrders;
    private Map<Integer, TradingOrder> executedOrders;

    @Override
    public void onSubscribe(Subscription subscription) {
        /// ToDo: connect to Order Procker
        orderGateway.connect();
    }

    @Override
    public void onNext(TradingOrder order) {
        currentOrders.put(order.getId(), order);
        orderGateway.placeOrder(order);

    }

    @Override
    public void onError(Throwable throwable) {
        // handel exception

    }

    @Override
    public void onComplete() {
        // cancel all orders
        if (orderGateway.isCancelAll())
            orderGateway.cancelAll();
        else
            currentOrders.values().stream().forEach(orderGateway::cancel);

    }

}