package com.dyp.tools.generator;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class HttpUtils {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static String url="http://localhost:8088/dyp-parent/";

    public static String doGet(String url) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);        // 创建http GET请求
        CloseableHttpResponse response = null;   //response 对象
        try {
            //伪装浏览器
            httpGet.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
            response = httpclient.execute(httpGet); // 执行http get请求
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {   // 判断返回状态是否为200
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("内容："+content);

                return content;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {//关闭资源
                response.close();
            }
            httpclient.close();
        }
        return null;
    }

public static String doPost(String url, Map maps) throws IOException {

    CloseableHttpClient httpclient = HttpClients.createDefault(); // 创建Httpclient对象
    HttpPost request = new HttpPost();  // 创建http POST请求
    CloseableHttpResponse response = null;
    List<NameValuePair> nvpL = new ArrayList<NameValuePair>();  //设置参数
    for (Iterator iter = maps.keySet().iterator(); iter.hasNext();) {
        String name = (String) iter.next();
        String value = String.valueOf(maps.get(name));
        nvpL.add(new BasicNameValuePair(name, value));
    }
    try {
        //伪装浏览器
        request.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        request.setURI(new URI(url));
        request.setEntity(new UrlEncodedFormEntity(nvpL, HTTP.UTF_8));
        response = httpclient.execute(request);  // 执行请求
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  // 判断返回状态是否为200
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("内容："+content);
            return content;
        }
    } catch (URISyntaxException | IOException e) {
        e.printStackTrace();
    }finally {
        if (response != null) {
            response.close();
        }
        httpclient.close();
    }
        return null;
}
    public static void main(String[] args) {


        JSONObject jjt=new JSONObject();
        jjt.put("key","dyp");
        jjt.put("code","testTable");
        jjt.put("Field01","test1");
        jjt.put("Field02","test2");
        jjt.put("Field03","test3");
        Map map = jjt;
        String res= null;
        String reqUrl=url+"/marketing/jdbc/setselectTableData";
        try {
            res = HttpUtils.doPost(reqUrl,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);


       /*

         JSONObject jjt=new JSONObject();
        jjt.put("key","dyp");
        jjt.put("code","testTable");
        jjt.put("Field01","test1");
        jjt.put("Field02","test2");
        jjt.put("Field03","test3");
        Map map = jjt;
        String res= null;
        String reqUrl=url+"/marketing/jdbc/setInsertTableData";
        try {
            res = HttpUtils.doPost(reqUrl,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);



        JSONObject jjt=new JSONObject();
        jjt.put("key","dyp");
        jjt.put("code","testTable");
        Map map = jjt;
        String res= null;
        String reqUrl=url+"/marketing/jdbc/setCreateTableF";
        try {
            res = HttpUtils.doPost(reqUrl,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);


       JSONObject jjt=new JSONObject();
        jjt.put("key","dyp");
        jjt.put("tb_code","testTable");
        Map map = jjt;
        String res= null;
        String reqUrl=url+"/dyp/dbTableField/getselectDate";
        try {
            res = HttpUtils.doPost(reqUrl,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);*/



    /* JSONObject jjt=new JSONObject();
        jjt.put("key","dyp");
        jjt.put("tb_code","testTable");
        jjt.put("tbf_code","FieldCode");
        jjt.put("dbef_name","Field03");
        jjt.put("dbcf_name","字段3");
        Map map = jjt;
        String res= null;
        String reqUrl=url+"/dyp/dbTableField/setAddDate";
        try {
            res = HttpUtils.doPost(reqUrl,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);*/


    /*    JSONObject jjt=new JSONObject();
        jjt.put("key","dyp");
        jjt.put("tb_code","testTable");
        Map map = jjt;
        String res= null;
        String reqUrl=url+"/dyp/dbTable/getselectDate";
        try {
            res = HttpUtils.doPost(reqUrl,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);
*/







    /*    JSONObject jjt=new JSONObject();
        jjt.put("key","dyp");
        jjt.put("tb_code","testTable");
        jjt.put("dbet_name","test_name");
        jjt.put("dbct_name","测试表");
        Map map = jjt;
        String res= null;
        String reqUrl=url+"/dyp/dbTable/setAddDate";
        try {
            res = HttpUtils.doPost(reqUrl,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);*/





  /*      JSONObject jjt=new JSONObject();
        jjt.put("key","dyp");
        jjt.put("code","test_table");
        Map map = jjt;
        String res= null;
        String reqUrl=url+"marketing/jdbc/setSelectTable";
        try {
            res = HttpUtils.doPost(reqUrl,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);*/


      /*  String reqUrl=url+"/marketing/jdbc/setSelectTable?key=dyp&code=test_table";
           String res= null;
        try {
            res = HttpUtils.doGet(reqUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(res);*/
    }


}
