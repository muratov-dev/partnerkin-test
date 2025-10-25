package dev.ymuratov.partnerkin_test.core.data.interceptor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.ymuratov.partnerkin_test.core.data.exception.NoConnectivityException
import dev.ymuratov.partnerkin_test.core.data.utils.hasActiveNetwork
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(@ApplicationContext private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.hasActiveNetwork()) {
            throw NoConnectivityException()
        }
        return try {
            chain.proceed(chain.request())
        } catch (e: Exception) {
            e.printStackTrace()
            throw NoConnectivityException()
        }
    }
}