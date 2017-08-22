package com.lytech.xvjialing.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_startService)
    Button btnStartService;
    @BindView(R.id.btn_stopService)
    Button btnStopService;
    @BindView(R.id.btn_bindService)
    Button btnBindService;
    @BindView(R.id.btn_unbindService)
    Button btnUnbindService;

    private MyService.MyBinder myBinder;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder= (MyService.MyBinder) iBinder;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_startService, R.id.btn_stopService, R.id.btn_bindService, R.id.btn_unbindService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_startService:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.btn_stopService:
                Intent intent2 = new Intent(this, MyService.class);
                stopService(intent2);
                break;
            case R.id.btn_bindService:
                Intent intent3 = new Intent(this, MyService.class);
                bindService(intent3,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbindService:
                unbindService(connection);
                break;
        }

    }


}
