package emailtodo.my.com.emailtodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractTodoActivity extends Activity {
    public static String KeyContent = "email_content";
    Pattern pattern_time = Pattern.compile("(([0-9])|([0-1][0-9])|2[0-3]):[0-5][0-9]");
    TextView timeText;
    EditText contentText;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extract_todo);
        timeText = (TextView) findViewById(R.id.activity_extract_todo_time_text);
        contentText = (EditText) findViewById(R.id.activity_extract_todo_content_text);
        addButton = (Button) findViewById(R.id.activity_extract_todo_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = timeText.getText().toString();
                String content = contentText.getText().toString();
                if (!TextUtils.isEmpty(time) && !TextUtils.isEmpty(content))
                {
                    String currentTodoList = TodoListActivity.getString(ExtractTodoActivity.this, "todo", "");
                    TodoListActivity.putString(ExtractTodoActivity.this, "todo", currentTodoList  + time + TodoListActivity.ItemDivider + content  + TodoListActivity.LineDivider);
                    startActivity(new Intent(ExtractTodoActivity.this, TodoListActivity.class));
                    finish();
                }
            }
        });

        String content = getIntent().getStringExtra(KeyContent);
        if (!TextUtils.isEmpty(content)) {
            Matcher matcher = pattern_time.matcher(content);
            if (matcher.find())
            {
                //Toast.makeText(ExtractTodoActivity.this, content.substring(matcher.start(), matcher.end()) + "," + content.substring(matcher.end()), Toast.LENGTH_LONG).show();
                timeText.setText(content.substring(matcher.start(), matcher.end()));
                contentText.setText(content.substring(matcher.end()));
            }
        }
    }
}
