package com.lesmtech.conversation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lesmtech.conversation.entity.Message;
import com.lesmtech.conversation.view.MessageItemView;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {

    @InjectView(R.id.chat)
    ListView chat;

    private List<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        messages = new ArrayList<>();

        //Log in with parse
        ParseUser.logInInBackground("rindt", "onbs9y8r", new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                Log.d("LogIn", "Success");
                getMessages();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private BaseAdapter mChatAdapter = new BaseAdapter() {


        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new MessageItemView(MainActivity.this, messages.get(position));
            } else {
                ((MessageItemView) convertView).setMessage(messages.get(position));
            }
            return convertView;
        }

    };

    private void getMessages() {
        // Get messages from relation
        ParseUser user = ParseUser.getCurrentUser();
        ParseRelation<ParseObject> relation = user.getRelation("Messages");
        relation.getQuery().orderByAscending("createdAt").findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> results, ParseException e) {
                if (e != null) {
                    // There was an error
                } else {
                    // results have all the Posts the current user liked.
                    for (ParseObject object : results) {
                        Message message = new Message(object.getCreatedAt().toString(), object.get("content").toString(), object.getBoolean("sender"));
                        messages.add(message);
//                        Log.d("content", object.get("content").toString()  + ";" + object.getCreatedAt().toString());

                        if (chat.getAdapter() == null) {
                            chat.setAdapter(mChatAdapter);
                        }
                        mChatAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
