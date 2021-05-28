package br.com.samuelnunes.valorantpassbattle.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.samuelnunes.valorantpassbattle.BD_APP_VERSION
import br.com.samuelnunes.valorantpassbattle.database.room.dao.UserTierDao
import br.com.samuelnunes.valorantpassbattle.model.dto.UserTier

@Database(entities = [UserTier::class], version = BD_APP_VERSION, exportSchema = true)
abstract class AppDB : RoomDatabase() {
    abstract val userTier: UserTierDao
}