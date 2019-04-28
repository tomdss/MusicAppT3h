package com.t3h.musicplayer.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;

import android.view.View;
import android.widget.Toast;

import com.t3h.musicplayer.R;
import com.t3h.musicplayer.SystemData;
import com.t3h.musicplayer.adapter.PagerAdapter;
import com.t3h.musicplayer.adapter.SongAdapter;

import com.t3h.musicplayer.base.BaseActivity;
import com.t3h.musicplayer.databinding.ActivityMainBinding;
import com.t3h.musicplayer.fragment.AlbumFragment;
import com.t3h.musicplayer.fragment.ArtistFragment;
import com.t3h.musicplayer.fragment.MusicFragment;
import com.t3h.musicplayer.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

public class MainActivity extends BaseActivity<ActivityMainBinding>
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, View.OnFocusChangeListener {

    private MusicFragment fmMusic = new MusicFragment();
    private AlbumFragment fmAlbum = new AlbumFragment();
    private ArtistFragment fmArtist = new ArtistFragment();
    private PagerAdapter adapter;
    private SearchView searchView;
    private List<String> data;
    private ArrayList<Song> dataSearch;
//    private TestAdapter testAdapter;
    private ArrayList<Song> arrSong;
    private SystemData systemData;
    private SongAdapter songAdapter;


    private final String[] PERMISSION_LIST = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };



    @Override
    protected void initAct() {
        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,binding.drawerLayout,binding.toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);

        adapter = new PagerAdapter(this,getSupportFragmentManager(),
                fmMusic,fmAlbum,fmArtist);
        binding.pager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.pager);

        initData();



    }

    private void initData() {
        arrSong = new ArrayList<>();
//        for (int i = 0; i < arrSong.size(); i++) {
//            data.add(arrSong.get(i).getTitle());
//        }

        if(checkPermission()){
            readData();
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_music:
                binding.pager.setCurrentItem(0);
                break;
            case R.id.nav_album:
                binding.pager.setCurrentItem(1);
                break;
            case R.id.nav_artist:
                binding.pager.setCurrentItem(2);
                break;

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //BT VE NHA : CODE SEARCH ACTION BAR , TIM HIEU LIVE DATA(ANDROID JETPACK-ARCHITECH)


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
//        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(this);

        searchView.setOnQueryTextFocusChangeListener(this);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Toast.makeText(this, "Search Success", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        binding.lvTest.setVisibility(View.VISIBLE);
        dataSearch = new ArrayList<>();
        if(s!=null){
            for (Song sv:arrSong) {
                String sv1 = sv.getTitle().toLowerCase();
                String s1 = s.toLowerCase();

                if(sv1.indexOf(s1)!=-1){
                    dataSearch.add(sv);
//                    testAdapter.notifyDataSetChanged();
//                    int n=1;
                }
            }
        }

//        String sss = s;


//        dataSearch.add("Liem");
//        dataSearch.add("Linh");
//        dataSearch.add("Lan");






//        testAdapter = new TestAdapter(this);
//        binding.lvTest.setAdapter(testAdapter);
//        testAdapter.setData(dataSearch);
//        testAdapter.notifyDataSetChanged();

        songAdapter = new SongAdapter(this);
        binding.lvTest.setAdapter(songAdapter);
        songAdapter.setData(dataSearch);

        songAdapter.notifyDataSetChanged();

        return false;
    }


    private void readData() {
        systemData = new SystemData(this);
        arrSong = systemData.getData();
//        arrSong = systemData.getData3();
        //int a=7;
//        songAdapter.setData(arrSong);

//        manager = new MediaManager(arrSong,this);

    }


    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : PERMISSION_LIST) {
                if (checkSelfPermission(p) !=
                        PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(PERMISSION_LIST, 0);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkPermission()) {
            readData();
        } else {
            finish();
        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        //to do
        if(!hasFocus)
        binding.lvTest.setVisibility(View.GONE);
    }
}
