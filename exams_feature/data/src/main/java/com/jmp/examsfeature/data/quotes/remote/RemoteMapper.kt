package com.jmp.examsfeature.data.quotes.remote

interface RemoteMapper<M,R> {
    fun mapFromRemote(data: R): M
}
