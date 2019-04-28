package com.t3h.musicplayer.fragment;

import com.t3h.musicplayer.R;
import com.t3h.musicplayer.SystemData;
import com.t3h.musicplayer.adapter.SongAdapter;
import com.t3h.musicplayer.base.BaseFragment;
import com.t3h.musicplayer.databinding.FragmentMusicBinding;
import com.t3h.musicplayer.model.Song;

import java.util.ArrayList;

public class MusicFragment extends BaseFragment<FragmentMusicBinding> {

    private ArrayList<Song> data;
    private SongAdapter adapter;
    private SystemData systemData;

    @Override
    protected void initViews() {
        adapter = new SongAdapter(this.getActivity());
        binding.lvFragMusic.setAdapter(adapter);
        systemData = new SystemData(this.getActivity());
        data = systemData.getData();
        adapter.setData(data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    public int getTitle() {
        return R.string.music;
    }



}
