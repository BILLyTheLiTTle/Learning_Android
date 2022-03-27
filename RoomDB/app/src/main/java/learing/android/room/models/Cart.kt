package learing.android.room.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Customer::class,
    parentColumns = ["id"],
    childColumns = ["customerIdReference"],
    onUpdate = ForeignKey.CASCADE,
    onDelete = ForeignKey.CASCADE
)]
)
data class Cart(
    @PrimaryKey
    val id: Long,
    val customerIdReference: Long
)
