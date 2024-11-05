package gutierrezruiz.francisco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Activity de la Presentacion.
 */
public class PresentacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // LLama al constructor de la clase padre
        super.onCreate(savedInstanceState);
        // Establece el layout de la actividad
        setContentView(R.layout.activity_presentacion);
        // El Handler se utiliza para ejecutar una tarea después de un tiempo
        new Handler().postDelayed(new Runnable() {
            @Override
            // Este método se ejecuta después de 3 segundos
            public void run() {
                // Crea un Intent para iniciar la actividad MainActivity
                Intent intent = new Intent(PresentacionActivity.this, MainActivity.class);
                // Inicia la actividad
                startActivity(intent);
                // Cierra la actividad actual
                finish();
            }
        }, 3000); // 3000 milliseconds = 3 seconds
    } // Fin onCreate
} // Fin class