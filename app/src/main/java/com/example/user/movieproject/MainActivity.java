package com.example.user.movieproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.user.movieproject.adapter.MovieAdapter;
import com.example.user.movieproject.adapter.TopListener;
import com.example.user.movieproject.topMovies.Result;
import com.example.user.movieproject.topMovies.TopMovieModel;
import com.example.user.movieproject.topMovies.TopService;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TopListener {
    //deklarasi kamus
    private ImageView ImageUtama;
    private ImageView ArrowLeft,ArrowRight;
    private int i,j; //variabel untuk menghitung index image utama
    private ListView lvNews;
    private Button btnNews,btnSeeAll,btnTheater;
    private RecyclerView rvTopMovie;
    MovieAdapter movieadapter;
    ArrayList<Movie> listTopMovie;


    //fake image
    int[] gambarUtama=new int[]{
            R.drawable.promo1,
            R.drawable.promo2,
            R.drawable.promo3
    };

    String[] judulPromosi=new String[]{
            "Buy 1 get 1",
            "Diskon 50% with Kecap ABC",
            "Potongan harga 35.000"
    };

    String[] isiPromosi=new String[]{
            "nah sekarang saat kamu pakai kartu ATM ABG!, kartu ini akan membuat kamu senang karena kamu bisa mendapatkan gratis 1 tiket untuk pembelian 1 tiket",
            "diskon ini berlaku jika kamu belanja di toko mataAri Bandung Indah Plaza. Kupon ini dapat kamu tukarkan di IndoMalet terdekat dengan membawa data data diri",
            "kamu bisa mendapatkan potongan harga sebesar 35.000 jika kamu berhasil menjadi rangking 1-3 besar di kelasmu! semangat Ujian Akhir Semester"
    };

    //fake news
    String[] news = new String[]{
            "Cinema XIX Bekasi Hadir!",
            "Jam Tutup Cinema XIX",
            "Popcorn dan minuman telah hadir",
            "Theater dengan wajah baru"
    };
    String[] isi = new String[]{
            "cinema XIX akan hadir di seluruh penjuru Idonesia, oleh karena itu, kami memulainya dari Bekas dahulu yang natinya akan kami teruskan ke jakarta,padang dan lain lain",
            "Dikarenakan sekarang lagi rawan sekali dengan yang namanya geng motor, kami dari pihak Cinema XIX tidak mengadakan theater pada jam malam (Pukul 21.00 keatas)",
            "nah ini nih yang telah di tunggu tunggu oleh para penonton semua!!sekarang kami telah menyediakan popcorn dan minman segar yang dapat dibeli di kasir",
            "Theater theater kami telah kami perbaharui dari mulai at,tempat duduk,dan tangga.dan juga karpet nya sudah kami cuci dengan sangat bersih"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //menyetting toolbar
        getSupportActionBar().setTitle("Cinema XIX");
        getSupportActionBar().setSubtitle("Cinema Inspirasi Bangsa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //inisialisasi variabel
        j=0;
        ArrowLeft=(ImageView) findViewById(R.id.arrow_left);
        ArrowRight=(ImageView)findViewById(R.id.arrow_right);
        ImageUtama=(ImageView)findViewById(R.id.image_utama);
        lvNews=(ListView)findViewById(R.id.lv_news);
        btnNews=(Button)findViewById(R.id.btn_news);
        btnSeeAll=(Button)findViewById(R.id.btn_see_all);
        rvTopMovie=(RecyclerView)findViewById(R.id.rv_top_movie);
        btnTheater=(Button)findViewById(R.id.btn_see_theater);

        btnTheater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AllTheater.class);
                startActivity(intent);
            }
        });

        rvTopMovie.setHasFixedSize(true);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvTopMovie.setLayoutManager(layoutManager);

        //buat cek siapa aja movie yang jadi top movie
        listTopMovie = new ArrayList<>();

        movieadapter = new MovieAdapter(listTopMovie,this);
        rvTopMovie.setAdapter(movieadapter);

        callTopMovieApi();

        btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AllMovie.class);
                startActivity(intent);
            }
        });

        //geser geser image utama
        final Handler handler=new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Picasso.with(getApplicationContext()).load(gambarUtama[j]).into(ImageUtama);
                handler.postDelayed(this, 7000);  //for interval 4s..
                j=(j+1)%gambarUtama.length;
            }
        };
        handler.postDelayed(runnable, 100);

        //program ketika image utama di-klik
        ImageUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=(j+(gambarUtama.length-1))%gambarUtama.length;
                Intent intent = new Intent(getApplicationContext(), Promosi.class);
                intent.putExtra("foto",gambarUtama[x]);
                intent.putExtra("judul",judulPromosi[x]);
                intent.putExtra("isi",isiPromosi[x]);
                startActivity(intent);
            }
        });

        //program jika button news di klik
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FullNews.class);
                intent.putExtra("judulNews",news);
                intent.putExtra("isiNews",isi);
                startActivity(intent);
            }
        });

        //program ketika arrow di klik
        /*arrow left*/
        ArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j=(j+(gambarUtama.length-2))%gambarUtama.length;
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 100);
            }
        });
        /*arrow right*/
        ArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 100);
            }
        });

        final String[] newsUtama=new String[]{
                news[0],
                news[1],
                news[2]
        };
        final String[] isiUtama=new String[]{
                isi[0],
                isi[1],
                isi[2]
        };
        CustomList adapter=new CustomList(MainActivity.this,newsUtama,isiUtama,R.layout.list_item,R.id.text_news,R.id.text_subNews);
        lvNews.setAdapter(adapter);

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(), DetailNews.class);
                intent.putExtra("judulDetail",newsUtama[position]);
                intent.putExtra("isiDetail",isiUtama[position]);
                startActivity(intent);
            }
        });

        lvNews.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });


        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    private void callTopMovieApi() {
        final ArrayList<Movie> listTopMovie = new ArrayList<>();

        TopService service = ApiHelper.client().create(TopService.class);

        Call<TopMovieModel> call = service.getTopMovie("d180ce17a02d3acb8fc79d2adc16fe96");

        String tes;
        String[] split;

        call.enqueue(new Callback<TopMovieModel>() {
            @Override
            public void onResponse(Call<TopMovieModel> call, Response<TopMovieModel> response) {
                List<Result> popularList = response.body().getResults();
                for (Result rSult : popularList){

                    String date = rSult.getReleaseDate();
                    String poster = rSult.getPosterPath();
                    String synopsis = rSult.getOverview();
                    String judul = rSult.getTitle();
                    double rating = rSult.getVoteAverage();
                    int id = rSult.getId();

                    Movie movie = new Movie();
                    movie.setId(id);
                    movie.setDate(date);
                    movie.setMovie(poster);
                    movie.setSynopsis(synopsis);
                    movie.setJudul(judul);
                    movie.setRating(rating);

                    listTopMovie.add(movie);

                }
                Log.d("tes", String.valueOf(listTopMovie.size()));
                movieadapter.refreshData(listTopMovie);

            }

            @Override
            public void onFailure(Call<TopMovieModel> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClickTop(int position,ArrayList<Movie> listTopMovie) {
        Intent intent =new Intent(getApplicationContext(), DetailMovie.class);
        intent.putExtra("poster",listTopMovie.get(position).getMovie());
        intent.putExtra("judul",listTopMovie.get(position).getJudul());
        intent.putExtra("date",listTopMovie.get(position).getDate());
        intent.putExtra("synopsis",listTopMovie.get(position).getSynopsis());
        intent.putExtra("rating",listTopMovie.get(position).getRating());
        intent.putExtra("id",listTopMovie.get(position).getId());
        startActivity(intent);
    }
}
