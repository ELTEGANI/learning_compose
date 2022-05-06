package com.example.test.di

import android.content.Context
import com.example.test.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestModule {
    @Singleton
    @Provides
    fun providesRepository(@ApplicationContext context: Context):Repository =
        Repository(context)
}