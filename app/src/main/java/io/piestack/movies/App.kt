package io.piestack.movies

import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.piestack.movies.di.DaggerAppComponent

open class App : DaggerApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        initAndroidThreeTen()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    /**
     * AndroidThreeTen Initialization
     */
    protected open fun initAndroidThreeTen() {
        AndroidThreeTen.init(this)
    }

}