package com.trade.manager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.trade.data.model.TradingSecurity;

public class SecurityTradingManager {
    private final TradingSecurity security;
    private final Executor executor;

    private SecurityTradingManager(TradingSecurity security, Executor executor) {
        this.security = security;
        this.executor = executor;
    }

    private SecurityTradingManager(TradingSecurity security) {
        this.security = security;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public static final SecurityTradingManager of(TradingSecurity security){
        return new SecurityTradingManager(security);
    }

}