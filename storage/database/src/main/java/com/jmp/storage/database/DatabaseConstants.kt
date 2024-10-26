package com.jmp.storage.database

object DatabaseConstants {
    const val EXAMS_TABLE = "exams"
    const val QUESTIONS_TABLE = "questions"
    const val GET_EXAMS = "SELECT * FROM $EXAMS_TABLE"
    const val CLEAR_EXAMS = "DELETE FROM $EXAMS_TABLE"
}
