package mine.com.testclient;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SendObjActivity extends AppCompatActivity {
    private static final String TAG = "DEBUG-WCL: " + MainActivity.class.getSimpleName();

    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
