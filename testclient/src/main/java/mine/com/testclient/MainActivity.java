package mine.com.testclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import mine.com.testserver.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface myAidlInterface;
    private MyServiceConnection mServiceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void open(View view){
        mServiceConnection = new MyServiceConnection();
        Intent intent = new Intent();
        intent.setAction("mine.com.service.MY_SERVICE");
        //第一种方法
        intent.setPackage("mine.com.testserver");
//        bindService(createExplicitFromImplicitIntent(this,intent),mServiceConnection, Context.BIND_AUTO_CREATE);
        //第二种方法
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }

    public void close(View view){
        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
        }
    }

    public void getSum(View view){
        try {
           int result =  myAidlInterface.sub(1,2);
            Log.i("mine", "getSum: "+"结果是："+result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("mine", "onServiceConnected: "+"链接成功");
//            myAidlInterface = (IMyAidlInterface) service;
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("mine", "onServiceConnected: "+"断开链接");
        }
    }
}
