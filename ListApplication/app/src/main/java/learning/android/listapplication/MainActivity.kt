package learning.android.listapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import learning.android.listapplication.adapters.delegation.AdapterWithDelegation
import learning.android.listapplication.adapters.inherit.AdapterWithInheritance
import learning.android.listapplication.adapters.simple.MyAdapter
import learning.android.listapplication.data.generateUsers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        Use the implementation of Adapter with Delegation, AdapterWithInheritance
         */
        val adapter = AdapterWithDelegation()
        val users = generateUsers()
        val recyclerView = findViewById<RecyclerView>(R.id.users_recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.users = users

        val sortAscButton = findViewById<Button>(R.id.sort_asc_button)
        sortAscButton.setOnClickListener {
            val sortedUsers = users.sortedBy {
                it.name
            }
            adapter.users = sortedUsers
        }

        val sortDescButton = findViewById<Button>(R.id.sort_desc_button)
        sortDescButton.setOnClickListener {
            val sortedUsers = users.sortedByDescending {
                it.name
            }
            adapter.users = sortedUsers
        }

        /*
        Use the implementation of Adapter with inheritance, AdapterWithInheritance
         */
//        val adapter = AdapterWithInheritance()
//        val users = generateUsers()
//        val recyclerView = findViewById<RecyclerView>(R.id.users_recyclerView)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        adapter.submitList(users)
//
//        val sortAscButton = findViewById<Button>(R.id.sort_asc_button)
//        sortAscButton.setOnClickListener {
//            val sortedUsers = users.sortedBy {
//                it.name
//            }
//            adapter.submitList(sortedUsers)
//        }
//
//        val sortDescButton = findViewById<Button>(R.id.sort_desc_button)
//        sortDescButton.setOnClickListener {
//            val sortedUsers = users.sortedByDescending {
//                it.name
//            }
//            adapter.submitList(sortedUsers)
//        }

        /*
        Use the simple implementation of Adapter, MyAdapter
         */
//        val adapter = MyAdapter()
//        val users = generateUsers()
//        val recyclerView = findViewById<RecyclerView>(R.id.users_recyclerView)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        adapter.submitList(users)
//
//        val sortAscButton = findViewById<Button>(R.id.sort_asc_button)
//        sortAscButton.setOnClickListener {
//            val sortedUsers = users.sortedBy {
//                it.name
//            }
//            adapter.submitList(sortedUsers)
//        }
//
//        val sortDescButton = findViewById<Button>(R.id.sort_desc_button)
//        sortDescButton.setOnClickListener {
//            val sortedUsers = users.sortedByDescending {
//                it.name
//            }
//            adapter.submitList(sortedUsers)
//        }
    }
}