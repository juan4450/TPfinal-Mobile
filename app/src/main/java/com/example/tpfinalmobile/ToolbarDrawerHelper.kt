package com.example.tpfinalmobile

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

object ToolbarDrawerHelper {

    fun setup(
        activity: AppCompatActivity,
        toolbarId: Int,
        drawerLayoutId: Int,
        navViewId: Int,
        listener: NavigationView.OnNavigationItemSelectedListener
    ): DrawerLayout {
        val toolbar: Toolbar = activity.findViewById(toolbarId)
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)

        val drawerLayout: DrawerLayout = activity.findViewById(drawerLayoutId)
        val navigationView: NavigationView = activity.findViewById(navViewId)
        navigationView.setNavigationItemSelectedListener(listener)

        val toggle = ActionBarDrawerToggle(
            activity, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.drawerArrowDrawable.color = activity.getColor(R.color.menu_icon_gray)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        return drawerLayout
    }
}
