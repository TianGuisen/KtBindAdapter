package kt.ktbindadapter.baseAdapter

class MultiItem<T>(var itemType: Int, var data: T) {
    override fun toString(): String {
        return "MultiItem(itemType=$itemType, data=$data)"
    }
}
