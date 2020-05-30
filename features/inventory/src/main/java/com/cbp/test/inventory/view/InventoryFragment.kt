package com.cbp.test.inventory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbp.test.inventory.InventoryDependencies
import com.cbp.test.inventory.R
import com.cbp.test.inventory.interactor.InventoryInteractor
import com.cbp.test.inventory.presenter.InventoryView
import com.cbp.test.inventory.presenter.ProductListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_inventory.*
import org.koin.android.ext.android.get

class InventoryFragment: Fragment(), InventoryView {

    private val groupieAdapter = GroupAdapter<GroupieViewHolder>()

    private lateinit var interactor: InventoryInteractor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_inventory_recycler_view.apply {
            val lm = LinearLayoutManager(requireContext())
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(requireContext(), lm.orientation))
            adapter = groupieAdapter
        }

        interactor = InventoryDependencies(
            this,
            get(),
            lifecycleScope
        ).interactor
    }

    override fun onStart() {
        super.onStart()
        interactor.load()
    }
    override fun displayError(error: String) {
        fragment_inventory_error_text.visibility = View.VISIBLE
        fragment_inventory_recycler_view.visibility = View.GONE

        fragment_inventory_error_text.text = error
    }

    override fun displayData(viewModel: ProductListViewModel) {
        fragment_inventory_error_text.visibility = View.GONE
        fragment_inventory_recycler_view.visibility = View.VISIBLE

        val items = viewModel.list.map {
            InventoryRow(it)
        }
        groupieAdapter.clear()
        groupieAdapter.add(InventoryRowHeader())
        groupieAdapter.addAll(items)
    }
}