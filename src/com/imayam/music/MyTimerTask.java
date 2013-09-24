// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyTimerTask.java

package com.imayam.music;

import java.util.TimerTask;

// Referenced classes of package com.imayam.music:
//            DataAccess

public class MyTimerTask extends TimerTask
{

    public MyTimerTask()
    {
    }

    public void run()
    {
        try
        {
            DataAccess.updatemonthCount();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
