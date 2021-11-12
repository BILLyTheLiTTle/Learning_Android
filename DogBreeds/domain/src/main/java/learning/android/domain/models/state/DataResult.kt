package learning.android.domain.models.state

/**
 * Wrapper for the data which will be used as State in the MVI pattern.
 */
data class DataResult<T>(
    val status: Status,
    val data: T? = null, // Data for success
    val error: Exception? = null, // Data for failure
    val message: String? = null
) {

    companion object {

        fun <T> success(data: T?): DataResult<T> {
            return DataResult(Status.SUCCESS, data, null,"Success!")
        }

        fun <T> error(e: Exception): DataResult<T> {
            return DataResult(Status.ERROR, null, e, e.message)
        }

        fun <T> loading(): DataResult<T> {
            return DataResult(Status.LOADING, null, null, "Loading...")
        }
    }
}
