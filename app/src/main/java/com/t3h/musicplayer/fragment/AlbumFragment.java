package com.t3h.musicplayer.fragment;

import com.t3h.musicplayer.R;
import com.t3h.musicplayer.base.BaseFragment;
import com.t3h.musicplayer.databinding.FragmentAlbumBinding;

public class AlbumFragment extends BaseFragment<FragmentAlbumBinding> {
    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_album;
    }

    @Override
    public int getTitle() {
        return R.string.album;
    }
}
