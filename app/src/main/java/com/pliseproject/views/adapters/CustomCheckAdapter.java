package com.pliseproject.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.pliseproject.R;
import com.pliseproject.models.CustomCheckData;

import java.util.List;

import static butterknife.ButterKnife.findById;

/**
 * 設定リストのAdapterです。
 */
public class CustomCheckAdapter extends ArrayAdapter<CustomCheckData> {
    LayoutInflater mInflater;

    /**
     * ホルダクラス。
     */
    class ViewHolder {
        TextView textView;
        CheckBox checkBox;

        ViewHolder(View view) {
            textView = findById(view, R.id.is_alarm);
            checkBox = findById(view, R.id.check_alarm);
        }
    }

    /**
     * コンストラクタ
     *
     * @param context コンテキスト
     * @param objects 対象のリスト
     */
    public CustomCheckAdapter(Context context, List<CustomCheckData> objects) {
        super(context, 0, objects);
        this.mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = this.mInflater.inflate(R.layout.row_check, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final CustomCheckData data = getItem(position);
        holder.textView.setText(data.getText());
        holder.checkBox
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        data.setCheckFlag(isChecked);
                    }
                });

        holder.checkBox.setChecked(data.isCheckFlag());

        return convertView;
    }
}
