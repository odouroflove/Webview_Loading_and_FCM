package txt.com.webview_loading;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceError;
import android.widget.TextView;

//import com.yubrajpoudel.R;

/**
 * Created by yubraj on 12/25/15.
 */
public class MainActivity extends Activity {
    private WebView webView;
    ProgressDialog prDialog;
    Button btnGo, btnTest;
    TextView txtContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
//        setActionBar((Toolbar) findViewById(R.id.toolbar));
        btnGo = (Button) findViewById(R.id.btntieptuc);
        btnTest = (Button) findViewById(R.id.btntest);



//        String url = "http://thunghiem.ovn.vn/xuongmay";
//        String url = "http://google.com";
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webView.loadUrl(url);

//        sự kiện click cho button
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.page_news);
                webView = (WebView) findViewById(R.id.wv_news);
                webView.setWebViewClient(new MyWebViewClient());
                // Lấy địa chỉ ở edittext và load webview
//                String url = edtUrl.getText().toString();
//                String url = "http://vksndcc2.gov.vn";
//                String url = "http://phanmemvietshop.com/index.php";
                String url = "https://phanmemvietshop.com/";
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webView.loadUrl(url);
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setContentView(R.layout.activity_test_back);
                doOpenTestbackActivity();
            }
        });

        txtContinue = (TextView) findViewById(R.id.txtcontinue);
        String utxtContinue= getString(R.string.gocontinue);
        SpannableString contentutxtContinue = new SpannableString(utxtContinue);
        contentutxtContinue.setSpan(new UnderlineSpan(), 0, utxtContinue.length(), 0);
        txtContinue.setText(contentutxtContinue);

        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenPageview();
            }
        });



    }

    public void doOpenPageview()
    {
        setContentView(R.layout.page_news);
        webView = (WebView) findViewById(R.id.wv_news);
        webView.setWebViewClient(new MyWebViewClient());
        // Lấy địa chỉ ở edittext và load webview
//                String url = edtUrl.getText().toString();
//                String url = "http://vksndcc2.gov.vn";
//                String url = "http://phanmemvietshop.com/index.php";
        String url = "https://phanmemvietshop.com/";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
//        Uri uri = Uri.parse("https://vksndcc2.org.vn");
//        Intent webviewintent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(webviewintent);
    }

    public void doOpenTestbackActivity()
    {
        Intent doOpenTestbackActivityIntent=new Intent(this, TestBackActivity.class);
        startActivity(doOpenTestbackActivityIntent);
        this.finish();
    }


    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            super.onPageStarted(view, url, favicon);
//            prDialog = new ProgressDialog(MainActivity.this);
//            prDialog.setMessage("Đang tải dữ liệu, Chờ xíu nhé ...");
//            prDialog.show();
//        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(prDialog!=null){
                prDialog.dismiss();
            }
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            prDialog.setMessage("Lỗi mạng hoặc nguồn dữ liệu ...");
            prDialog.show();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }
}