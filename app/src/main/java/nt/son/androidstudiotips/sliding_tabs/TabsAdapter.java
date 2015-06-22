package nt.son.androidstudiotips.sliding_tabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import nt.son.androidstudiotips.R;

/**
 * Created by Sonnt on 5/28/15.
 */
public class TabsAdapter extends FragmentPagerAdapter {
    private ArrayList<String> titles;
    public TabsAdapter(Context context, FragmentManager fm) {
        super(fm);
        String []arr = context.getResources().getStringArray(R.array.planets_array);
        titles = new ArrayList<>(Arrays.asList(arr));
    }

    @Override
    public Fragment getItem(int position) {
        return TabsFragment.newInstance(titles.get(position), String.valueOf(position));

    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
