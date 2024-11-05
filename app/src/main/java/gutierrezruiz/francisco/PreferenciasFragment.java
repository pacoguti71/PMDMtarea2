package gutierrezruiz.francisco;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatDelegate;
import java.util.Locale;

public class PreferenciasFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferencias, rootKey);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("Idioma")) {
            boolean isSpanish = sharedPreferences.getBoolean(key, true);
            if (!isSpanish) {
                setLanguage("en"); // Cambiar a inglés
            } else {
                setLanguage("es"); // Cambiar a español
            }
        } else if (key.equals("Modo")) {
            boolean isDarkMode = sharedPreferences.getBoolean(key, true);
            if (isDarkMode) {
                setDarkMode();
            } else {
                setLightMode();
            }
        }
    }

    private void setLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);
        config.setLocales(new LocaleList(locale));

        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());

        // Reiniciar la actividad para aplicar el nuevo idioma
        getActivity().recreate();
    }


    private void setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    private void setLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}
