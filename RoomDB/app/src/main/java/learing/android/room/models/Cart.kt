package learing.android.room.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// This class is used for many-to-many relationship

@Entity(primaryKeys = ["customerIdReference", "productIdReference"],
//    foreignKeys = [ForeignKey(
//    entity = Customer::class,
//    parentColumns = ["id"],
//    childColumns = ["customerIdReference"],
//    onUpdate = ForeignKey.CASCADE,
//    onDelete = ForeignKey.CASCADE
//),
//    ForeignKey(
//        entity = Product::class,
//        parentColumns = ["id"],
//        childColumns = ["productIdReference"],
//        onUpdate = ForeignKey.CASCADE,
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class Cart(
    val customerIdReference: Long,
    val productIdReference: Long
)
