package gal.uvigo.esei.dm.taskcursoradapterapp;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySQLite mySQLite = ((MyApplication)getApplication()).getMySQLite();
        Cursor cursor = new TaskFacade(mySQLite).getTasks();

        ListView listView = findViewById(R.id.idListViewTasks);
        MyCursorAdapter myCursorAdapter = new MyCursorAdapter(MainActivity.this.getBaseContext(), cursor);
        listView.setAdapter(myCursorAdapter);
        myCursorAdapter.notifyDataSetChanged();
    }

    private class MyCursorAdapter extends CursorAdapter {

        private LayoutInflater inflater  =null;
        public MyCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            if (inflater==null)
                inflater = LayoutInflater.from(context);
            return inflater.inflate(R.layout.layout_task_list_item, viewGroup, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView textView = view.findViewById(R.id.textViewName);
            textView.setText(cursor.getString(cursor.getColumnIndex("name")));
            textView = view.findViewById(R.id.textViewDescription);
            textView.setText(cursor.getString(cursor.getColumnIndex("description")));
        }
    }
}
