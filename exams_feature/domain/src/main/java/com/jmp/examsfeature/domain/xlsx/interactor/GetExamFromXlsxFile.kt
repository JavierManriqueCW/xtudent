package com.jmp.examsfeature.domain.xlsx.interactor

import android.net.Uri
import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure
import com.jmp.examsfeature.domain.xlsx.repository.XlsxRepository
import javax.inject.Inject

open class GetExamFromXlsxFile @Inject constructor(
    private val repository: XlsxRepository
) {

    open suspend operator fun invoke(uri: Uri): Either<Failure, Exam> {
        return repository.getExamFromXlsxFile(uri)
    }
}
