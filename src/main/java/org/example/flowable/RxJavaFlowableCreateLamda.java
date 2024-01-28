package org.example.flowable;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaFlowableCreateLamda {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.create(emitter -> {
            String[] datas = {"Hello", "RxJava!"};
            for(String data : datas) {
                // 구독이 해지되면 처리 중단
                if (emitter.isCancelled())
                    return;

                // 데이터 통지
                emitter.onNext(data);
            }

            // 데이터 통지 완료를 알린다
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);

        flowable.observeOn(Schedulers.computation())
                .subscribe(System.out::println,
                        System.err::println,
                        () -> System.out.println("complete!!"));
    }
}
