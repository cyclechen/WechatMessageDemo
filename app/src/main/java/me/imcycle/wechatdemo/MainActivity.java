package me.imcycle.wechatdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    WechatReceiver SMSbr = new WechatReceiver();
//
//    MyBRReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //核心部分代码：
//        myReceiver = new MyBRReceiver();
//        IntentFilter itFilter = new IntentFilter();
//        itFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        registerReceiver(myReceiver, itFilter);
//        this.registerReceiver(SMSbr, SMSfilter);

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button btnWechatContact = new Button(this);
        btnWechatContact.setText(getString(R.string.wx_start));
        btnWechatContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getPackageManager().getLaunchIntentForPackage("com.tencent.mm"));
            }
        });
        layout.addView(btnWechatContact);

        Button btnWechatChating = new Button(this);
        btnWechatChating.setText("启动微信聊天");
        btnWechatChating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.chatting.ChattingUI"));
//                startActivity(intent);
            }
        });
        layout.addView(btnWechatChating);
        setContentView(layout);
    }

    @Override
    public void onClick(View view) {

    }

    final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    IntentFilter SMSfilter = new IntentFilter(SMS_RECEIVED);

    private boolean checkSMS() {
        // Sets the sms inbox's URI
        Uri uriSMS = Uri.parse("content://sms");
        Cursor c = getBaseContext().getContentResolver().query(uriSMS, null,
                "read = 0", null, null);
        // Checks the number of unread messages in the inbox
        if (c.getCount() == 0) {
            return false;
        } else
            return true;
    }


    public void startWechat() {
        startActivity(getPackageManager().getLaunchIntentForPackage("com.tencent.mm"));
    }

    //别忘了将广播取消掉哦~
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(myReceiver);
//        unregisterReceiver(SMSbr);
    }
}
