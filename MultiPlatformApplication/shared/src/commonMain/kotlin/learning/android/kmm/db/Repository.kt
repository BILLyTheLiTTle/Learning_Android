package learning.android.kmm.db

class Repository(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = Database(databaseDriverFactory)

    fun getData(id: Long) = database.selectItem(id)

    fun addData(id: Long, data: String) = database.insertItem(id, data)

    fun updateData(id: Long, data: String) = database.updateItem(id, data)
}