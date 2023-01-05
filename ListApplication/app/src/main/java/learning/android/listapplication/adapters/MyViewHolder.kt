package learning.android.listapplication.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import learning.android.listapplication.R
import learning.android.listapplication.data.User

class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var itemTextView: TextView

    init {
        itemTextView = itemView.findViewById(R.id.textView)
    }

    fun bind(user: User) {
        itemTextView.text = user.name
    }
}