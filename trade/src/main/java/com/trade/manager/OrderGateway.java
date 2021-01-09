package com.trade.manager;

import com.trade.data.model.TradingCredintial;
import com.trade.data.model.TradingOrder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

//@Service
public class OrderGateway {
    @Value("trading.baseURL")
    private String baseURL;
    @Value("trading.order.createURL")
    private String createOrderURI;
    private HttpMethod createOrderMethod; //default POST
    @Value("trading.order.cancelURL")
    private String cancelOrderURI;
    private HttpMethod cancelOrderMethod; //default DELETE
    @Value("trading.order.cancelAllURL")
    private String cancelAll;
    private TradingCredintial credintial;

	public void connect() {
	}
	public void placeOrder(TradingOrder order) {
    }
    public void cancel(TradingOrder order){
    }
    public void cancelAll(){
    }
	public boolean isCancelAll() {
		return false;
	}


}
