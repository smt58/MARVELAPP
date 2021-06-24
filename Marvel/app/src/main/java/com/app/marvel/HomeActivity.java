package com.app.marvel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    customAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        String[] charNames = new String[]{
                "Black Panther", "Black Widow", "Bucky", "Captain America", "Captain Marvel (Carol Danvers)",
                "Deadpool", "Drax", "Falcon", "Groot", "Hawkeye", "Hulk", "Iron Man", "Korg",
                "Loki", "Mantis", "Nebula", "Nick Fury", "Okoye", "Rocket Raccoon",
                "Scarlet Witch", "Spider-Man", "Thanos", "Thor", "Vision", "Wasp", "Wolverine"
        };


        int[] character_icon_ids = {
                R.drawable.black_panther_icon, R.drawable.black_widow_icon, R.drawable.bucky_icon, R.drawable.captain_america_icon, R.drawable.captain_marvel_icon,
                R.drawable.deadpool_icon, R.drawable.drax_icon, R.drawable.falcon_icon, R.drawable.groot_icon, R.drawable.hawkeye_icon,
                R.drawable.hulk_icon, R.drawable.iron_man_icon, R.drawable.korg_icon, R.drawable.loki_icon, R.drawable.mantic_icon,
                R.drawable.nebula_icon, R.drawable.nick_fury_icon, R.drawable.okoye_icon, R.drawable.rocket_racoon_icon,
                R.drawable.scarlet_witch_icon, R.drawable.spider_man_icon, R.drawable.thanos_icon, R.drawable.thor_icon,
                R.drawable.vision_icon, R.drawable.wasp_icon, R.drawable.wolverine_icon
        };

        ListView listView=findViewById(R.id.listview);

        //adaaa=new ArrayAdapter(this,R.layout.name_character,charNames);
        //listView.setAdapter(adaaa);



        pagerAdapter = new customAdapter(HomeActivity.this, charNames,character_icon_ids);
        listView.setAdapter(pagerAdapter);





    }



    public void youtubeVideosClicked(View view) {

        startActivity(new Intent(HomeActivity.this, YoutubeCharacterListActivity.class));

    }
}