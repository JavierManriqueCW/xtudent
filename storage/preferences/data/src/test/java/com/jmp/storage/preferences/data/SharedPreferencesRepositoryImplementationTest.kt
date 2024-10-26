package com.jmp.storage.preferences.data

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SharedPreferencesRepositoryImplementationTest {
    
    private val mockManager: SharedPreferencesManager = mock()
    
    private val sut: SharedPreferencesRepositoryImplementation =
        SharedPreferencesRepositoryImplementation(mockManager)

    @Test
    fun `shouldShowOnboarding returns default value when preference is not set`() {
        whenever(mockManager.getBoolean(PREFERENCE, DEFAULT_VALUE)).thenReturn(DEFAULT_VALUE)
        
        assertEquals(
            DEFAULT_VALUE,
            sut.shouldShowOnboarding()
        )
        verify(mockManager).getBoolean(PREFERENCE, DEFAULT_VALUE)
    }

    @Test
    fun `shouldShowOnboarding returns false when preference is set to false`() {
        sut.saveShouldShowOnboarding(NEGATIVE_VALUE)
        
        assertEquals(
            NEGATIVE_VALUE,
            sut.shouldShowOnboarding()
        )
        verify(mockManager).getBoolean(PREFERENCE, DEFAULT_VALUE)
    }

    @Test
    fun `saveShouldShowOnboarding saves POSITIVE_VALUE value`() {
        sut.saveShouldShowOnboarding(POSITIVE_VALUE)
        
        verify(mockManager).setPreferenceBoolean(PREFERENCE, POSITIVE_VALUE)
    }

    @Test
    fun `saveShouldShowOnboarding saves NEGATIVE_VALUE value`() {
        sut.saveShouldShowOnboarding(NEGATIVE_VALUE)
        
        verify(mockManager).setPreferenceBoolean(PREFERENCE, NEGATIVE_VALUE)
    }
    
    companion object {
        const val PREFERENCE = "should_show_onboarding"
        const val DEFAULT_VALUE = true
        const val POSITIVE_VALUE = true
        const val NEGATIVE_VALUE = false
    }
}
