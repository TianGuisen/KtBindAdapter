# KtBindAdapter
实现比较简单,主要运用了databinding的特性实现.
不建议使用，databinding并不那么美好。
### 使用方法    
##### 添加依赖  
[![](https://jitpack.io/v/TianGuisen/KtBindAdapter.svg)](https://jitpack.io/#TianGuisen/KtBindAdapter)
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
```
dependencies {
	implementation 'com.github.TianGuisen:KtBindAdapter:1.1'
}
```
-------
##### item布局
所有item布局中name必须相同,推荐name="item"
```
<data>
        <variable
            name="item"
            type="绑定的实体类"></variable>
	    <TextView
            ...
            android:text="@{item.name}"/>
</data>
```
##### 创建adapter
```
//单type,直接创建SimpleAdapter即可,没有item子view的点击事件
val testAdapter = SimpleAdapter(listData, R.layout.item_sigle1)
//点击事件
testAdapter.setOnItemClickLisener { itemInfo, view, i ->
      
}
```
```
//单type,没有item子view的点击事件,使用只需要创建SimpleAdapter
val sigle2Adapger= object : BaseSigleAdapter<ItemInfo, ItemSigle2Binding>(listData, R.layout.item_sigle2){
    override fun decorator(bean: ItemInfo, holder: BindViewHolder<ItemSigle2Binding>, position: Int) {
        holder.binding.run {
            tvName.setOnClickListener({ v -> childClickLisener!!.childClick(bean, tvName, position) })
            tvAge.setOnClickListener({ v -> childClickLisener!!.childClick(bean, tvAge, position) })
        }
    }
}
```
```
//多type布局,没有item子view的点击事件
val multiAdapter = SimpleMultiAdapter(itemInfos)
//添加布局
multiAdapter.addItemType(TYPE0, R.layout.item_type0)
multiAdapter.addItemType(TYPE1, R.layout.item_type1)
```
```
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
