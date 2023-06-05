package cz.cvut.fit.kot.cashierapplication.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

private const val SERVER_URL = "http://134.122.66.98:8080"

/**
 * Client for creating implementations of API endpoints
 */
@Singleton
class ApiClient @Inject constructor() {
    private val retrofit: Retrofit? = Retrofit.Builder()
        .baseUrl(SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>) = retrofit?.create(service)
}
