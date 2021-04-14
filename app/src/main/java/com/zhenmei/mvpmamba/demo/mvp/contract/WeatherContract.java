package com.zhenmei.mvpmamba.demo.mvp.contract;

import com.zhenmei.mvpmamba.mvp.IModel;
import com.zhenmei.mvpmamba.mvp.IView;
import com.zhenmei.mvpmamba.demo.mvp.entity.WeatherEntity;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.QueryMap;

public interface WeatherContract {
    interface Model extends IModel {
        Observable<WeatherEntity> getWeather(@QueryMap Map<String, String> map);
    }

    interface MView extends IView {
        void loadSuccess();

        void loadError();

    }
}
