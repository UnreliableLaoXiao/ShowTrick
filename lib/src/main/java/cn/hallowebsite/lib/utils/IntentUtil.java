package cn.hallowebsite.lib.utils;

import android.content.Intent;
import android.net.Uri;
import android.provider.Contacts;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

//TODO 待测试
public class IntentUtil {

    /**
     * 拨打电话
     * @param activity
     * @param phoneNumber
     */
    public void CallPhone(AppCompatActivity activity, String phoneNumber){
        Uri uri = Uri.parse("tel:"+phoneNumber);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        activity.startActivity(intent);
    }

    /**
     * 发送短信
     * @param activity
     * @param phoneNumber
     * @param sms_body
     */
    public void SendSms(AppCompatActivity activity, String phoneNumber, String sms_body){
        Uri uri = Uri.parse("smsto:"+phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", sms_body);
        activity.startActivity(intent);
    }

    /**
     * 进入联系人
     * @param activity
     */
    public void IntoPeople(AppCompatActivity activity){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Contacts.People.CONTENT_URI);
        activity.startActivity(intent);
    }

    /**
     * 安装apk
     * @param apkFile
     * @param activity
     */
    public static void installApk(File apkFile, AppCompatActivity activity)
    {
        if (!apkFile.exists())
        {
            return;
        }
        // 通过Intent安装APK文件
        Intent intent = new Intent("android.intent.action.VIEW");
        // 添加默认分类
        intent.addCategory("android.intent.category.DEFAULT");
        // 设置数据和类型 在文件中
        intent.setDataAndType(
                Uri.fromFile(apkFile),
                "application/vnd.android.package-archive");
        // 如果开启的activity 退出的时候 会回调当前activity的onActivityResult
        activity.startActivityForResult(intent, 0);
    }

}
