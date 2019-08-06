package io.piestack.movies.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.piestack.movies.viewmodel.MovieViewModel

@Module
interface FragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun bindUserViewModel(viewModel: MovieViewModel): ViewModel
}