package com.uam.bolsatrabajo.remote

import com.uam.bolsatrabajo.util.Global.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val retrofit: Retrofit by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val empresaApi: EmpresaApi by lazy {
        retrofit.create(EmpresaApi::class.java)
    }

    val postulacionApi: PostulacionApi by lazy {
        retrofit.create(PostulacionApi::class.java)
    }

    val vacanteApi: VacanteApi by lazy {
        retrofit.create(VacanteApi::class.java)
    }

    fun getClient(): Retrofit {
        return retrofit
    }
}
