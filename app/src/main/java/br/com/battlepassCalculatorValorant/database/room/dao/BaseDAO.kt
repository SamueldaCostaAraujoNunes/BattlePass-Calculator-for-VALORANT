package br.com.battlepassCalculatorValorant.database.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(values: List<T>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(value: T)

    @Delete
    suspend fun delete(values: List<T>)

    @Delete
    suspend fun delete(value: T)
}