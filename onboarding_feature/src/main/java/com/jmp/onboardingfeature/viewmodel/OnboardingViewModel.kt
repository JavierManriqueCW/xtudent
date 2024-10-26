package com.jmp.onboardingfeature.viewmodel

import androidx.lifecycle.ViewModel
import com.jmp.storage.preferences.domain.interactor.DisableShouldShowOnboardingPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class OnboardingViewModel @Inject constructor(
    private val disableShouldShowOnboardingPreference: DisableShouldShowOnboardingPreference
) : ViewModel() {

    fun disableShouldShowOnboarding() = disableShouldShowOnboardingPreference()
}
