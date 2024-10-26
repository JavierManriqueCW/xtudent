package com.jmp.xtudent.di

import com.jmp.common.usecase.exams.FetchExams
import com.jmp.common.usecase.exams.GetExamsState
import com.jmp.xtudent.core.FetchExamsAdapter
import com.jmp.xtudent.core.GetExamsStateAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SharedUseCaseModule {

    @Provides
    fun provideFetchExams(impl: FetchExamsAdapter): FetchExams = impl

    @Provides
    fun provideGetExamsState(impl: GetExamsStateAdapter): GetExamsState = impl
}
