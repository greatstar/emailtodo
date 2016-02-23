package emailtodo.my.com.emailtodo;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import emailtodo.my.com.emailtodo.contract.Info;
import emailtodo.my.com.emailtodo.contract.TodoItem;

public class TodoListAdapter extends BaseAdapter {
    ArrayList<TodoItem> mList = new ArrayList<TodoItem>();
    Context mCtx;

    LayoutInflater inflater = null;

    public TodoListAdapter(Context ctx, ArrayList<TodoItem> list) {
        mCtx = ctx;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            if (inflater == null) {
                inflater = (LayoutInflater) mCtx
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.item_normal, null);
            viewHolder = new ViewHolder();
            viewHolder.titleText = (TextView) convertView
                    .findViewById(R.id.email_title);

            viewHolder.contentText = (TextView) convertView
                    .findViewById(R.id.email_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TodoItem data = mList.get(position);
        viewHolder.titleText.setText(data.title);
        viewHolder.contentText.setText(data.content);

        return convertView;
    }

    class ViewHolder {
        TextView titleText;
        TextView contentText;
    }
}

