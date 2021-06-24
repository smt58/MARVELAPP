package com.app.marvel;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CharacterDetails extends AppCompatActivity {

    JSONObject characterDetailsObject;
    TextView name,diziler,stores,etkinlikler,cizgiRoman;
    ImageView img_character_icon;
    String  description;
    int[] character_icon_ids = {
            R.drawable.black_panther_icon, R.drawable.black_widow_icon, R.drawable.bucky_icon, R.drawable.captain_america_icon, R.drawable.captain_marvel_icon,
            R.drawable.deadpool_icon, R.drawable.drax_icon, R.drawable.falcon_icon, R.drawable.groot_icon, R.drawable.hawkeye_icon,
            R.drawable.hulk_icon, R.drawable.iron_man_icon, R.drawable.korg_icon, R.drawable.loki_icon, R.drawable.mantic_icon,
            R.drawable.nebula_icon, R.drawable.nick_fury_icon, R.drawable.okoye_icon, R.drawable.rocket_racoon_icon,
            R.drawable.scarlet_witch_icon, R.drawable.spider_man_icon, R.drawable.thanos_icon, R.drawable.thor_icon,
            R.drawable.vision_icon, R.drawable.wasp_icon, R.drawable.wolverine_icon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        final String characterName = getIntent().getStringExtra("characterName");
        final int position = getIntent().getIntExtra("position", 0);

        Initialization();


        img_character_icon.setImageResource(character_icon_ids[position]);


        String timeStamp = new Timestamp(System.currentTimeMillis()) + "";
        timeStamp = timeStamp.replaceAll("\\s", "");


        //Use your own Marvel private API key here
        String MARVEL_PRIVATE_API_KEY = getString(R.string.marvel_private_api_key);

        //Use your own Marvel public API key here
        String MARVEL_PUBLIC_API_KEY = getString(R.string.marvel_public_api_key);

        String FinalAPIHashingString = timeStamp+MARVEL_PRIVATE_API_KEY+MARVEL_PUBLIC_API_KEY;

        String hashedValue = GenerateMD5Hash.digest(FinalAPIHashingString);

        String requestUrl = "https://gateway.marvel.com/v1/public/characters?name="+characterName+"&ts="+timeStamp+"&apikey=27d73a9bd200536337e5a4ee2e3ddf61&hash="+hashedValue;
        requestUrl=requestUrl.replaceAll("\\s", "%20");

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject object1 = response.getJSONObject("data");
                    JSONArray array1 = object1.getJSONArray("results");
                    characterDetailsObject = array1.getJSONObject(0);

                    description = characterDetailsObject.getString("description");
                    if(description.isEmpty()){
                        name.setText(characterName);
                    }else{
                        name.setText(description);
                    }

                    String comicsString= getDataString(getData("comics"));
                    String seriesString=  getDataString(getData("series"));
                    String storiesString= getDataString(( getData("stories")));
                    String eventsString=  getDataString(getData(("events")));

                    if(storiesString!=null)
                        stores.setText(storiesString);
                    if(eventsString!=null)
                        etkinlikler.setText(eventsString);
                    if(seriesString!=null)
                        diziler.setText(seriesString);
                    if(comicsString!=null)
                        cizgiRoman.setText(comicsString);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CharacterDetails.this, "Network  hata verdiError.", Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(request);

    }

    public String getDataString(List<String> array){
        String arg="\n";
        for (String stories:array){
            arg+=stories;
            arg=arg +"\n";
        }

        return  arg;

    }

    public  List<String> getData(String typeOfData){

        List<String> ItemsList= new ArrayList<>();

        try {
            JSONObject object4 = characterDetailsObject.getJSONObject(typeOfData);
            JSONArray array3 = object4.getJSONArray("items");
            for (int i=0; i<array3.length(); i++){

                JSONObject object = array3.getJSONObject(i);
                ItemsList.add(object.getString("name"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  ItemsList;

    }


    private void Initialization() {
        img_character_icon=findViewById(R.id.imageView);
        name=findViewById(R.id.nameDetail);
        diziler=findViewById(R.id.diziler);
        stores=findViewById(R.id.stores);
        cizgiRoman=findViewById(R.id.cizgiRoman);
        etkinlikler=findViewById(R.id.etkinlikler);

    }
}