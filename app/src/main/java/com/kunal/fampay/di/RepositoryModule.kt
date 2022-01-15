package com.kunal.fampay.di

import com.kunal.fampay.data.network.apis.ApiInterface
import com.kunal.fampay.data.repositories.CardsGroupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCardsGroupRepository(
        api: ApiInterface
    ): CardsGroupRepository {
        return CardsGroupRepository(api)
    }

}