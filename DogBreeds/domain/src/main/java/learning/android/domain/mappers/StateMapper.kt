package learning.android.domain.mappers

import learning.android.domain.models.state.DataResult
import learning.android.domain.models.state.LocalResult
import learning.android.domain.models.state.NetworkResult
import javax.inject.Inject

/**
 * It maps the state from one to another.
 */
class StateMapper<T> @Inject constructor() {

    fun toDataResult(result: NetworkResult<T>): DataResult<T> {
        return DataResult(
            status = result.status,
            data = result.data,
            error = result.error,
            message = result.message
        )
    }

    fun toDataResult(result: LocalResult<T>): DataResult<T> {
        return DataResult(
            status = result.status,
            data = result.data,
            error = result.error,
            message = result.message
        )
    }
}