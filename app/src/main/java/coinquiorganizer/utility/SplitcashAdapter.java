package coinquiorganizer.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import coinquiorganizer.model.SplitcashEntity;
import firstexample.helloworld.R;
import firstexample.helloworld.SplitcashActivity;

public class SplitcashAdapter extends RecyclerView.Adapter<SplitcashAdapter.ViewHolder> {
    private ArrayList<SplitcashEntity> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
         void onItemClick(int position);
         void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView header;
        public TextView body;
        public ImageView deleteImage;

        public ViewHolder(View itemView, OnItemClickListener listener){
                super(itemView);
                mImageView = itemView.findViewById(R.id.imageViewCard);
                header = itemView.findViewById(R.id.headerCard);
                body = itemView.findViewById(R.id.bodyCard);
                deleteImage = itemView.findViewById(R.id.deletebyimage);

                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                            if(listener!=null){
                                int position = getAdapterPosition();
                                if(position != RecyclerView.NO_POSITION){
                                    listener.onItemClick(position);
                                }

                            }
                    }
                });
                deleteImage.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(listener!=null){
                            int position = getAdapterPosition();
                            if(position!= RecyclerView.NO_POSITION){
                                listener.onDeleteClick(position);
                            }
                        }
                    }
                });
        }
    }

    public SplitcashAdapter(ArrayList<SplitcashEntity> exampleList){
        mExampleList = exampleList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.splitcash_card, parent, false);
        ViewHolder evh = new ViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SplitcashEntity current = mExampleList.get(position);
        holder.mImageView.setImageResource(R.drawable.ic_money);
        holder.header.setText(current.getCoinquilinoDonatore() + " +" +current.getSomma().toString());
        holder.body.setText(current.getMotivazione() + " in:" + current.getDataVersamento() + " alle:" + current.getOrario());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
