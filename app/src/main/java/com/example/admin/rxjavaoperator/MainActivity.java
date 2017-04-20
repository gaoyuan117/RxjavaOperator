package com.example.admin.rxjavaoperator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        map();
//        zip();
//        taken();
//        timer();
//        interval();
//        single();
//        reduce();
//        buffer();
//        filter();
//        skip();
//        scan();
//        concat();
//        merge();
//        distinct();
//        last();
//        delay();
//        flatMap();
//        just();
//        from();
        toList();
    }

    private void toList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Observable.fromIterable(list)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        Log.e("gy","map："+integer);
                        return "字符串："+integer;
                    }
                })
                .toList()
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        for (int i = 0; i < strings.size(); i++) {
                            Log.e("gy","toList："+strings.get(i));
                        }
                    }
                });
    }

    private void from() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        Observable.fromIterable(list)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("gy","fromIterable："+s);
                    }
                });
    }

    private void just() {
        Observable.just(1,2,3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("gy","just："+integer);
                    }
                });
    }

    private void flatMap() {
        Observable.just("3")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just("flatMap："+s);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("gy",s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("gy","失败");
                    }
                });
    }


    private void delay() {
        Observable.just(1)
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("gy", "delay：" + integer);
                    }
                });
    }

    private void last() {
        Observable.just(1, 2, 3, 4)
                .last(0)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("gy", "last：" + integer);
                    }
                });
    }

    private void distinct() {
        Observable.just(1, 1, 1, 1, 1, 2)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("gy", "distinct：" + integer);
                    }
                });
    }

    private void merge() {
        Observable.merge(Observable.just(1, 2, 3), Observable.just(5, 6, 7))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("gy", "merge：" + integer);
                    }
                });
    }

    private void concat() {
        Observable.concat(Observable.just(1, 4, 6), Observable.just(2, 5, 3))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("gy", "concat：" + integer);
                    }
                });
    }

    private void scan() {
        Observable.just("1", "2", "3", "4", "5")
                .scan(new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        return s + s2;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("gy", s);
                    }
                });
    }

    private void skip() {
        Observable.just(1, 2, 3, 4, 5)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("gy", "skip：" + integer);
                    }
                });
    }

    private void filter() {
        Observable.just(1)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 5;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("gy", "filter：" + integer);
                    }
                });
    }

    private void buffer() {
        Observable.just(1, 2, 3, 4)
                .buffer(3, 3)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.e("gy", "buffer：" + integers);
                    }
                });
    }

    private void reduce() {
        Observable.just("1", "2", "3")
                .reduce(new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        return s + s2;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("gy", "reduce：" + s);
                    }
                });
    }

    private void single() {
        Single.just(1)
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer value) {
                        Log.e("gy", "single:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("gy", "onError");
                    }
                });
    }

    private void interval() {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("gy", "interval：" + aLong);
                    }
                });
    }

    private void timer() {
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("gy", "timer:" + aLong);
                    }
                });
    }

    private void taken() {
        Observable.just(1, 2, 3, 4)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("gy", "take：" + integer);
                    }
                });
    }

    private void zip() {
        Observable<Integer> observable3 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
            }
        });

        Observable<Integer> observable4 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
            }
        });
        //短的发送完，下游不在接收，但是长的继续发送
        Observable.zip(observable3, observable4, new BiFunction<Integer, Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer, Integer integer2) throws Exception {
                return integer > integer2;
            }
        })
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {
                        Log.e("gy", "结果：" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("gy", "onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("gy", "onComplete");
                    }
                });
    }

    private void map() {
        Observable.just(1, 2, 3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "map操作符：" + integer;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("gy", s);
                    }
                });
    }

    public void click(View view) {

    }
}
