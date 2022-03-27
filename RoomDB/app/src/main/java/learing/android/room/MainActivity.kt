package learing.android.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import learing.android.room.api.TheDB
import learing.android.room.models.Customer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbDao = TheDB.getInstance(this)?.dataDao()

        val textView = findViewById<TextView>(R.id.query_result)

        // DB actions
        dbDao?.insertCustomer(Customer(1, "Bill"))
        val result = dbDao?.getCustomers()

        // show results to view
        textView.text = result.toString()
    }
}