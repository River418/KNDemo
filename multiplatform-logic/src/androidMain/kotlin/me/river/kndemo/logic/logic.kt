package me.river.kndemo.logic

import android.os.Build

//Android
actual fun getPlatform():String{
    return "Android ${Build.VERSION.RELEASE}"
}