package com.avisosms.iuri.avisasms.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avisosms.iuri.avisasms.R;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.UndoAdapter;

public class NomeAdapter extends ArrayAdapter<String> implements UndoAdapter {

    private Context mContext;


    public NomeAdapter(final Context context) {
        mContext = context;
        for (int i = 0; i < 20; i++) {
            add(" Iúri Batista Teles " + i);
        }
    }

    @Override
    public long getItemId(final int position) {
        return getItem(position).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_row_dynamiclistview, parent, false);
        }

        ((TextView) view.findViewById(R.id.list_row_draganddrop_textview_titulo)).setText("Paciente:" + getItem(position));
        ((TextView) view.findViewById(R.id.list_row_draganddrop_textview_subtitulo)).setText("Telefone: (79) 9 9670-2237");

        return view;
    }

    @NonNull
    @Override
    public View getUndoView(final int position, final View convertView, @NonNull final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.undo_row, parent, false);
        }
        return view;
    }

    @NonNull
    @Override
    public View getUndoClickView(@NonNull final View view) {
        return view.findViewById(R.id.undo_row_undobutton);
    }
}