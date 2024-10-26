package com.jmp.onboardingfeature.viewmodel

import com.jmp.onboardingfeature.fake.FakePreferencesRepositoryImplementation
import com.jmp.storage.preferences.domain.interactor.DisableShouldShowOnboardingPreference
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

class OnboardingViewModelTest {

    private val disableShouldShowOnboardingPreference: DisableShouldShowOnboardingPreference = mock(
        DisableShouldShowOnboardingPreference::class.java,
        Mockito.withSettings().useConstructor(mock(FakePreferencesRepositoryImplementation::class.java))
    )

    private val sut: OnboardingViewModel =
        OnboardingViewModel(disableShouldShowOnboardingPreference)

    @Test
    fun `disableShouldShowOnboarding should call use case`() {
        sut.disableShouldShowOnboarding()

        verify(disableShouldShowOnboardingPreference).invoke()
    }
}
