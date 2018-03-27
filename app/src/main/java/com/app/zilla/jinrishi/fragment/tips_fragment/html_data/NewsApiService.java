package com.app.zilla.jinrishi.fragment.tips_fragment.html_data;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 74434 on 2018/3/21.
 */

public interface NewsApiService {
    @GET("wxnew/")
    Observable<String> getString(@Query("key")String key, @Query("num") String num, @Query("page") int page);

    @GET("wxnew/")
    Observable <NewsGson> getNewsData(@Query("key")String key,@Query("num") String num,@Query("page") int page);
}
