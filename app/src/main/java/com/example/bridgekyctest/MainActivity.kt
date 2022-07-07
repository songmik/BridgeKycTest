package com.example.bridgekyctest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bridgekyctest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mWebView = binding.webView

        mWebView.webViewClient = WebViewClient()
        mWebView.webChromeClient = WebChromeClient()
        mWebView.settings.javaScriptEnabled = true

        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                val permission = ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA)
                if (permission == PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        request.grant(request.resources)
                    }
                } else {
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CAMERA), 99)
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 99)
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 99)
                }
            }
        }


        mWebView.addJavascriptInterface(BridgeActivity(), "AndroidBridge")
        mWebView.loadUrl("https://kyc-dev.rootone.com/")


    }
}