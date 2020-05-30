package com.example.sportstoday;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {
    public static final String WEBSITE_ADDRESS = "website_address";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url  = getIntent().getStringExtra(WEBSITE_ADDRESS);
        if (url == null || url.isEmpty()) finish();

        setContentView(R.layout.activity_webview);
        WebView webView = findViewById(R.id.nyc_poi_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(WebViewActivity.this,"Loading...", Toast.LENGTH_SHORT).show();
            }
        });
        webView.loadUrl(url);
    }
}

