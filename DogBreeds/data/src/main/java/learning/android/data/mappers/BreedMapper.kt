package learning.android.data.mappers

import learning.android.data.models.BreedModel
import learning.android.domain.models.response.UiBreedModel
import javax.inject.Inject

/**
 * It maps the breed from JSON to the appropriate UI object and reverse
 */
class BreedMapper @Inject constructor() {

    fun toUiBreedImage(image: BreedModel.Image?) = UiBreedModel.UiImage(image?.url ?: "")

    fun toUiBreedModel(breedModel: BreedModel): UiBreedModel {

        fun toUiBreedWeight(weight: BreedModel.Weight) = UiBreedModel.UiWeight(weight.metric)

        fun toUiBreedHeight(height: BreedModel.Height) = UiBreedModel.UiHeight(height.metric)

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

    fun toBreedModel(breedModel: UiBreedModel): BreedModel {
        fun toBreedImage(image: UiBreedModel.UiImage?) = BreedModel.Image(url = image?.url ?: "")

        fun toBreedWeight(weight: UiBreedModel.UiWeight) = BreedModel.Weight(metric = weight.metric)

        fun toBreedHeight(height: UiBreedModel.UiHeight) = BreedModel.Height(metric = height.metric)

        return BreedModel(
            weight = toBreedWeight(breedModel.weight),
            height = toBreedHeight(breedModel.height),
            id = breedModel.id,
            name = breedModel.name ?: "",
            lifeSpan = breedModel.lifeSpan ?: "",
            temperament = breedModel.temperament ?: "",
            origin = breedModel.origin ?: "",
            image = toBreedImage(breedModel.image),
            countryCode = breedModel.countryCode?: "",
            description = breedModel.description ?: ""
        )
    }
}