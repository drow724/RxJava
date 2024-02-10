package org.example.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

import java.time.LocalDate;

public class MaybeFromSingleExample {
    public static void main(String[] args) {
        Single<String> single = Single.create(emitter -> emitter.onSuccess(LocalDate.now().toString()));

        Maybe.fromSingle(single)
                .subscribe(
                        (s) -> System.out.println("현재 시간 : " + s),
                        (e) -> System.out.println("e.getMessage() = " + e.getMessage()),
                        () -> System.out.println("완료!!")
                );
    }
}
