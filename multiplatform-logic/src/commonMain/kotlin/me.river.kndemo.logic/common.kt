package me.river.kndemo.logic

/**
 * App输入参数，并打印
 */
fun setUser(username:String){
    println("username is :$username")
}

/**
 * 为App提供相应的参数
 */
fun getUser() : String{
    return "Kotlin/Native"
}

/**
 * 获取平台信息
 */
expect fun getPlatform():String