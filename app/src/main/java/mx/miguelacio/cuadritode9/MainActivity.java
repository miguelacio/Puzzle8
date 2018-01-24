package mx.miguelacio.cuadritode9;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import mx.miguelacio.cuadritode9.utils.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    String configs[] = {"Escoge una configuración","Forma 1","Forma 2", "Forma 3", "Aleatorio"};
    String modes[] = {"Escoge una modo","Manual","Automatico"};
    EditText editTextName;
    Spinner spinnerConfig, spinnerMode;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.edittext_name);
        buttonSave = findViewById(R.id.button_save);
        spinnerConfig = findViewById(R.id.spinner_config);
        spinnerMode = findViewById(R.id.spinner_mode);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_item, modes);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMode.setAdapter(spinnerArrayAdapter2);


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_item, configs);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConfig.setAdapter(spinnerArrayAdapter);
        
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                
                if (!name.isEmpty()){
                    if (spinnerConfig.getSelectedItemPosition() != 0){
                        if (spinnerMode.getSelectedItemPosition() != 0) {

                            if (spinnerMode.getSelectedItemPosition() == 1){


                            Intent intent = new Intent(MainActivity.this, PuzzleActivity.class);
                            intent.putExtra("config", spinnerConfig.getSelectedItem().toString());
                            intent.putExtra("name", name);
                            startActivity(intent);

                            } else {

                                Intent intent = new Intent(MainActivity.this, AutomaticActivity.class);
                                startActivity(intent);
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Selecciona un modo", Toast.LENGTH_SHORT).show();
                        }
                        
                    } else {
                        Toast.makeText(MainActivity.this, "Selecciona una configuración", Toast.LENGTH_SHORT).show();
                    }

                    
                } else {
                    
                    Toast.makeText(MainActivity.this, "Escribe un nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
