package com.t3h.musicplayer.fragment;

import com.t3h.musicplayer.R;
import com.t3h.musicplayer.base.BaseFragment;
import com.t3h.musicplayer.databinding.FragmentArtistBinding;

public class ArtistFragment extends BaseFragment<FragmentArtistBinding> {
    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_artist;
    }

    @Override
    public int getTitle() {
        return R.string.artist;
    }
}
