package org.example.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

import java.time.LocalDate;

public class MaybeJustExample {
    public static void main(String[] args) {
        Maybe.just(LocalDate.now().toString())
                .subscribe(
                        (s) -> System.out.println("현재 시간 : " + s),
                        (e) -> System.out.println("e.getMessage() = " + e.getMessage()),
                        () -> System.out.println("완료!!")
                );

        Maybe.empty()
                .subscribe(
                        (s) -> System.out.println("현재 시간 : " + s),
                        (e) -> System.out.println("e.getMessage() = " + e.getMessage()),
                        () -> System.out.println("완료!!")
                );
    }
}
