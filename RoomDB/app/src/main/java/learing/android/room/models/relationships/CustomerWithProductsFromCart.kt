package learing.android.room.models.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import learing.android.room.models.Cart
import learing.android.room.models.Customer
import learing.android.room.models.Product

data class CustomerWithProductsFromCart(
    @Embedded
    val customer: Customer,
    @Relation(
        parentColumn = "id", // customer (customerIdReference)
        entity = Product::class,
        entityColumn = "id", // product (productIdReference)
        associateBy = Junction(Cart::class,
            parentColumn = "customerIdReference", // customer (customerIdReference)
            entityColumn = "productIdReference" // product (productIdReference)
            )
    )
    val product: List<Product>
)
