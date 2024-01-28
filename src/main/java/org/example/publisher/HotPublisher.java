package org.example.publisher;

import io.reactivex.rxjava3.processors.PublishProcessor;

public class HotPublisher {
    public static void main(String[] args) {
        PublishProcessor<Integer> processor = PublishProcessor.create();
        processor.subscribe(data -> System.out.println("구독자 1 : " + data));
        processor.onNext(1);
        processor.onNext(3);

        processor.subscribe(data -> System.out.println("구독자 2 : " + data));
        processor.onNext(5);
        processor.onNext(7);

        processor.onComplete();
    }
}