package org.example.backPressure;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 버퍼가 가득차면 버퍼 바깥쪽에서 통지 대기중인 데이터들은 계속 파기(DROP)하고
 * 버퍼를 비운 시점에 Drop되지 않고 대기중인 데이터부터 버퍼에 담는다.
 */
public class BackpressureDrop {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("# start : " + LocalDateTime.now());

        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> System.out.println("# inverval doOnNext = " + data))
                .onBackpressureDrop(data -> System.out.println(data + " Drop!"))
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(data -> {
                    Thread.sleep(1000L);
                    System.out.println("subscribe doOnNext = " + data);
                }, System.err::println);

        Thread.sleep(5000L);
    }
}
