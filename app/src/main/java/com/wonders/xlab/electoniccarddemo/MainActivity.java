package com.wonders.xlab.electoniccarddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wonders.xlab.cardbag.CBag;
import com.wonders.xlab.qrscanner.XQrScanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 进入电子会员卡
     * @param view
     */
    public void showCard(View view) {
        CBag.get()
                .setCardSearchModel(new CardSearchModelImpl())
                .start(this);
    }

    /**
     * 电子会员卡SDK中已经依赖了扫一扫,如果需要直接调用扫描条形码功能,则参照如下调用
     * @param view
     */
    public void scan(View view) {
        XQrScanner.getInstance().startForResult(this, 12);
    }
}
