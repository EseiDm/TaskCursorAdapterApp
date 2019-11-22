package gal.uvigo.esei.dm.taskcursoradapterapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MySQLite extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "tasks_db";
    private static final int DATABASE_VERSION = 2;

    public MySQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE task (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name string(255) NOT NULL," +
                "description string(255) NOT NULL)";
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL(sql);
            for (int i = 1 ; i<=100 ; i++){
                String taskName="TASK "+i;
                String description = "Description " +taskName;
                sql = "INSERT INTO task(name, description) VALUES(\""+ taskName+"\", \""+description+"\")";
                sqLiteDatabase.execSQL(sql);
            }
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            Log.wtf(MySQLite.class.getName(), e);
        }finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql  ="UPDATE task SET description = \"Description UPDATED!\"";
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL(sql);
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            Log.wtf(MySQLite.class.getName(), e);
        }finally {
            sqLiteDatabase.endTransaction();
        }
    }
}
