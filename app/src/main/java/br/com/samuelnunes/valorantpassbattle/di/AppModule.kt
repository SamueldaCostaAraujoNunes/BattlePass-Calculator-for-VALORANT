package br.com.samuelnunes.valorantpassbattle.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import br.com.samuelnunes.valorantpassbattle.BD_APP_NAME
import br.com.samuelnunes.valorantpassbattle.dataStore
import br.com.samuelnunes.valorantpassbattle.database.room.AppDB
import br.com.samuelnunes.valorantpassbattle.model.BattlePassManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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

    @Provides
    @Singleton
    fun battlePassManager(@ApplicationContext context: Context): BattlePassManager =
        BattlePassManager(context)


    @Provides
    @Singleton
    fun dataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}