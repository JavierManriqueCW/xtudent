package com.jmp.storage.preferences.data

import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class SharedPreferencesManagerTest {
    
    private val sharedPreferences: SharedPreferences = mock(SharedPreferences::class.java)
    private var editor: SharedPreferences.Editor = mock(SharedPreferences.Editor::class.java)

    private var sut: SharedPreferencesManager =
        SharedPreferencesManager(sharedPreferences)

    @Before
    fun setUp() {
        whenever(sharedPreferences.edit()).thenReturn(editor)
    }

    @Test
    fun `set boolean preference saves value in shared preferences`() {
        whenever(editor.putBoolean(KEY, VALUE)).thenReturn(editor)

        sut.setPreferenceBoolean(KEY, VALUE)
        
        verify(editor).putBoolean(KEY, VALUE)
        verify(editor).apply()
    }

    @Test
    fun `get boolean preference returns value`() {
        whenever(sharedPreferences.getBoolean(KEY, DEFAULT_VALUE)).thenReturn(VALUE)

        val result = sut.getBoolean(KEY, DEFAULT_VALUE)

        assert(result == VALUE)
        verify(sharedPreferences).getBoolean(KEY, DEFAULT_VALUE)
    }
    
    companion object {
        private const val KEY = "key"
        private const val VALUE = true
        private const val DEFAULT_VALUE = false
    }
}
