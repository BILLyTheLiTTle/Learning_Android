package learing.android.room.models.relationships

import androidx.room.Embedded
import androidx.room.Relation
import learing.android.room.models.Cart
import learing.android.room.models.Customer

data class CustomerWithCart(
    @Embedded
    val customer: Customer,
    @Relation(
        parentColumn = "id",
        entityColumn = "customerIdReference"
    )
    val cart: List<Cart> // With a Cart is one-to-one, with a List<Cart> is one-to-many
)
