package com.jmp.common.usecase.xlsx

import android.net.Uri
import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure

interface GetExamFromXlsxFile {
    suspend operator fun invoke(uri: Uri): Either<Failure, Exam>
}
