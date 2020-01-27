package com.crevado.fr.customdatepicker;
/*
  Created by Fazle Rabbi 26,January,2020.
  ┌────────────────────────────────────────
  │ Web: https://fr.crevado.com
  ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
  │ Email: fazle.rabbi.cse@gmail.com
  └────────────────────────────────────────
 */

import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * Common date utilities
 */
public class CommonDateUtils {

    public static final SimpleDateFormat NO_YEAR_DATE_FORMAT = new SimpleDateFormat("--MM-dd", Locale.US);
    public static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    public static final SimpleDateFormat DATE_AND_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
    public static final SimpleDateFormat NO_YEAR_DATE_AND_TIME_FORMAT = new SimpleDateFormat("--MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

    public final static int DEFAULT_HOUR = 8;

}
