package com.alap.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alap.app3.util.DataUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    //   1.既然是保存第一次数据的。所以，执行调用隐私前，必须拿到是否同意哪个隐私内容了。
    String isPrivacy = DataUtils.get(MainActivity.this, "privacy", "0").toString();
    String isPolicy = DataUtils.get(MainActivity.this, "policy", "0").toString();

//2.有两个隐私，那就需要在全局定义一个变量来确保当前属于那个隐私的
    /**
     * 当前表现的隐私类型:
     * 1:个人信息隐私
     * 2:使用条款
     **/
    private int prviacyType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 注意：一个是用户协议记录状态，一个是隐私协议记录状态。至于那个，你们自己定也可以看我的。
        //      3.拿到状态后，就判断弹出那个协议界面了。
        //没有同意隐私条约的,则弹出隐私条约
        if(isPrivacy.equals("1")&&isPolicy.equals("1")){
            //都同意了，直接跳过隐私弹窗处理
        }else if(isPrivacy.equals("0")){
            prviacyType = 1;
            showPrivacy("privacy.txt", "个人信息隐私");
        }else if(isPrivacy.equals("1")&&isPolicy.equals("0")){
            prviacyType = 2;
            showPrivacy("policy.txt", "使用条款");
        }
    }

    //4.前置内容都设定了，那就开始调用弹出隐私，并且根据调用的隐私内容去加载隐私内容回来并且展示出来。

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void showPrivacy(String privacyFileName, String title) {
        //加载当前要显示的隐私内容文本
        String str = initAssets(privacyFileName);

        //布局ui界面信息
        final View inflate = LayoutInflater.from(this).inflate(R.layout.activity_privacy_policy, null);
        TextView tv_title = (TextView) inflate.findViewById(R.id.tv_title);
        //设置隐私内容抬头
        tv_title.setText(title);
        //显示隐私内容，因为文本布局，需要美观，所以内容用需要使用换行符，但加载回来的内容用\n的话无法真正做到换行，只能在文本中用<br/>作为换行符，然后进行替换成\n
        TextView tv_content = (TextView) inflate.findViewById(R.id.tv_content);
        tv_content.setText(str.replace("<br/>", "\n"));

        //获取同意和退出两个按钮并且添加事件
        TextView btn_exit = (TextView) inflate.findViewById(R.id.btn_exit);
        TextView btn_enter = (TextView) inflate.findViewById(R.id.btn_enter);

        //开始弹出隐私界面
        final Dialog dialog = new AlertDialog
                .Builder(this)
                .setView(inflate)
                .show();
        //对话框弹出后点击或按返回键不消失
        dialog.setCancelable(false);

        WindowManager m = getWindowManager();
        Display defaultDisplay = m.getDefaultDisplay();
        final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (defaultDisplay.getWidth() * 0.90);
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        //退出按钮事件
        btn_exit.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });
        //同意按钮事件
        btn_enter.setOnClickListener(v -> {
            dialog.dismiss();
            if (prviacyType == 1) {
                prviacyType = 2;
                //保存隐私同意状态
                DataUtils.put(MainActivity.this, "privacy", "1");
                //显示下一个隐私内容
                showPrivacy("policy.txt", "使用条款");
            } else if (prviacyType == 2) {
                DataUtils.put(MainActivity.this, "policy", "1");
                //两个隐私内容都确定后，开始执行下一步
            }

        });
    }

    /**
     * 从assets下的txt文件中读取数据
     */
    public String initAssets(String fileName) {
        String str = null;
        try {
            InputStream inputStream = getAssets().open(fileName);
            str = getString(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return str;
    }
}