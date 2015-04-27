package nt.son.androidstudiotips.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import nt.son.androidstudiotips.MainFragment;
import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseActivity;
import nt.son.androidstudiotips.retrofit.RetrofitFragment;
import nt.son.androidstudiotips.shape.ShapeFragment;


public class MainActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add MainFragment
        if (savedInstanceState == null) {
            Fragment mainF = MainFragment.newInstance("", "");
            FragmentTransaction ft = getSafeFragmentManager().beginTransaction();
            ft.add(R.id.ll_main, mainF, "main-f");
            ft.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onReplaceFragment(int pos) {
        FragmentTransaction ft = getSafeFragmentManager().beginTransaction();
        Fragment f;
        switch (pos) {
            case 1:
                 f = new RetrofitFragment();
                ft.replace(R.id.ll_main, f, "retrofit");
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();

                break;
            case 3:
                 f = new ShapeFragment();
                ft.replace(R.id.ll_main, f, "shape");
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;
        }
    }
}
