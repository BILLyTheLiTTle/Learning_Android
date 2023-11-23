package learning.android.kmm.network

sealed class NetworkState<out T>{
    data class Success<T>(val data: T?) : NetworkState<T>()
    data class Error(val message: String, val errorIcon: String) : NetworkState<Nothing>()
}
