package learning.android.domain.usecases

import kotlinx.coroutines.flow.Flow
import learning.android.domain.models.flow.NetworkResult

interface UseCase<T> {
    suspend fun execute(): T
}