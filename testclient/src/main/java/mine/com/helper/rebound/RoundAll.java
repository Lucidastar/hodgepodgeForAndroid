package mine.com.helper.rebound;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

public class RoundAll {
    private List<RoundA> mRoundAList = new ArrayList();

    public RoundAll() {
    }

    public void b(RoundA r) {
        r.mView.setVisibility(View.INVISIBLE);
        r.mSpring.setCurrentValue(1.0D);
        this.mRoundAList.add(r);
    }

    public void c() {
        (new Handler() {
            private int size;

            {
                this.size = RoundAll.this.mRoundAList.size();
            }

            public void handleMessage(Message msg) {
                try {
                    RoundA r = (RoundA)RoundAll.this.mRoundAList.get(msg.what);
                    r.d(0.0D);
                    r.mView.setVisibility(VISIBLE);
                    if(msg.what < this.size - 1) {
                        this.sendEmptyMessageDelayed(msg.what + 1, 100L);
                    } else {
                        RoundAll.this.mRoundAList.clear();
                    }
                } catch (Exception var3) {
                    ;
                }

            }
        }).sendEmptyMessageDelayed(0, 200L);
    }
}
