package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.alinequintana.clinica_dental.Registro;

public class SQLite_OpenHelper extends SQLiteOpenHelper {

    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table usuarios(Nombre text, " +
                "Usuario text primary key, " +
                "Password text, " +
                "Edad text, " +
                "Direccion text, " +
                "Telefono text);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    //ABRIR BD
    public void abrir() {
        this.getWritableDatabase();
    }

    //CERRAR BD
    public void cerrar() {
        this.close();
    }

    //INSERTAR EN BD
    public void insertarReg(String nom, String usu, String pass, String edad, String dire, String tel) {


        ContentValues valores = new ContentValues();
        valores.put("Nombre", nom);
        valores.put("Usuario", usu);
        valores.put("Password", pass);
        valores.put("Edad", edad);
        valores.put("Direccion", dire);
        valores.put("Telefono", tel);
        this.getWritableDatabase().insert("usuarios", null, valores);


    }


    //VALIDACION DE USUARIO
    public Cursor Validarusupas(String usu, String pass) throws SQLException {
        Cursor mcursor = null;
        mcursor = this.getReadableDatabase().query("usuarios",
                new String[]{"Nombre", "Usuario", "Password", "Edad", "Direccion", "Telefono"},
                "Usuario like'" + usu + "' and Password like '" + pass + "'",
                null, null, null, null);

        return mcursor;
    }

    public static class dbadapter {

        private final Context context;
        private SQLite_OpenHelper DBHelper;
        private SQLiteDatabase db;

        public dbadapter(Context ctx) {
            this.context = ctx;
// TODO Auto-generated constructor stub
        }

        public dbadapter open() throws SQLException {
            DBHelper = new SQLite_OpenHelper(context, "BDClinica", null, 1);
            db = DBHelper.getWritableDatabase();
            return this;
        }


        public Cursor getalldata() throws SQLException
        {
            Cursor mCursor = db.query("usuarios", new String[] {"Nombre","Usuario","Password","Edad","Direccion","Telefono"}
, null, null, null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor;
        }
    }

}



