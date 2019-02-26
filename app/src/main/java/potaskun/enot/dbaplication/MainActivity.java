package potaskun.enot.dbaplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        //Создаем базу данных
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("db_application.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, phone INTEGER)"); //create table USERS
        db.execSQL("INSERT INTO users VALUES ('Stan Smith', 18115555555)");// add new string to able users
        db.execSQL("INSERT INTO users VALUES ('Rodjer Smith', 18115555553)");
        db.execSQL("INSERT INTO users VALUES ('Tom Brune', 18115554533)");
        db.execSQL("INSERT INTO users VALUES ('Bill Smith', 18115355553)");

        /**
         * Cursor - это набор строк в табличном виде
         */
        Cursor query = db.rawQuery("SELECT * FROM users;", null); // create query for select strings from table of users and input this string to variable with name is query have type Cursor

        TextView textView = findViewById(R.id.textView);
        if(query.moveToFirst()){ //перемещаемся в курсоре к первому объекту
            do{

                String name = query.getString(0); //строка[0]
                int phone = query.getInt(1);    //строка[1]
                textView.append("Name: " + name + " Phone: " + phone + "\n");
            }
            while (query.moveToNext());
        }
        query.close();
        db.close();
    }
}
