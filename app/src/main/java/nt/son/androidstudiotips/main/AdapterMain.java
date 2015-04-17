package nt.son.androidstudiotips.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import nt.son.androidstudiotips.R;

/**
 * Created by Sonnt on 4/17/15.
 */
public class AdapterMain extends ArrayAdapter<MainDto> {
    Context context;
    List<MainDto> list;

    public AdapterMain(Context context, List<MainDto> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_main, parent, false);
            holder = new Holder();
            holder.txt = (TextView) view.findViewById(R.id.row_main_txt);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        MainDto dto = list.get(position);
        holder.txt.setText(dto.name);
        return view;

    }

    static class Holder {
        TextView txt;
    }

}
