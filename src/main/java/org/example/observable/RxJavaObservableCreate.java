package org.example.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaObservableCreate {

    public static void main(String[] args) throws InterruptedException {
        Observable<String> observable =
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        String[] datas = {"Hello", "RxJava!"};
                        for(String data : datas){
                            if(emitter.isDisposed())
                                return;

                            emitter.onNext(data);
                        }
                        emitter.onComplete();
                    }
                });

        observable.observeOn(Schedulers.computation())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        // 아무 처리도 하지 않음.
                    }

                    @Override
                    public void onNext(String data) {
                        System.out.println("data = " + data);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("error = " + error);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete!!");
                    }
                });

        Thread.sleep(500L);
    }
}
