package com.giussepr.thecatsapp.data.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val request: Request = original.newBuilder()
            .header("x-api-key", "bda53789-d59e-46cd-9bc4-2936630fde39")
            .method(original.method, original.body)
            .build()

        return chain.proceed(request)
    }
}