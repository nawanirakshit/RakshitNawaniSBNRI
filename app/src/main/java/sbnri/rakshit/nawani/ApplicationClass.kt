package sbnri.rakshit.nawani

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin
import sbnri.rakshit.nawani.di.*

class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        startKoin(
            this, listOf(
                networkModule,
                apiModule,
                roomModule,
                viewModelModule,
                sharedPreferencesModule
            )
        )
    }
}