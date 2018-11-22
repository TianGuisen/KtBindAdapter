package tgs.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup
import kt.ktbindadapter.baseAdapter.BindViewHolder

/**
 * 多type
 * 如果item内容填充较复杂,无法在item的xml中完成,需要在decorator完成填充,就使用这个
 * 或者需要子view的点击事件使用这个
 */
open abstract class BaseMultiAdapter<E : Any> : BaseAdapter<MultiItem<E>, ViewDataBinding> {
    constructor(data: MutableList<MultiItem<E>>) : super(data)

    private val layouts: SparseIntArray = SparseIntArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder<ViewDataBinding> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), layouts.get(viewType), parent, false)
        onCreateViewHolderDecorate(binding, viewType)
        return BindViewHolder(binding.root, binding)
    }

    open fun onCreateViewHolderDecorate(view: ViewDataBinding, viewType: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return mDatas.get(position).itemType
    }

    fun addItemType(type: Int, @LayoutRes layoutResId: Int) {
        layouts.put(type, layoutResId)
    }
}
