package com.example.remotelinq;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.progressindicator.LinearProgressIndicator;

public class CustomWebViewClient extends WebViewClient {
    LinearProgressIndicator progressHorizontal; // = findViewById(R.id.progress_horizontal);
    WebView webView;
    Context context;

    public CustomWebViewClient(LinearProgressIndicator progressHorizontal, WebView webView, Context context) {
        this.progressHorizontal = progressHorizontal;
        this.webView = webView;
        this.context=context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (context.getString(R.string.backend_url) .equals(Uri.parse(url).getHost())) {
            // This is my website, so do not override; let my WebView load the page
            return false;
        }

        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
        //        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        //        new Activity().startActivity(intent);

        return false; //should return true, but disabled because of inadequate support from host
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (progressHorizontal.getVisibility() != View.INVISIBLE){
            progressHorizontal.setVisibility(View.INVISIBLE);
            webView.setVisibility(View.VISIBLE);
        }
    }
}
