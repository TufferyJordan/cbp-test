package com.cbp.test.features.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cbp.test.features.input.presenter.InputView
import com.cbp.test.interfaces.routing.InputRouter
import kotlinx.android.synthetic.main.fragment_input.*
import org.koin.android.ext.android.get
import java.util.*


class InputFragment : Fragment(), InputView {

    private var barcodeId: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        barcodeId = arguments?.getString(getString(R.string.args_barcode_id))
        val dependencies = InputDependencies(
            this,
            get(),
            lifecycleScope
        )
        input_fragment_save_button.setOnClickListener {
            barcodeId?.let {
                dependencies.interactor.input(
                    it,
                    input_fragment_text_input_edit_text.text.toString(),
                    getDateFromPicker()
                )
            }

        }
    }

    override fun displaySuccess() {
        get<InputRouter>().navigate()
    }

    override fun displayError(error: String) {
        input_fragment_error_label.text = error
    }

    private fun getDateFromPicker(): Long {
        val day: Int = input_fragment_date_picker.dayOfMonth
        val month: Int = input_fragment_date_picker.month
        val year: Int = input_fragment_date_picker.year

        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        return calendar.timeInMillis
    }
}