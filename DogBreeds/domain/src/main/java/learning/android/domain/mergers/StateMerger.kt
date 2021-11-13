package learning.android.domain.mergers

import learning.android.domain.models.state.DataResult
import learning.android.domain.utils.erLog
import java.lang.UnsupportedOperationException
import javax.inject.Inject

/**
 * It merges to similar states.
 */
class StateMerger<T> @Inject constructor() {

    inline fun <reified T> concat(vararg results: DataResult<T>, priority: Priority = Priority.DATA): DataResult<T> {
        var existsAnyException = false
        var exception = Exception()
        val mData = mutableListOf<T>()
        for (result in results) {
            if (result.error != null && !existsAnyException) {
                existsAnyException = true
                exception = result.error
            }
            if (result.data != null) {
                mData.add(result.data)
            }
        }

        val data: T? =  if (mData[0] is List<*>) {
            (mData as MutableList<List<*>>).flatten()
        } else {
            val exception = UnsupportedOperationException("You passed \"${T::class.simpleName}\". " +
                    "Make sure the results parameter is type parameter of \"DataResult\" is \"List\"")
            erLog(StateMerger::class.simpleName, "concat", exception)
            throw exception
        } as T

        return if (priority == Priority.EXCEPTION && existsAnyException) {
            DataResult.error(exception)
        } else if (priority == Priority.DATA && data != null) {
            DataResult.success(data)
        } else if (data != null) {
            DataResult.success(data)
        } else if (existsAnyException) {
            DataResult.error(exception)
        } else {
            DataResult.error(Exception("Weird data state"))
        }
    }

    /**
     * Define the priority flag for merging.
     * DATA: The result is always success even if any of the data fetching fails
     * EXCEPTION: The result is always error even if any of the data fetching is successful
     */
    enum class Priority {
        DATA,
        EXCEPTION
    }
}