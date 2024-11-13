package com.betrybe.arcadeatlas.core.di

import com.betrybe.arcadeatlas.core.repository.IGamesRepository
import com.betrybe.arcadeatlas.core.repository.IStreamRepository
import com.betrybe.arcadeatlas.core.repository.ITwitchRepository
import com.betrybe.arcadeatlas.data.api.ApiInterface
import com.betrybe.arcadeatlas.data.api.ApiInterfaceTwitch
import com.betrybe.arcadeatlas.data.repository.GamesRepository
import com.betrybe.arcadeatlas.data.repository.StreamRepository
import com.betrybe.arcadeatlas.data.repository.TwitchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGamesRepository(api: ApiInterface) : IGamesRepository {
        return GamesRepository(api)
    }

    @Provides
    @Singleton
    fun provideTwitchRepository(api: ApiInterfaceTwitch) : ITwitchRepository {
        return TwitchRepository(api)
    }

    @Provides
    @Singleton
    fun provideStreamRepository(api: ApiInterfaceTwitch) : IStreamRepository {
        return StreamRepository(api)
    }
}