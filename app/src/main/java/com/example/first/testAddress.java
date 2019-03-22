package com.example.first;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

public class testAddress extends AppCompatActivity {
    private WebView webView;
    private TextView result;
    private Handler handler;

    public static String code_Address;
    public static String result_Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_address);

        result = (TextView) findViewById(R.id.result);

        // WebView 초기화
        init_webView();

        // 핸들러를 통한 JavaScript 이벤트 반응
        handler = new Handler();
    }
    public void init_webView() {
        // WebView 설정
        webView = (WebView) findViewById(R.id.webView);
        // JavaScript 허용
        webView.getSettings().setJavaScriptEnabled(true);
        // JavaScript의 window.open 허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        // 두 번째 파라미터는 사용될 php에도 동일하게 사용해야함
        webView.addJavascriptInterface(new AndroidBridge(), "TestApp");
        // web client 를 chrome 으로 설정
        webView.setWebChromeClient(new WebChromeClient());
        // webview url load
        webView.loadUrl("http://questrip.ivyro.net/getAddress.php");
    }
    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    result.setText(String.format("(%s) %s %s", arg1, arg2, arg3));
                    // WebView를 초기화 하지않으면 재사용할 수 없음
                    code_Address = arg1;
                    result_Address = arg2 + arg3;
                    setresultAddress(result_Address);
                    OnSuccess();
                    init_webView();
                }
            });
        }
    }

    private void OnSuccess() {
        Intent intent =new Intent(getApplicationContext(), MainActivity.class);
        resultAddress Addr = new resultAddress(result_Address, code_Address);
        intent.putExtra("class", Addr);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void setresultAddress(String temp) {
        this.result_Address = temp;
    }

}
