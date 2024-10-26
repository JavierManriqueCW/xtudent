package com.jmp.commons.utils.types

import com.jmp.common.utils.R

sealed class Failure(val message: Int) {

    data object NoConnectivityException : Exception()

    data object WrongExamFormatException : Exception()

    class NoConnectivity : Failure(R.string.no_connectivity_error)

    class UnknownError : Failure(R.string.unknown_error)

    data object NoQuotes : Failure(R.string.no_quotes_error)

    data object CouldNotOpenFile : Failure(R.string.open_file_error)

    data object WrongExamFormat : Failure(R.string.wrong_exam_format)

    data object CouldNotSaveExam : Failure(R.string.exam_save_error)

    data object CouldNotGetExamsFromDatabase : Failure(R.string.local_exams_fetch_error)

    data object CouldNotDeleteExamFromDatabase : Failure(R.string.exam_could_not_be_deleted)
}
