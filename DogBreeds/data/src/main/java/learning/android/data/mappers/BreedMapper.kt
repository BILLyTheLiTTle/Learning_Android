package learning.android.data.mappers

import learning.android.data.models.BreedModel
import learning.android.domain.models.response.UiBreedModel
import javax.inject.Inject

/**
 * It maps the breed from JSON to the appropriate UI object
 */
class BreedMapper @Inject constructor() {
    fun toUiBreedModel(breedModel: BreedModel): UiBreedModel {

        fun toUiBreedWeight(weight: BreedModel.Weight) = UiBreedModel.UiWeight(weight.metric)

        fun toUiBreedHeight(height: BreedModel.Height) = UiBreedModel.UiHeight(height.metric)

        fun toUiBreedImage(image: BreedModel.Image) = UiBreedModel.UiImage(image.url)

        return UiBreedModel(
            toUiBreedWeight(breedModel.weight),
            toUiBreedHeight(breedModel.height),
            breedModel.id,
            breedModel.name,
            breedModel.lifeSpan,
            breedModel.temperament,
            breedModel.origin,
            toUiBreedImage(breedModel.image),
            breedModel.countryCode,
            breedModel.description
        )
    }
}