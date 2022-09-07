package coinquiorganizer.utility;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import coinquiorganizer.model.CronologiaSplitEntity;
import coinquiorganizer.model.SplitcashEntity;
import firstexample.helloworld.R;

public class CronologiaAdapter extends RecyclerView.Adapter<CronologiaAdapter.ViewHolder>  {
    private ArrayList<CronologiaSplitEntity> splitEntities;
    private onItemClickListenerCrono mListener;
    public interface onItemClickListenerCrono{
        void onEyeClick(int position);
    }
    public void setOnItemClickListener(onItemClickListenerCrono listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView historyViewCard;
        private ImageView openInfoEye;
        private TextView dataHistorySplit;

        public ViewHolder(View itemView, onItemClickListenerCrono listener){
            super(itemView);
            historyViewCard = itemView.findViewById(R.id.historyViewCard);
            openInfoEye = itemView.findViewById(R.id.openinfo);
            dataHistorySplit = itemView.findViewById(R.id.historyHeaderCard);

            /*itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onEyeClick(position);
                        }
                    }
                }
            });*/
            openInfoEye.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onEyeClick(position);
                        }
                    }
                }
            });
        }

    }
    public CronologiaAdapter(ArrayList<CronologiaSplitEntity> splitEntities){this.splitEntities=splitEntities;}
    @NonNull
    @Override
    public CronologiaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cronologiasplit_card, parent, false);
        ViewHolder evh = new ViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CronologiaSplitEntity current = splitEntities.get(position);
        holder.historyViewCard.setImageResource(R.drawable.ic_baseline_history_24);
        holder.dataHistorySplit.setText(current.getDataInizio()+ " - " + current.getDataFine());
        //holder.dataHistorySplit.setPaintFlags(holder.dataHistorySplit.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); -> Idea per ToDo, sbarrare quelle con fatte==True
    }

    @Override
    public int getItemCount() {
        return splitEntities.size();
    }

}
