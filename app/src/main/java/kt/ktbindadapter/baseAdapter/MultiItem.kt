package kt.ktbindadapter.baseAdapter

/**
 * 多type的需要用这个包装实体类
 */
class MultiItem<T>(var itemType: Int, var data: T) {
    override fun toString(): String {
        return "MultiItem(itemType=$itemType, data=$data)"
    }
}
