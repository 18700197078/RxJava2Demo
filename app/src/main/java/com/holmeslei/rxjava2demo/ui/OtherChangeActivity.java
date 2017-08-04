package com.holmeslei.rxjava2demo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.holmeslei.rxjava2demo.R;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class OtherChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_change);
        actionChange();
        functionChange();
        doOnCancel();
    }

    /**
     * Action更新
     * Action0->Action
     * Action1->Consumer
     * Action2->BiConsumer
     * ActionN->Consumer<Object[]>
     * 去除了Action3 - Action9
     */
    private void actionChange() {
        Flowable.just(1, 2, 3, 4, 5)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("rx2_test", "actionChange：" + integer);
                    }
                });
    }

    /**
     * Function更新
     * Func1->Function
     * Func2->BiFunction
     * Func3-Func9->Function3-Function9
     * FuncN->Function<Object[], R>
     * Action与Function都增加了throws Exception，免去了try-catch
     */
    private void functionChange() {
        Flowable.just(1, 2, 3, 4, 5)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return "xulei" + integer;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("rx2_test", "functionChange：" + s);
                    }
                });
    }
}
