package com.handos.demo.easythread;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.AdapterView;
import com.handos.easyutil.android.activity.BaseActivity;
import com.handos.easyutil.android.view.listview.TextListView;

import java.util.*;

/**
 * Created by jimmylee on 5/27/14.
 */
public class ListActivity extends BaseActivity {

    TextListView textListView;


    Map<String, Contact> incomingMap=null;
    Map<String, Contact> outgoingMap=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylistview);
        textListView=(TextListView)findViewById(R.id.textListView);

        //logCallLog();


        textListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               if(i==0)
               {
                   //降序排列
               }
            }
        });

    }

    class Contact{
        String name;
        String phone;

        int count=0;
    }

    private void logCallLog() {
        long dialed=0;
        List<String> list=new ArrayList<String>();

        String sqlWhere=CallLog.Calls.TYPE+"=3";
        Map<String,Contact> map=new HashMap<String,Contact>();
        Cursor c;

        map=getContactMap(sqlWhere);
        int i=0;
        //
        incomingMap=getContactMap(CallLog.Calls.TYPE+"=1");
        Contact maxIncomingContact=getMaxCount(incomingMap);
        if(maxIncomingContact!=null)
        {
            list.add(String.format("最常打电话给你的人是:%s,%s,%s",maxIncomingContact.name,maxIncomingContact.phone,maxIncomingContact.count));
        }

        outgoingMap=getContactMap(CallLog.Calls.TYPE+"=2");
        Contact maxOutGoingContact=getMaxCount(outgoingMap);
        if(maxIncomingContact!=null)
        {
            list.add(String.format("你最常打电话给:%s,%s,%s",maxOutGoingContact.name,maxOutGoingContact.phone,maxOutGoingContact.count));
        }

        Contact maxOutgoingContact=null;


        textListView.setDataSource(list);
    }

    /**
     * 获取count最大，并对其进行排序
     *@param map
     * @return
     */
    private Contact getMaxCount(Map<String, Contact> map){
        Contact maxContact=null;
        Contact[] contactsArray=(Contact[])map.values().toArray();
        Arrays.sort(contactsArray,new ContactComparator());

        return contactsArray[0];
    }

    private Map<String, Contact> getContactMap(String sqlWhere) {
        Map<String, Contact> map =new HashMap<String, Contact>();
        String columns[]=new String[] {
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION,
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.TYPE};
        Cursor c;
        long dialed;
        c = getContentResolver().query(Uri.parse("content://call_log/calls"),columns,
                sqlWhere, null, "Calls._ID DESC"); //last record first
        while (c.moveToNext()) {

            dialed=c.getLong(c.getColumnIndex(CallLog.Calls.DATE));
            String number=c.getString(1);
            int groupCount=0;
            if(map.containsKey(number))
            {
                Contact contact=map.get(number);
                contact.count++;

                map.put(number,contact);
            }else {
                Contact con=new Contact();
                con.name=c.getString(4);
                con.phone=number;
                con.count++;
                map.put(number,con);
            }
        }
        return map;
    }


    class ContactComparator implements Comparator {


        @Override
        public int compare(Object o, Object o2) {
            Contact con=(Contact)o;
            Contact con2=(Contact)o2;
            if(con.count>con2.count)
            {
                return 1;
            }
            return 0;
        }
    }

}
