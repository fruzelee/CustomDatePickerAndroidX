package com.crevado.fr.customdatepicker;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CustomDatePickerDialog.OnDateSetListener , CustomDatePickerDialog.OnDateCancelListener{

    TextView dateTextView;
    Button dateButton;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateButton = (Button) findViewById(R.id.set_date_button);
        dateTextView = (TextView) findViewById(R.id.date_textview);
        simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.US);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate(1990, 0, 1, R.style.DatePickerSpinner);
            }
        });
    }

    @Override
    public void onDateSet(CustomDatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        dateTextView.setText(simpleDateFormat.format(calendar.getTime()));
    }

    @Override
    public void onCancelled(CustomDatePicker view) {
        dateTextView.setText(R.string.cancelled);
    }


    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(MainActivity.this)
                .callback(MainActivity.this)
                .onCancel(MainActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }


}