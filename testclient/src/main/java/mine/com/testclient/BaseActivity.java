package mine.com.testclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import mine.com.helper.ScaleScreenHelperFactory;
import mine.com.helper.bound.BoundViewHelper;

public class BaseActivity extends AppCompatActivity {


    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        try {
            ScaleScreenHelperFactory.getInstance().loadViewGroup((ViewGroup) BoundViewHelper.boundView(this, this.getWindow().getDecorView()));
        } catch (Exception var3) {
            ;
        }
    }
}
