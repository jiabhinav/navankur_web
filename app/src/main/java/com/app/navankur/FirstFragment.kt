package com.app.navankur

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.app.navankur.constant.Config.BASE_URL
import com.app.navankur.databinding.FragmentFirstBinding
import dmax.dialog.SpotsDialog


class FirstFragment : Fragment() {

     var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callPrivacy()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun callPrivacy() {

        binding.progress.visibility=View.VISIBLE
        binding.webview.webViewClient = MyWebViewClient()
        binding.webview.loadUrl(BASE_URL)
        binding.webview.settings.javaScriptEnabled = true



    }


    inner class MyWebViewClient internal constructor() : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progress.visibility=View.GONE
        }


        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
           // Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
            binding.progress.visibility=View.GONE
        }
    }



}