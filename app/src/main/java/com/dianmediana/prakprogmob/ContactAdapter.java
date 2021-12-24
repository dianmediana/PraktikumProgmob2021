package com.dianmediana.prakprogmob;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList contact_id, contact_name, contact_no, contact_email;

    ContactAdapter(Activity activity, Context context, ArrayList contact_id, ArrayList contact_name, ArrayList contact_no,
                  ArrayList contact_email){
        this.activity = activity;
        this.context = context;
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.contact_no = contact_no;
        this.contact_email = contact_email;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_contact, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.contact_name_txt.setText(String.valueOf(contact_name.get(position)));
        holder.contact_no_txt.setText(String.valueOf(contact_no.get(position)));

        //Recyclerview onClickListener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditContact.class);
                intent.putExtra("id", String.valueOf(contact_id.get(position)));
                intent.putExtra("name", String.valueOf(contact_name.get(position)));
                intent.putExtra("number", String.valueOf(contact_no.get(position)));
                intent.putExtra("email", String.valueOf(contact_email.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contact_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView contact_name_txt, contact_no_txt;
        CardView cardView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_name_txt = itemView.findViewById(R.id.text1);
            contact_no_txt = itemView.findViewById(R.id.text2);
            cardView = itemView.findViewById(R.id.row);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            cardView.setAnimation(translate_anim);
        }
    }
}
