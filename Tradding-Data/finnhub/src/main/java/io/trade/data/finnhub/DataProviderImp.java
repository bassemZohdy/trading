package io.trade.data.finnhub;

import com.google.gson.Gson;
import io.trade.data.common.ApiConfig;
import io.trade.data.common.DataProvider;
import io.trade.data.common.SocketProfile;
import io.trade.data.common.Trade;
import io.trade.data.common.TradeRequest;
import io.trade.data.common.TradeResToTrade;
import io.trade.data.common.TradeResponse;
import reactor.adapter.JdkFlowAdapter;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class DataProviderImp implements DataProvider {

    WebSocket.Listener listener;
    HttpClient client;
    WebSocket webSocket;
    Function<String, URI> sympolToURI;
    Function<URI, HttpRequest> uriToRequest;
    SubmissionPublisher<String> publisher;

    public DataProviderImp() throws ExecutionException, InterruptedException {
        publisher= new SubmissionPublisher<>();
        listener= new WebSocket.Listener() {
            @Override
            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                publisher.submit(data.toString());
                return WebSocket.Listener.super.onText(webSocket, data, last);
            }

            @Override
            public CompletionStage<?> onClose(WebSocket webSocket, int statusCode,
                                              String reason) {
                publisher.close();
                return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
            }

            @Override
            public void onError(WebSocket webSocket, Throwable error) {
                publisher.closeExceptionally(error);
                WebSocket.Listener.super.onError(webSocket, error);
            }
        };
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        webSocket = HttpClient.newHttpClient().newWebSocketBuilder().buildAsync(URI.create(getConfig().getTradeWS()+"?token="+getConfig().getApiKey()),listener).get();
        sympolToURI = s ->
                URI.create(getConfig().getBaseURL()+getConfig().getSocketProfileApiURI() + "?symbol=" + s);
        uriToRequest = u ->
                HttpRequest.newBuilder().uri(u).setHeader(getConfig().getTokenHeaderKey(), getConfig().getApiKey()).build()
        ;
    }

    private ApiConfig getConfig() {
        return new ApiConfigImpl();
    }

    @Override
    public SocketProfile getStock(String sympol) throws IOException, InterruptedException {
        var request = uriToRequest.apply(sympolToURI.apply(sympol));
        var responseStr = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return new Gson().fromJson(responseStr, SocketProfile.class);
    }

    @Override
    public Flux<Trade> getTradeHandeler(String sympol) {
        TradeRequest request= new TradeRequest();
        request.setType("subscribe");
        request.setSymbol(sympol);
        String data = new Gson().toJson(request);
        webSocket.sendText(data, true);
        TradeResToTrade tradeResToTrade=new TradeResToTrade();
        return JdkFlowAdapter.flowPublisherToFlux(publisher).map(s->new Gson().fromJson(s, TradeResponse.class)).log().filter(r->r.getType()=="trade").map(tradeResToTrade);
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        DataProvider provider = new DataProviderImp();
//        SocketProfile profile=provider.getStock("AAPL");
//        System.out.println(profile);
        provider.getTradeHandeler("AAPL").blockLast(Duration.ofSeconds(120));
    }
}
