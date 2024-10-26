package com.jmp.exams_feature.data.quotes.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.jmp.examsfeature.data.quotes.remote.NetworkHandler
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class NetworkScenario(
    private val networkHandler: NetworkHandler,
    private val connectivityManager: ConnectivityManager,
    private val networkCapabilities: NetworkCapabilities
) {

    fun givenCellularNetworkConnection() {
        stubSystemConnectivityService()
        stubNetworkCapabilities()
        stubCellularNetworkConnection()
    }

    fun givenWifiNetworkConnection() {
        stubSystemConnectivityService()
        stubNetworkCapabilities()
        stubWifiNetworkConnection()
    }

    fun givenNoNetworkConnection() {
        stubSystemConnectivityService()
        whenever(connectivityManager.activeNetwork).thenReturn(null)
    }

    fun givenConnectionWithoutInternet() {
        stubSystemConnectivityService()
        stubNetworkCapabilities()
        stubNoNetworkConnection()
    }

    private fun stubSystemConnectivityService() {
        whenever(networkHandler.context.getSystemService(Context.CONNECTIVITY_SERVICE))
            .thenReturn(connectivityManager)
    }

    private fun stubNetworkCapabilities() {
        whenever(connectivityManager.activeNetwork)
            .thenReturn(mock(Network::class.java))
        whenever(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork))
            .thenReturn(networkCapabilities)
    }

    private fun stubCellularNetworkConnection() {
        whenever(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
            .thenReturn(true)
    }

    private fun stubWifiNetworkConnection() {
        whenever(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            .thenReturn(true)
    }

    private fun stubNoNetworkConnection() {
        whenever(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            .thenReturn(false)
        whenever(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
            .thenReturn(false)
    }
}
