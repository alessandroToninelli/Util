package com.toninelli.fileutil

import android.util.Log

object ALog {

    val DEBUG_MODE = true
    private val enableLog = DEBUG_MODE
    private val enableVerboseLog = DEBUG_MODE
    private val enableDebugLog = DEBUG_MODE
    private val writeLogToFile = false
    val TAG = "logger"

    fun v(msg: String) {
        if (enableVerboseLog) {
            Log.v(TAG, msg)
        }
    }

    fun i(msg: String) {
        if (enableLog) {
            Log.i(TAG, msg)
        }
    }

    fun e(vararg objects: Any?) {
        if (enableLog) {
            val fullClassName = Thread.currentThread().stackTrace[4].className
            val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
            val methodName = Thread.currentThread().stackTrace[4].methodName
            val lineNumber = Thread.currentThread().stackTrace[4].lineNumber
            val finalMessage =
                className + "." + methodName + "():Line:" + lineNumber + ":" + str(*objects)
            Log.e(TAG, finalMessage)
        }
    }

    fun d(vararg objects: Any?) {
        if (enableLog) {
            val fullClassName = Thread.currentThread().stackTrace[4].className
            val className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
            val methodName = Thread.currentThread().stackTrace[4].methodName
            val lineNumber = Thread.currentThread().stackTrace[4].lineNumber
            val finalMessage =
                className + "." + methodName + "():Line:" + lineNumber + ":" + str(*objects)
            Log.d(TAG, finalMessage)
        }
    }

    private fun str(vararg objects: Any?): String {
        val stringBuilder = StringBuilder()
        objects.forEach { stringBuilder.append(it ?: "null") }
        return stringBuilder.toString()
    }
}

fun logd(vararg obj: Any?) = ALog.d(*obj)
fun loge(vararg obj: Any?) = ALog.e(*obj)
fun logv(msg: String) = ALog.v(msg)
fun logi(msg: String) = ALog.i(msg)
