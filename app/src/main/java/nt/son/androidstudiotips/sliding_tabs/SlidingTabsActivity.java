package nt.son.androidstudiotips.sliding_tabs;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseActivity;
import nt.son.androidstudiotips.sliding_tabs.extras.SlidingTabLayout;

public class SlidingTabsActivity extends BaseActivity implements TabsFragment.OnFragmentInteractionListener{
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private TabsAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tabs);
        initLayout();
    }

    private void initLayout() {
        viewPager = (ViewPager) findViewById(R.id.tabs_view_pager);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs_sliding_layout);
        adapter = new TabsAdapter(this, getSafeFragmentManager());
        viewPager.setAdapter(adapter);
        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.md_blue_500));
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.md_red_500));
        slidingTabLayout.setViewPager(viewPager);
//        slidingTabLayout.setHorizontalScrollBarEnabled(true);
//        slidingTabLayout.setHorizontalFadingEdgeEnabled(true);

        toolbar = (Toolbar) findViewById(R.id.tabs_toolbar);
        toolbar.setTitle("Son handsome");
        getDelegate().setSupportActionBar(toolbar);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sliding_tabs, menu);
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
}
