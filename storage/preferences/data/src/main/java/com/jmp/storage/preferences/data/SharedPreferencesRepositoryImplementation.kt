package com.jmp.storage.preferences.data

import com.jmp.storage.preferences.domain.repository.PreferencesRepository
import javax.inject.Inject

open class SharedPreferencesRepositoryImplementation @Inject constructor(
    private val manager: SharedPreferencesManager
): PreferencesRepository {

    override fun shouldShowOnboarding(): Boolean =
        manager.getBoolean(
            PREF_SHOULD_SHOW_ONBOARDING,
            true
        )

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        manager.setPreferenceBoolean(
            PREF_SHOULD_SHOW_ONBOARDING,
            shouldShow
        )
    }

    private companion object {
        const val PREF_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
    }
}
