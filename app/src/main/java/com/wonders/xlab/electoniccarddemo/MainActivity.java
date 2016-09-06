package com.wonders.xlab.electoniccarddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12 && resultCode == RESULT_OK) {
            String result = data.getStringExtra(XQrScanner.EXTRA_RESULT_BAR_OR_CODE_STRING);
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
}
