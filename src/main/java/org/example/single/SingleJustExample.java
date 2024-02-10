package org.example.single;

import io.reactivex.rxjava3.core.Single;

import java.time.LocalDate;

public class SingleJustExample {
    public static void main(String[] args) {
        Single.just(LocalDate.now().toString()).subscribe((s) -> System.out.println("현재 시간 : " + s), (e) -> System.out.println("e.getMessage() = " + e.getMessage()));
    }
}
