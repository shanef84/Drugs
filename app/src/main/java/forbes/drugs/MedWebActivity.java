package forbes.drugs;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MedWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_web);
        //Bundle from Medicines activity
        Bundle bundle = getIntent().getExtras();
        final String url = bundle.getString("URL");

        WebView myWebView = (WebView) findViewById(R.id.medwebview);
        myWebView.loadUrl(url);
        myWebView.setWebViewClient(new WebViewClient());
    }
}
