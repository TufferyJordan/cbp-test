package com.cbp.test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import com.cbp.test.features.barcode.BarcodeFragmentDirections
import com.cbp.test.features.input.InputFragmentDirections
import com.cbp.test.routing.BarcodeRouterImpl
import com.cbp.test.routing.InputRouterImpl

class NavigationBroadcastReceiver(
    private val navController: NavController,
    private val context: Context
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        with(intent) {
            when (action) {
                BarcodeRouterImpl.ACTION_BARCODE_ROUTER -> {
                    val barcodeId = getStringExtra(BarcodeRouterImpl.ARGS_BARCODE_ID)
                    if (barcodeId != null) {
                        val direction = BarcodeFragmentDirections.barcodeToInput(barcodeId)
                        navController.navigate(direction)
                    }
                }
                InputRouterImpl.ACTION_INPUT_ROUTER -> {
                    val direction = InputFragmentDirections.inputToInventory()
                    navController.navigate(direction)
                }
            }
        }
    }

    fun registerReceiver() {
        val broadcastManager = LocalBroadcastManager.getInstance(context)
        broadcastManager.registerReceiver(
            this,
            IntentFilter(BarcodeRouterImpl.ACTION_BARCODE_ROUTER)
        )
        broadcastManager.registerReceiver(
            this,
            IntentFilter(InputRouterImpl.ACTION_INPUT_ROUTER)
        )
    }

    fun unregisterReceiver() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(this)
    }
}