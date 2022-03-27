package learing.android.room.api

import androidx.room.*
import learing.android.room.models.Customer
import learing.android.room.models.Product

@Dao
interface RoomDao {
    @Query("SELECT * FROM Customer")
    fun getCustomers(): List<Customer>
    @Query("SELECT * FROM Product")
    fun getProducts(): List<Product>

    @Insert
    fun insertCustomer(customer: Customer)
    @Insert
    fun insertProduct(product: Product)

    @Update
    fun updateCustomer(customer: Customer)
    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteCustomer(customer: Customer)
    @Delete
    fun deleteProduct(product: Product)
}