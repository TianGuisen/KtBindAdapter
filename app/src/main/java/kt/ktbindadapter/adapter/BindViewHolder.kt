package kt.ktbindadapter.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

class BindViewHolder<VB>(itemView: View, val binding: VB) : RecyclerView.ViewHolder(itemView)