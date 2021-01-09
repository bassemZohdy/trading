package io.trade.data.common;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class RStream<T> {
    private Consumer<T> consumer;
    private final Flux<T> f;

    public RStream() {
        f=Flux.create(sink -> consumer = sink::next);
    }

    public Flux<T> get(){
        return f;
    }
    public void consume(T t){
        this.consumer.accept(t);
    }
}
