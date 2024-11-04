package gutierrezruiz.francisco;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import gutierrezruiz.francisco.databinding.ActivityDetallesPersonajeBinding;

public class DetallesActivity extends AppCompatActivity {


    @Override
    // Este método se ejecuta cuando se crea la actividad
    protected void onCreate(Bundle savedInstanceState) {
        // Llama al constructor de la clase padre
        super.onCreate(savedInstanceState);
        // Configura el layout de la actividad
        ActivityDetallesPersonajeBinding binding = ActivityDetallesPersonajeBinding.inflate(getLayoutInflater());
        // Configura la vista de la actividad
        setContentView(binding.getRoot());

        // Obtiene el intent que inició la actividad
        Intent intent = getIntent();

        // Obtiene los datos del intent
        String nombre = intent.getStringExtra("nombre");
        int imagenId = intent.getIntExtra("imagen", 0); // 0 es un valor por defecto si no se encuentra el extra
        String descripcion = intent.getStringExtra("descripcion");
        String habilidad = intent.getStringExtra("habilidad");

        // Configura los datos en la vista
        binding.textViewNombre.setText(nombre);
        binding.imageView.setImageResource(imagenId);
        binding.textViewDescripcion.setText(descripcion);
        binding.textViewHabilidades.setText(habilidad);

    }


} // Fin class