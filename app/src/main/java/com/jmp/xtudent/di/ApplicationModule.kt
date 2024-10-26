package com.jmp.xtudent.di

import com.jmp.common.ui.navigation.ActivityNavigator
import com.jmp.xtudent.navigation.ActivityNavigatorImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideActivityNavigator(
        activityNavigatorImplementation: ActivityNavigatorImplementation
    ) : ActivityNavigator = activityNavigatorImplementation
}
