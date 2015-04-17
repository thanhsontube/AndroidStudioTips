package nt.son.androidstudiotips.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * Created by Sonnt on 4/17/15.
 */
public abstract class BaseFragment extends Fragment {
    protected FragmentManager fragmentManager;

    protected FragmentManager getSafeFragmentManager() {
        if (fragmentManager == null) {
            fragmentManager = getFragmentManager();
        }
        return fragmentManager;
    }

    public abstract void initData();
    public abstract void initLayout(View view);
    public abstract void initListener();
}
