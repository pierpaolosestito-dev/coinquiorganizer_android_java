package coinquiorganizer.utility;

import android.graphics.Paint;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import coinquiorganizer.model.ToDoEntity;
import firstexample.helloworld.R;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    public static ArrayList<ToDoEntity> toDoEntities;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onEyeClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mFirstImage;
        private ImageView checkImage;
        private ImageView openInfoEye;
        private TextView header;
        public static boolean clicked = false;
        public ViewHolder(View itemView, OnItemClickListener listener){
            super(itemView);
            mFirstImage = itemView.findViewById(R.id.toDoViewCard);
            checkImage = itemView.findViewById(R.id.checkToDoImage);
            openInfoEye = itemView.findViewById(R.id.openInfoToDo);
            header = itemView.findViewById(R.id.toDoHeaderCard);

            openInfoEye.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEyeClick(position);
                            /*if(clicked == false) {
                                openInfoEye.setImageResource(R.drawable.ic_task);
                                header.setPaintFlags(header.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                clicked = true;
                                return;
                            }
                            if(clicked == true){
                                openInfoEye.setImageResource(R.drawable.ic_eye);
                                clicked = false;
                                return;
                            }*/
                        }

                    }
                }
            });
            checkImage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){

                            if(clicked == false) {
                                checkImage.setImageResource(R.drawable.ic_check);
                                header.setPaintFlags(header.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                header.setTextSize(25);
                                ToDoAdapter.toDoEntities.get(position).setSegnato(true);
                                clicked = true;
                                return;
                            }
                            if(clicked == true){
                                checkImage.setImageResource(R.drawable.ic_uncheck);
                                header.setTextSize(20);
                                header.setPaintFlags(header.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                                ToDoAdapter.toDoEntities.get(position).setSegnato(false);
                                clicked = false;
                                return;
                            }
                        }

                    }
                }
            });



        }
    }

    public ToDoAdapter(ArrayList<ToDoEntity> toDoEntities){this.toDoEntities=toDoEntities;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_card, parent, false);
        ToDoAdapter.ViewHolder evh = new ToDoAdapter.ViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoEntity current = toDoEntities.get(position);
        if(current.isImportante())
            holder.mFirstImage.setImageResource(R.drawable.ic_baseline_stars_24);
        else holder.mFirstImage.setImageResource(R.drawable.ic_task);

        holder.header.setText(current.getNomeAttivita());

        if(current.isSegnato()){
            holder.checkImage.setImageResource(R.drawable.ic_check);
            holder.header.setPaintFlags(holder.header.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.header.setTextSize(25);
            return;
        }else{
            holder.checkImage.setImageResource(R.drawable.ic_uncheck);
            holder.header.setPaintFlags(holder.header.getPaintFlags() | ~ Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public int getItemCount() {
        return toDoEntities.size();
    }
}
