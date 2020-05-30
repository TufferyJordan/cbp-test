package com.cbp.test.inventory.view

import com.cbp.test.inventory.R
import com.cbp.test.inventory.presenter.ProductViewModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.view_product_row.view.*

class InventoryRowHeader: Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) { }

    override fun getLayout(): Int =
        R.layout.view_product_row_header
}