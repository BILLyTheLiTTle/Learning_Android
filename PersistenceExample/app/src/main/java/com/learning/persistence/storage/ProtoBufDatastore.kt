package com.learning.persistence.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.learning.persistence.UserData
import kotlinx.coroutines.flow.map

class ProtoBufDatastore(ctx: Context) {
    private val dataSource = ctx.userProtoDataStore

    fun retrieve() = dataSource.data.map {
        User(it.firstName, it.lastName)
    }

    suspend fun save(user: User) {
        dataSource.updateData {
            it.toBuilder()
                .setFirstName(user.firstname)
                .setLastName(user.lastname)
                .build()
        }
    }
}

private val Context.userProtoDataStore: DataStore<UserData> by dataStore(
    fileName = "user.pb",
    serializer = UserDataSerializer
)