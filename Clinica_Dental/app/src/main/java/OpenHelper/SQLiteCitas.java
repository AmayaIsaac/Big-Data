package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteCitas extends SQLite_OpenHelper {
    public SQLiteCitas(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        String query = "create table citas(Fecha text ," +
                "Hora text);";
        db.execSQL(query);

    }

    //ABRIR BD
    public void abrircitas() {
        this.getWritableDatabase();
    }

    //CERRAR BD
    public void cerrarcitas() {
        this.close();
    }

    //INSERTAR EN BD
    public void insertarRegcitas(String fecha, String hora) {


        ContentValues valores = new ContentValues();
        valores.put("Fecha", fecha);
        valores.put("Hora", hora);
        this.getWritableDatabase().insert("citas", null, valores);


    }

    public static class dbadapter {

        private final Context context;
        private SQLite_OpenHelper DBHelper;
        private SQLiteDatabase db;

        public dbadapter(Context ctx) {
            this.context = ctx;

        }

        public dbadapter open() throws SQLException {
            DBHelper = new SQLite_OpenHelper(context, "BDClinica", null, 1);
            db = DBHelper.getWritableDatabase();
            return this;
        }
    }
}
