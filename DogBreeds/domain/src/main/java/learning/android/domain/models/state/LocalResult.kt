package learning.android.domain.models.state

/**
 * Wrapper for the local response.
 */
data class LocalResult<T>(
    val status: Status,
    val data: T? = null, // Data for success
    val error: Exception? = null, // Data for failure
    val message: String? = null
) {

    companion object {

        fun <T> success(data: T?): LocalResult<T> {
            return LocalResult(Status.SUCCESS, data, null,"Success!")
        }

        fun <T> error(e: Exception): LocalResult<T> {
            return LocalResult(Status.ERROR, null, e, e.message)
        }

        fun <T> loading(): LocalResult<T> {
            return LocalResult(Status.LOADING, null, null, "Loading...")
        }
    }
}
