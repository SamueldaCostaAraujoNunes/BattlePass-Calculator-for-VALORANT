package br.com.battlepassCalculatorValorant.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import br.com.battlepassCalculatorValorant.BD_APP_NAME
import br.com.battlepassCalculatorValorant.dataStore
import br.com.battlepassCalculatorValorant.database.room.AppDB
import br.com.battlepassCalculatorValorant.database.sharedPreferences.MSharedPreferences
import br.com.battlepassCalculatorValorant.model.battlePass.BattlePass
import br.com.battlepassCalculatorValorant.model.battlePass.BattlePassFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context): AppDB {
        return Room.databaseBuilder(context, AppDB::class.java, BD_APP_NAME)
            .enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration()
            .build()
    }

//    @Provides
//    @Singleton
//    fun battlePass(@ApplicationContext context: Context): NewBattlePass =
//        NewBattlePass(context)


    @Provides
    @Singleton
    fun battlePass(@ApplicationContext context: Context): BattlePass =
        BattlePassFactory(context).getBattlePass()

    @Provides
    @Singleton
    fun mSharedPreferences(@ApplicationContext context: Context): MSharedPreferences =
        MSharedPreferences(context)

    @Provides
    @Singleton
    fun dataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}