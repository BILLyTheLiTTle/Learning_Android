package learning.android.domain.models.flow

data class NetworkResult<T>(val status: Status, val data: T? = null, val message: String? = null) {

    companion object {

        fun <T> success(data: T?): NetworkResult<T> {
            return NetworkResult(Status.SUCCESS, data, "Success")
        }

        fun <T> error(msg: String): NetworkResult<T> {
            return NetworkResult(Status.ERROR, null, msg)
        }

        fun <T> loading(): NetworkResult<T> {
            return NetworkResult(Status.LOADING, null, "Loading...")
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
