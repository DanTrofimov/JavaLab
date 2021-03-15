package ru.itis.trofimoff.todoapp.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PostRequestTest {
    private static final String endpoint = "https://api.telegram.org/bot1614707727:AAGCxX_J_zCxdJ65mDpkozSxq5lX0Po5JJc/sendDocument?chat_id=406039741";
    private static final String filePath = "D:\\images\\cat.jpg";
    public static void main(String[] args) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(URI.create(endpoint));

        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("document", new FileBody(new File(filePath)));

        httppost.setEntity(reqEntity);

        HttpResponse response = httpclient.execute(httppost);

        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
