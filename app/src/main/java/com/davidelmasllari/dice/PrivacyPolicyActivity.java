package com.davidelmasllari.dice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.davidelmasllari.dice.utils.Constants;
import com.davidelmasllari.dice.utils.LogUtil;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        LogUtil.d("dave_log", "PrivacyPolicyActivity: onCreate: ");
        setTitle(R.string.privacy_policy);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }

        WebView webView = findViewById(R.id.webView);
        final ProgressDialog pd = ProgressDialog.show(this, "", "Loading...",true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if(pd!=null && pd.isShowing())
                {
                    pd.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl) {
                LogUtil.d("dave_log", "PrivacyPolicyActivity: onReceivedError: " + errorCode);
            }
        });
        //webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Constants.PRIVACY_POLICY_LINK);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // app icon in action bar clicked; goto parent activity.
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
