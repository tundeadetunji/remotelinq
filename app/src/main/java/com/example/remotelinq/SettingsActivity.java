package com.example.remotelinq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements PCGroupInputFragment.NoticeDialogListener{

    TextView textPCGroup;
    PrefStore pref;

    RadioGroup groupNotification;
    RadioButton radioStandardNotification;
    RadioButton radioTextToSpeechNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeSettings);
        setContentView(R.layout.activity_settings);

        pref = new PrefStore(getApplicationContext());

        groupNotification = findViewById(R.id.groupNotification);
        radioStandardNotification = findViewById(R.id.radioStandardNotification);
        radioTextToSpeechNotification = findViewById(R.id.radioTextToSpeechNotification);

        groupNotification.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioStandardNotification){
                    pref.setNotification(0);
                }
                else if (checkedId == R.id.radioTextToSpeechNotification){
                    pref.setNotification(1);
                }
            }
        });

        if (pref.getNotification() == 0) {
            radioStandardNotification.setChecked(true);
            radioTextToSpeechNotification.setChecked(false);
        } else if (pref.getNotification() == 1) {
            radioStandardNotification.setChecked(false);
            radioTextToSpeechNotification.setChecked(true);
        } else {
            radioStandardNotification.setChecked(true);
            radioTextToSpeechNotification.setChecked(false);
        }

        textPCGroup = findViewById(R.id.textPCGroup);
        textPCGroup.setText(pref.getPcgroup());
        textPCGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PCGroupInputFragment fragment = new PCGroupInputFragment();
                fragment.show(getSupportFragmentManager(), "PCGroupInput");
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Dialog dialogView = dialog.getDialog();
        TextView textView = dialogView.findViewById(R.id.textNewPCGroup);
        if (textView.getText().toString().trim().length() < 1) return;
        pref.setPcgroup(textView.getText().toString());
        textPCGroup.setText(textView.getText().toString());
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}