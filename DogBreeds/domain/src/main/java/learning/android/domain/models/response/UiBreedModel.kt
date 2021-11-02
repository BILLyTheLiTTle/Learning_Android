package learning.android.domain.models.response

data class UiBreedModel(
    val weight: UiWeight,
    val height: UiHeight,
    val id: Int,
    val name: String?,
    val lifeSpan: String?,
    val temperament: String?,
    val origin: String?,
    val image: UiImage,
    val countryCode: String?,
    val description: String?) {

    class UiHeight(val metric: String)

    class UiWeight(val metric: String)

    class UiImage(val url: String)
}
