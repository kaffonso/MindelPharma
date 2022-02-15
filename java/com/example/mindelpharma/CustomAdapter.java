package com.example.mindelpharma;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList id, name, address, telephone, fax, email, latitude, longitude;

    CustomAdapter(Context context, ArrayList id, ArrayList name, ArrayList address, ArrayList telephone,
                  ArrayList fax, ArrayList email, ArrayList latitude, ArrayList longitude){
        this.context = context;
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.farmacia_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //atribuindo id, name and address na lista de farmacias, de acordo com cada position
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.address_txt.setText(String.valueOf(address.get(position)));

        //atribuindo logo a farmacias
        if (String.valueOf(id.get(position)).equals("1")){
            holder.iv_logo.setImageResource(R.mipmap.nena);
        } else if (String.valueOf(id.get(position)).equals("2")){
            holder.iv_logo.setImageResource(R.mipmap.higiene);
        } else if (String.valueOf(id.get(position)).equals("3")){
            holder.iv_logo.setImageResource(R.mipmap.jovem);
        } else if (String.valueOf(id.get(position)).equals("4")){
            holder.iv_logo.setImageResource(R.mipmap.mindelo);
        } else if (String.valueOf(id.get(position)).equals("5")){
            holder.iv_logo.setImageResource(R.mipmap.avenida);
        } else if (String.valueOf(id.get(position)).equals("6")){
            holder.iv_logo.setImageResource(R.mipmap.leao);
        }

        // funcao que eh chamada quando for clicado em algum elemento da lista
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(context, Singlepage.class);

                //passando parametros para que sejam acessados na pagina seguinte
                page.putExtra("id",String.valueOf(id.get(position)));
                page.putExtra("name",String.valueOf(name.get(position)));
                page.putExtra("address",String.valueOf(address.get(position)));
                page.putExtra("telephone",String.valueOf(telephone.get(position)));
                page.putExtra("fax",String.valueOf(fax.get(position)));
                page.putExtra("email",String.valueOf(email.get(position)));
                page.putExtra("latitude",String.valueOf(latitude.get(position)));
                page.putExtra("longitude",String.valueOf(longitude.get(position)));

                //iniciando a pagina
                context.startActivity(page);
            }
        });
    }

    @Override
    // funcao para verificar tamanho da lista
    public int getItemCount() {
        return id.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_txt, address_txt;
        public ImageView iv_logo;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //criando as variaves quem vem do layout
            name_txt = itemView.findViewById(R.id.name_txt);
            address_txt = itemView.findViewById(R.id.address_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            iv_logo = itemView.findViewById(R.id.iv_logo);
        }
    }
}
