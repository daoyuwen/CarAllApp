package com.alap.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


//   1. 跳转三方应用

//            核心代码
//    PackageManager packageManager = this.getPackageManager();
//    Intent intent = packageManager.getLaunchIntentForPackage("项目包名");
//    startActivity(intent);
    /**
     * 封装方法
     * @param context     上下文
     * @param packageName 包名
     */
    public void skipOtherApp(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        startActivity(intent);
    }



// 2.   跳转三方应用 + 指定页面（Activity）
//    跳转三方应用的具体界面与上方方式稍有不同，
//    主要通过ComponentName类来帮助打开另一个应用的Activity或者Service等，
//    具体通过Intent.setComponent方法实现。



  //  核心代码

//    Intent intent = new Intent();
//    //这里跳转的是淘宝的启动页
//    ComponentName comp = new ComponentName("com.taobao.taobao", "com.taobao.tao.welcome.Welcome");
//    intent.setComponent(comp);
//    //为三方的activity新开任务栈
//    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    startActivity(intent);

    /**
     * 方法封装
     * @param packageName   包名
     * @param activityClass 跳转界面的activity
     */
    public void skipOtherAppActivity(String packageName, String activityClass) {
        Intent intent = new Intent();
        //ComponentName类主要是用来帮助打开另一个应用的Activity或者Service等，通过Intent.setComponent方法实现
        ComponentName comp = new ComponentName(packageName, activityClass);
        intent.setComponent(comp);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    //3.如何获取手机上已有的app包名与启动页（Activity）？
    //获取手机中安装的app包名、启动Activity

    /**
     * 通过PackageManager获取手机内所有app的包名和启动页（首个启动Activity的类名）
     * 可根据自己业务需求封装方法的返回体，可以是单app信息，也可以是appList
     */
    public void getAllApp(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> appsInfo = packageManager.queryIntentActivities(intent, 0);
        Collections.sort(appsInfo, new ResolveInfo.DisplayNameComparator(packageManager));
        for (ResolveInfo info : appsInfo) {
            String pkg = info.activityInfo.packageName;
            String cls = info.activityInfo.name;
            Log.e("app_info", "pkg:" + pkg + " —— cls:" + cls);
        }
    }
   // 方法封装（或许有点画蛇添足了，就当做下次copy元素吧）
    /**
     * 获取手机app列表
     */
    public List<ResolveInfo> getAllAppList(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> appsInfo = packageManager.queryIntentActivities(intent, 0);
        Collections.sort(appsInfo, new ResolveInfo.DisplayNameComparator(packageManager));
        return appsInfo;
    }

//  实例网站   https://www.yisu.com/zixun/623528.html

}




























