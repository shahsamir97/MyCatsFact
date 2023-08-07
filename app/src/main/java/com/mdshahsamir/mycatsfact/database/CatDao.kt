package com.mdshahsamir.mycatsfact.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdshahsamir.mycatsfact.model.Cat
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(cats : List<Cat>)

    @Query("SELECT * FROM cat")
    fun fetchAll() : Flow<List<Cat>>

    @Query("DELETE FROM cat")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM cat")
    fun getNumberOfCats() : Int
}