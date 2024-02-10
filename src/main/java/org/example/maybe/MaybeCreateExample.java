package org.example.maybe;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;

import java.time.LocalDate;

public class MaybeCreateExample {
    public static void main(String[] args) {
        Maybe<String> maybe = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<String> emitter) throws Throwable {
                emitter.onComplete();
                emitter.onSuccess(LocalDate.now().toString());
            }
        });

        maybe.subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull String s) {
                System.out.println("현재 시각 : " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("error!");
            }

            @Override
            public void onComplete() {
                System.out.println("완료!");
            }
        });
    }
}
