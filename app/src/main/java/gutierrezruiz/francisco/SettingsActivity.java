package gutierrezruiz.francisco;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    // Método que se ejecuta al crear la actividad de ajustes
    protected void onCreate(Bundle savedInstanceState) {
        // Llama al constructor de la clase padre
        super.onCreate(savedInstanceState);
        // Configura el layout de la actividad
        setContentView(R.layout.activity_settings);
    }
}
