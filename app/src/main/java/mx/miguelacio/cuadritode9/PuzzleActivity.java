package mx.miguelacio.cuadritode9;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.miguelacio.cuadritode9.models.Board;
import mx.miguelacio.cuadritode9.models.Cell;
import mx.miguelacio.cuadritode9.utils.AdminSQLiteOpenHelper;
import mx.miguelacio.cuadritode9.utils.PuzzleAdapter;

public class PuzzleActivity extends AppCompatActivity  {

    GridView gridView;
    int turn = -1;
    String firstMovePlace;
    String secondMovePlace;
    String firstMoveValue;
    String secondMoveValue;
    boolean canMovementBeMade= false;

    Board board = new Board();
    PuzzleAdapter gridAdapter;
    int movements = 0;
    TextView textViewMovements, textViewTime;
    String name, config;
    int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        gridView = findViewById(R.id.grid_view);
        textViewMovements = findViewById(R.id.text_view_movements);
        textViewTime = findViewById(R.id.text_view_time);
        board.populateArray();

        gridAdapter = new PuzzleAdapter(board.getCellArrayList(), PuzzleActivity.this);

        gridView.setAdapter(gridAdapter);

        name = getIntent().getStringExtra("name");
        config = getIntent().getStringExtra("config");


        final Handler h = new Handler();
        final Runnable r = new Runnable() {

            @Override
            public void run() {
                count++;
                textViewTime.setText("Tiempo transcurrido: "+count);
                h.postDelayed(this, 1000); //ms
            }
        };
        h.postDelayed(r, 1000);
    }



    public void presion(View view) {

        String tag = view.getTag(R.string.tag).toString();
        String value = view.getTag(R.string.value).toString();

       if (turn == -1){
           if (value.equals(" ")) {
               firstMovePlace = tag;
               firstMoveValue = value;
               turn = 1;
           } else {
               Toast.makeText(this, "Empieza por el lugar vacio", Toast.LENGTH_SHORT).show();
           }


       } else {
           secondMovePlace = tag;
           secondMoveValue = value;
           //A1
           if (secondMovePlace.equals("A1") && firstMovePlace.equals("A2") ||
                   secondMovePlace.equals("A1") && firstMovePlace.equals("B1")){
               canMovementBeMade = true;
           }
           //B1
           else if (secondMovePlace.equals("B1") && firstMovePlace.equals("A1") ||
                   secondMovePlace.equals("B1") && firstMovePlace.equals("C1") ||
                   secondMovePlace.equals("B1") && firstMovePlace.equals("B2")) {
               canMovementBeMade = true;
           }
           //C1
           else if (secondMovePlace.equals("C1") && firstMovePlace.equals("B1") ||
                   secondMovePlace.equals("C1") && firstMovePlace.equals("C2")) {
               canMovementBeMade = true;
           }
           //A2
           else if (secondMovePlace.equals("A2") && firstMovePlace.equals("A1") ||
                   secondMovePlace.equals("A2") && firstMovePlace.equals("B2") ||
                   secondMovePlace.equals("A2") && firstMovePlace.equals("A3")) {
               canMovementBeMade = true;
           }
           //B2
           else if (secondMovePlace.equals("B2") && firstMovePlace.equals("A2") ||
                   secondMovePlace.equals("B2") && firstMovePlace.equals("B3") ||
                   secondMovePlace.equals("B2") && firstMovePlace.equals("C2") ||
                   secondMovePlace.equals("B2") && firstMovePlace.equals("B1")) {
               canMovementBeMade = true;
           }
           //C2
           else if (secondMovePlace.equals("C2") && firstMovePlace.equals("C1") ||
                   secondMovePlace.equals("C2") && firstMovePlace.equals("B2") ||
                   secondMovePlace.equals("C2") && firstMovePlace.equals("C3")) {
               canMovementBeMade = true;
           }
           //A3
           else if (secondMovePlace.equals("A3") && firstMovePlace.equals("A2") ||
                   secondMovePlace.equals("A3") && firstMovePlace.equals("B3")){
               canMovementBeMade = true;
           }
           //B3
           else if (secondMovePlace.equals("B3") && firstMovePlace.equals("A3") ||
                   secondMovePlace.equals("B3") && firstMovePlace.equals("C3") ||
                   secondMovePlace.equals("B3") && firstMovePlace.equals("B2")) {
               canMovementBeMade = true;
           }
           //C3
           else if (secondMoveValue.equals("C3") && firstMovePlace.equals("B3") ||
                   secondMoveValue.equals("C3") && firstMovePlace.equals("C2")) {
               canMovementBeMade = true;
           }
           if (canMovementBeMade){
              ArrayList<Cell> cells = board.getCellArrayList();

               board.updateArray(cells, firstMoveValue, secondMoveValue);

               gridAdapter = new PuzzleAdapter(board.getCellArrayList(), PuzzleActivity.this);

               gridView.setAdapter(gridAdapter);

               canMovementBeMade = false;

               movements = movements + 1;

               textViewMovements.setText("Número de movimientos: " + movements);

               checkIfSolved(board.getCellArrayList(), config);


           } else {
               Toast.makeText(this, "Movimiento inválido", Toast.LENGTH_SHORT).show();
           }

           turn = -1;
       }


    }

    private void checkIfSolved(ArrayList<Cell> cellArrayList, String config) {

        ArrayList<Cell> forma1 = new ArrayList<>();

        forma1.add(new Cell("1"));
        forma1.add(new Cell("2"));
        forma1.add(new Cell("3"));
        forma1.add(new Cell("4"));
        forma1.add(new Cell("5"));
        forma1.add(new Cell("6"));
        forma1.add(new Cell("7"));
        forma1.add(new Cell("8"));
        forma1.add(new Cell(" "));

        ArrayList<Cell> forma2 = new ArrayList<>();

        forma2.add(new Cell("1"));
        forma2.add(new Cell("2"));
        forma2.add(new Cell("3"));
        forma2.add(new Cell("4"));
        forma2.add(new Cell("0"));
        forma2.add(new Cell("5"));
        forma2.add(new Cell("6"));
        forma2.add(new Cell("7"));
        forma2.add(new Cell("8"));

        ArrayList<Cell> forma3 = new ArrayList<>();

        forma3.add(new Cell("1"));
        forma3.add(new Cell("4"));
        forma3.add(new Cell("7"));
        forma3.add(new Cell("2"));
        forma3.add(new Cell("3"));
        forma3.add(new Cell("8"));
        forma3.add(new Cell("3"));
        forma3.add(new Cell("6"));
        forma3.add(new Cell("0"));

        if (config.equals("Forma 1"))
        {
            boolean equal = areEqual(forma1, cellArrayList);

            if (equal){

                AlertDialog alertDialog = new AlertDialog.Builder(PuzzleActivity.this).create();
                alertDialog.setTitle("Felicidades " + name);
                alertDialog.setMessage("Has completado el Puzzle 8 de la forma: "+ config);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                saveToSQL();
                            }
                        });
                alertDialog.show();
                

            }
        } else if (config.equals("Forma 2")){

            boolean equal = areEqual(forma2, cellArrayList);

            if (equal){
                AlertDialog alertDialog = new AlertDialog.Builder(PuzzleActivity.this).create();
                alertDialog.setTitle("Felicidades " + name);
                alertDialog.setMessage("Has completado el Puzzle 8 de la forma: "+ config);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                saveToSQL();
                            }
                        });
                alertDialog.show();
            }
        } else if (config.equals("Forma 3")){

            boolean equal = areEqual(forma3, cellArrayList);

            if (equal){
                AlertDialog alertDialog = new AlertDialog.Builder(PuzzleActivity.this).create();
                alertDialog.setTitle("Felicidades " + name);
                alertDialog.setMessage("Has completado el Puzzle 8 de la forma: "+ config);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                saveToSQL();
                            }
                        });
                alertDialog.show();
            }
        } else if (config.equals("Aleatorio")){

            boolean equal1 = areEqual(forma1, cellArrayList);
            boolean equal2 = areEqual(forma2, cellArrayList);
            boolean equal3 = areEqual(forma3, cellArrayList);

            if (equal1){
                AlertDialog alertDialog = new AlertDialog.Builder(PuzzleActivity.this).create();
                alertDialog.setTitle("Felicidades " + name);
                alertDialog.setMessage("Has completado el Puzzle 8 de la forma: "+ config);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                saveToSQL();
                            }
                        });
                alertDialog.show();
            }
            if (equal2){
                AlertDialog alertDialog = new AlertDialog.Builder(PuzzleActivity.this).create();
                alertDialog.setTitle("Felicidades " + name);
                alertDialog.setMessage("Has completado el Puzzle 8 de la forma: "+ config);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                saveToSQL();
                            }
                        });
                alertDialog.show();
            }
            if (equal3){
                AlertDialog alertDialog = new AlertDialog.Builder(PuzzleActivity.this).create();
                alertDialog.setTitle("Felicidades " + name);
                alertDialog.setMessage("Has completado el Puzzle 8 de la forma: "+ config);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                saveToSQL();
                            }
                        });
                alertDialog.show();
            }
        }
    }

    private void saveToSQL() {

        AdminSQLiteOpenHelper adminSQL = new AdminSQLiteOpenHelper(PuzzleActivity.this, "db", null, 1);
        SQLiteDatabase sqlDB = adminSQL.getWritableDatabase();

        ContentValues player = new ContentValues();


        player.put("name", name);
        player.put("config", config);
        player.put("movements", movements);
        player.put("time", String.valueOf(count));

        long rows = sqlDB.insert("jugadores", null, player);

        sqlDB.close();

        Toast.makeText(this, "Terminado", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(PuzzleActivity.this, RecordActivity.class);
        startActivity(intent);

    }


    public boolean areEqual(ArrayList<Cell> cells1, ArrayList<Cell> cells2){

        for (int i = 0; i < cells1.size(); i++) {

            if (cells1.get(i).getValue() != cells2.get(i).getValue()) {
                return false;
            }

        }
        return true;
    }
    
    
}
