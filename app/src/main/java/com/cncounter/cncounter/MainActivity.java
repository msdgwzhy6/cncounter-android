package com.cncounter.cncounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.cncounter.base.BaseActivity;

public class MainActivity extends BaseActivity {

    public final static String EXTRA_MESSAGE = "com.cncounter.cncounter.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStop() {
        //
        String input_text = "";
        EditText edit_message = (EditText) findViewById(R.id.edit_message);
        if(null != edit_message){
            input_text = edit_message.getText().toString();
            Log.d(this.getClass().getPackage().getName(),"onStop(): input_text="+input_text);
        }
        //
        Context context = this;//getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        //
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_text_input_key), input_text);
        editor.commit();
        super.onStop();
    }

    @Override
    protected void onStart() {
        //
        Context activity = this;//getActivity();
        //
        //SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref = activity.getSharedPreferences(
                getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        String input_text = sharedPref.getString(getString(R.string.saved_text_input_key), "");

        Log.d(this.getClass().getPackage().getName(),"onStart(): input_text="+input_text);
        if(null != input_text && false == input_text.trim().isEmpty()){
            EditText edit_message = (EditText) findViewById(R.id.edit_message);
            if(null != edit_message){
                edit_message.setText(input_text);
            }
        }
        //
        super.onStart();
    }

    @Override
    protected void onPause() {
        //
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //
        MenuInflater inflater = super.getMenuInflater();
        //
        inflater.inflate(R.menu.menu_main, menu);
        //
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
            //openSettings();
            return true;
        } else if (id == R.id.action_search) {
            //openSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) super.findViewById(R.id.edit_message);

        String message = editText.getText().toString();
        //
        intent.putExtra(EXTRA_MESSAGE, message);
        super.startActivity(intent);

    }
}
