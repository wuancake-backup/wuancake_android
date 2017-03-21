package com.cloud.cloud.test;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloud on 2017/3/21.
 */

public class PersonService {

    private static final String ENCODE = "utf-8";
    private static final String NAMESPACE = null;


    //使用pull写xml
    public static void writePull(Context ctx, List<Person> persons) throws Exception {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.i("hyrz", Environment.getExternalStorageDirectory().getPath());
            XmlSerializer serializer = Xml.newSerializer();
            //设置输出位置
            File file=new File(Environment.getExternalStorageDirectory(),"person.xml");
            serializer.setOutput(new FileOutputStream(file),ENCODE);
            serializer.startDocument(ENCODE,true);
            //根节点
            serializer.startTag(NAMESPACE, "persons");
            for (Person p : persons) {
                serializer.startTag(NAMESPACE,"person");
                serializer.attribute(NAMESPACE,"id",p.getId()+"");
                serializer.startTag(NAMESPACE,"name");
                serializer.text(p.getName());
                serializer.endTag(NAMESPACE,"name");
                serializer.startTag(NAMESPACE,"age");
                serializer.text(p.getAge()+"");
                serializer.endTag(NAMESPACE,"age");
                serializer.endTag(NAMESPACE,"person");
            }
            serializer.endTag(NAMESPACE,"persons");
            serializer.endDocument();
        }
        else
        {
            throw new RuntimeException("sdcard不存在!");
        }
    }
    public static List<Person> getPerson(Context ctx) throws Exception {
        List<Person> persons = new ArrayList<>();
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            XmlPullParser xp=Xml.newPullParser();
            File file=new File(Environment.getExternalStorageDirectory(),"person.xml");
            xp.setInput(new FileInputStream(file),ENCODE);
            //定义person必须在while外面
            Person person=null;
            int eventType=xp.getEventType();
            while (eventType!=XmlPullParser.END_DOCUMENT)
            {
                String tagName=xp.getName();
                switch (eventType)
                {
                    case XmlPullParser.START_TAG:
                        if("person".equals(tagName))
                        {
                            person = new Person();
                            person.setId(Integer.parseInt(xp.getAttributeValue(NAMESPACE,"id")));
                        }
                        else if(person!=null&&"name".equals(tagName))
                        {
                            person.setName(xp.nextText());
                        }
                        else if(person!=null && "age".equals(tagName))
                        {
                            person.setAge(Integer.parseInt(xp.nextText()));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if("person".equals(tagName)) {
                            persons.add(person);
                            person=null;
                        }
                        break;
                }
                eventType=xp.next();
            }
        }
        else
        {
            throw new RuntimeException("sdcard不存在!");
        }
        return persons;
    }
}
