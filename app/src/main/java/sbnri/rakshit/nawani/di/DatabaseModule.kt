package sbnri.rakshit.nawani.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import sbnri.rakshit.nawani.db.AppDatabase

val roomModule = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single(createOnStart = false) { get<AppDatabase>().getSNBRIDao() }
}