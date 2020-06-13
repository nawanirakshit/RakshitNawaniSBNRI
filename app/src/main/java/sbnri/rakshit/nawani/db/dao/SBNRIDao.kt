package sbnri.rakshit.nawani.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import sbnri.rakshit.nawani.model.SbnriModel

@Dao
interface SBNRIDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(data: SbnriModel)

    @Query("SELECT * FROM TABLE_SNBRI")
    fun allData(): List<SbnriModel>

    @Query("DELETE FROM TABLE_SNBRI")
    fun nukeTable()
}