package com.example.james.ghost

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_single_fragment.*

abstract class SingleFragmentActivity : AppCompatActivity() {

    protected val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_single_fragment

    protected abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24px)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val fm = supportFragmentManager
        var fragment: Fragment? = fm.findFragmentById(R.id.contentFrame)
        if (fragment == null) {
            fragment = createFragment()
            fm.beginTransaction()
                    .add(R.id.contentFrame, fragment)
                    .commit()
        }

    }
}