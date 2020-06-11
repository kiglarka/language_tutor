package com.codecool.languagetutor.history;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.db.History;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity implements HistoryContract {

    HistoryPresenter presenter;

    @BindView(R.id.recycler_view_history)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ButterKnife.bind(this);
        presenter = new HistoryPresenter(this);
        presenter.onAttach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllHistory();
    }

    @Override
    public void successfullyLoaded(List<History> histories) {
        HistoryAdapter adapter = new HistoryAdapter(this,histories);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toast t = Toast.makeText(this,Integer.toString(histories.size()),Toast.LENGTH_LONG);
        t.show();
    }
}