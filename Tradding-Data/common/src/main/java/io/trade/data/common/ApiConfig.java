package io.trade.data.common;

public interface ApiConfig {
    String getBaseURL();
    String getApiKey();
    String getTokenHeaderKey();
    Long getConnectionTimeout();
    String getSocketProfileApiURI();
    String getTradeWS();
    AuthType getAuthType();
}
