package org.example.backPressure;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class MissingBackPressureException {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
                .doOnNext(System.out::println)
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    System.out.println("# 소비자 처리 대기중");
                    Thread.sleep(1000L);
                    System.out.println(data + "처리 완료");
                }, System.err::println, System.out::println);

        Thread.sleep(2000L);
    }
}