package com.jmp.xtudent.core.di.module

import com.jmp.common.ui.navigation.ActivityNavigator
import com.jmp.xtudent.di.ApplicationModule
import com.jmp.xtudent.navigation.ActivityNavigatorImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ApplicationModule::class]
)
object ApplicationTestModule {

    @Singleton
    @Provides
    fun provideActivityNavigator(
        activityNavigatorImplementation: ActivityNavigatorImplementation
    ) : ActivityNavigator = activityNavigatorImplementation
}
