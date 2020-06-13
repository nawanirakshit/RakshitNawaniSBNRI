package sbnri.rakshit.nawani.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import sbnri.rakshit.nawani.ui.dashboard.MainViewModel

val viewModelModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
}