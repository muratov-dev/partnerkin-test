package dev.ymuratov.partnerkin_test.core.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.hasActiveNetwork(): Boolean {
    var result = false
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    connectivityManager?.run {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
            result = when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
    }
    return result
}