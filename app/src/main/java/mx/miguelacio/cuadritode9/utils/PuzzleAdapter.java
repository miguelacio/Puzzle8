package mx.miguelacio.cuadritode9.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.miguelacio.cuadritode9.R;
import mx.miguelacio.cuadritode9.models.Cell;

/**
 * Created by miguelacio on 19/01/18.
 */

public class PuzzleAdapter extends BaseAdapter {
    public ArrayList<Cell> cellArrayList;
    private Context context;



    public PuzzleAdapter(ArrayList<Cell> cellArrayList, Context context) {
        this.cellArrayList = cellArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cellArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String[] vec_tags = {
                "A3", "B3", "C3",
                "A2", "B2", "C2",
                "A1", "B1", "C1", };
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final Cell cell = cellArrayList.get(position);

        Holder holder = new Holder();
        View rowView;

        rowView = layoutInflater.inflate(R.layout.single_cell, null);
        holder.tv = rowView.findViewById(R.id.text_view_number);


        holder.tv.setText(cell.getValue());

        holder.tv.setTag(R.string.value, cell.getValue());
        holder.tv.setTag(R.string.tag, vec_tags[position]);



        return rowView;
    }

    public class Holder
    {
        TextView tv;
    }


}
