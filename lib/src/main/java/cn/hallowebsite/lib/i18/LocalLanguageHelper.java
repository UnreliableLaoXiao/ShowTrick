package cn.hallowebsite.lib.i18;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

public final class LocalLanguageHelper {

    private static final String TAG = "LocalLanguageHelper";

    /**
     * 得到当前语言
     * 如果没有自己主动设置的话 返回的是系统当前语言
     * @param context
     * @return 语言 - 国家
     */
    public static String getLocalLanguageStr(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        Locale locale = null;
        //适配高版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = configuration.getLocales().get(0);
        } else {
            locale = configuration.locale;
        }

        //获取语言
        String language = locale.getLanguage();
        //获取国家
        String country = locale.getCountry();
        return language + "-" + country;
    }


    /**
     * 设置本地语言
     * @param context
     * @param locale
     * @return Context
     */
    public static Context setLocalLanguage(Context context, Locale locale) {
        //如果设置的locale为null 则返回本身
        if (locale == null) {
            return context;
        }
        Configuration newConfiguration = new Configuration(context.getResources().getConfiguration());
        Context newContext = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            newConfiguration.setLocale(locale);
            newContext = context.createConfigurationContext(newConfiguration);
        } else {
            newConfiguration.locale = locale;
            context.getResources().updateConfiguration(newConfiguration, context.getResources().getDisplayMetrics());
            newContext = context;
        }
        return newContext;
    }



}
