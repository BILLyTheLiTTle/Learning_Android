package learning.android.kmm.db

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Repository: KoinComponent {

    private val database : Database by inject()// = Database(databaseDriverFactory)

    fun getData(id: Long) = database.selectItem(id)

    fun addData(id: Long, data: String) = database.insertItem(id, data)

    fun updateData(id: Long, data: String) = database.updateItem(id, data)
}