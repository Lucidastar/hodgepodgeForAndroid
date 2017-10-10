package com.lucidastar.hodgepodge;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mine.lucidastarutils.log.KLog;
import com.mine.lucidastarutils.utils.FileUtils;
import com.mine.lucidastarutils.utils.Utils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.lucidastar.hodgepodge", appContext.getPackageName());
    }

    @Test
    public void  getByteFormat() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Utils.init(appContext);
        String bytesFormat = FileUtils.getBytesFormat(28374629);
        KLog.i(bytesFormat);
        assertEquals("27.06MB", bytesFormat);
    }
}
