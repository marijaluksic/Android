package hr.tvz.android.listaluksic.network

import hr.tvz.android.listaluksic.network.model.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    private val service : ApiService
    private val baseUrl = "https://api.npoint.io/"

    init{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor)
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build()).build()

        service = retrofit.create(ApiService::class.java)
    }

    fun getService() : ApiService = service
}