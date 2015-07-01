package com.mycompany.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class myAdapter extends BaseAdapter {

    private final List<Contacts> data;
    private static LayoutInflater inflater = null;

    public myAdapter(Context context, List<Contacts> savedContacts) 
    {
        this.data = savedContacts;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Contacts getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row, null);
        TextView text = (TextView) vi.findViewById(R.id.rowText);
        ImageView image = (ImageView) vi.findViewById(R.id.rowImage);
        Contacts contact = data.get(position);
        text.setText(contact.getName());
        image.setTag(data.get(position));

        return vi;
    }
}
