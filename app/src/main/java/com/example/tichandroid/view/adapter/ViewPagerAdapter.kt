package com.example.tichandroid.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tichandroid.view.ui.WalkFirstFragment
import com.example.tichandroid.view.ui.WalkLastFragment
import com.example.tichandroid.view.ui.WalkSecondFragment
import com.example.tichandroid.view.ui.WalkThirdFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WalkFirstFragment()
            1 -> WalkSecondFragment()
            2 -> WalkThirdFragment()
            else -> WalkLastFragment()
        }
    }

    override fun getCount(): Int = 4

}