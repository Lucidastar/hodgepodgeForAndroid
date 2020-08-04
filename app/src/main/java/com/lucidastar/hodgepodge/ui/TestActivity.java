package com.lucidastar.hodgepodge.ui;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lucidastar.hodgepodge.R;
import com.lucidastar.hodgepodge.adapter.HeaderRecyclerAdapter;

import java.util.Arrays;

public class TestActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HeaderRecyclerAdapter mHeaderRecyclerAdapter;

    private BridgeWebView mBridgeWebView;
    BridgeWebView bridgeWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        mBridgeWebView = (BridgeWebView) findViewById(R.id.bwv_webView);
//        setWebView(mBridgeWebView);
//        mBridgeWebView.loadUrl("https://blog.csdn.net/kui2015/article/details/52249262");
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mHeaderRecyclerAdapter = new HeaderRecyclerAdapter<String>() {
                    @Override
                    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
                        return new TestItemHolder(LayoutInflater.from(TestActivity.this).
                                inflate(R.layout.item_test, parent, false));
                    }

                    @Override
                    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, String data) {

                    }


                }
        );


        mHeaderRecyclerAdapter.addDatas(Arrays.asList("sdf","sdf","sdfas","sdfa"));
        mHeaderRecyclerAdapter.setHeaderView(createHeaderView());
        bridgeWebView.loadUrl("https://blog.csdn.net/kui2015/article/details/52249262");
//        mRecyclerView.addHeaderView(createHeaderView());
//        mHeaderRecyclerAdapter.setOnItemClickListener(new HeaderRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, Object data) {
//                Toast.makeText(TestActivity.this,"点击了",Toast.LENGTH_SHORT).show();
//                bridgeWebView.loadUrl("https://blog.csdn.net/kui2015/article/details/52249262");
//            }
//        });
    }

    public class TestItemHolder extends RecyclerView.ViewHolder {

        public TextView test;
        public TestItemHolder(View itemView) {
            super(itemView);
            test = (TextView) itemView.findViewById(R.id.tv_test);
        }
    }

    private View createHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.item_header, (ViewGroup)findViewById(android.R.id.content),false);
        bridgeWebView = (BridgeWebView) view.findViewById(R.id.bwv_webView);
        setWebView(bridgeWebView);
        bridgeWebView.loadUrl("https://blog.csdn.net/kui2015/article/details/52249262");
        return view;
    }

    private void setWebView(BridgeWebView webView) {
        webView.setDefaultHandler(new DefaultHandler());
        android.webkit.WebSettings setting = webView.getSettings();
        setting.setDomStorageEnabled(true);
        setting.setAppCacheEnabled(false);// 一定不能设置为缓存
        setting.setJavaScriptEnabled(true);
        setting.setAllowFileAccess(true);
        setting.setUseWideViewPort(true);//设定支持viewport

        setting.setLoadWithOverviewMode(true);

        setting.setBuiltInZoomControls(true);
        setting.setDisplayZoomControls(false);
        setting.setSupportZoom(true);
        setting.setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
            }

            @Override
            public void onProgressChanged(android.webkit.WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }



        });

        webView.setWebViewClient(new WebViewClient(){
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String result = request.getUrl().toString();
                Log.i("result", "shouldOverrideUrlLoading: "+result);
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

    }
}
