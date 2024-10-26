package com.jmp.examsfeature.data.xlsx

import android.content.ContentResolver
import android.net.Uri
import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.domain.xlsx.repository.XlsxRepository
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.IOException
import javax.inject.Inject

class XlsxRepositoryImplementation @Inject constructor(
    private val contentResolver: ContentResolver,
    private val mapper: XlsxWorkbookMapper
): XlsxRepository {

    override suspend fun getExamFromXlsxFile(uri: Uri): Either<Failure, Exam> =
        contentResolver.openInputStream(uri)?.use {
            runCatching {
                Either.Success(mapper.mapToExam(WorkbookFactory.create(it)))
            }.fold(
                onSuccess = { it },
                onFailure = { handleExceptions(it) }
            )

        } ?: Either.Error(Failure.CouldNotOpenFile)

    private fun handleExceptions(e: Throwable): Either<Failure, Exam> =
        when (e) {
            is Failure.WrongExamFormatException,
            is RuntimeException -> {
                when (e.cause) {
                    is IOException -> Either.Error(Failure.UnknownError())
                    else -> Either.Error(Failure.WrongExamFormat)
                }
            }
            else -> Either.Error(Failure.UnknownError())
        }.also { e.printStackTrace() }
}
