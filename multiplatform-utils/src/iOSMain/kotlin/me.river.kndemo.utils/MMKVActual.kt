package me.river.kndemo.utils

import cocoapods.MMKV.MMKV
import platform.Foundation.NSLog


actual class MMKV actual constructor() {

    private var mmkvInstance: MMKV? = null

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

    actual fun putString(configFileName: String, key: String, value: String) {
        try {
            getSp(configFileName).setString(value, key)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("putString: $configFileName")
        }

    }


    actual fun putInt(configFileName: String, key: String, value: Int) {
        try {
            getSp(configFileName).setInt32(value, key)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("putInt: $configFileName")
        }

    }

    actual fun putLong(configFileName: String, key: String, value: Long) {
        try {
            getSp(configFileName).setInt64(value, key)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("putLong: $configFileName")
        }

    }

    actual fun putFloat(configFileName: String, key: String, value: Float) {
        try {
            getSp(configFileName).setFloat(value, key)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("putFloat: $configFileName")
        }

    }

    actual fun putBoolean(configFileName: String, key: String, value: Boolean) {
        try {
            getSp(configFileName).setBool(value, key)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("putBoolean: $configFileName")
        }

    }

    actual fun remove(configFileName: String, key: String) {
        try {
            getSp(configFileName).removeValueForKey(key)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("remove: $configFileName")
        }

    }

    actual fun clear(configFileName: String) {
        try {
            getSp(configFileName).clearAll()
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("clear: $configFileName")
        }

    }


    actual fun getString(configFileName: String, key: String, defValue: String): String? {
        try {
            return getSp(configFileName).getStringForKey(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("getString: $configFileName")
        }

        return defValue
    }


    actual fun getInt(configFileName: String, key: String, defValue: Int): Int {
        try {
            return getSp(configFileName).getInt32ForKey(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("getInt: $configFileName")
        }

        return defValue
    }

    actual fun getLong(configFileName: String, key: String, defValue: Long): Long {
        try {
            return getSp(configFileName).getInt64ForKey(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("getLong: $configFileName")
        }

        return defValue
    }

    actual fun getFloat(configFileName: String, key: String, defValue: Float): Float {
        try {
            return getSp(configFileName).getFloatForKey(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("getFloat: $configFileName")
        }

        return defValue
    }

    actual fun getBoolean(configFileName: String, key: String, defValue: Boolean): Boolean {
        try {
            return getSp(configFileName).getBoolForKey(key, defValue)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("getBoolean: $configFileName")
        }

        return defValue
    }

    actual fun contains(configFileName: String, key: String): Boolean {
        try {
            return getSp(configFileName).containsKey(key)
        } catch (e: Exception) {
            e.printStackTrace()
            NSLog("contains: $configFileName")
        }
        return false
    }
}


