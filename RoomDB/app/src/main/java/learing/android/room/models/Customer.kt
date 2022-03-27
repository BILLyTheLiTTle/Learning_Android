package learing.android.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey
    val id: Long,
    val name: String
)