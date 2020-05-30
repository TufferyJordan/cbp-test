package com.cbp.test.features.barcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cbp.test.interfaces.routing.BarcodeRouter
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.koin.android.ext.android.get

class BarcodeFragment : Fragment() {

    private lateinit var zxingScannerView: ZXingScannerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        zxingScannerView = ZXingScannerView(activity)
        zxingScannerView.setAutoFocus(true)
        zxingScannerView.setResultHandler {
            get<BarcodeRouter>().navigate(it.text)
        }
        return zxingScannerView
    }

    override fun onStart() {
        super.onStart()
        zxingScannerView.startCamera()
    }
}