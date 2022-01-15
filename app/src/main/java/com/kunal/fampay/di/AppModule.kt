package com.kunal.fampay.di

import android.content.Context
import com.kunal.fampay.BaseApplication
import com.kunal.fampay.data.prefs.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context:Context):BaseApplication{
        return context as BaseApplication
    }

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext context: Context):Preferences{
        return Preferences(context)
    }
}