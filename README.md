1. 启动和关闭服务

```
@OnClick({R.id.btn_startService, R.id.btn_stopService})       
public void onViewClicked(View view) {                        
    switch (view.getId()) {                                   
        case R.id.btn_startService:                           
            Intent intent=new Intent(this,MyService.class);   
            startService(intent);                             
            break;                                            
        case R.id.btn_stopService:                            
            Intent intent2=new Intent(this,MyService.class);  
            stopService(intent2);                             
            break;                                            
    }                                                         
}                                                             
```
2. 绑定服务

在IBinder方法返回binder对象
```
public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();

    private MyBinder mBinder=new MyBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    class MyBinder extends Binder{
        public void startDownload(){
            Log.d(TAG, "startDownload: ");
        }
    }
}

```

绑定和解绑服务
```
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
    

    case R.id.btn_bindService:
        Intent intent3 = new Intent(this, MyService.class);
        bindService(intent3,connection,BIND_AUTO_CREATE);
        break;
    case R.id.btn_unbindService:
        unbindService(connection);
        break;
```

3. 创建前台服务

只需修改oncreate()函数
```
@Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

        Notification notification=new Notification(R.mipmap.ic_launcher,"有通知到来",System.currentTimeMillis());
        Intent notificationIntent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);
        startForeground(1,notification);
    }
```

然后启动与停止服务

```
case R.id.btn_startForegroundService:
    startService(new Intent(this,ForegroundService.class));
    break;
case R.id.btn_stopForegroundService:
    stopService(new Intent(this,ForegroundService.class));
    break;
```