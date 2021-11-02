package learning.android.domain.models.request

import javax.inject.Inject

class BreedsRequest @Inject constructor() {
    lateinit var limit: String
    lateinit var page: String
}
