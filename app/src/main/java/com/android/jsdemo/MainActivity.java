package com.android.jsdemo;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;

/**
 * 主Activity
 */
public class MainActivity extends AppCompatActivity {
    private Button buttonJS;//无参数调用JS按钮
    private Button buttonJSWithArg;//有参数调用JS按钮
    private WebView webViewJS;//显示网页的WebView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonJS = (Button) findViewById(R.id.buttonJS);
        buttonJSWithArg = (Button) findViewById(R.id.buttonJSWithArg);
        webViewJS = (WebView) findViewById(R.id.webViewJS);

        initEvent();
    }

    /**
     * 初始化事件
     */
    private void initEvent(){
        buttonJS.setOnClickListener(JS);
        buttonJSWithArg.setOnClickListener(JSWithArg);
    }

    /**
     * 按钮监听器
     */
    private View.OnClickListener JS = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            webViewJS.loadUrl("javascript:androidcalljs()");
        }
    };

    /**
     * 按钮监听器
     */
    private View.OnClickListener JSWithArg = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            webViewJS.loadUrl("javascript:androidcalljs(" + "'Android调用了JS的带参函数'" + ")");
        }
    };

    /**
     * JS调用无参函数
     * 由于安全原因 需要加 @JavascriptInterface
     */
    @JavascriptInterface
    public void startFunction(){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this).setMessage("JS调用Android无参数代码").show();
            }
        });
    }

    /**
     * JS调用有参函数
     * @param text
     */
    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this).setMessage(text).show();
            }
        });
    }
}
