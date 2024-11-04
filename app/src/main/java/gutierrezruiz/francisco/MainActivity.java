package gutierrezruiz.francisco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import gutierrezruiz.francisco.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<Personaje> misPersonajes = new ArrayList<>(); // Lista de personajes
    private ActivityMainBinding binding; // Declaración de la variable de enlace
    private RecyclerView recyclerView; // Declaración de la variable de RecyclerView
    private Adaptador adaptador; // Declaración de la variable de adaptador

    private DrawerLayout drawerLayout; // Declaración de la variable de DrawerLayout
    private ActionBarDrawerToggle toggle; // Declaración de la variable de ActionBarDrawerToggle
    private NavigationView navigationView; // Declaración de la variable de NavigationView

    @Override
    // Este método se ejecuta cuando se crea la actividad
    protected void onCreate(Bundle savedInstanceState) {
        // Llama al constructor de la clase padre
        super.onCreate(savedInstanceState);
        // Configura el diseño de la actividad
        EdgeToEdge.enable(this);
        // Configura el layout de la actividad
        setContentView(R.layout.activity_main);
        // Configura el padding de la vista principal


        // Configura el binding de la actividad
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Configura la vista de la actividad
        setContentView(binding.getRoot());
        // Configura la barra de herramientas
        setSupportActionBar(binding.toolbar);
        // Configura la lista de personajes
        iniciarListaPersonajes();

        // Configura el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewPersonajes);
        // Configura el layout del RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        // Configura el adaptador
        adaptador = new Adaptador(misPersonajes);
        // Configura el adaptador del RecyclerView
        recyclerView.setAdapter(adaptador);

        // Configura el snackbar
        View rootView = findViewById(android.R.id.content);
        // Muestra el snackbar
        Snackbar.make(rootView, getString(R.string.mensajeSnack), Snackbar.LENGTH_SHORT).show();

        // Configura el DrawerLayout y el NavigationView
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        // Configurar el ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // Agregar el ActionBarDrawerToggle al DrawerLayout
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // Configurar el NavigationView
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            // Este método se ejecuta cuando se selecciona un elemento del menú
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Manejar la selección de elementos
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    // Mostrar un Toast indicando que estás en Home
                    Toast.makeText(MainActivity.this, "Estás en Home", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.nav_settings) {
                    // Ir a la actividad de Ajustes
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_language) {
                    // Mostrar un diálogo para cambiar el idioma
                    showLanguageDialog();
                }
                // Cerrar el menú después de la selección
                drawerLayout.closeDrawers();
                // Devuelve true para indicar que el evento ha sido manejado
                return true;
            }
        });

// Configura el OnClickListener del adaptador
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            // Este método se ejecuta cuando se hace clic en un elemento del RecyclerView
            public void onClick(View view) {
                // Obtiene la posición del elemento seleccionado
                int posicion = recyclerView.getChildAdapterPosition(view);
                // Obtiene el personaje seleccionado
                Personaje personaje = misPersonajes.get(posicion);
                // Crea un Intent para iniciar la actividad DetallesActivity
                Intent intent = new Intent(MainActivity.this, DetallesActivity.class);
                // Pasa los datos del personaje a la actividad DetallesActivity
                intent.putExtra("nombre", personaje.getNombre());
                intent.putExtra("imagen", personaje.getImagenId());
                intent.putExtra("descripcion", personaje.getDescripcion());
                intent.putExtra("habilidad", personaje.getHabilidad());
                // Muestra un Toast con el nombre del personaje seleccionado
                Toast.makeText(MainActivity.this, getString(R.string.mensaje_toast, personaje.getNombre()), Toast.LENGTH_SHORT).show();
                // Inicia la actividad DetallesActivity
                startActivity(intent);
            }
        });
    } // Fin onCreate

    @Override
    // Este método se ejecuta cuando se crea el menú de opciones
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú de opciones
        getMenuInflater().inflate(R.menu.menu, menu);
        // Devuelve true para que se muestre el menú
        return true;
    }

    @Override
    // Este método se ejecuta cuando se selecciona un elemento del menú
    public boolean onOptionsItemSelected(MenuItem item) {
        // Verifica si el item seleccionado es del Drawer Toggle
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Manejamos las opciones elegidas
        if (item.getItemId() == R.id.menu_icon) {
            // Mostrar el submenú al pulsar el ícono
            showPopupMenu(findViewById(R.id.menu_icon));
            // Devuelve true para indicar que el evento ha sido manejado
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            // Volver a la actividad anterior
            finish();
            // Devuelve true para indicar que el evento ha sido manejado
            return true;
        }
        // Si no coincide con ninguna opción, llama al super
        return super.onOptionsItemSelected(item);
    }

    // Muestra el submenú
    private void showPopupMenu(View view) {
        // Cargamos el menú del submenú
        PopupMenu popup = new PopupMenu(this, view);
        // Inflamos el menú del submenú
        popup.getMenuInflater().inflate(R.menu.submenu, popup.getMenu());
        // Manejamos las selecciones del submenú
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            // Este método se ejecuta cuando se selecciona un elemento del submenú
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                // Manejamos las opciones elegidas
                if (itemId == R.id.acercade) {
                    // Acción para la opción acerca de
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    // Creamos un objeto de nuestra clase AcercadeDialogo que muestra el diálogo
                    AcercadeDialogo dialogo = new AcercadeDialogo();
                    // Mostramos el diálogo
                    dialogo.show(fragmentManager, "dialogoAcercade");
                    // Devuelve true para indicar que el evento ha sido manejado
                    return true;
                } else if (itemId == R.id.salir) {
                    // Acción para la opción salir
                    finish();
                    // Devuelve true para indicar que el evento ha sido manejado
                    return true;
                }
                // Si no coincide con ninguna opción, devuelve false
                return false;
            }
        });
        // Mostramos el PopupMenu
        popup.show();
    }

    // Muestra el diálogo para cambiar el idioma
    private void showLanguageDialog() {
        // Crear el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Título del diálogo
        builder.setTitle("Cambiar idioma");
        // Configurar el Switch
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_languaje_switch, null);
        // Agregar el Switch al diálogo
        builder.setView(dialogView);
        // Referencia al Switch
        Switch languageSwitch = dialogView.findViewById(R.id.languageSwitch);
        // Cargar el idioma seleccionado
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        // Cargar el estado del Switch
        boolean isEnglish = prefs.getBoolean("isEnglish", true);
        // Establecer el estado del Switch
        languageSwitch.setChecked(isEnglish);
        // Manejar el cambio de idioma
        languageSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Guardar el estado del Switch
            SharedPreferences.Editor editor = prefs.edit();
            // Guardar el estado del Switch
            editor.putBoolean("isEnglish", isChecked);
            // Guardar los cambios
            editor.apply();
            // Cambiar el idioma según el estado del Switch
            setLanguage(isChecked ? "en" : "es");
        });
        // Botón de aceptar
        builder.setPositiveButton("Cerrar", (dialog, which) -> dialog.dismiss());
        // Mostrar el diálogo
        builder.create().show();
    }

    // Cambiar el idioma
    private void setLanguage(String langCode) {
        // Cambiar el idioma
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        // Actualizar la configuración
        Configuration config = getResources().getConfiguration();
        // Actualizar la configuración
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        // Reiniciar actividad para aplicar cambios
        recreate();
    }

    /**
     * Inicia la lista de personajes.
     */
    private void iniciarListaPersonajes() {
        misPersonajes.add(new Personaje(getString(R.string.boo), R.drawable.boo, getString(R.string.boo_descripcion), getString(R.string.boo_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.bowser), R.drawable.bowser, getString(R.string.bowser_descripcion), getString(R.string.bowser_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.bowser_jr), R.drawable.bowserjr, getString(R.string.bowserjr_descripcion), getString(R.string.bowserjr_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.daisy), R.drawable.daisy, getString(R.string.daisy_descripcion), getString(R.string.daisy_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.diddy_kong), R.drawable.diddykong, getString(R.string.diddykong_descripcion), getString(R.string.diddykong_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.donkey_kong), R.drawable.donkeykong, getString(R.string.donkeykong_descripcion), getString(R.string.donkeykong_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.luigi), R.drawable.luigi, getString(R.string.luigi_descripcion), getString(R.string.luigi_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.mario), R.drawable.mario, getString(R.string.mario_descripcion), getString(R.string.mario_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.peach), R.drawable.peach, getString(R.string.peach_descripcion), getString(R.string.peach_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.rosalina), R.drawable.rosalina, getString(R.string.rosalina_descripcion), getString(R.string.rosalina_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.toad), R.drawable.toad, getString(R.string.toad_descripcion), getString(R.string.toad_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.waluigi), R.drawable.waluigi, getString(R.string.waluigi_descripcion), getString(R.string.waluigi_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.wario), R.drawable.wario, getString(R.string.wario_descripcion), getString(R.string.wario_habilidad)));
        misPersonajes.add(new Personaje(getString(R.string.yoshi), R.drawable.yoshi, getString(R.string.yoshi_descripcion), getString(R.string.yoshi_habilidad)));
    }

} // Fin class
