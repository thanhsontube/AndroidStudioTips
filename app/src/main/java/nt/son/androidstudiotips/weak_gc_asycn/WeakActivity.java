package nt.son.androidstudiotips.weak_gc_asycn;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseAppCompatActivity;

public class WeakActivity extends BaseAppCompatActivity {
    private static final String TAG = "WeakTask";
    private Button btnStart;
    private Button btnStop;
    private TextView txtValues;
//    private WeakTask weakTask;
    private WeakReference<WeakTask> weakTask = new WeakReference<WeakTask>(null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, ">>>" + "onCreate");
        setContentView(R.layout.activity_weak);
        btnStart = (Button) findViewById(R.id.weak_btn_start);
        btnStop = (Button) findViewById(R.id.weak_btn_stop);
        txtValues = (TextView) findViewById(R.id.weak_txt_value);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weakTask = (WeakReference<WeakTask>) getLastCustomNonConfigurationInstance();
                if (weakTask != null && weakTask.get() != null && !weakTask.get().getStatus().equals(AsyncTask.Status.FINISHED)) {

                    weakTask.get().attach(WeakActivity.this);
                } else {
                    WeakTask task = new WeakTask(WeakActivity.this);
                    weakTask = new WeakReference<WeakTask>(task);
                    weakTask.get().execute();
                }

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        weakTask = (WeakReference<WeakTask>) getLastCustomNonConfigurationInstance();
        if (weakTask != null && weakTask.get() != null && !weakTask.get().getStatus().equals(AsyncTask.Status.FINISHED)) {
            Log.d(TAG, ">>>" + "Attach");
            weakTask.get().attach(WeakActivity.this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(getLastCustomNonConfigurationInstance() == null) {
//            Log.d(TAG, ">>>" + "onDestroy NULL saved");
//            if (weakTask != null && weakTask.get() != null && !weakTask.get().getStatus().equals(AsyncTask.Status.FINISHED)) {
//                weakTask.get().cancel(true);
//            }
//        }
        Log.d(TAG, ">>>" + "onDestroy");
    }




    private class WeakTask extends AsyncTask<Void, Integer, Void> {

        private WeakReference<WeakActivity> activity = new WeakReference<WeakActivity>(null);

        public WeakTask(Activity context) {
            this.activity = new WeakReference<WeakActivity>((WeakActivity) context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 20; i++) {
//                if (isCancelled()) {
//                    return null;
//                }
                if(activity.get() == null) {
                    Log.d(TAG, ">>>" + "doInBackground activity.get() == null");
                    cancel(true);
                    return null;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
//                Log.d(TAG, ">>>" + "RUNNING:" + i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, ">>>" + "onProgressUpdate:" + values[0]);
            activity.get().txtValues.setText("onProgressUpdate:" + values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        public void attach(Activity activity) {
            this.activity = new WeakReference<WeakActivity>((WeakActivity) activity);
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Log.d(TAG, ">>>" + "onRetainCustomNonConfigurationInstance");
        WeakReference<WeakTask> mWeakTaskWeakReference = null;
        if (weakTask != null && weakTask.get() != null && !weakTask.get().getStatus().equals(AsyncTask.Status.FINISHED)) {
            mWeakTaskWeakReference = weakTask;
        }
        return  mWeakTaskWeakReference;
    }
}
