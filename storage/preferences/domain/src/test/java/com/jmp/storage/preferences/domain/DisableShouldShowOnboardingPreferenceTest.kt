package com.jmp.storage.preferences.domain

import com.jmp.storage.preferences.domain.interactor.DisableShouldShowOnboardingPreference
import com.jmp.storage.preferences.domain.repository.PreferencesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * Dummy test class for [DisableShouldShowOnboardingPreference]
 */
@ExperimentalCoroutinesApi
class DisableShouldShowOnboardingPreferenceTest {

    private val repository: PreferencesRepository = mock()
    private val sut: DisableShouldShowOnboardingPreference =
        DisableShouldShowOnboardingPreference(repository)

    @Test
    fun `repository function saveShouldShowOnboarding is invoked correctly`() = runTest {
        sut.invoke()
        verify(repository).saveShouldShowOnboarding(false)
    }
}
