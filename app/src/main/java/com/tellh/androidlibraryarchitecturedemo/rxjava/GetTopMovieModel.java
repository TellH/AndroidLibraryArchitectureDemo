package com.tellh.androidlibraryarchitecturedemo.rxjava;

import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccessListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GetTopMovieModel {

    public interface MovieService {
        @GET("top250")
        Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
    }

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MovieService movieService;

    //构造方法私有
    private GetTopMovieModel() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(MovieService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final GetTopMovieModel INSTANCE = new GetTopMovieModel();
    }

    //获取单例
    public static GetTopMovieModel getInstance(){
        return SingletonHolder.INSTANCE;
    }

     /**
     * 用于获取豆瓣电影Top250的数据
     * @param subscriber 由调用者传过来的观察者对象
     * @param start 起始位置
     * @param count 获取长度
     */
    public void getTopMovie(Subscriber<List<MovieEntity.SubjectsEntity>> subscriber, int start, int count, NetworkAccessListener listener){
        movieService.getTopMovie(start, count)
                //下面两个compose的次序不能颠倒
                .compose(SchedulersCompat.<MovieEntity>applyIoSchedulers())
                .compose(RxJavaUtils.<MovieEntity>setListener(listener))
                .flatMap(new Func1<MovieEntity, Observable<List<MovieEntity.SubjectsEntity>>>() {
                    @Override
                    public Observable<List<MovieEntity.SubjectsEntity>> call(MovieEntity movieEntity) {
                        return Observable.just(movieEntity.getSubjects());
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }
}