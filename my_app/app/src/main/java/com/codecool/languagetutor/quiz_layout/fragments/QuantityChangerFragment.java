package com.codecool.languagetutor.quiz_layout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuantityChangerFragment extends Fragment {

    public static int MAXIMUM_QUANTITY = 25;
    public static int MINIMUM_QUANTITY = 5;

    @BindView(R.id.quantity_text)
    TextView textView;
    @BindView(R.id.seekbar)
    SeekBar seekBar;
    @BindView(R.id.quant_btn)
    Button button;

    QuantityChangerInterface quantityChangerInterface;

    public interface QuantityChangerInterface{
        void setQuantity(int quantity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quantity_changer_fragment,container,false);
        ButterKnife.bind(this, container);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(v -> {
            int quantity = Integer.parseInt(textView.getText().toString());
            quantityChangerInterface.setQuantity(quantity);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float percent = ((float)progress/100) * (float)MAXIMUM_QUANTITY;
                if ( percent < MINIMUM_QUANTITY){
                    percent = MINIMUM_QUANTITY;
                }
                int fixedPercent = Math.round(percent);
                QuantityChangerFragment.this.textView.setText(Integer.toString(fixedPercent));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        quantityChangerInterface = (QuantityChangerInterface)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
