package com.jmp.common.usecase.quotes

import com.jmp.commons.utils.model.Quote
import com.jmp.commons.utils.types.Either
import com.jmp.commons.utils.types.Failure

interface GetQuotes {
    suspend operator fun invoke(): Either<Failure, List<Quote>>
}
