package com.cbp.test.routing

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.cbp.test.interfaces.routing.InputRouter

class InputRouterImpl(
    private val context: Context
): InputRouter {
    override fun navigate() {
        LocalBroadcastManager.getInstance(context).sendBroadcast(Intent(ACTION_INPUT_ROUTER))
    }
    companion object {
        const val ACTION_INPUT_ROUTER = "ACTION_INPUT_ROUTER"
    }
}