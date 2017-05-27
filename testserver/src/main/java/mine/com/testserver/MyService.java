package mine.com.testserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {

    public MyService() {

    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    private class MyBinder extends IMyAidlInterface.Stub{
        public MyService getMyService() {
            return MyService.this;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int sub(int a, int b) throws RemoteException {
            return a + b;
        }
    }

}
