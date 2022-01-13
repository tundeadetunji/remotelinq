package com.example.remotelinq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    LinearProgressIndicator progressIndicator;
    WebView webView;
    FloatingActionButton buttonSettings;

    Handler handlerHideSettingsButton = new Handler();
    private Runnable HideSettingsButton = new Runnable() {
        @Override
        public void run() {
            buttonSettings = findViewById(R.id.buttonSettings);
            buttonSettings.setVisibility(View.GONE);
        }
    };
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_RemoteLinq);

        setContentView(R.layout.activity_main);

        buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });
        //handlerHideSettingsButton.postDelayed(HideSettingsButton, 180000);
        handlerHideSettingsButton.post(HideSettingsButton);

        //progress indicator
        progressIndicator = findViewById(R.id.progressIndicator);
        progressIndicator.setVisibility(View.VISIBLE);


        //webView
        webView = (WebView) findViewById(R.id.webView);
        webView.setVisibility(View.INVISIBLE);
        try {
            webView.clearCache(false);
        } catch (Exception e) {

        }
        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Force links and redirects to open in the WebView instead of in a browser
        CustomWebViewClient webViewClient = new CustomWebViewClient(progressIndicator, webView, getApplicationContext());
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(getString(R.string.backend_url));
    }
}