package com.kosigo.showcase.app.data.retrofit

import com.kosigo.showcase.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class UserAgentInterceptor : Interceptor {
    private val userAgent = "showcase/${BuildConfig.VERSION_NAME} ${System.getProperty("http.agent")}"

    override fun intercept(chain: Interceptor.Chain): Response = chain
        .request()
        .newBuilder()
        .header("User-Agent", userAgent)
        .build()
        .let { chain.proceed(it) }
}
