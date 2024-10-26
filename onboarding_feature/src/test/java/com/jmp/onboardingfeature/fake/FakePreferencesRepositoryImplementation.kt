package com.jmp.onboardingfeature.fake

import com.jmp.storage.preferences.domain.repository.PreferencesRepository

abstract class FakePreferencesRepositoryImplementation :
    PreferencesRepository {

    override fun shouldShowOnboarding(): Boolean = true

    abstract override fun saveShouldShowOnboarding(shouldShow: Boolean)
}
