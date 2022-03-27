package learing.android.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import learing.android.room.api.TheDB
import learing.android.room.models.Cart
import learing.android.room.models.Customer
import learing.android.room.models.Product

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbDao = TheDB.getInstance(this)?.dataDao()

        val textView = findViewById<TextView>(R.id.query_result)

        // DB pre-actions
        // TODO uncomment all for first run
//        dbDao?.insertCustomer(Customer(1, "Bill"))
//        dbDao?.insertCustomer(Customer(2, "Jim"))
//        dbDao?.insertCustomer(Customer(3, "Takis"))
//        dbDao?.insertProduct(Product(1, "PC"))
//        dbDao?.insertProduct(Product(2, "Mobile Phone"))
//        dbDao?.insertProduct(Product(3, "Football Ball"))
//        dbDao?.insertProduct(Product(4, "Football Gloves"))
//        dbDao?.insertProduct(Product(5, "Captain America Toy"))
//        dbDao?.insertCart(Cart(1, 1))
//        dbDao?.insertCart(Cart(2, 3))
//        dbDao?.insertCart(Cart(3, 5))
//        dbDao?.insertCart(Cart(1, 2))

        // DB actions
        val result = dbDao?.getCustomerWithProductsFromCart()

        // show results to view
        textView.text = result?.joinToString(separator = "\n\n") ?: "EMPTY"
    }
}