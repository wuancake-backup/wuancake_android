package com.example.wch.testweather.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by WCH on 2017/2/9.
 */

/*参考信息
 *
 * String(0) 到 String(4)：省份，城市，城市代码，城市图片名称，最后更新时间。
 * String(5) 到 String(11)：当天的 气温，概况，风向和风力，天气趋势开始图片名称(以下称：图标一)，
 * 天气趋势结束图片名称(以下称：图标二)，现在的天气实况，天气和生活指数。String(12) 到 String(16)：
 * 第二天的 气温，概况，风向和风力，图标一，图标二。String(17) 到 String(21)：
 * 第三天的 气温，概况，风向和风力，图标一，图标二。String(22) 被查询的城市或地区的介绍
 */

/*实际返回数据
 *
 * anyType{string=广东; string=梅州; string=59117; string=59117.jpg; string=2013-5-30 9:47:57;
 * string=25℃/35℃; string=5月30日 晴转多云; string=无持续风向微风; string=0.gif; string=1.gif;
 * string=今日天气实况：气温：29℃；风向/风力：南风 2级；湿度：69%；空气质量：暂无；紫外线强度：暂无;
 * string=穿衣指数：暂无。。。（还有各种指数）。。紫外线指数：暂无。;
 * string=25℃/35℃; string=5月31日 晴; string=无持续风向微风; string=0.gif; string=0.gif;
 * string=25℃/35℃; string=6月1日 晴; string=无持续风向微风; string=0.gif; string=0.gif;
 * string=梅州市位于广东省东北部，东北邻福建省的武平、上杭、永定、平和4县，西北接江西省寻乌县，
 * 西面连广东省河源市的龙川县、东源县、紫金县，西南、南面与汕尾市的陆河县、揭阳市的榕城区、揭西县相接，
 * 东南面和潮州市郊区、饶平县相连。全境地理座标位于东经115。18＇至116。56＇、北纬23。23＇至24。56＇之间，
 * 全市总面积15836平方公里。梅州市属亚热带季风气候区，是南亚热带和中亚热带气候区的过渡地带。平远、
 * 蕉岭和梅县北部为中亚热带气候区南缘，五华、丰顺、兴宁、大埔和平远、蕉岭、梅县南部为南亚热带气候区。
 * 这种地处低纬，近临南海、太平洋和山地的特定地形影响，形成夏日长、冬日短，气温高、冷势悬殊、光照充足、
 * 气流闭塞、雨水丰盈且集中的气候。梅州有较多的名胜古迹和人文景观。始建于唐代的千年古刹灵光寺、阴那山五指峰、
 * 松口元魁塔、泮坑瀑布、蕉岭长潭一线天、兴宁合水水库、神光山、大埔丰溪自然保护区、平远南台山、五指石、五华益塘水库、
 * 丰顺温泉及千佛塔等景点，均以其雄、奇、幽、秀之特色吸引许多游客。历史名流文天祥、祝枝山、韩愈等在这些名胜留下足迹。; }
 */
public class WeatherUtils {
    // 通过WebXml获取天气预报所需要的参数以及实现和解析的方法.
    /** 获取天气的WebServiceURL */
    private static String URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName";
    private static String Arg = "theCityName=";
    /**
     * 获得天气的详细信息
     */
    public static String[] getWeather(String cityName) throws IOException,
            XmlPullParserException {
//        cityName = new String(cityName.getBytes(),"UTF-8");
        cityName = URLEncoder.encode(cityName, "UTF-8");
        InputStream detail = httpRequest.sendGet(URL,Arg+cityName);
        if (detail != null) {// 当前城市有天气信息
            ArrayList<String> list = parseWeather(detail);//解析天气
            String[] result = new String[list.size()];
            list.toArray(result);
            return result;
        }
        return null;
    }

    private static ArrayList<String> parseWeather(InputStream detail) throws XmlPullParserException {
        ArrayList<String> data = new ArrayList<String>();
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput(detail,"UTF-8");
        int eventCode = xmlPullParser.getEventType();
        try {
        while (eventCode != XmlPullParser.END_DOCUMENT) {
            String name = xmlPullParser.getName();
            lable1:
            switch (eventCode) {
                case XmlPullParser.START_DOCUMENT:// 文档开始
                    break;
                case XmlPullParser.START_TAG:// 元素开始.
                    if (name.equalsIgnoreCase("string")) {
                        data.add(xmlPullParser.nextText());
                        break;
                    } else {
                        break lable1;
                    }
                case XmlPullParser.END_TAG:// 元素结束
                    break;
                case XmlPullParser.END_DOCUMENT:// 文档结束
                    break;
                default:
                    break;
            }
            eventCode = xmlPullParser.next();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return data;
    }
}

class httpRequest {

    // return String
    static InputStream sendGet(String url, String param) throws IOException {
        url = url + "?" + param;
        URL realurl = new URL(url);
        HttpURLConnection cn = (HttpURLConnection) realurl.openConnection();

        cn.setRequestMethod("GET");
        cn.setConnectTimeout(6 * 1000);
//        cn.setReadTimeout(6*1000);
        cn.setRequestProperty("contentType", "UTF-8");

        if (cn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return cn.getInputStream();
        }
        return null;
    }
}