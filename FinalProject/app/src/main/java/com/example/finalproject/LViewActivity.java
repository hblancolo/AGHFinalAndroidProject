package com.example.finalproject;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;


public class LViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lview);

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getBaseContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        ListView list = (ListView)findViewById(R.id.listView);
        String[] columns = {"pass"};
        Cursor cursor = db.query("entries",columns,null,null,null,null,null);

        int counter=0;
        String pass;
        ArrayList<String> array = new ArrayList();
        if(cursor.moveToFirst()!=false) {
            pass = cursor.getString(cursor.getColumnIndex("pass"));
            array.add(pass);
        }
        while(true) {
            if(cursor.moveToNext()!=false) {
                pass = cursor.getString(cursor.getColumnIndex("pass"));
                array.add(pass);
                counter++;
            }
            else{
                break;
            }
        }

        String[] larray = new String[array.size()];
        for(int i = 0;i<array.size();i++){
            larray[i]=array.get(i);
        }

        ArrayAdapter ad = new ArrayAdapter<String>(list.getContext(), android.R.layout.simple_list_item_1,larray);
        list.setAdapter(ad);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
