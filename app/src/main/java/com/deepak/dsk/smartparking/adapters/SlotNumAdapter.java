package com.deepak.dsk.smartparking.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepak.dsk.smartparking.R;
import com.libRG.CustomTextView;

/**
 * Created by dsk on 13-Mar-18.
 */

public class SlotNumAdapter extends RecyclerView.Adapter<SlotNumAdapter.SlotNumViewHolder> {


    Context context;
    boolean addOrRemoveItemClicked=false;
    public ClickListener clickListener;
    int numberOfQuestion=0;
    int slotIndex;
    int slot[];

    private LayoutInflater inflater;
    public SlotNumAdapter(Context context, int numberOfQuestion,int slot[])
    {
        inflater = LayoutInflater.from(context);
        this.context=context;
        this.numberOfQuestion=numberOfQuestion;
        this.slot=slot;
    }

    public void setOnClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }

    @Override
    public SlotNumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.slot_number_view,parent,false);
         SlotNumViewHolder slotNumViewHolder=new SlotNumViewHolder(view);
        return slotNumViewHolder;
    }

    @Override
    public void onBindViewHolder(SlotNumViewHolder holder, int position) {
        holder.customTextView.setText(""+(position+1));
        if(slot[slotIndex]==(position+1))
        {
            holder.customTextView.setBackgroundColor(context.getResources().getColor(R.color.red));
            holder.customTextView.setTextColor(context.getResources().getColor(R.color.white));
            slotIndex++;
        }

    }

    @Override
    public int getItemCount() {
        return numberOfQuestion;
    }

    class SlotNumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CustomTextView customTextView;


        public SlotNumViewHolder(View itemView) {
            super(itemView);
            customTextView=itemView.findViewById(R.id.question_number_view_id);
            customTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener!=null)
            {
                switch (view.getId())
                {
                    case R.id.question_number_view_id: clickListener.ItemClick(view,getAdapterPosition());
                        break;
                    default:
                        break;
                }
            }

        }
    }



    public interface ClickListener{
        public void ItemClick(View view, int position);

    }


    public void removeItem(int position)
    {
       /* if(data.size()!=0) {
            addOrRemoveItemClicked=true;
            data.remove(position);
            notifyItemRemoved(position);
        }
        else {
            Toast.makeText(context,"No item to remove",Toast.LENGTH_SHORT).show();
        }*/
    }
    public void addItem(String title)
    {
        addOrRemoveItemClicked=true;
        //Information item=new Information();
        //data.add(item);
       // notifyItemInserted(data.size());
    }
    public int getAdapterPosition()
    {
        return getAdapterPosition();
    }



}
