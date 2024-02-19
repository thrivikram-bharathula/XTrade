package com.intern.xtrade.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.intern.xtrade.IPO.ForthComing
import com.intern.xtrade.IPO.LiveIPOS
import com.intern.xtrade.IPO.PastBids
import com.intern.xtrade.Orders.OrdersExecuted
import com.intern.xtrade.Orders.OrdersOpen

class FragmentPageAdapterOrders(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            return OrdersOpen()
        else
            return OrdersExecuted()
    }
}
