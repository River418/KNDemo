package me.river.kndemo.logic

import platform.UIKit.UIDevice

//iOS
actual fun getPlatform():String{
    return UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}