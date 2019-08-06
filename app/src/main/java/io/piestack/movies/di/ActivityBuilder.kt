package io.piestack.movies.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.piestack.movies.ui.MainActivity
import io.piestack.movies.viewmodel.MovieViewModel

@Module
interface ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ActivityModule::class,
            FragmentBuilder::class
        ]
    )
    fun contributeMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun bindUserViewModel(viewModel: MovieViewModel): ViewModel
}