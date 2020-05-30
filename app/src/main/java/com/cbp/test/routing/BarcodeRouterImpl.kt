package com.cbp.test.routing

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.cbp.test.interfaces.routing.BarcodeRouter

class BarcodeRouterImpl(
    private val context: Context
): BarcodeRouter {
    override fun navigate(barcodeId: String) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(Intent(ACTION_BARCODE_ROUTER).apply {
            putExtra(ARGS_BARCODE_ID, barcodeId)
        })
    }

    companion object {
        const val ACTION_BARCODE_ROUTER = "ACTION_BARCODE_ROUTER"
        const val ARGS_BARCODE_ID = "ARGS_BARCODE_ID"
    }
}