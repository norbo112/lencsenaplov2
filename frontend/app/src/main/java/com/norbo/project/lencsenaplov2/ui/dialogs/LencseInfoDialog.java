package com.norbo.project.lencsenaplov2.ui.dialogs;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.databinding.LencseListItemBinding;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LencseInfoDialog {
    private Context context;

    @Inject
    public LencseInfoDialog(Context context) {
        this.context = context;
    }

    public void showDialog(String title, String message, Lencse lencse) {
        LencseListItemBinding itemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.lencse_list_item, null, false);
        itemBinding.setLencseadat(lencse);

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_info_e)
                .setView(itemBinding.getRoot())
                .setPositiveButton("Ok", (dialog, which) -> {
                    dialog.dismiss();
                }).create().show();
    }
}
