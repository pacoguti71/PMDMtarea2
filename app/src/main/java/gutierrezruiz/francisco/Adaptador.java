package gutierrezruiz.francisco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> implements View.OnClickListener {

    ArrayList<Personaje> listaPersonajes; // Lista de personajes
    private View.OnClickListener listener; // Escuchador de clics

    // Constructor para la clase Adaptador
    public Adaptador(ArrayList<Personaje> listaPersonajes) {
        // Inicializa la lista de personajes
        this.listaPersonajes = listaPersonajes;
    }

    @NonNull
    @Override
    // Crea un nuevo ViewHolder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el diseño del elemento de la lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personaje, null, false);
        // Establece el escuchador de clics en la vista
        view.setOnClickListener(this);
        // Crea y devuelve un nuevo ViewHolder
        return new ViewHolder(view);
    }

    @Override
    // Vincula los datos del personaje con el ViewHolder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Establece los datos del personaje en el ViewHolder
        holder.textViewNombre.setText(listaPersonajes.get(position).getNombre());
        holder.imageView.setImageResource(listaPersonajes.get(position).getImagenId());
    }

    @Override
    // Devuelve el número de elementos en la lista
    public int getItemCount() {
        return listaPersonajes.size();
    }

    // Establece el escuchador de clics
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    // Maneja el clic en un elemento de la lista
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);

    }
    }


    // Crea un nuevo ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Atributos de la clase ViewHolder
        TextView textViewNombre;
        ImageView imageView;

        // Constructor para la clase ViewHolder
        public ViewHolder(@NonNull View itemView) {
            // Llama al constructor de la clase padre
            super(itemView);
            // Inicializa los atributos
            textViewNombre = itemView.findViewById(R.id.textViewNombrePersonaje);
            imageView = itemView.findViewById(R.id.imageViewPersonaje);
        }
    }

}
