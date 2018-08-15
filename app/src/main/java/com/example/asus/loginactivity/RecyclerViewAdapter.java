package com.example.asus.loginactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public  class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG="RecyclerViewAdapter";
    private List<TextModel> mAddedTexts;
    private Context mConnext;
    private AdapterListener listener;


    public RecyclerViewAdapter(Context context, List<TextModel> addedTexts, AdapterListener listener){
      this.mAddedTexts=addedTexts;
      this.mConnext=context;
      this.listener=listener;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent,false );
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {
    holder.textView.setText(mAddedTexts.get(position).getText());
    holder.parentLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(listener!=null)
                listener.onItemClick(view,position);
        }
    });
    holder.deleteButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(listener!=null)
                listener.onItemClick(view,position);
        }
    });
    }

    @Override
    public int getItemCount() {
        return mAddedTexts.size();
    }
        public void setListener(AdapterListener listener){
            this.listener=listener;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button deleteButton;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view);
            parentLayout=itemView.findViewById(R.id.parent_layout);
            deleteButton=itemView.findViewById(R.id.delete_button);
        }
    }
}
