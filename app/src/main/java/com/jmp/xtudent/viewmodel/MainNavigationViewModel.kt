package com.jmp.xtudent.viewmodel

import androidx.lifecycle.ViewModel
import com.jmp.common.ui.navigation.Screen
import com.jmp.storage.preferences.domain.interactor.GetShouldShowOnboardingPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainNavigationViewModel @Inject constructor(
    private val getShouldShowOnboardingPreference: GetShouldShowOnboardingPreference
) : ViewModel() {

    fun getStartDestination(): String =
        if (getShouldShowOnboardingPreference())
            Screen.Onboarding.route
        else
            Screen.Main.route
}
