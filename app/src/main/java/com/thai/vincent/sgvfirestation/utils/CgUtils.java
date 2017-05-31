package com.thai.vincent.sgvfirestation.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.thai.vincent.sgvfirestation.BuildConfig;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by cg-mayur on 31/1/17.
 */

public class CgUtils {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
    public static SimpleDateFormat timeFormat2 = new SimpleDateFormat("h:mm a");
    public static DecimalFormat decimalFormat = new DecimalFormat();
    public static GregorianCalendar gregorianCalendar = new GregorianCalendar();

    public static final int REQUEST_CODE_FOR_CAMERA = 795;

    private static SimpleDateFormat generalFormat = new SimpleDateFormat();

    public static Boolean isToday(Date date)
    {
        return DateUtils.isToday(date.getTime());
    }

    public static Boolean isEmptyString(String string)
    {
        if (null == string || string.trim().equalsIgnoreCase(""))
        {
            return true;
        }

        return false;
    }


    public static boolean isYesterday(Date date)
    {
        Calendar now = Calendar.getInstance();
        Calendar cdate = Calendar.getInstance();
        cdate.setTimeInMillis(date.getTime());

        now.add(Calendar.DATE,-1);

        return now.get(Calendar.YEAR) == cdate.get(Calendar.YEAR)
                && now.get(Calendar.MONTH) == cdate.get(Calendar.MONTH)
                && now.get(Calendar.DATE) == cdate.get(Calendar.DATE);
    }

    public static long timeDifference(Date date1, Date date2)
    {
        long millisecondDiff = date1.getTime() - date2.getTime();

        long secondsDiff = TimeUnit.MILLISECONDS.toSeconds(millisecondDiff);

        return secondsDiff;
    }

    public static String stringWithFormat(String pattern, Object obj)
    {
        decimalFormat.applyPattern(pattern);
        return decimalFormat.format(obj);
    }


    public static String dateString(Date date, String pattern)
    {
        generalFormat.applyPattern(pattern);
        return generalFormat.format(date);
    }

    public static Date midnight(Date date)
    {
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);
        gregorianCalendar.set(Calendar.MILLISECOND, 0);

        return gregorianCalendar.getTime();
    }

    public static Date addDaysToDate(Date date, int days)
    {
        return CgUtils.addUnitToDate(date, Calendar.DATE, days);
    }

    public static Date addSecondsToDate(Date date, int seconds)
    {    return CgUtils.addUnitToDate(date, Calendar.SECOND, seconds);
    }


    public static Date addUnitToDate(Date date, int field, int value)
    {
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(field, value);

        return gregorianCalendar.getTime();
    }

    public static Date getTomorrowDate()
    {
        Date today = CgUtils.getTodayDate();
        gregorianCalendar.add(Calendar.DATE, 1);

        return gregorianCalendar.getTime();
    }

    public static Date getFirstDateOfWeek()
    {
        CgUtils.getTodayDate();
        gregorianCalendar.set(Calendar.DAY_OF_WEEK,gregorianCalendar.getFirstDayOfWeek());

        return gregorianCalendar.getTime();
    }

    public static Date nowWithNoSeconds()
    {
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.SECOND, 0);
        gregorianCalendar.set(Calendar.MILLISECOND, 0);

        return gregorianCalendar.getTime();
    }


    public static Date getFirstDateOfNextWeek()
    {
        CgUtils.getFirstDateOfWeek();
        gregorianCalendar.add(Calendar.WEEK_OF_YEAR, 1);

        return gregorianCalendar.getTime();
    }

    public static Date getFirstDateOfMonth()
    {
        CgUtils.getTodayDate();
        gregorianCalendar.set(Calendar.DAY_OF_MONTH,1);

        return gregorianCalendar.getTime();
    }

    public static Date getFirstDateOfNextMonth()
    {
        CgUtils.getFirstDateOfMonth();
        gregorianCalendar.add(Calendar.MONTH, 1);

        return gregorianCalendar.getTime();
    }

    public static Date getTodayDate()
    {
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);
        gregorianCalendar.set(Calendar.MILLISECOND, 0);

        return gregorianCalendar.getTime();
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics.heightPixels;

    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    public static int getStatusBarHeight(Context context) {
        Resources res = context.getResources();

        int result = 0;
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void showLog(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void setPic(ImageView mImageView, String mCurrentPhotoPath) {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }

    public static double divide(double dividend, double divisor)
    {
        if (divisor == 0)
        {
            return 0;
        }

        return dividend / divisor;
    }

    public static void alertOk(Context context, String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


}
