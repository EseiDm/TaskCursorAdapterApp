package gal.uvigo.esei.dm.taskcursoradapterapp;

import android.database.Cursor;

public class TaskFacade {
    private final MySQLite sqlLite;

    public TaskFacade(MySQLite sqLite){
        this.sqlLite = sqLite;
    }

    public Cursor getTasks(){
        String sql = "SELECT * FROM task ORDER BY name asc";
        Cursor cursor = sqlLite.getReadableDatabase().rawQuery(sql,null);
        return cursor;
    }

}
