// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Auto2.java

package com.imayam.music;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

// Referenced classes of package com.imayam.music:
//            DataAccess

public class Auto2 extends HttpServlet
{

    public Auto2()
    {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try
        {
            String s[] = null;
            String query = request.getParameter("q");
            Connection con = DataAccess.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery((new StringBuilder("select distinct artist_name from music_artist where artist_name like '")).append(query).append("%'").toString());
            List li = new ArrayList();
            for(; rs.next(); li.add(rs.getString(1)));
            String str[] = new String[li.size()];
            Iterator it = li.iterator();
            for(int i = 0; it.hasNext(); i++)
            {
                String p = (String)it.next();
                str[i] = p;
            }

            int cnt = 1;
            for(int j = 0; j < str.length; j++)
            {
                if(!str[j].toUpperCase().startsWith(query.toUpperCase()))
                    continue;
                out.print((new StringBuilder(String.valueOf(str[j]))).append("\n").toString());
                if(cnt >= 10)
                    break;
                cnt++;
                request.getRequestDispatcher("/isai/index.jsp");
            }

            rs.close();
            st.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static final long serialVersionUID = 1L;
}
