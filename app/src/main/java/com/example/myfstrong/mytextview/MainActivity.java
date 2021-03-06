package com.example.myfstrong.mytextview;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;

    String s1;
    String s2;
    String s3;
    String s4;
    String s5;

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

        //实例2：利用HTML标签实现超链接
        s2 = "<font color='red'>我的主页是：</font>";
        s2 += "<a href='http://www.baidu.com'>百度</a>";

        Spanned spanned = Html.fromHtml(s2);

        tv2.setText(spanned);
        tv2.setMovementMethod(LinkMovementMethod.getInstance());

        //实例3：利用HTML标签插入图片
        s3 = "图标：<img src='ic_launcher'/>";

        //步骤1：实现字符串s3的HTML标签化，并赋值给spanned1（跨分区卷）
        Spanned spanned1 = Html.fromHtml(s3, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String src) {

                Drawable drawable = null;
                try {
                    Field field = R.drawable.class.getField(src);
                    int resourceId = Integer.parseInt(field.get(null).toString());
                    drawable = getResources().getDrawable(resourceId);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                return drawable;
            }
        }, null);

        //步骤2：设置TextView的内容
        tv3.setText(spanned1);

        //实例4.1:实现部分文字点击事件
        s4 = "点击【这里】，显示Toast";

        SpannableString ss = new SpannableString(s4);
        ss.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this,"哈哈哈",Toast.LENGTH_SHORT).show();
            }
        }, 3, 5, Toast.LENGTH_SHORT);

        //实例4.2:实现部分文字设置颜色
        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")),7,9,Toast.LENGTH_SHORT);

        tv4.setText(ss);
        tv4.setMovementMethod(LinkMovementMethod.getInstance());

        //实例5：实现走马灯效果
        s5 = "实例5：实现走马灯效果实例5：实现走马灯效果实例5：实现走马灯效果实例5：实现走马灯效果实例5：实现走马灯效果";
        tv5.setText(s5);

    }
}