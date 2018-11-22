package tgs.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import kt.ktbindadapter.baseAdapter.BindViewHolder

/**
 * 单type
 * 如果item内容填充较复杂,无法在item的xml中完成,需要在decorator完成填充,就使用这个
 * 或者需要子view的点击事件使用这个
 */
open abstract class BaseSigleAdapter<E : Any, VB : ViewDataBinding> : BaseAdapter<E, VB> {

    private var layoutId: Int = 0

    constructor(datas: MutableList<E>, layoutId: Int) : super(datas) {
        this.layoutId = layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder<VB> {
        val binding = DataBindingUtil.inflate<VB>(LayoutInflater.from(parent.context), layoutId, parent, false)
        return BindViewHolder(binding.root, binding)
    }

    fun add(data: E) {
        mDatas.add(data)
        notifyDataSetChanged()
    }

    fun add(position: Int, data: E) {
        mDatas.add(position, data)
        notifyDataSetChanged()
    }

    fun set(data: List<E>) {
        mDatas.clear()
        addAll(data)
    }

    fun addAll(data: List<E>) {
        mDatas.addAll(data)
        notifyDataSetChanged()
    }
}
