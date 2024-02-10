package org.example.single;

import io.reactivex.rxjava3.core.Single;

import java.time.LocalDate;

public class SingleLambdaExample {
    public static void main(String[] args) {
        Single<String> single = Single.create(emitter -> emitter.onSuccess(LocalDate.now().toString()));

        single.subscribe((s) -> System.out.println("현재 시간 = " + s), (e) -> System.out.println("error!! " + e.getMessage()));
    }
}
