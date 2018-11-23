package tgs.kttool.util

import android.content.Context
import tgs.kttool.ToolApplication.toolAppContext
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


const val SPFileName = "Setting"
/**
 * 使用defSP的话key为""在SPUtil中key会赋值成变量名
 * 所以一般用这个
 */
inline fun <reified R, T> R.defSP(default: T) = defSP("", default)

/**
 * 指定key用这个
 */
inline fun <reified R, T> R.defSP(key: String, default: T) = SPUtil(key, default, R::class.java.name)

class SPUtil<T>(val key: String, val defValue: T, val fileName: String = SPFileName) : ReadWriteProperty<Any?, T> {
    val sp by lazy {
        toolAppContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val temKey = if (key.isEmpty()) property.name else key
        return when (defValue) {
            is String -> sp.getString(temKey, defValue)
            is Boolean -> sp.getBoolean(temKey, defValue)
            is Int -> sp.getInt(temKey, defValue)
            is Float -> sp.getFloat(temKey, defValue)
            is Long -> sp.getLong(temKey, defValue)
            else -> throw IllegalArgumentException("类型错误")
        } as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val temKey = if (key.isEmpty()) property.name else key
        with(sp.edit()) {
            when (value) {
                is String -> putString(temKey, value)
                is Boolean -> putBoolean(temKey, value)
                is Int -> putInt(temKey, value)
                is Float -> putFloat(temKey, value)
                is Long -> putLong(temKey, value)
                else -> throw IllegalArgumentException("类型错误")
            }
            commit()
        }
    }
}

fun cleanSP(fileName: String = SPFileName) {
    toolAppContext.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit().clear().commit()
}
