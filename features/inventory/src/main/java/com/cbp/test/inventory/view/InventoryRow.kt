package com.cbp.test.inventory.view

import com.cbp.test.inventory.R
import com.cbp.test.inventory.presenter.ProductViewModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.view_product_row.view.*

class InventoryRow(
    private val vm: ProductViewModel
): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            view_product_row_title.text = vm.title
            view_product_row_date.text = vm.expirationDate
        }
    }

    override fun getLayout(): Int =
        R.layout.view_product_row
}