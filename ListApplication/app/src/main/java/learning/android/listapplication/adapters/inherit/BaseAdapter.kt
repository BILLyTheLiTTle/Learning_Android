package learning.android.listapplication.adapters.inherit

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import learning.android.listapplication.adapters.MyViewHolder

abstract class BaseAdapter<T>: RecyclerView.Adapter<MyViewHolder>() {

    open fun submitList(item: T) {
        Log.v("Items (Inheritance)", "$item")
    }
}