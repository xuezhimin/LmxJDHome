package com.umeng.soexample.httputils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
    public static String get(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
          /*  urlConnection.setRequestMethod("GET");//若果是get请求可以不用配置; 其他请求必须配置
            urlConnection.setConnectTimeout(8000);//设置链接超时间*/
            InputStream inputStream = urlConnection.getInputStream();//获取网络返回的输入流;
            //可拼接的字符串
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp = "";
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp);
                temp = "";
            }
            //这个是网络获取的数据
            String data = stringBuilder.toString();
            return data;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
