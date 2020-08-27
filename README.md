# MVPMamba
## 一个整合dagger2+MVP架构+Retrofit2网络请求+RxJava的库（androidx）

[![](https://www.jitpack.io/v/huanghaoming/MVPMamba.svg)](https://www.jitpack.io/#huanghaoming/MVPMamba)

### 推荐使用理由：
1.使用Rx方式通过Retrofit进行网络请求，并直接封装解决内存泄漏问题
2.也整合了RxCache的缓存使用
3.也支持多个baseUrl的请求源
4.支持token拦截器
5.同时支持activity和fragment
6.教你封装统一请求错误处理


### 先确认一个小需求
请求得到天气的数据，以下是一个请求天气的api接口
http://wthrcdn.etouch.cn/weather_mini?city=北京
我们通过这个作为案例，来实现整合一个demo

### 我的环境
android studio 4.0.1 + gradle-5.6.4

### 整合步骤（一步一步按照顺序照抄即可）
导入库
```groovy
implementation 'com.github.huanghaoming:MVPMamba:1.0'
```

除此之外，还要有
```groovy
annotationProcessor 'com.google.dagger:dagger-compiler:2.28.3'
annotationProcessor 'com.google.dagger:dagger-android-processor:2.28.3'
```

编写自己的Application类，继承MVPApplication
```java
public class BaseApplication extends MVPApplication {


    @Override
    public void onCreate() {
        super.onCreate();
		   /**
         * 配置网络
         */
        ManBaNetBuilder.init(this)
                .withApiHost("http://wthrcdn.etouch.cn/")
                .configure();


        /**
         * dagger必写，照抄即可
         */
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
	}
}
```
> 注：DaggerAppComponent这个类需要工程编译生成，目前代码是变红的，可以先放一放，继续下面步骤



我们先看看数据格式
```json
{
  "data": {
    "yesterday": {
      "date": "25日星期二",
      "high": "高温 33℃",
      "fx": "东南风",
      "low": "低温 20℃",
      "fl": "<![CDATA[1级]]>",
      "type": "阴"
    },
    "city": "北京",
    "forecast": [
      {
        "date": "26日星期三",
        "high": "高温 29℃",
        "fengli": "<![CDATA[2级]]>",
        "low": "低温 22℃",
        "fengxiang": "东南风",
        "type": "多云"
      },
      {
        "date": "27日星期四",
        "high": "高温 31℃",
        "fengli": "<![CDATA[2级]]>",
        "low": "低温 20℃",
        "fengxiang": "南风",
        "type": "多云"
      },
      {
        "date": "28日星期五",
        "high": "高温 30℃",
        "fengli": "<![CDATA[2级]]>",
        "low": "低温 22℃",
        "fengxiang": "东南风",
        "type": "多云"
      },
      {
        "date": "29日星期六",
        "high": "高温 29℃",
        "fengli": "<![CDATA[2级]]>",
        "low": "低温 23℃",
        "fengxiang": "东南风",
        "type": "阴"
      },
      {
        "date": "30日星期天",
        "high": "高温 30℃",
        "fengli": "<![CDATA[2级]]>",
        "low": "低温 23℃",
        "fengxiang": "南风",
        "type": "雷阵雨"
      }
    ],
    "ganmao": "感冒低发期，天气舒适，请注意多吃蔬菜水果，多喝水哦。",
    "wendu": "28"
  },
  "status": 1000,
  "desc": "OK"
}
```

首先status=1000是返回文本的状态值，很多公司是code=0作为成功的标志
根据后台服务定义的response规则，制定一个基本类

```java
public class BaseResponse<T> implements Serializable {
    public int status;
    public String desc;
    public T data;
    public boolean isSuccess() {
        return status == 1000;
    }
}

```
编写网络请求service部分

```java
public interface WeatherService {


    @GET("/weather_mini")
    Observable<BaseResponse<WeatherEntity>> getList(@QueryMap Map<String, String> map);

}
```


接着，在这里，我们用到RxJava写一个统一处理类PayLoad，作为网络请求时统一处理
```java
public class PayLoad<T> implements Function<BaseResponse<T>, T> {

    @Override
    public T apply(BaseResponse<T> tBaseResponse) {

        if (!tBaseResponse.isSuccess()) {
            //抛出异常
        }

        return tBaseResponse.data;
    }
}

```

接下来，写一个天气数据的类，这个没问题吧（get和set方法省略了）
```java
public class WeatherEntity {
    private String wendu;
    private String ganmao;
    private String city;
}
```


编写Model和View两个类，网上的大佬将两者写到了一个类（contract）里，为的是省事

```java
public interface WeatherContract {
    interface Model extends IModel {
        Observable<WeatherEntity> getWeather(@QueryMap Map<String, String> map);
    }

    interface MView extends IView {
        void loadSuccess();
    }
}
```
编写model的实现类(同时在这里通过map方法，传入PayLoad处理，使用了Rx特性，一些数据缓存等操作也可以在这里写)
```java
public class WeatherModel extends BaseModel implements WeatherContract.Model {

    @Override
    public Observable<WeatherEntity> getWeather(Map<String, String> map) {
        return observe(apiService(WeatherService.class).getList(bean2Map(map))).map(new PayLoad<>());
    }
}
```


编写presenter类
```java
public class WeatherPresenter extends BasePresenter<WeatherContract.Model, WeatherContract.MView> {


    @Inject
    public WeatherPresenter(WeatherContract.Model model) {
        super(model);
    }

    public void getWeather() {

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("city", "北京");


        mModel.getWeather(paramMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new MambaHandlerSubscriber<WeatherEntity>(context) {
                    @Override
                    public void onNext(WeatherEntity subject) {
                        mView.loadSuccess();
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.loadError();
                    }
                });


    }


}

```


编写activity的基类（一定要，一切为了dagger2的注入，对着抄就好）
```java
public abstract class MyBaseActivity<P extends BasePresenter> extends FMVPActivity {


    @Override
    protected boolean enableInject() {
        return true;
    }

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.attachView(this);
        mPresenter.setContext(this);
    }
}
```
终于开始写Activity类

```java
public class WeatherActivity extends MyBaseActivity<WeatherPresenter> implements WeatherContract.MView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //调用天气请求，就是一句话这么简单
        mPresenter.getWeather();


    }

    @Override
    public void loadSuccess() {

    }

    @Override
    public void loadError() {

    }


}
```



接下来是收拾dagger的摊子
编写ActivityModule类，注入Model
```java
@Module
public class ActivityModule {


    @Provides
    WeatherContract.Model provideWeatherModel() {
        return new WeatherModel();
    }


}
```


编写BuildersModule类，注入activity或者fragment
```java
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract WeatherActivity bindWeatherActivity();

}
```


编写AppComponent类，照抄就可以（自己写的Application要在这里用到了）

```java
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        BuildersModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {

        AppComponent build();


        @BindsInstance
        Builder application(BaseApplication application);

    }

    void inject(BaseApplication app);

}
```

最后一步，需要在清单文件加上一句话（这个跟框架没关系。是android 9后网络请求的配置问题）
```
android:networkSecurityConfig="@xml/network_security_config"
```


### 以上是代码部分，接下来，make一下工程，就可以了（希望你们没有报错）





------------




### 扩展用法：
自定义请求拦截器（如token）：

```java
public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .method(originalRequest.method(), originalRequest.body());


        requestBuilder.addHeader("deviceId", DeviceUtils.getUniqueDeviceId());
        requestBuilder.addHeader("deviceName", DeviceUtils.getManufacturer() + ":" + DeviceUtils.getModel());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
```

```java
        ManBaNetBuilder.init(this)
                .withApiHost("http://wthrcdn.etouch.cn/")
                .withInterceptor(new TokenInterceptor()) //这么使用
                .configure();
```