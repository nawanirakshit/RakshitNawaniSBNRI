package sbnri.rakshit.nawani.di

import sbnri.rakshit.nawani.io.RequestAPIs
import org.koin.dsl.module.module
import retrofit2.Retrofit

val apiModule = module {
    single(createOnStart = false) { get<Retrofit>().create(RequestAPIs::class.java) }
}