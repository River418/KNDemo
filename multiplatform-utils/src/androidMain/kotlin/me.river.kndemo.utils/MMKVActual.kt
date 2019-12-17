package me.river.kndemo.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.tencent.mmkv.MMKV

/**
 * 对MMKV的封装，保证在多平台应用里可以直接使用
 */
actual class MMKV actual constructor(){

    private val TAG = "MMKVCommon"
    private var mmkvInstance: MMKV? = null

    companion object{
        fun initialize(context: Context){
            MMKV.initialize(context)
        }
    }

    actual constructor(configFileName: String) : this() {
        mmkvInstance = mmkvWithId(configFileName)
    }

    actual constructor(configFileName: String, relativePath: String) : this() {
        mmkvInstance = mmkvWithId(configFileName, relativePath)
    }

    private fun mmkvWithId(configName: String): MMKV {
        mmkvInstance = MMKV.mmkvWithID(configName)
        if (mmkvInstance == null) {
            throw Exception("create mmkv with id $configName failed")
        }
        return mmkvInstance!!
    }


    private fun mmkvWithId(configName: String, relativePath: String): MMKV {
        mmkvInstance = MMKV.mmkvWithID(configName, relativePath)
        if (mmkvInstance == null) {
            throw Exception("create mmkv with id $configName failed")
        }
        return mmkvInstance!!
    }


    private fun getSp(configFileName: String): MMKV {
        if (mmkvInstance == null) {
            mmkvInstance = mmkvWithId(configFileName)
        }
        return mmkvInstance!!
    }

    private fun getEditor(configFileName: String): SharedPreferences.Editor {
        return getSp(configFileName).edit()
    }

    actual fun putString(configFileName: String, key: String, value: String) {
        try {
            getEditor(configFileName).putString(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "putString: $configFileName")
        }

    }


    actual fun putInt(configFileName: String, key: String, value: Int) {
        try {
            getEditor(configFileName).putInt(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "putInt: $configFileName")
        }

    }

    actual fun putLong(configFileName: String, key: String, value: Long) {
        try {
            getEditor(configFileName).putLong(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "putLong: $configFileName")
        }

    }

    actual fun putFloat(configFileName: String, key: String, value: Float) {
        try {
            getEditor(configFileName).putFloat(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "putFloat: $configFileName")
        }

    }

    actual fun putBoolean(configFileName: String, key: String, value: Boolean) {
        try {
            getEditor(configFileName).putBoolean(key, value).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "putBoolean: $configFileName")
        }

    }

    actual fun remove(configFileName: String, key: String) {
        try {
            getEditor(configFileName).remove(key).commit()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "remove: $configFileName")
        }

    }

    actual fun clear(configFileName: String) {
        try {
            getEditor(configFileName).clear().commit()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "clear: $configFileName")
        }

    }


    actual fun getString(configFileName: String, key: String, defValue: String): String? {
        try {
            return getSp(configFileName).getString(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getString: $configFileName")
        }

        return defValue
    }


    actual fun getInt(configFileName: String, key: String, defValue: Int): Int {
        try {
            return getSp(configFileName).getInt(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getInt: $configFileName")
        }

        return defValue
    }

    actual fun getLong(configFileName: String, key: String, defValue: Long): Long {
        try {
            return getSp(configFileName).getLong(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getLong: $configFileName")
        }

        return defValue
    }

    actual fun getFloat(configFileName: String, key: String, defValue: Float): Float {
        try {
            return getSp(configFileName).getFloat(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getFloat: $configFileName")
        }

        return defValue
    }

    actual fun getBoolean(configFileName: String, key: String, defValue: Boolean): Boolean {
        try {
            return getSp(configFileName).getBoolean(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "getBoolean: $configFileName")
        }

        return defValue
    }

    actual fun contains(configFileName: String, key: String): Boolean {
        try {
            return getSp(configFileName).contains(key)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "contains: $configFileName")
        }

        return false
    }
}
