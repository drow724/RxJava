package org.example.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

import java.time.LocalDate;

public class MaybeLambdaExample {
    public static void main(String[] args) {
        Maybe<String> maybe = Maybe.create(emitter -> {
            emitter.onComplete();
            emitter.onSuccess(LocalDate.now().toString());
        });

        maybe.subscribe((s) -> System.out.println("현재 시간 = " + s), (e) -> System.out.println("error!! " + e.getMessage()), () -> System.out.println("완료!!"));
    }
}
