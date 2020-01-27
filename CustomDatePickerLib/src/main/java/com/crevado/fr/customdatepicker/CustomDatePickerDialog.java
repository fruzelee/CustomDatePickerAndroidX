package com.crevado.fr.customdatepicker;
/*
  Created by Fazle Rabbi 26,January,2020.
  ┌────────────────────────────────────────
  │ Web: https://fr.crevado.com
  ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
  │ Email: fazle.rabbi.cse@gmail.com
  └────────────────────────────────────────
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * A fork of the Android Open Source Project CustomDatePickerDialog class
 */
public class CustomDatePickerDialog extends AlertDialog implements OnClickListener,
        OnDateChangedListener {

    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";
    private static final String TITLE_SHOWN = "title_enabled";
    private static final String CUSTOM_TITLE = "custom_title";

    private final CustomDatePicker mCustomDatePicker;
    private final OnDateSetListener mCallBack;
    private final OnDateCancelListener mOnCancel;
    private final DateFormat mTitleDateFormat;

    private boolean mIsDayShown = true;
    private boolean mIsTitleShown = true;
    private String mCustomTitle = "";

    /**
     * The callback used to indicate the user is done filling in the date.
     */
    public interface OnDateSetListener {
        /**
         * @param view        The view associated with this listener.
         * @param year        The year that was set
         * @param monthOfYear The month that was set (0-11) for compatibility
         *                    with {@link java.util.Calendar}.
         * @param dayOfMonth  The day of the month that was set.
         */
        void onDateSet(CustomDatePicker view, int year, int monthOfYear, int dayOfMonth);
    }

    /**
     * Callback for when things are cancelled
     */
    public interface OnDateCancelListener {
        /**
         * Called when cancel happens.
         *
         * @param view The view associated with this listener.
         */
        void onCancelled(CustomDatePicker view);
    }

    CustomDatePickerDialog(Context context,
                           int theme,
                           int spinnerTheme,
                           OnDateSetListener callBack,
                           OnDateCancelListener onCancel,
                           Calendar defaultDate,
                           Calendar minDate,
                           Calendar maxDate,
                           boolean isDayShown,
                           boolean isTitleShown,
                           String customTitle) {
        super(context, theme);

        mCallBack = callBack;
        mOnCancel = onCancel;
        mTitleDateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        mIsDayShown = isDayShown;
        mIsTitleShown = isTitleShown;
        mCustomTitle = customTitle;

        updateTitle(defaultDate);

        setButton(BUTTON_POSITIVE, context.getText(android.R.string.ok),
                this);
        setButton(BUTTON_NEGATIVE, context.getText(android.R.string.cancel),
                this);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.date_picker_dialog_container, null);
        setView(view);
        mCustomDatePicker = new CustomDatePicker((ViewGroup) view, spinnerTheme);
        mCustomDatePicker.setMinDate(minDate.getTimeInMillis());
        mCustomDatePicker.setMaxDate(maxDate.getTimeInMillis());
        mCustomDatePicker.init(defaultDate.get(Calendar.YEAR), defaultDate.get(Calendar.MONTH), defaultDate.get(Calendar.DAY_OF_MONTH), isDayShown, this);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case BUTTON_POSITIVE: {
                if (mCallBack != null) {
                    mCustomDatePicker.clearFocus();
                    mCallBack.onDateSet(mCustomDatePicker, mCustomDatePicker.getYear(),
                            mCustomDatePicker.getMonth(), mCustomDatePicker.getDayOfMonth());
                }
                break;
            }
            case BUTTON_NEGATIVE: {
                if (mOnCancel != null) {
                    mCustomDatePicker.clearFocus();
                    mOnCancel.onCancelled(mCustomDatePicker);
                }
                break;
            }
        }
    }

    @Override
    public void onDateChanged(CustomDatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar updatedDate = Calendar.getInstance();
        updatedDate.set(Calendar.YEAR, year);
        updatedDate.set(Calendar.MONTH, monthOfYear);
        updatedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateTitle(updatedDate);
    }

    private void updateTitle(Calendar updatedDate) {
        if (mIsTitleShown && mCustomTitle != null && !mCustomTitle.isEmpty()) {
            setTitle(mCustomTitle);
        } else if (mIsTitleShown) {
            final DateFormat dateFormat = mTitleDateFormat;
            setTitle(dateFormat.format(updatedDate.getTime()));
        } else {
            setTitle(" ");
        }
    }

    @Override
    public Bundle onSaveInstanceState() {
        Bundle state = super.onSaveInstanceState();
        state.putInt(YEAR, mCustomDatePicker.getYear());
        state.putInt(MONTH, mCustomDatePicker.getMonth());
        state.putInt(DAY, mCustomDatePicker.getDayOfMonth());
        state.putBoolean(TITLE_SHOWN, mIsTitleShown);
        state.putString(CUSTOM_TITLE, mCustomTitle);
        return state;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int year = savedInstanceState.getInt(YEAR);
        int month = savedInstanceState.getInt(MONTH);
        int day = savedInstanceState.getInt(DAY);
        mIsTitleShown = savedInstanceState.getBoolean(TITLE_SHOWN);
        mCustomTitle = savedInstanceState.getString(CUSTOM_TITLE);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        updateTitle(c);
        mCustomDatePicker.init(year, month, day, mIsDayShown, this);
    }
}