package learning.android.domain.models.request

import javax.inject.Inject

/**
 * Contains data which will be used in the request for a list of breeds.
 */
data class BreedsRequest @Inject constructor(
    val limit: Int,
    val page: Int
)