package io.trade.data.finnhub;

import io.trade.data.common.ApiConfig;
import io.trade.data.common.AuthType;

public class ApiConfigImpl implements ApiConfig {

    @Override
    public String getBaseURL(){
        return "https://finnhub.io/api/v1/";
    }

    @Override
    public String getApiKey(){
        return "buaimdf48v6ocn3p9ml0";
    }

    @Override
    public String getTokenHeaderKey() {
        return "X-Finnhub-Token";
    }

    @Override
    public Long getConnectionTimeout() {
        return 5000l;
    }

    @Override
    public String getSocketProfileApiURI(){
        return "stock/profile2";
    }

    @Override
    public String getTradeWS(){
        return "wss://ws.finnhub.io";
    }

    @Override
    public AuthType getAuthType() {
        return AuthType.HEADER;
    }

}
