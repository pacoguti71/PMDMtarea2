package gutierrezruiz.francisco;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class PreferenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings_container, new PreferenciasFragment())
                    .commit();
        }
    }
}
