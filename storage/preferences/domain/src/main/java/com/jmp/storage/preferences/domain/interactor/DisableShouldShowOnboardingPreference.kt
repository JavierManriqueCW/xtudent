package com.jmp.storage.preferences.domain.interactor

import com.jmp.storage.preferences.domain.repository.PreferencesRepository
import javax.inject.Inject

open class DisableShouldShowOnboardingPreference @Inject constructor(
    private val repository: PreferencesRepository
) {

    operator fun invoke() {
        repository.saveShouldShowOnboarding(false)
    }
}
