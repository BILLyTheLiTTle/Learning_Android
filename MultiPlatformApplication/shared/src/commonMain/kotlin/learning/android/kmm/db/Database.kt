package learning.android.kmm.db

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAll()
        }
    }

    internal fun insertItem(id: Long, content: String) {
        dbQuery.insertItem(
            id = id,
            content = content
        )
    }

    internal fun selectItem(id: Long): Flow<String> {
        val result = dbQuery.selectById(
            id = id
        ).asFlow()
            .mapToOne()
            .map { it.content }
        return result
    }

    internal fun updateItem(id: Long, content: String) {
        dbQuery.updateById(content = content, id = id)
    }
}