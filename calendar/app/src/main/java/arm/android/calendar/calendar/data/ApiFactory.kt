package arm.android.calendar.calendar.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

object ApiFactory {
    private const val OMDB_BASE_URL = "https://www.omdbapi.com/"
    private const val NEWS_BASE_URL = "https://newsapi.org/v2/"


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    private val omdbRetrofit = Retrofit.Builder()
        .baseUrl(OMDB_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()


    private val newsRetrofit = Retrofit.Builder()
        .baseUrl(NEWS_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val omdbApi: OmdbApi = omdbRetrofit.create(OmdbApi::class.java)
    val newsApi: NewsApi = newsRetrofit.create(NewsApi::class.java)


}