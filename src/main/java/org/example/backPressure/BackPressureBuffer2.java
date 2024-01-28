package org.example.backPressure;

import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 - DROP_OLDEST 전략 : 생산자쪽에서 데이터 통지 시점에 버퍼가 가득 차있으면 버퍼내에 있는 데이터 중에서 가장 먼저(OLDEST) 버퍼
 * 안에 들어온 데이터를 삭제하고 버퍼 밖에서 대기하는 데이터를 채운다.
 */
public class BackPressureBuffer2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("# start : " + LocalDateTime.now());

        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> System.out.println("# inverval doOnNext = " + data))
                .onBackpressureBuffer(2, () -> System.err.println("overflow!"), BackpressureOverflowStrategy.DROP_OLDEST)
                .doOnNext(data -> System.out.println("#onBackpressureBuffer doOnNext() = " + data))
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(data -> {
                    Thread.sleep(1000L);
                    System.out.println("subscribe doOnNext = " + data);
                }, System.err::println);

        Thread.sleep(5000L);
    }
}