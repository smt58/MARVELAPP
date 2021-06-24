package com.app.marvel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customAdapter extends BaseAdapter {
    private LayoutInflater userInflater;
    private String[] list;
    int[] icons;
    Activity activity;
    public customAdapter(Activity activity, String[] list, int[] icons) {
        userInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.activity=activity;
        this.icons=icons;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int i) {
        return list[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View lineView;
        lineView = userInflater.inflate(R.layout.name_character, null);
        TextView charNamesTxt = (TextView) lineView.findViewById(R.id.nameC);
        ImageView imageView=lineView.findViewById(R.id.setImageChar);
        String name = list[i];
        imageView.setImageResource(icons[i]);
        charNamesTxt.setText(name);

        lineView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, CharacterDetails.class);
                intent.putExtra("characterName", list[i]);
                intent.putExtra("position", i);
                activity.startActivity(intent);

            }
        });

        return lineView;
    }
}