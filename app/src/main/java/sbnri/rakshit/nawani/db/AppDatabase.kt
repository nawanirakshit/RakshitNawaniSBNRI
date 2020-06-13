package sbnri.rakshit.nawani.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import sbnri.rakshit.nawani.db.AppDatabase.Companion.DB_VERSION
import sbnri.rakshit.nawani.db.dao.SBNRIDao
import sbnri.rakshit.nawani.model.LicenceTypeConverter
import sbnri.rakshit.nawani.model.PermissionTypeConverter
import sbnri.rakshit.nawani.model.SbnriModel

@Database(
    entities = [
        SbnriModel::class
    ],
    version = DB_VERSION,
    exportSchema = false
)
@TypeConverters(LicenceTypeConverter::class, PermissionTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSNBRIDao(): SBNRIDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "SBNRI_DATABASE"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}