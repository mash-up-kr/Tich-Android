package com.example.tichandroid.view.ui.showcycle

import android.os.Bundle
import androidx.fragment.app.commit
import com.example.tichandroid.R
import com.example.tichandroid.base.BaseActivity
import com.example.tichandroid.view.ui.items.ItemFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowCycleActivity : BaseActivity(), ShowCycleContainer {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_cycle)
        onSetUpViews()
    }

    private fun onSetUpViews() {
        supportFragmentManager.commit { replace(R.id.container, ShowCycleFragment()) }
    }

    override fun showItemManager(itemType: Int) {
        supportFragmentManager.commit { add(R.id.container, ItemFragment.getInstance(itemType)) }
    }
}