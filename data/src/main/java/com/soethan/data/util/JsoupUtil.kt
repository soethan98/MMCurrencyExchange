package com.soethan.data.util

/*
https://github.com/wyphyoe/android-kyats/blob/master/mm_exchange_android/app/src/main/java/wyp/kyats/component/util/JSoupUtil.java
 */

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException


object JsoupUtil {
    suspend fun getPageData(url: String): Document? {
        try {
            return withContext(Dispatchers.IO) {
                return@withContext Jsoup.connect(url).get()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }


}