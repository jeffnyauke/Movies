package io.piestack.movies.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.piestack.movies.App

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        ViewModelFactoryModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}
