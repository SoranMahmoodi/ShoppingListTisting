package com.soran.shoppingtest.di

import android.content.Context
import androidx.room.Room
import com.soran.shoppingtest.repository.local.Database
import com.soran.shoppingtest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun providerDatabaseTest(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, Database::class.java, Constants.DATABASE_NAME)
        .allowMainThreadQueries().build()
}