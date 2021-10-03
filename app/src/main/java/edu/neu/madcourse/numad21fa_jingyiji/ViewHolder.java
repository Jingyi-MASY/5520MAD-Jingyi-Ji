package edu.neu.madcourse.numad21fa_jingyiji;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView nameText;
    public TextView link;

    public ViewHolder(final View itemView, final ItemClickListener listener) {
        super(itemView);
        link = itemView.findViewById(R.id.link);
        nameText = itemView.findViewById(R.id.item_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}
