package com.jmp.xtudent.viewmodel

import com.jmp.common.ui.navigation.Screen
import com.jmp.storage.preferences.domain.interactor.GetShouldShowOnboardingPreference
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class MainNavigationViewModelTest {

    private val getShouldShowOnboardingPreference: GetShouldShowOnboardingPreference =
        GetShouldShowOnboardingPreference(mock())

    private val sut: MainNavigationViewModel =
        MainNavigationViewModel(getShouldShowOnboardingPreference)

    @Test
    fun `getStartDestination should return Onboarding route when preference is true`() {
        whenever(getShouldShowOnboardingPreference.invoke()).thenReturn(true)

        Assert.assertEquals(
            Screen.Onboarding.route,
            sut.getStartDestination()
        )
    }

    @Test
    fun `getStartDestination should return Main route when preference is false`() {
        whenever(getShouldShowOnboardingPreference.invoke()).thenReturn(false)

        Assert.assertEquals(
            Screen.Main.route,
            sut.getStartDestination()
        )
    }
}
