package io.trade.data.polygon;

import io.trade.data.common.ApiConfig;
import io.trade.data.common.AuthType;

public class ApiConfigImpl implements ApiConfig {
    public String getBaseURL() {
        return "https://api.polygon.io/v2/";
    }

    public String getApiKey() {
        return "sJGHbnSnl3MJBlhvyopM9ZsQ4L86hZA0";
    }

    public String getTokenHeaderKey() {
        return null;
    }

    public Long getConnectionTimeout() {
        return null;
    }

    public String getSocketProfileApiURI() {
        return null;
    }

    public String getTradeWS() {
        return null;
    }

    public AuthType getAuthType() {
        return AuthType.QUERY_PARAM;
    }
}
