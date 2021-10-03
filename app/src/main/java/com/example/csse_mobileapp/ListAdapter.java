package com.example.csse_mobileapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.database.annotations.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Items> itemList;

    public ListAdapter(Activity mContext, List<Items> itemList){
        super(mContext, R.layout.list_items, itemList);
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NotNull
    @Override
    public View getView(int position, @Nullable View convertView, @NotNull ViewGroup parent){

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_items, null, true);

        TextView itemName = listItemView.findViewById(R.id.itemName_add);
        TextView unitPrice = listItemView.findViewById(R.id.unitPrice_add);
        TextView thresholdUnits = listItemView.findViewById(R.id.thresholdUnit_add);
        TextView currentUnits = listItemView.findViewById(R.id.currentUnits_add);

        Items items = itemList.get(position);

        itemName.setText(items.getName());
        unitPrice.setText(items.getPrice());
        thresholdUnits.setText(items.getThreshold_units());
        currentUnits.setText(items.getCurrent_units());

        return listItemView;
    }

}
