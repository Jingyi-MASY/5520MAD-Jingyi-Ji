package edu.neu.madcourse.numad21fa_jingyiji;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final ArrayList<ItemCard> itemList;
    private ItemClickListener listener;

    //Constructor
    public ViewAdapter(ArrayList<ItemCard> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("jj", holder.toString());
        Log.d("jj", String.valueOf(position));
        ItemCard currentItem = itemList.get(position);


        holder.link.setText(currentItem.getItemLink());
//        Log.d("jj", currentItem.getItemName());
        holder.nameText.setText(currentItem.getItemName());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
