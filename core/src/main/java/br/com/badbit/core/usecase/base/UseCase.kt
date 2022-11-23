package br.com.badbit.core.usecase.base

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Esse useCase não irá trabalhar com o Paging
 */
abstract class UseCase<in P, R> {

    /**
     * Utilizando a palavra chave operator para quando eu for invocar esse método invoke eu passe
     * diretamente as chaves do bloco de código e com isso não preciso chamar objeto.invoke diretamente
     */
    operator fun invoke(params: P): Flow<ResultStatus<R>> = flow {
        emit(ResultStatus.Loading)
        emit(doWork(params))
    }.catch { throwable ->
        emit(ResultStatus.Error(throwable))
    }

    /**
     * Essa função que realmente fará a chamada da API
     */
    protected abstract suspend fun doWork(params: P): ResultStatus<R>
}

abstract class PagingUseCase<in P, R : Any> {

    operator fun invoke(params: P): Flow<PagingData<R>> = createFlowObservable(params)

    protected abstract fun createFlowObservable(params: P): Flow<PagingData<R>>
}
