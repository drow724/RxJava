package org.example.flowable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RxJavaFlowableCreate {

    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Throwable {
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
            }
        }, BackpressureStrategy.BUFFER);

        flowable.observeOn(Schedulers.computation()).subscribe(new Subscriber<String>() {
            // 데이터 개수 요청 및 구독을 취소하기 위한 Subscription 객체
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String data) {
                System.out.println("data = " + data);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("error = " + error);
            }

            @Override
            public void onComplete() {
                System.out.println("complete!!");
            }
        });

        Thread.sleep(500L);
    }
}
