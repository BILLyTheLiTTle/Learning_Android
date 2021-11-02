package learning.android.domain.models.request

import javax.inject.Inject

data class BreedsRequest @Inject constructor(
    val limit: Int,
    val page: Int
)