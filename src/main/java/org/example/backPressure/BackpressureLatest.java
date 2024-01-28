package org.example.backPressure;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 통지된 데이터로 채워진 버퍼의 데이터를 소비자가 모두 소비하면 버퍼 밖에서 대기중인 통지된 데이터 중에서
 * 가장 나중에(최근에) 통지된 데이터부터 다시 버퍼에 채운다.
 */
public class BackpressureLatest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("# start : " + LocalDateTime.now());

        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .filter(i -> i < 10)
                .doOnNext(data -> System.out.println("# inverval doOnNext = " + data))
                .onBackpressureLatest()
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(data -> {
                    Thread.sleep(1000L);
                    System.out.println("subscribe doOnNext = " + data);
                }, System.err::println);

        Thread.sleep(100000L);
    }
}
