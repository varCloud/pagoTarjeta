package com.bluecloude.pagotarjeta.AdaptersPagoT;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bluecloude.pagotarjeta.EntidadesPagoT.ClientePagoT;
import com.bluecloude.pagotarjeta.EntidadesPagoT.PaymentMethod;
import com.bluecloude.pagotarjeta.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMetodosPago extends RecyclerView.Adapter<AdapterMetodosPago.MyViewHolder> {

private List<PaymentMethod>items;
private Context context;


public AdapterMetodosPago(Context context, List<PaymentMethod> items) {
        this.context = context;
        this.items= items;
        }

    @Override
    public int getItemCount() {
        return items.size();
        }

    @Override
    public void onBindViewHolder(MyViewHolder itemsViewHolder, int i) {
            Picasso.get().load(items.get(i).getImageUrl()).into(itemsViewHolder.imgTarjetaT);
            itemsViewHolder.txtNombreT.setText(items.get(i).getCardType());
            itemsViewHolder.txtNumTarEnmasc.setText("**** "+items.get(i).getLast4());
        }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
        from(viewGroup.getContext()).
        inflate(R.layout.fragment_item_metodo_pagot, viewGroup, false);

        return new MyViewHolder(itemView);

        }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        protected ImageView imgTarjetaT;
        protected TextView txtNombreT;
        protected TextView txtNumTarEnmasc;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View v) {
            super(v);
            txtNombreT = (TextView) v.findViewById(R.id.txtNombreT);
            txtNumTarEnmasc = (TextView) v.findViewById(R.id.txtNumTarEnmasc);
            imgTarjetaT = (ImageView) v.findViewById(R.id.imgTarjetaT);
            viewForeground = (RelativeLayout)  v.findViewById(R.id.viewForeground);
            viewBackground = (RelativeLayout)  v.findViewById(R.id.view_background);

        }
    }

    public void removeItem(int position) {
        items.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(PaymentMethod item, int position) {
        items.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
