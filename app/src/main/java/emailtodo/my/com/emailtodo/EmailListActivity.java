package emailtodo.my.com.emailtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import emailtodo.my.com.emailtodo.contract.EmailList;
import emailtodo.my.com.emailtodo.contract.Info;

public class EmailListActivity extends AppCompatActivity {
    ArrayList<Info> mList;
    Button goToTodoListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_list);
        ListView mListView = (ListView) findViewById(R.id.listview_infos);

        // Read mock data from local files
        String dataString = readAssets("data/MockData.txt");
        // Use Gson lib to parse json data
        EmailList list = new Gson().fromJson(dataString, new TypeToken<EmailList>() {}.getType());
        mList = list.emailList;
        MyAdapter mAdapter = new MyAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        // TODO click item to go to ExtractTodoActivity.class, with extra ExtractTodoActivity.KeyContent -> email content

        // TODO initialize button and click it to go to TodoListActivity
    }


    private String readAssets(String fileName)
    {
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
