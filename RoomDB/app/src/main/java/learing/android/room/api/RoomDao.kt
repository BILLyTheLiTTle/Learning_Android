package learing.android.room.api

import androidx.room.*
import learing.android.room.models.Customer
import learing.android.room.models.Cart
import learing.android.room.models.Product
import learing.android.room.models.relationships.CustomerWithProductsFromCart
import learing.android.room.models.relationships.CustomerWithCart

@Dao
interface RoomDao {
    @Query("SELECT * FROM Customer")
    fun getCustomers(): List<Customer>
    @Query("SELECT * FROM Product")
    fun getProducts(): List<Product>
    @Query("SELECT * FROM Cart")
    fun getCart(): List<Cart>

    @Insert
    fun insertCustomer(customer: Customer)
    @Insert
    fun insertProduct(product: Product)
    @Insert
    fun insertCart(cart: Cart)

    @Update
    fun updateCustomer(customer: Customer)
    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteCustomer(customer: Customer)
    @Delete
    fun deleteProduct(product: Product)

    // Relationships
    @Transaction
    @Query("SELECT * FROM Customer")
    fun getCustomersAndCart(): List<CustomerWithCart>
    @Transaction
    @Query("SELECT * FROM Customer")
    fun getCustomerWithProductsFromCart(): List<CustomerWithProductsFromCart>
}