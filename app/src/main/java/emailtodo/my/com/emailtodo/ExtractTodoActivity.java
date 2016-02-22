package emailtodo.my.com.emailtodo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractTodoActivity extends Activity {
    public static String KeyContent = "email_content";
    Pattern pattern_time = Pattern.compile("(([0-9])|([0-1][0-9])|2[0-3]):[0-5][0-9]");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extract_todo);
        String content = getIntent().getStringExtra(KeyContent);
        if (!TextUtils.isEmpty(content)) {
            Matcher matcher = pattern_time.matcher(content);
            if (matcher.find())
            {
                Toast.makeText(ExtractTodoActivity.this, content.substring(matcher.start(), matcher.end()) + "," + content.substring(matcher.end()), Toast.LENGTH_LONG).show();
            }
        }
    }
}
