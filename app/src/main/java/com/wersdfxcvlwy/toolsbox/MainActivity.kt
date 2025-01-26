package com.wersdfxcvlwy.toolsbox

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.loadUrl("file:///android_asset/desk.html")
        myWebView.settings.javaScriptEnabled = true
        myWebView.webViewClient = WebViewClient()

        // 添加与 JS 交互的接口
        myWebView.addJavascriptInterface(WebAppInterface(), "Android")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val myWebView: WebView = findViewById(R.id.webview)
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    // 定义接口类，供 JS 调用
    inner class WebAppInterface {
        @JavascriptInterface
        fun launchActivity() {
            try {
                // 显式启动 LineageOS 启动器的活动
                val intent = Intent()
                intent.component = ComponentName(
                    "com.android.launcher3", // LineageOS 启动器的包名
                    "com.android.launcher3.uioverrides.QuickstepLauncher" // 主活动名称
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
