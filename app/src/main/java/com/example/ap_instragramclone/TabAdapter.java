package com.example.ap_instragramclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {

        switch (tabPosition){
            case 0:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            case 1:
                UserFragment userFragment = new UserFragment();
                return userFragment;
            case 2:
                return new SharePictureFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Profile";
            case 1:
                return "Users";
            case 2:
                return "Share Picture";
            default:
                return null;
        }
    }
}
