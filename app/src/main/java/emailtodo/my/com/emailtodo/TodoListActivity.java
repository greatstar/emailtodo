package emailtodo.my.com.emailtodo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import emailtodo.my.com.emailtodo.contract.EmailList;
import emailtodo.my.com.emailtodo.contract.Info;
import emailtodo.my.com.emailtodo.contract.TodoItem;

public class TodoListActivity extends AppCompatActivity {
    ArrayList<TodoItem> mList = new ArrayList<>();
    private static String KeyPreferenceName = "TodoList";
    public static String LineDivider = ";;;";
    public static String ItemDivider = ",,,";

    // Persist data by SharedPreferences
    public static void putString(Context context, String keyName, String value) {
        if (TextUtils.isEmpty(keyName)) {
            return;
        }

        SharedPreferences pref = context.getSharedPreferences(KeyPreferenceName, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(keyName, value);
        editor.apply();
    }

    public static String getString(Context context, String keyName, String defaultValue) {
        if (TextUtils.isEmpty(keyName)) {
            return defaultValue;
        }

        SharedPreferences pref = context.getSharedPreferences(KeyPreferenceName, 0);
        return pref.getString(keyName, defaultValue);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        ListView mListView = (ListView) findViewById(R.id.listview_infos);

        String dataString = getString(this, "todo", "");
        String[] list = dataString.split(LineDivider);

        for (String s : list) {
            String data[] = s.split(ItemDivider);
            if (data.length == 2) {
                mList.add(new TodoItem(data[0], data[1]));
            }
        }

        TodoListAdapter mAdapter = new TodoListAdapter(this, mList);
        mListView.setAdapter(mAdapter);
    }


    private String readAssets(String fileName) {
        String content = "";
        try {
            InputStream stream = getAssets().open(fileName);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            content = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
            e.printStackTrace();
        }

        return content;
    }
}
