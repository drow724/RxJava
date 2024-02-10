package org.example.single;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;

import java.time.LocalDate;

public class SingleExample {
    public static void main(String[] args) {
        Single<String> single = Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<String> emitter) throws Throwable {
                emitter.onSuccess(LocalDate.now().toString());
            }
        });

        single.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //do nothing...
            }

            @Override
            public void onSuccess(@NonNull String s) {
                System.out.println("현재 시각 : " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("error!! " + e.getMessage());
            }
        });
    }
}
