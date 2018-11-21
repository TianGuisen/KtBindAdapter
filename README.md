# KtBindAdapter
使用方法
```
//单type,直接创建SimpleAdapter即可,没有item子view的点击事件
val testAdapter = SimpleAdapter(listData, R.layout.item_sigle1)

//单type,没有item子view的点击事件,使用只需要创建SimpleAdapter
val sigle2Adapger= object : BaseSigleAdapter<ItemInfo, ItemSigle2Binding>(listData, R.layout.item_sigle2){
    override fun decorator(bean: ItemInfo, holder: BindViewHolder<ItemSigle2Binding>, position: Int) {
        holder.binding.run {
            tvName.setOnClickListener({ v -> childClickLisener!!.childClick(bean, tvName, position) })
            tvAge.setOnClickListener({ v -> childClickLisener!!.childClick(bean, tvAge, position) })
        }
    }
}

//多type布局,没有item子view的点击事件
val multiAdapter = SimpleMultiAdapter(itemInfos)
//添加布局
multiAdapter.addItemType(TYPE0, R.layout.item_type0)
multiAdapter.addItemType(TYPE1, R.layout.item_type1)

////多type布局,有item子view的点击事件
val multiAdapter = object : BaseMultiAdapter<ItemInfo>(itemInfos) {
    override fun decorator(bean: MultiItem<ItemInfo>, holder: BindViewHolder<ViewDataBinding>, position: Int) {
        when (bean.itemType) {
            TYPE1 -> {
                (holder.binding as ItemType1Binding).run {
                    tvName.setOnClickListener { v -> childClickLisener?.childClick(bean, tvName, position) }
                    tvAge.setOnClickListener { v -> childClickLisener?.childClick(bean, tvAge, position) }
                }
            }
        }
    }
}
//添加布局
multiAdapter.addItemType(TYPE0, R.layout.item_type0)
multiAdapter.addItemType(TYPE1, R.layout.item_type1)
```