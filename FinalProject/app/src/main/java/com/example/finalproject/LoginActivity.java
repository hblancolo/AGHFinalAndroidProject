package com.example.finalproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void lClick(View v){
        EditText medit = (EditText)findViewById(R.id.editTextLogin);
        String pass = medit.getText().toString();

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getBaseContext());
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("pass", pass);

        // Insert the new row
        db.insert("entries",null,values);


        if(!pass.equals("pass")){
            EditText etext = (EditText)findViewById(R.id.editTextLogin);
            CharSequence text = "";
            etext.setText(text);

            Context context = getApplicationContext();
            text = "Wrong pass!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{

            SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("isAuthorised", true);
            e.commit();
            Intent i = new Intent(v.getContext(), SecureDataActivity.class);
            startActivity(i);
        }

    }
}
