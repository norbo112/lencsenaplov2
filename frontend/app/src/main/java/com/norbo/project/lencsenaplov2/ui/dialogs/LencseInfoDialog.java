package com.norbo.project.lencsenaplov2.ui.dialogs;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.databinding.LencseListItemBinding;
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.ui.utils.DataUtils;

import javax.inject.Inject;

public class LencseInfoDialog {
    private static final String TAG = "LencseInfoDialog";
    private Context context;

    @Inject
    DataUtils utils;

    public LencseInfoDialog(Context context) {
        this.context = context;
        ((LencsenaploApplication)context.getApplicationContext()).getGraph().inject(this);
    }

    public void showDialog(String title, String message, Lencse lencse) {
        LencseListItemBinding itemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.lencse_list_item, null, false);
        itemBinding.setLencseadat(lencse);
        itemBinding.setDataUtils(utils);

        new AlertDialog.Builder(context, R.style.MyTheme_Dialog)
                .setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_info_e)
                .setView(itemBinding.getRoot())
                .setPositiveButton("Ok", (dialog, which) -> {
                    dialog.dismiss();
                }).create().show();
    }
}
