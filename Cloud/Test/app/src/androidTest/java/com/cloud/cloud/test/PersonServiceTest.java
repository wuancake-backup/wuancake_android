package com.cloud.cloud.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import junit.extensions.TestSetup;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Cloud on 2017/3/21.
 */
public class PersonServiceTest {

    private Context mContext;
    private List<Person> persons;
    @Before
    public void setUp()
    {
        mContext=InstrumentationRegistry.getTargetContext();
        persons = new ArrayList<Person>();
        for (int i = 1; i < 100; i++) {
            persons.add(new Person(i,"name"+i,25+i));
        }
    }

    @Test
    public void writePull() throws Exception {
        PersonService.writePull(mContext,persons);
    }
    @Test
    public void getPersons() throws Exception
    {
        List<Person> ps=PersonService.getPerson(mContext);
        Log.i("hyrz",ps.size()+"");
    }

}