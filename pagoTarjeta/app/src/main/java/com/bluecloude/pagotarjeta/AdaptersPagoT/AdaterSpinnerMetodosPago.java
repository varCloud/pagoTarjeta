package com.bluecloude.pagotarjeta.AdaptersPagoT;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluecloude.pagotarjeta.EntidadesPagoT.PaymentMethod;
import com.bluecloude.pagotarjeta.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaterSpinnerMetodosPago  extends ArrayAdapter<PaymentMethod> {

    private List<PaymentMethod> items;
    private Context context;
    public AdaterSpinnerMetodosPago(Context context, List<PaymentMethod> items) {
        super(context, R.layout.fragment_item_spinner_metodo_pago);
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //ViewHolder mViewHolder = new ViewHolder();
        View Item = convertView;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Item = mInflater.inflate(R.layout.fragment_item_spinner_metodo_pago, parent, false);
            ImageView mIamgeT = (ImageView) Item.findViewById(R.id.imgSPTarjetaT);
            TextView mTxtNumCard = (TextView) Item.findViewById(R.id.txtSPNombreT);
            Picasso.get().load(items.get(position).getImageUrl()).into(mIamgeT);
            mTxtNumCard.setText("****"+items.get(position).getLast4());
            //mViewHolder.mPopulation = (TextView) convertView.findViewById(R.id.tvPopulation);
            //convertView.setTag(mViewHolder);
        }

        return Item;
    }
}
