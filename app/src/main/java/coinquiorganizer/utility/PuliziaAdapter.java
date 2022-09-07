package coinquiorganizer.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import coinquiorganizer.model.PuliziaEntity;
import firstexample.helloworld.R;

public class PuliziaAdapter extends RecyclerView.Adapter<PuliziaAdapter.ViewHolder>{

    public ArrayList<PuliziaEntity> list;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView header,body;
        RelativeLayout currentCard;

        public ViewHolder(View itemView){
            super(itemView);
            header = itemView.findViewById(R.id.headerCard);
            body = itemView.findViewById(R.id.bodyCard);
            currentCard = itemView.findViewById(R.id.currentCard);
        }
    }

    public PuliziaAdapter(ArrayList<PuliziaEntity> list){
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pulizie_card,parent,false);
        ViewHolder evh = new ViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PuliziaEntity current = list.get(position);
        holder.header.setText(current.getCoinquilinoAddetto() + " " + current.getPostoCasa());
        holder.body.setText(current.getGiornoPulizia());
        if(current.getPostoCasa().equals("Cucina"))
            holder.currentCard.setBackgroundResource(R.drawable.kitchenroom);
        if(current.getPostoCasa().equals("Soggiorno"))
            holder.currentCard.setBackgroundResource(R.drawable.livingroom);
        if(current.getPostoCasa().equals("Bagno"))
            holder.currentCard.setBackgroundResource(R.drawable.bathroom);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
