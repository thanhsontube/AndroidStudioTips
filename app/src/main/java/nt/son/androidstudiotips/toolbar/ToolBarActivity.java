package nt.son.androidstudiotips.toolbar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseActivity;

public class ToolBarActivity extends BaseActivity {

    Toolbar toolbar;
    Toolbar toolbarBtm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        toolbar = (Toolbar) findViewById(R.id.mToolbar);
        getDelegate().setSupportActionBar(toolbar);


        toolbarBtm = (Toolbar) findViewById(R.id.mToolbar2);
        toolbarBtm.setTitle("ToolBar bottom");
        toolbarBtm.setSubtitle("Sub title");
        toolbarBtm.setLogo(R.mipmap.ic_launcher);
        toolbarBtm.setLogoDescription("logo Description");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tool_bar, menu);
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
}
