package com.jmp.xtudent.core.di.module

import android.content.Context
import androidx.room.Room
import com.jmp.storage.database.XtudentDatabase
import com.jmp.storage.database.di.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object DatabaseTestModule {

    @Provides
    @Singleton
    fun provideInMemoryDatabase(
        @ApplicationContext context: Context
    ): XtudentDatabase =
        Room
            .inMemoryDatabaseBuilder(context, XtudentDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}