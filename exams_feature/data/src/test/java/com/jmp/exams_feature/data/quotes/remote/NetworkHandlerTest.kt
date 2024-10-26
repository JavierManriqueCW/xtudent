package com.jmp.exams_feature.data.quotes.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.jmp.examsfeature.data.quotes.remote.NetworkHandler
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock

class NetworkHandlerTest {

    private val sut: NetworkHandler = NetworkHandler(mock(Context::class.java))
    private val scenario: NetworkScenario =
        NetworkScenario(
            sut,
            mock(ConnectivityManager::class.java),
            mock(NetworkCapabilities::class.java)
        )

    @Test
    fun `should return true when connected to WiFi`() {
        scenario.givenWifiNetworkConnection()

        assertTrue(sut.isConnected())
    }

    @Test
    fun `should return true when connected to Cellular`() {
        scenario.givenCellularNetworkConnection()

        assertTrue(sut.isConnected())
    }

    @Test
    fun `should return false when not connected to any network`() {
        scenario.givenNoNetworkConnection()

        assertFalse(sut.isConnected())
    }

    @Test
    fun `isConnected should return false when connected to a network without internet`() {
        scenario.givenConnectionWithoutInternet()

        assertFalse(sut.isConnected())
    }
}
