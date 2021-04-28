package learning.android.domain.usecases

import io.reactivex.rxjava3.core.Single

interface SingleUseCase<T> {
    fun execute(): Single<T>
}