package gutierrezruiz.francisco;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

/**
 * Acercade dialogo.
 *
 * @author Francisco Gutiérrez Ruiz
 * @version 1.0
 * @since 2024 /10/16 <p> Muestra un diálogo de alerta mostrando información sobre la aplicación.
 */
public class AcercadeDialogo extends DialogFragment {


    /**
     * Crea el diálogo de alerta.
     *
     * @param savedInstanceState el estado de la instancia guardada
     * @return el diálogo
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inicializamos las variables
        String autor = getString(R.string.app_author);// Autor de la aplicación
        String versionName; // Versión de la aplicación

        // Intentamos obtener la información del paquete y la versión
        try {
            PackageInfo packageInfo = requireActivity().getPackageManager().getPackageInfo(requireActivity().getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionName = getString(R.string.version0); // En caso de error
        }

        // Creamos el objeto AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String titulo = getActivity().getString(R.string.acercade);
        String opcionAceptar = getActivity().getString(R.string.aceptar);
        String mensaje = getString(R.string.mensaje_acerca_de, autor, versionName);

        // Estabecemos el título y el mensaje del diálogo
        builder.setTitle(titulo).setMessage(mensaje).setPositiveButton(opcionAceptar, new DialogInterface.OnClickListener() {
            // Cuando pulsamos el botón cerramos el diálogo
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        // Devolvemos el diálogo creado
        return builder.create();
    }
} // Fin class
