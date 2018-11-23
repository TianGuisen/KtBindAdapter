package tgs.kttool.util

import com.alibaba.fastjson.JSON

/**
 * 获取最后位置的字符串
 */
fun String.lastStr(): String = this.get(this.length - 1).toString()

fun <T> String.fromJson(clazz: Class<T>) = JSON.parseObject(this, clazz)

inline fun <reified T> String.fromJson() = JSON.parseObject(this, T::class.java)
/**
 * 获取后面i个字符串
 */
fun String.subEnd(i: Int) = this.substring(this.length - i)

/**
 * 获取前面i个字符串
 */
fun String.subStart(i: Int) = this.substring(0, i)

/**
 * 对字符加星号处理：除前面几位和后面几位外，其他的字符以星号代替
 *
 * @param content  传入的字符串
 * @param frontNum 保留前面字符的位数
 * @param endNum   保留后面字符的位数
 */
fun String.getStarStr(content: String, frontNum: Int, endNum: Int): String {

    if (frontNum >= content.length || frontNum < 0) {
        return content
    }
    if (endNum >= content.length || endNum < 0) {
        return content
    }
    if (frontNum + endNum >= content.length) {
        return content
    }
    var starStr = ""
    for (i in 0 until content.length - frontNum - endNum) {
        starStr = "$starStr*"
    }
    return (content.substring(0, frontNum) + starStr + content.substring(content.length - endNum, content.length))

}