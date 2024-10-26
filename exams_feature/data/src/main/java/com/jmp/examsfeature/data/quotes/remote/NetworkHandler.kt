package com.jmp.examsfeature.data.quotes.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

open class NetworkHandler @Inject constructor(val context: Context) {

     fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val activeNetwork = connectivityManager.getNetworkCapabilities(network)

         return activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true ||
                 activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
    }
}
