package nl.codestone.aospissue145770600

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiNetworkSpecifier
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network?) {
                disconnectFromHotspotButton.isEnabled = true
            }
            override fun onUnavailable() {
                connectTotHotspotButton.isEnabled = true
                disconnectFromHotspotButton.isEnabled = false
            }
        }

        connectTotHotspotButton.setOnClickListener {

            val ssid = ssidInputField.text.toString()

            if (ssid.isBlank()) {
                return@setOnClickListener
            }

            ssidInputField.closeSoftkeyboard()

            connectTotHotspotButton.isEnabled = false

            val specifier = WifiNetworkSpecifier.Builder()
                    .setSsid(ssid)
                    .build()

            val request = NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .setNetworkSpecifier(specifier)
                    .build()

            connectivityManager.requestNetwork(request, networkCallback)
        }

        disconnectFromHotspotButton.setOnClickListener {
            connectTotHotspotButton.isEnabled = true
            disconnectFromHotspotButton.isEnabled = false
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }

    }
}

fun View.closeSoftkeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)
}
