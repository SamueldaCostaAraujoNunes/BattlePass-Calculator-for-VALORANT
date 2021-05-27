package br.com.battlepassCalculatorValorant.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.battlepassCalculatorValorant.BD_APP_VERSION
import br.com.battlepassCalculatorValorant.database.room.dao.UserTierDao
import br.com.battlepassCalculatorValorant.model.dto.UserTier

@Database(entities = [UserTier::class], version = BD_APP_VERSION, exportSchema = true)
abstract class AppDB : RoomDatabase() {
    abstract val userTier: UserTierDao
}