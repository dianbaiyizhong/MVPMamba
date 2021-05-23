package com.zhenmei.mvpmamba.demo.mvp.model;

import com.zchu.rxcache.stategy.CacheStrategy;
import com.zhenmei.mvpmamba.demo.mvp.contract.WeatherContract;
import com.zhenmei.mvpmamba.demo.mvp.entity.WeatherEntity;
import com.zhenmei.mvpmamba.demo.mvp.model.api.service.WeatherService;
import com.zhenmei.mvpmamba.demo.net.PayLoad;
import com.zhenmei.mvpmamba.mvp.BaseModel;
import com.zhenmei.mvpmamba.net.base.CacheManager;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;

public class WeatherModel extends BaseModel implements WeatherContract.Model {

    @Override
    public Observable<WeatherEntity> getWeather(Map<String, String> map) {

        return Observable.just(observe(apiService(WeatherService.class).getList(bean2Map(map))).map(new PayLoad<>()))
                .flatMap((Function<Observable<WeatherEntity>, ObservableSource<WeatherEntity>>) baseResponseObservable -> baseResponseObservable.compose(CacheManager.getInstance().getRxCache().transformObservable("1", WeatherEntity.class, CacheStrategy.firstCacheTimeout(10000)))
                        .map(weatherEntityCacheResult -> weatherEntityCacheResult.getData()));
    }
}
