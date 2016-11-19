package com.royshow.rxjavatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.NumberPicker;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private NumberPicker showNumProjectPicker;
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNumProjectPicker = (NumberPicker) findViewById(R.id.showNumProjectPicker);

//        Request request = new Request.Builder().url("你的Json Url").build();
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String json = response.body().string();
//                Log.d("OkHttp", json);
//                ProjectResponse tmpProjectResponse = new Gson().fromJson(response.body().string(), ProjectResponse.class);
//                Log.d("OkHttp2", tmpProjectResponse.toString());
//            }
//        });

//        String[] items = {"test1", "test2", "test3", "test4"};
//
//        showNumProjectPicker.setMinValue(0);
//        showNumProjectPicker.setMaxValue(items.length - 1);
//        showNumProjectPicker.setDisplayedValues(items);
//        showNumProjectPicker.setWrapSelectorWheel(false);
//        showNumProjectPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        init();
    }

    public void init() {
        Observable.create(new Observable.OnSubscribe<ProjectViewModel>() {
            @Override
            public void call(Subscriber<? super ProjectViewModel> subscriber) {
                try {
                    Request request = new Request.Builder().url("http://60.250.132.191/IETM2016/Json/ProjectJson").build();
                    Response projectResponse = client.newCall(request).execute();
                    ProjectResponse tmpProjectResponse = new Gson().fromJson(projectResponse.body().string(), ProjectResponse.class);

                    subscriber.onNext(new ProjectViewModel(tmpProjectResponse.getProjects()));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ProjectViewModel>() {
                    @Override
                    public void call(ProjectViewModel projectViewModel) {
                        String[] items = new String[projectViewModel.getProjects().size()];
                        for (int i = 0; i < items.length; i++) {
                            items[i] = projectViewModel.getProjects().get(i).getName();
                        }
                        showNumProjectPicker.setMinValue(0);
                        showNumProjectPicker.setMaxValue(projectViewModel.getProjects().size() - 1);
                        showNumProjectPicker.setDisplayedValues(items);
                        showNumProjectPicker.setWrapSelectorWheel(false);
                        showNumProjectPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("call", "call: " + throwable.getLocalizedMessage());
                    }
                });
    }
}
