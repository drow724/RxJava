package org.example.completable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CompletableCreateExample {
    public static void main(String[] args) throws InterruptedException {
        Completable completable = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Throwable {
                int sum = 0;
                for(int i = 0; i < 100; i++) {
                    sum += i;
                }
                System.out.println("[" + Thread.currentThread().getName() + "] 합계 = " + sum);

                emitter.onComplete();
            }
        });

        completable.subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //do nothing...
            }

            @Override
            public void onComplete() {
                System.out.println("[" + Thread.currentThread().getName() + "] 완료!!");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("[" + Thread.currentThread().getName() + "] error!!" + e.getMessage());
            }
        });

        Thread.sleep(1000L);
    }
}
