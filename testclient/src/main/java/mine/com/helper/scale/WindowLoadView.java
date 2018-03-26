package mine.com.helper.scale;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qiuyouzone on 2018/3/22.
 * 对应D
 */

public interface WindowLoadView {
    View loadViewMinMax(View var1, int var2, int var3, int var4, int var5);

    View loadViewPadding(View var1, int var2, int var3, int var4, int var5);

    View loadViewMargin(View var1, int var2, int var3, int var4, int var5);

    View loadViewWidthHeightSize(View var1, int var2, int var3, int var4);

    View loadViewWidthHeight(View var1, int var2, int var3);

    View loadViewSize(View var1, int var2);

    View loadViewGroup(ViewGroup var1);

    int getWidthHeight(int var1);

    float getSize(int var1);
}
