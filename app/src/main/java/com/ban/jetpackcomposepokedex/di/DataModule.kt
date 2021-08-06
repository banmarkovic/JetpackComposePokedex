package com.ban.jetpackcomposepokedex.di

import com.ban.jetpackcomposepokedex.data.repository.PokemonRepository
import com.ban.jetpackcomposepokedex.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun pokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository
}