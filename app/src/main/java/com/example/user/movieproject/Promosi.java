package com.example.user.movieproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Promosi extends AppCompatActivity {
    int imgPromosi;
    String judul,isi;
    TextView judulPromosi,isiPromosi;
    ImageView fotoPromosi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promosi);

        getSupportActionBar().setTitle("Detail Promosi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        judulPromosi=(TextView)findViewById(R.id.judul_promosi);
        isiPromosi=(TextView)findViewById(R.id.isi_promosi);
        fotoPromosi=(ImageView)findViewById(R.id.foto_promosi);

        Intent intent = getIntent();

        imgPromosi=intent.getIntExtra("foto",0);
        judul=intent.getStringExtra("judul");
        isi=intent.getStringExtra("isi");

        judulPromosi.setText(judul);
        isiPromosi.setText(isi);
        fotoPromosi.setImageResource(imgPromosi);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_promo,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        if (item.getItemId()==R.id.share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TITLE, judul);
            sendIntent.putExtra(Intent.EXTRA_TEXT, isi);
            startActivity(sendIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
