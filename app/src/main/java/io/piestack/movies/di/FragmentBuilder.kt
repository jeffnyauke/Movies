package io.piestack.movies.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.piestack.movies.ui.detail.DetailFragment
import io.piestack.movies.ui.main.MainFragment

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector
    fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    fun contributeDetailFragment(): DetailFragment
}