package kt.ktbindadapter.baseAdapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import kt.ktbindadapter.BR.item

abstract class BaseAdapter<E : Any, VB : ViewDataBinding> internal constructor(datas: MutableList<E>) : RecyclerView.Adapter<BindViewHolder<VB>>() {
    internal var mDatas: MutableList<E> = datas
    internal var itemClickLisener: OnItemClickLisener<E>? = null
    internal var childClickLisener: OnChildClickLisener<E>? = null
    internal var itemLongClickLisener: OnItemLongClickLisener<E>? = null


    fun setChildClickLisener(function: (E, View, Int) -> Unit) {
        this.childClickLisener = object : OnChildClickLisener<E> {
            override fun childClick(bean: E, view: View, position: Int) {
                function(bean, view, position)
            }
        }
    }

    fun setOnItemClickLisener(itemClickLisener: OnItemClickLisener<E>) {
        this.itemClickLisener = itemClickLisener
    }

    fun setOnItemClickLisener(function: (E, View, Int) -> Unit) {
        this.itemClickLisener = object : OnItemClickLisener<E> {
            override fun itemClick(bean: E, view: View, position: Int) {
                function(bean, view, position)
            }
        }
    }

    fun setItemLongClickLisener(function: (E, View, Int) -> Unit) {
        this.itemLongClickLisener = object : OnItemLongClickLisener<E> {
            override fun itemLongClick(bean: E, view: View, position: Int) {
                function(bean, view, position)
            }
        }
    }

    interface OnItemClickLisener<E> {
        fun itemClick(bean: E, view: View, position: Int)
    }

    interface OnChildClickLisener<E> {
        fun childClick(bean: E, view: View, position: Int)
    }

    interface OnItemLongClickLisener<E> {
        fun itemLongClick(bean: E, view: View, position: Int)
    }

    override fun onBindViewHolder(holder: BindViewHolder<VB>, position: Int) {
        val bean = mDatas[position]
        val itemView = holder.binding.getRoot()
        if (itemClickLisener != null) {
            itemView.setOnClickListener {
                itemClickLisener!!.itemClick(bean, holder.binding.getRoot(), position)
            }
        }
        decorator(bean, holder, position)
        //如果是BaseMultiAdapter需要处理一下
        if (mDatas[position] is MultiItem<*>) {
            val pinnedHeaderEntity = mDatas[position] as MultiItem<*>
            holder.binding.setVariable(item, pinnedHeaderEntity.data)
        } else {
            holder.binding.setVariable(item, mDatas[position])
        }
        holder.binding.executePendingBindings()
    }

    abstract fun decorator(bean: E, holder: BindViewHolder<VB>, position: Int)

    open fun remove(position: Int) {
        mDatas.removeAt(position)
        notifyItemRemoved(position)
    }

    open fun clear() {
        mDatas.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

}
