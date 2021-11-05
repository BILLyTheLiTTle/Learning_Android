package learning.android.domain.usecases

/**
 * The use-case abstraction
 */
interface UseCase<T> {
    suspend fun execute(): T
}