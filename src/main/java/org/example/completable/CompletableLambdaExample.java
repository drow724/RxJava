package org.example.completable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CompletableLambdaExample {
    public static void main(String[] args) throws InterruptedException {
        Completable completable = Completable.create(emitter -> {
            int sum = 0;
            for(int i = 0; i < 100; i++) {
                sum += i;
            }
            System.out.println("[" + Thread.currentThread().getName() + "] 합계 = " + sum);

            emitter.onComplete();
        });

        completable.subscribeOn(Schedulers.computation())
                .subscribe(
                        () -> System.out.println("[" + Thread.currentThread().getName() + "] 완료!!"),
                        (e) -> System.out.println("[" + Thread.currentThread().getName() + "] error!!" + e.getMessage())
                );

        Thread.sleep(1000L);
    }
}
