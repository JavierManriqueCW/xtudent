package com.jmp.xtudent.core

import com.jmp.storage.preferences.domain.repository.PreferencesRepository
import javax.inject.Inject

class ApplicationScenario @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) {

    fun givenThatTheOnboardingWasCompleted() {
        preferencesRepository.saveShouldShowOnboarding(false)
    }

    fun givenThatTheOnboardingWasNeverCompleted() {
        preferencesRepository.saveShouldShowOnboarding(true)
    }
}
