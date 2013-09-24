// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ControllerServlet.java

package com.imayam.music;

import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.id3.ID3v23Tag;

// Referenced classes of package com.imayam.music:
//            DataAccess

public class ControllerServlet extends HttpServlet
{

    public ControllerServlet()
    {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession();
            session.setAttribute("artists1", null);
            String action = request.getParameter("action");
            logger.debug((new StringBuilder("action : ")).append(action).toString());
            if("reindex".equalsIgnoreCase(action))
            {
                String dir = request.getParameter("dir");
                if(dir != null)
                {
                    File myFiles = new File((new StringBuilder("/home/imayam2/public_html/songs/")).append(dir).toString());
                    StringBuffer buffer = getFileNames(myFiles);
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/plain");
                    out.print(buffer.toString());
                    out.flush();
                }
            }
            if("moviesearch".equalsIgnoreCase(action))
            {
                String s = request.getParameter("id");
                ArrayList songsList = DataAccess.getmovieSearchList(s);
                session.setAttribute("artists1", songsList);
                response.sendRedirect("/isai/songs/radio/index.jsp");
            } else
            if("numbmoviesearch".equalsIgnoreCase(action))
            {
                ArrayList songsList = DataAccess.getnumbmovieSearchList();
                session.setAttribute("artists1", songsList);
                response.sendRedirect("/isai/songs/radio/index.jsp");
            } else
            if("artist".equalsIgnoreCase(action))
            {
                session.setAttribute("artists1", null);
                ArrayList songsList = DataAccess.getArtistsList();
                session.setAttribute("artists", songsList);
                response.sendRedirect("/isai/songs/radio/index.jsp");
            } else
            if("movie".equalsIgnoreCase(action))
            {
                session.setAttribute("artists1", null);
                ArrayList songsList = DataAccess.getMovieList();
                session.setAttribute("artists", songsList);
                response.sendRedirect("/isai/songs/radio/index.jsp");
            } else
            if("composer".equalsIgnoreCase(action))
            {
                session.setAttribute("artists1", null);
                ArrayList songsList = DataAccess.getComposerList();
                session.setAttribute("artists", songsList);
                response.sendRedirect("/isai/songs/radio/index.jsp");
            } else
            if("lyrics".equalsIgnoreCase(action))
            {
                session.setAttribute("artists1", null);
                ArrayList songsList = DataAccess.getLyricsList();
                session.setAttribute("artists", songsList);
                response.sendRedirect("/isai/songs/radio/index.jsp");
            } else
            if(action != null && action.indexOf("playSong") > -1)
                getPlayList(action, response);
            else
            if(action != null && action.indexOf("search") > -1)
            {
                session.setAttribute("artists1", null);
                getsearch(action, response);
            } else
            if(action != null && action.indexOf("playRssSong") > -1)
            {
                session.setAttribute("artists1", null);
                getPlayListRss(action, response);
            } else
            if(action != null && action.indexOf("getSongs") > -1)
            {
                session.setAttribute("artists1", null);
                getSongs(action, response);
                session.setAttribute("artists1", null);
            } else
            if(action != null && action.indexOf("customRssPlayList") > -1)
            {
                session.setAttribute("artists1", null);
                getcustomRssPlayList(action, response);
            } else
            {
                ArrayList gm = new ArrayList();
                gm = DataAccess.getImage();
                request.setAttribute("img1", gm);
                request.getRequestDispatcher("songs/radio/index.jsp").forward(request, response);
            }
        }
        catch(Exception e)
        {
            logger.debug((new StringBuilder("Exception : ")).append(e.getMessage()).toString(), e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    public void getPlayList(String action, HttpServletResponse response)
        throws Exception
    {
        String artist = action.replaceAll("playSong", "");
        String playList = DataAccess.getSongsList(artist);
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out.print(playList);
        out.flush();
    }

    public void getSongs(String action, HttpServletResponse response)
        throws Exception
    {
        String artist = action.replaceAll("getSongs", "");
        String playList = DataAccess.getSongs(artist);
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out.print(playList);
        out.flush();
    }

    public void getcustomRssPlayList(String action, HttpServletResponse response)
        throws Exception
    {
        String artist = action.replaceAll("customRssPlayList", "");
        String playList = DataAccess.getCustomRssPlayList(artist);
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out.print(playList);
        out.flush();
    }

    public void getPlayListRss(String action, HttpServletResponse response)
        throws Exception
    {
        String artist = action.replaceAll("playRssSong", "");
        String playList = DataAccess.getSongsListRss(artist);
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out.print(playList);
        out.flush();
    }

    public void getsearch(String action, HttpServletResponse response)
        throws Exception
    {
        String artist = action.replaceAll("search", "");
        String playList = DataAccess.getSearch(artist);
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out.print(playList);
        out.flush();
    }

    public StringBuffer getFileNames(File folder)
        throws Exception
    {
        String mySystem[] = folder.list();
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < mySystem.length; i++)
        {
            for(int j = 0; j < mySystem.length; j++)
            {
                String extension = mySystem[j].substring(mySystem[j].lastIndexOf(".") + 1, mySystem[j].length());
                String image = "jpg";
                if(extension.equals(image))
                {
                    File imageFile = new File((new StringBuilder()).append(folder).append("/").append(mySystem[j]).toString());
                    String image1 = "jpg";
                    String extension1 = mySystem[i].substring(mySystem[i].lastIndexOf(".") + 1, mySystem[i].length());
                    if(!extension1.equals(image1))
                    {
                        File newFile = new File((new StringBuilder()).append(folder).append("/").append(mySystem[i]).toString());
                        if(newFile.isDirectory())
                            getFileNames(newFile);
                        else
                            buffer.append(getTagValues(newFile, imageFile));
                    }
                }
            }

        }

        return buffer;
    }

    public StringBuffer getTagValues(File filename, File imagename)
        throws Exception
    {
        AudioFile audioFile = AudioFileIO.read(filename);
        java.awt.Image img = ImageIO.read(imagename);
        ID3v23Tag tag = (ID3v23Tag)audioFile.getTag();
        StringBuffer buffer = new StringBuffer();
        ArrayList artist = new ArrayList();
        String tempArtist = tag.getFirst(FieldKey.ARTIST);
        String album = tag.getFirst(FieldKey.ALBUM);
        String title = tag.getFirst(FieldKey.TITLE);
        String lyricist = tag.getFirst(FieldKey.LYRICIST);
        String composer = tag.getFirst(FieldKey.COMPOSER);
        buffer.append((new StringBuilder("This is : ")).append(filename.getAbsoluteFile()).toString());
        buffer.append((new StringBuilder("This is : ")).append(imagename.getAbsoluteFile()).toString());
        buffer.append((new StringBuilder("\nArtist : ")).append(tempArtist).append("\n").toString());
        String regex = "[&;,]";
        String tokens[] = tempArtist.split(regex);
        for(int a = 0; a < tokens.length; a++)
        {
            String token = tokens[a];
            buffer.append(token);
            artist.add(token);
        }

        buffer.append((new StringBuilder("Album : ")).append(album).append("\n").toString());
        buffer.append((new StringBuilder("title : ")).append(title).append("\n").toString());
        buffer.append((new StringBuilder("composer : ")).append(composer).append("\n\n").toString());
        DataAccess.saveSongs(album, title, composer, artist, lyricist, filename.getAbsolutePath(), imagename.getAbsolutePath());
        return buffer;
    }
    private static final Logger logger = Logger
			.getLogger(ControllerServlet.class);
	private static final String errorPage = "error.jsp";
   
}
