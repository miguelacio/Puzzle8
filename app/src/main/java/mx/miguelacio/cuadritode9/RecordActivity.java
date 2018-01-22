package mx.miguelacio.cuadritode9;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import mx.miguelacio.cuadritode9.models.Cell;
import mx.miguelacio.cuadritode9.models.Player;
import mx.miguelacio.cuadritode9.utils.AdminSQLiteOpenHelper;
import mx.miguelacio.cuadritode9.utils.PlayersAdapter;

public class RecordActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PlayersAdapter playersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        recyclerView = findViewById(R.id.players_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        AdminSQLiteOpenHelper adminSQL = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase sqlDB = adminSQL.getWritableDatabase();

        ArrayList<Player> players = new ArrayList<>();

        Cursor row = sqlDB.rawQuery("select * from jugadores ", null);
        if (row.moveToFirst()) {
            do {

                Player player = new Player();

                player.setName(row.getString(1));
                player.setConfig(row.getString(2));
                player.setMovements(row.getString(3));
                player.setTime(row.getString(4));

                players.add(player);


            } while (row.moveToNext());
        }

        playersAdapter = new PlayersAdapter(players);
        recyclerView.setAdapter(playersAdapter);

    }
}
