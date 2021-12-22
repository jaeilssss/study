package com.example.studyrxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private String greeting= "Hello From RxJava";

    private Observable<String> myObservable;

    private Observer<String> myObserver;

    private TextView textView;

    private final static String TAG = "myApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.mainTv);
        myObservable = Observable.just(greeting);
        myObservable.subscribeOn(Schedulers.io());

        myObservable.observeOn(AndroidSchedulers.mainThread());

        
        myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG," onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG," inNext");
                textView.setText(s);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG," onError");

            }

            @Override
            public void onComplete() {
                Log.i(TAG," Complete");

            }
        };

        myObservable.subscribe(myObserver);
    }
}