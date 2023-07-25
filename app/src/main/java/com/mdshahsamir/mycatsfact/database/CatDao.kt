package com.mdshahsamir.mycatsfact.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mdshahsamir.mycatsfact.model.Cat
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Insert
    fun insertAll(cats : List<Cat>)

    @Query("SELECT * FROM cat")
    fun fetchAll() : Flow<List<Cat>>
}