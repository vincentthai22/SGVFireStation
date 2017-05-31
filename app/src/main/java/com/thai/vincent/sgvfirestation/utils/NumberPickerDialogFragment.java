package com.thai.vincent.sgvfirestation.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by vincent on 3/22/17.
 *
 * Superclass -- DialogFragment
 * NumberPickerDialogFragment is used to create a dialog fragment that has number pickers
 * integrated into the view.
 *
 */

public class NumberPickerDialogFragment extends DialogFragment {

    public interface NumberPickerDialogListener {
        void onFinishedPickingDialog(String result, String dialogCode, float ounces);
    }

    public interface HeightNumberPickerDialogListener {
        void onFinishedPickingDialog(String result, String dialogCode, float height1, float height2);
    }

    public interface DurationNumberPickerDialogListener{
        void onFinishedPickingDuration(String result, int hours, int minutes);
    }

    public NumberPickerDialogFragment(){

    }

    /**
     *
     * @param title
     * @param min
     * @param max
     * @param val
     * @param color
     * @param minTwo
     * @param maxTwo
     * @param valTwo
     * @param dialogCode
     * @return
     *
     * Constructor
     * sets the initial values of the number pickers within the Dialog along with the constraints.
     */
    public static NumberPickerDialogFragment newInstance(String title, int min, int max, int val, int color, int minTwo, int maxTwo, int valTwo, String dialogCode){
        NumberPickerDialogFragment frag = new NumberPickerDialogFragment();
        Bundle args = new Bundle();
        args.putInt("min", min);
        args.putInt("max", max);
        args.putInt("color", color);
        args.putInt("val", val);
        args.putInt("minTwo", minTwo);
        args.putInt("maxTwo", maxTwo);
        args.putInt("valTwo", valTwo);
        args.putString("title", title);
        args.putString("dialogCode", dialogCode);
        frag.setArguments(args);
        return frag;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        int min, max, val, minTwo, maxTwo, valTwo, color;
        final String title, dialogCode;
        final MyNumberPicker leftNumberPicker, rightNumberPicker;
        final LinearLayout ll;

        String[] displayedValues;
        min = getArguments().getInt("min");
        max = getArguments().getInt("max");
        val = getArguments().getInt("val");
        color = getArguments().getInt("color");
        minTwo = getArguments().getInt("minTwo");
        maxTwo = getArguments().getInt("maxTwo");
        valTwo = getArguments().getInt("valTwo");
        title = getArguments().getString("title");
        dialogCode = getArguments().getString("dialogCode");

        //create a custom view for the dialog.
        ll = new LinearLayout(getContext());
        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);



        return builder.create();
    }

}
