package com.zhenmei.p7i.mvpmamba.mvp.contract;

import com.zhenmei.p7i.core.mvp.IModel;
import com.zhenmei.p7i.core.mvp.IView;
import com.zhenmei.p7i.mvpmamba.mvp.entity.WeatherEntity;
import com.zhenmei.p7i.mvpmamba.mvp.model.api.subject.CommonListSubject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.QueryMap;

public interface WeatherContract {
    interface Model extends IModel {


        Observable<WeatherEntity> getWeather(@QueryMap Map<String, String> map);

    }

    interface MView extends IView {


        void loadSuccess();


    }
}
