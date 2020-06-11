package com.codecool.languagetutor.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.db.History;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHandler> {

    List<History> historyList;
    Context context;
    LayoutInflater inflater;

    public HistoryAdapter(Context ct,List<History> list){
        context = ct;
        historyList = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.history_row, parent,false);
        return new ViewHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, int position) {
        if ( position % 2 == 0)
        {
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.sandstone));
        }else{
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.burtnOrange));
        }
        holder.quizName.setTextColor(context.getResources().getColor(R.color.textColor));
        holder.quizDate.setTextColor(context.getResources().getColor(R.color.textColor));
        holder.quizPercent.setTextColor(context.getResources().getColor(R.color.textColor));

        History history = historyList.get(position);

        holder.quizName.setText("Quiz "+Integer.toString(history.getId()));
        holder.quizPercent.setText(Integer.toString(history.getPercentage()) + "%");
        holder.quizDate.setText(history.getDate());

        holder.layout.setOnClickListener(v -> {
            Toast toast = Toast.makeText(context,history.getIncorrectGuesses(),Toast.LENGTH_LONG);
            toast.show();
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {

        @BindView(R.id.quiz_name)
        TextView quizName;
        @BindView(R.id.quiz_date)
        TextView quizDate;
        @BindView(R.id.quiz_percent)
        TextView quizPercent;
        ConstraintLayout layout;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            layout = (ConstraintLayout) quizPercent.getParent();
        }
    }
}
