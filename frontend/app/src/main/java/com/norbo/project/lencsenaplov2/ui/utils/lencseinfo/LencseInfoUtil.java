package com.norbo.project.lencsenaplov2.ui.utils.lencseinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.databinding.LencseListItemBinding;
import com.norbo.project.lencsenaplov2.ui.utils.DataUtils;
import com.norbo.project.lencsenaplov2.ui.utils.FormatUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class LencseInfoUtil {
    private static final String TAG = "LencseInfoDialog";
    private Context context;
    private DataUtils dataUtils;

    @Inject
    public LencseInfoUtil(Context context, DataUtils dataUtils) {
        this.context = context;
        this.dataUtils = dataUtils;
    }

    public void showDialog(String title, String message, Lencse lencse) {
        LencseListItemBinding itemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.lencse_list_item, null, false);
        itemBinding.setLencseadat(lencse);
        itemBinding.setDataUtils(dataUtils);

        new AlertDialog.Builder(context, R.style.MyTheme_Dialog)
                .setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_info_e)
                .setView(itemBinding.getRoot())
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss()).create().show();
    }

    public LencseInfoUtil.Info getInfo(List<Lencse> list) {
        return getInfoMsg(list);
    }

    @SuppressLint("DefaultLocale")
    private LencseInfoUtil.Info getInfoMsg(List<Lencse> lencseList) {
        LencseInfoUtil.Info info = new LencseInfoUtil.Info();
        info.setCountMsg(lencseList.size()+" db bejegyzés mentve");
        Lencse max = Collections.max(lencseList, getLencseComparator());
        Lencse min = Collections.min(lencseList, getLencseComparator());
        float elteltIdoMax = dataUtils.elapsedTimeFloat(max.getBetetelIdopont(), max.getKivetelIdopont());
        float elteltIdoMin = dataUtils.elapsedTimeFloat(min.getBetetelIdopont(), min.getKivetelIdopont());

        String format = "%s: %s \n%.0f óra és %.0f perc";

        info.setMaxMsg(String.format(format,
                "Legtöbbet viselt nap",
                FormatUtils.getDayShortFormat(max.getBetetelIdopont()),
                Math.floor(elteltIdoMax/60), Math.floor(elteltIdoMax % 60), " perc."));

        info.setMinMsg(String.format(format,
                "Legkevesebb nap",
                FormatUtils.getDayShortFormat(min.getBetetelIdopont()),
                Math.floor(elteltIdoMin/60), Math.floor(elteltIdoMin % 60), " perc."));
        return info;
    }

    private Comparator<Lencse> getLencseComparator() {
        return (o1, o2) -> {
            long o1_elt = o1.getKivetelIdopont() - o1.getBetetelIdopont();
            long o2_elt = o2.getKivetelIdopont() - o2.getBetetelIdopont();
            return Long.compare(o1_elt, o2_elt);
        };
    }

    public float elapsedTimeFloat(Lencse lencse) {
        return dataUtils.elapsedTimeFloat(lencse.getBetetelIdopont(), lencse.getKivetelIdopont());
    }

    public class Info {
        private String countMsg;
        private String minMsg;
        private String maxMsg;

        public String getCountMsg() {
            return countMsg;
        }

        public void setCountMsg(String countMsg) {
            this.countMsg = countMsg;
        }

        public String getMinMsg() {
            return minMsg;
        }

        public void setMinMsg(String minMsg) {
            this.minMsg = minMsg;
        }

        public String getMaxMsg() {
            return maxMsg;
        }

        public void setMaxMsg(String maxMsg) {
            this.maxMsg = maxMsg;
        }
    }
}
