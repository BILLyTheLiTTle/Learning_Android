package learning.android.domain.usecases

/**
 * The use-case abstraction
 */
interface UseCase<T> {
    fun execute(): T
}