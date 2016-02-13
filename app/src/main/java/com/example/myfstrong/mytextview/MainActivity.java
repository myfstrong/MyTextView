package com.example.myfstrong.mytextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;

    String s1;
    String s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv4 = (TextView)findViewById(R.id.textView4);
        tv5 = (TextView)findViewById(R.id.textView5);

        //实例1：显示个人联系信息，并能显示超链接
        s1 = "个人主页：http://www.baidu.com\n";
        s1 += "电子邮箱：asdf@tom.com\n";
        s1 += "联系电话：027-12345678";

        tv1.setText(s1);

        //实例2：利用HTML实现超链接
        s2 = "<font color='red'>我的主页是：</font>";
        s2 += "<a href='http://www.baidu.com'>百度</a>";

        Spanned spanned = Html.fromHtml(s2);

        tv2.setText(spanned);
        tv2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}