package com.jmp.examsfeature.domain.xlsx.repository

import android.net.Uri
import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure

interface XlsxRepository {

    suspend fun getExamFromXlsxFile(uri: Uri): Either<Failure, Exam>
}
