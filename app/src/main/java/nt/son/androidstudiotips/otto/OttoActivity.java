package nt.son.androidstudiotips.otto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseActionBarActivity;
import nt.son.androidstudiotips.main.MainDto;

public class OttoActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otto);
        txt = (TextView) findViewById(R.id.otto_txt_main);

        if (savedInstanceState == null) {
            Fragment topF = new OttoFragmentTop();
            Fragment btmF = new OttoFragmentBottom();
            FragmentTransaction ft = getSafeFragmentManager().beginTransaction();
            ft.add(R.id.otto_ll_1, topF);
            ft.add(R.id.otto_ll_2, btmF);
            ft.commit();
        }
//        SingletonBus.post(new MainDto("OttoActivity onCreate"));
        SingletonBus.register(this);

        findViewById(R.id.otto_btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingletonBus.post(new MainDto("Main otto activity"));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_otto, menu);
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

//  @Produce
//    public MainDto getDataFromMainOttoActivity () {
//        return new MainDto("Start up");
//    }

    @Subscribe
    public void getMsgFromTop(OttoDto dto) {
        txt.setText(dto.name);
    }
    TextView txt;

}
