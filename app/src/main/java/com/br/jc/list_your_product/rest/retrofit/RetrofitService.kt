package com.br.jc.list_your_product.rest.retrofit

import com.br.jc.list_your_product.BASE_URL
import com.br.jc.list_your_product.rest.retrofit.interfaces.APIRest
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {


    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor()) // Adiciona o interceptor de autenticação
        .build()

    val apiRest: APIRest by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRest::class.java)
    }
}