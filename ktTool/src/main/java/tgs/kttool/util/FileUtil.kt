package tg.my.util


import android.os.Environment
import android.text.TextUtils
import java.io.File
import java.io.FilenameFilter
import java.io.IOException
import java.util.*


object FileUtil {
    /**
     * 分隔符.
     */
    val FILE_EXTENSION_SEPARATOR = "."

    /**
     * "/"
     */
    val SEP = File.separator

    /**
     * SD卡根目录
     */
    val SDPATH = Environment.getExternalStorageDirectory().toString() + File.separator

    /**
     * 判断SD卡是否可用
     *
     * @return SD卡可用返回true
     */
    fun hasSdcard(): Boolean {
        val status = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == status
    }


    /**
     * 获取某个目录下的文件名
     *
     * @param dirPath    目录
     * @param fileFilter 过滤器
     * @return 某个目录下的所有文件名
     */
    fun getFileNameList(dirPath: String,
                        fileFilter: FilenameFilter?): List<String> {
        if (fileFilter == null)
            return getFileNameList(dirPath)
        if (TextUtils.isEmpty(dirPath))
            return emptyList()
        val dir = File(dirPath)

        val files = dir.listFiles(fileFilter) ?: return emptyList()

        val conList = ArrayList<String>()
        for (file in files) {
            if (file.isFile)
                conList.add(file.name)
        }
        return conList
    }

    /**
     * 获取某个目录下的文件名
     *
     * @param dirPath 目录
     * @return 某个目录下的所有文件名
     */
    fun getFileNameList(dirPath: String): List<String> {
        if (TextUtils.isEmpty(dirPath))
            return emptyList()
        val dir = File(dirPath)
        val files = dir.listFiles() ?: return emptyList()
        val conList = ArrayList<String>()
        for (file in files) {
            if (file.isFile)
                conList.add(file.name)
        }
        return conList
    }

    /**
     * 获取某个目录下的指定扩展名的文件名称
     *
     * @param dirPath 目录
     * @return 某个目录下的所有文件名
     */
    fun getFileNameList(dirPath: String,
                        extension: String): List<String> {
        if (TextUtils.isEmpty(dirPath))
            return emptyList()
        val dir = File(dirPath)
        val files = dir.listFiles { dir, filename -> if (filename.indexOf(".$extension") > 0) true else false }
                ?: return emptyList()
        val conList = ArrayList<String>()
        for (file in files) {
            if (file.isFile)
                conList.add(file.name)
        }
        return conList
    }

    /**
     * 获得文件的扩展名
     *
     * @param filePath 文件路径
     * @return 如果没有扩展名，返回""
     */
    fun getFileExtension(filePath: String): String? {
        if (TextUtils.isEmpty(filePath)) {
            return filePath
        }
        val extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR)
        val filePosi = filePath.lastIndexOf(File.separator)
        if (extenPosi == -1) {
            return ""
        }
        return if (filePosi >= extenPosi) "" else filePath.substring(extenPosi + 1)
    }

    /**
     * 创建文件
     *
     * @param path 文件的绝对路径
     * @return
     */
    fun createFile(path: String): Boolean {
        return if (TextUtils.isEmpty(path)) false else createFile(File(path))
    }

    /**
     * 创建文件
     *
     * @param file
     * @return 创建成功返回true
     */
    fun createFile(file: File?): Boolean {
        if (file == null || !makeDirs(getFolderName(file.absolutePath)))
            return false
        if (!file.exists())
            try {
                return file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                return false
            }

        return false
    }

    /**
     * 创建目录（可以是多个）
     *
     * @param filePath 目录路径
     * @return 如果路径为空时，返回false；如果目录创建成功，则返回true，否则返回false
     */
    fun makeDirs(filePath: String?): Boolean {
        if (TextUtils.isEmpty(filePath)) {
            return false
        }
        val folder = File(filePath!!)
        return if (folder.exists() && folder.isDirectory)
            true
        else
            folder
                    .mkdirs()
    }

    /**
     * 创建目录（可以是多个）
     *
     * @param dir 目录
     * @return 如果目录创建成功，则返回true，否则返回false
     */
    fun makeDirs(dir: File?): Boolean {
        if (dir == null)
            return false
        return if (dir.exists() && dir.isDirectory) true else dir.mkdirs()
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return 如果路径为空或者为空白字符串，就返回false；如果文件存在，且是文件，
     * 就返回true；如果不是文件或者不存在，则返回false
     */
    fun isFileExist(filePath: String): Boolean {
        if (TextUtils.isEmpty(filePath)) {
            return false
        }
        val file = File(filePath)
        return file.exists() && file.isFile
    }

    /**
     * 获得不带扩展名的文件名称
     *
     * @param filePath 文件路径
     * @return
     */
    fun getFileNameWithoutExtension(filePath: String): String? {
        if (TextUtils.isEmpty(filePath)) {
            return filePath
        }
        val extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR)
        val filePosi = filePath.lastIndexOf(File.separator)
        if (filePosi == -1) {
            return if (extenPosi == -1)
                filePath
            else
                filePath.substring(0,
                        extenPosi)
        }
        if (extenPosi == -1) {
            return filePath.substring(filePosi + 1)
        }
        return if (filePosi < extenPosi)
            filePath.substring(filePosi + 1,
                    extenPosi)
        else
            filePath.substring(filePosi + 1)
    }

    /**
     * 获得文件名
     *
     * @param filePath 文件路径
     * @return 如果路径为空或空串，返回路径名；不为空时，返回文件名
     */
    fun getFileName(filePath: String): String? {
        if (TextUtils.isEmpty(filePath)) {
            return filePath
        }
        val filePosi = filePath.lastIndexOf(File.separator)
        return if (filePosi == -1) filePath else filePath.substring(filePosi + 1)
    }

    /**
     * 获得所在目录名称
     *
     * @param filePath 文件的绝对路径
     * @return 如果路径为空或空串，返回路径名；不为空时，如果为根目录，返回"";
     * 如果不是根目录，返回所在目录名称，格式如：C:/Windows/Boot
     */
    fun getFolderName(filePath: String): String? {
        if (TextUtils.isEmpty(filePath)) {
            return filePath
        }
        val filePosi = filePath.lastIndexOf(File.separator)
        return if (filePosi == -1) "" else filePath.substring(0, filePosi)
    }

    /**
     * 判断目录是否存在
     *
     * @param
     * @return 如果路径为空或空白字符串，返回false；如果目录存在且，确实是目录文件夹，
     * 返回true；如果不是文件夹或者不存在，则返回false
     */
    fun isFolderExist(directoryPath: String): Boolean {
        if (TextUtils.isEmpty(directoryPath)) {
            return false
        }
        val dire = File(directoryPath)
        return dire.exists() && dire.isDirectory
    }

    /**
     * 删除指定文件或指定目录内的所有文件
     *
     * @param path 文件或目录的绝对路径
     * @return 路径为空或空白字符串，返回true；文件不存在，返回true；文件删除返回true；
     * 文件删除异常返回false
     */
    fun deleteFile(path: String): Boolean {
        return if (TextUtils.isEmpty(path)) {
            true
        } else deleteFile(File(path))
    }

    /**
     * 删除指定文件或指定目录内的所有文件
     *
     * @param file
     * @return 路径为空或空白字符串，返回true；文件不存在，返回true；文件删除返回true；
     * 文件删除异常返回false
     */
    fun deleteFile(file: File?): Boolean {
        if (file == null)
            throw NullPointerException("file is null")
        if (!file.exists()) {
            return true
        }
        if (file.isFile) {
            return file.delete()
        }
        if (!file.isDirectory) {
            return false
        }

        val files = file.listFiles() ?: return true
        for (f in files) {
            if (f.isFile) {
                f.delete()
            } else if (f.isDirectory) {
                deleteFile(f.absolutePath)
            }
        }
        return file.delete()
    }

    /**
     * 删除指定目录中特定的文件
     *
     * @param dir
     * @param filter
     */
    fun delete(dir: String, filter: FilenameFilter?) {
        if (TextUtils.isEmpty(dir))
            return
        val file = File(dir)
        if (!file.exists())
            return
        if (file.isFile)
            file.delete()
        if (!file.isDirectory)
            return

        var lists: Array<File>? = null
        if (filter != null)
            lists = file.listFiles(filter)
        else
            lists = file.listFiles()

        if (lists == null)
            return
        for (f in lists) {
            if (f.isFile) {
                f.delete()
            }
        }
    }

    /**
     * 获得文件或文件夹的大小
     *
     * @param path 文件或目录的绝对路径
     * @return 返回当前目录的大小 ，注：当文件不存在，为空，或者为空白字符串，返回 -1
     */
    fun getFileSize(path: String): Long {
        if (TextUtils.isEmpty(path)) {
            return -1
        }
        val file = File(path)
        return if (file.exists() && file.isFile) file.length() else -1
    }
}

