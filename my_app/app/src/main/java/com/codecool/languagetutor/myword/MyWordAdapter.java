package com.codecool.languagetutor.myword;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.db.French;

import org.w3c.dom.Text;

import java.util.List;

public class MyWordAdapter extends RecyclerView.Adapter<MyWordAdapter.MyAdapterView> {

    Context context;
    List<French> wordsList;
    LayoutInflater inflater;

    public MyWordAdapter(Context ct, List<French> wordsList){
        context = ct;
        this.wordsList = wordsList;
        inflater = LayoutInflater.from(ct);
    }

    @NonNull
    @Override
    public MyAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.my_words_row, parent,false);
        return new MyAdapterView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterView holder, int position) {
        if ( position % 2 == 0)
        {
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.sandstone));
        }else{
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.burtnOrange));
        }
        holder.english.setTextColor(context.getResources().getColor(R.color.textColor));
        holder.france.setTextColor(context.getResources().getColor(R.color.textColor));
        French word = wordsList.get(position);
        holder.english.setText(word.getLocalWord());
        holder.france.setText(word.getTranslation());
    }

    @Override
    public int getItemCount() {
        return wordsList.size();
    }

    public class MyAdapterView extends RecyclerView.ViewHolder {

        TextView english;
        TextView france;
        ConstraintLayout constraintLayout;

        public MyAdapterView(@NonNull View itemView) {
            super(itemView);
            english = itemView.findViewById(R.id.english_word);
            france = itemView.findViewById(R.id.france_word);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.france_word).getParent();
        }
    }
}
