package com.ag.bta.ui.navigationDrawer;


import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;
        import java.util.ArrayList;
import com.ag.bta.main.R;
// Extends the Adapter class to RecyclerView.Adapter
// and implement the unimplemented methods
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    ArrayList courseImg, courseName;
    Context context;
ClickCallback callBack;
    // Constructor for initialization
    public ListAdapter(Context context, ArrayList courseImg, ArrayList courseName,ClickCallback argClick) {
        callBack = argClick;
        this.context = context;
        this.courseImg = courseImg;
        this.courseName = courseName;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wavelayout_item_listtype_nav, parent, false);

        // Passing view to ViewHolder
        ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
        return viewHolder;
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        // TypeCast Object to int type
        int res = (int) courseImg.get(position);

        holder.images.setImageResource(res);
        holder.text.setText((String) courseName.get(position));
    }

    @Override
    public int getItemCount() {
        // Returns number of items
        // currently available in Adapter
        return courseImg.size();
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView text;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.onItemClicked(getAbsoluteAdapterPosition());
                }
            });
            images = (ImageView) view.findViewById(R.id.imageViewIcon);
            text = (TextView) view.findViewById(R.id.textViewName);
        }
    }

    public interface ClickCallback{
        void onItemClicked(int pos);
    }
}
