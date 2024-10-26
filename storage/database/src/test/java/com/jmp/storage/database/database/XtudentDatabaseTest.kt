package com.jmp.storage.database.database

import android.content.Context
import com.jmp.storage.database.XtudentDatabase
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.IOException

class XtudentDatabaseTest {

    private val context: Context = mock(Context::class.java)
    private val database: XtudentDatabase = XtudentDatabase.getInstance(context)

    @After
    @Throws(IOException::class)
    fun teardown() {
        database.close()
    }

    @Test
    fun `database is created successfully`() {
        assertNotNull(database)
    }

    @Test
    fun `DAOs are available`() {
        assertNotNull(database.examDao())
        assertNotNull(database.examQuestionDao())
    }

    @Test
    fun `database is a singleton`() {
        val firstInstance = XtudentDatabase.getInstance(context)
        val secondInstance = XtudentDatabase.getInstance(context)

        assert(firstInstance === secondInstance)
    }
}
