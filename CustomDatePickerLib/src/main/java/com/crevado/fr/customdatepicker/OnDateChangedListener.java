package com.crevado.fr.customdatepicker;

/*
  Created by Fazle Rabbi 26,January,2020.
  ┌────────────────────────────────────────
  │ Web: https://fr.crevado.com
  ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
  │ Email: fazle.rabbi.cse@gmail.com
  └────────────────────────────────────────
 */
public interface OnDateChangedListener {
    /**
     * Called upon a date change.
     *
     * @param view        The view associated with this listener.
     * @param year        The year that was set.
     * @param monthOfYear The month that was set (0-11) for compatibility
     *                    with {@link java.util.Calendar}.
     * @param dayOfMonth  The day of the month that was set.
     */
    void onDateChanged(CustomDatePicker view, int year, int monthOfYear, int dayOfMonth);
}
