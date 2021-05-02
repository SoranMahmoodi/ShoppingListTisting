package com.soran.shoppingtest.di

import android.content.Context
import androidx.room.Room
import com.soran.shoppingtest.repository.RepositoryImplShopping
import com.soran.shoppingtest.repository.RepositoryShopping
import com.soran.shoppingtest.repository.local.Database
import com.soran.shoppingtest.repository.network.ImageApi
import com.soran.shoppingtest.utils.Constants.BASE_URL
import com.soran.shoppingtest.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    fun providerDatabase(
        @ApplicationContext context: Context
    ): Database = Room.databaseBuilder(
        context,
        Database::class.java,
        DATABASE_NAME
    ).build()


    @Provides
    fun providerRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()


    @Provides
    fun providerShoppingDao(database: Database) = database.getShoppingDao()

    @Provides
    fun providerImageApi(retrofit: Retrofit): ImageApi = retrofit.create(ImageApi::class.java)

    @Provides
    fun providerRepositoryShopping(repositoryImplShopping: RepositoryImplShopping): RepositoryShopping =
        repositoryImplShopping
}