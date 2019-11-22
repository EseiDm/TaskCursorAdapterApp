package gal.uvigo.esei.dm.taskcursoradapterapp;

import android.app.Application;

public class MyApplication extends Application {

    private MySQLite mySQLite = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mySQLite =new MySQLite(MyApplication.this.getBaseContext());
    }

    public MySQLite getMySQLite() {
        return mySQLite;
    }
}
