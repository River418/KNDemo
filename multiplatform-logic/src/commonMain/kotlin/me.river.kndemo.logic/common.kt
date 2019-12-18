package me.river.kndemo.logic

import me.river.kndemo.utils.MMKV

/**
 * App输入参数，并打印
 */
fun setUser(username:String){
    println("username is :$username")
    val mmkv = MMKV()
    mmkv.putString("User","username", username)
}

/**
 * 为App提供相应的参数
 */
fun getUser() : String{
    val mmkv = MMKV()
    val name = mmkv.getString("User","username", "Kotlin/Native")
    return name!!
}

/**
 * 获取平台信息
 */
expect fun getPlatform():String