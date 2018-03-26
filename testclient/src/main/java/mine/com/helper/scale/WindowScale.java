package mine.com.helper.scale;

import mine.com.utils.UtilScreen;

/**
 * Created by qiuyouzone on 2018/3/22.
 */

abstract class WindowScale {
    int width;
    int height;

    public WindowScale() {
        int[] s = UtilScreen.screenSize();
        this.width = s[0];
        this.height = s[1];
    }
}
