package io.trade.data.common;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
//        Consumer<Integer> consumer = i->{
//            i=i*2+5;
//            try {
//                OutputStream.nullOutputStream().write(i);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        };
//        int size=200_0000_000;
//        long start = System.nanoTime();
//        BlockingQueue<Integer> q =new LinkedBlockingQueue<>(size);
//        IntSupplier supplier = ()->{
//            try {
//                return q.take();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        };
//        CompletableFuture.runAsync(()->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            IntStream.range(0,size).forEach(i->{
//                try {
//                    q.put(i);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        });
//        Stream stream = Stream.iterate(supplier.getAsInt(),i->supplier.getAsInt());
//        stream.limit(size).forEach(consumer);
//        System.out.println("Stream:"+(System.nanoTime()-start));
//
//
//        start = System.nanoTime();
//        SequenceCreator sequenceCreator = new SequenceCreator();
//        CompletableFuture.runAsync(()->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            IntStream.range(0,size).boxed().forEach(sequenceCreator.consumer);
//        });
//        Flux f=sequenceCreator.createNumberSequence();
//        f.take(size).doOnNext(consumer).blockLast();
//        System.out.println("Flux:"+(System.nanoTime()-start));
//        RStream<Trade> f= new RStream<>();
//        CompletableFuture.runAsync(()->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            IntStream.range(0,100).boxed().map(i->"Sympol"+i).map(Trade::new).forEach(f::consume);
//            f.close();
//        });
//        f.get().take(1000).log().blockLast();

    }
}
class SequenceCreator {
    public Consumer<Integer> consumer;
    public Flux<Integer> createNumberSequence() {
        return Flux.create(sink -> SequenceCreator.this.consumer = sink::next);
    }
}
