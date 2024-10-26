package com.jmp.storage.preferences.domain

import com.jmp.storage.preferences.domain.interactor.GetShouldShowOnboardingPreference
import com.jmp.storage.preferences.domain.repository.PreferencesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * Dummy test class for [GetShouldShowOnboardingPreference]
 */
@ExperimentalCoroutinesApi
class GetShouldShowOnboardingPreferenceTest {

    private val repository: PreferencesRepository = mock()
    private val sut: GetShouldShowOnboardingPreference =
        GetShouldShowOnboardingPreference(repository)

    @Test
    fun `repository function shouldShowOnboarding is invoked`() = runTest {
        sut.invoke()
        verify(repository).shouldShowOnboarding()
    }
}
