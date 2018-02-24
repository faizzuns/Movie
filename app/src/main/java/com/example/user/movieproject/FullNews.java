package com.example.user.movieproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FullNews extends AppCompatActivity {
    String[] judulNews,isiNews;
    ListView listAllNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);

        getSupportActionBar().setTitle("Cinema News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listAllNews=(ListView)findViewById(R.id.list_allnews);

        Intent intent = getIntent();

        judulNews=intent.getStringArrayExtra("judulNews");
        isiNews=intent.getStringArrayExtra("isiNews");

        CustomList adapter= new CustomList(FullNews.this,judulNews,isiNews,R.layout.item_all_news,R.id.text_Allnews,R.id.text_AllsubNews);
        listAllNews.setAdapter(adapter);

        listAllNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailNews.class);
                intent.putExtra("judulDetail",judulNews[position]);
                intent.putExtra("isiDetail",isiNews[position]);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
