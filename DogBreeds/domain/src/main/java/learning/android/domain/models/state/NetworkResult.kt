package learning.android.domain.models.state

/**
 * Wrapper around the network response which will be used as State in the MVI pattern
 */
data class NetworkResult<T>(
    val status: Status,
    val data: T? = null, // Data for success
    val error: Exception? = null, // Data for failure
    val message: String? = null
) {

    companion object {

        fun <T> success(data: T?): NetworkResult<T> {
            return NetworkResult(Status.SUCCESS, data, null,"Success!")
        }

        fun <T> error(e: Exception): NetworkResult<T> {
            return NetworkResult(Status.ERROR, null, e, e.message)
        }

        fun <T> loading(): NetworkResult<T> {
            return NetworkResult(Status.LOADING, null, null, "Loading...")
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
