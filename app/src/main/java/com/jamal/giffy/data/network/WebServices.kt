package com.jamal.giffy.data.network

import com.jamal.giffy.BuildConfig
import com.jamal.giffy.data.model.GiffyResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val TRENDING_GIF_URL = "/v1/gifs/trending?api_key=dc6zaTOxFJmzC"
const val SEARCH_GIF_URL="/v1/gifs/search?api_key=dc6zaTOxFJmzC"
const val BASE_URL="https://api.giphy.com"
interface WebServices {
    companion object {
        val instance: WebServices by lazy {
            val logging = HttpLoggingInterceptor()
            if(BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            }
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(WebServices::class.java)
        }
    }

    @GET(TRENDING_GIF_URL)
    fun fetchTrendingGif(): Single<GiffyResponse>

    @GET(SEARCH_GIF_URL)
    fun searchGif(@Query("q") query: String): Single<GiffyResponse>

}