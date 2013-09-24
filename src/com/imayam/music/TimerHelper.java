// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimerHelper.java

package com.imayam.music;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Timer;

// Referenced classes of package com.imayam.music:
//            MyTimerTask

public class TimerHelper
{

    public TimerHelper()
    {
    }

    public static void main(String args[])
    {
        Calendar runDate = Calendar.getInstance();
        runDate.set(5, 1);
        runDate.set(11, 0);
        runDate.set(12, 0);
        runDate.add(2, 1);
        MyTimerTask myTask = new MyTimerTask();
        Timer myTimer = new Timer();
        myTimer.schedule(myTask, runDate.getTime());
        System.out.println((new StringBuilder("Date")).append(runDate.getTime()).toString());
    }
}
