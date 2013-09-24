// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataAccess.java

package com.imayam.music;

import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import org.apache.log4j.Logger;

// Referenced classes of package com.imayam.music:
//            GetMovie, SongVo

public final class DataAccess
{
	static Logger logger = Logger.getLogger(DataAccess.class);
    public DataAccess()
    {
    }

    public static void saveSongs(String movie, String song, String composer, ArrayList artist, String lyricist, String fileName, String imagename)
        throws Exception
    {
        Connection conn = getConnection();
        String sql = "INSERT INTO music_catalog(movie, song, composer, lyrics, file_name,month_hitcount,create_time,image_file_name) VALUES (?, ?, ?, ?, ?,0,sysdate(),?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, movie.trim());
        ps.setString(2, song.trim());
        ps.setString(3, composer.trim());
        ps.setString(4, lyricist);
        ps.setString(5, fileName);
        ps.setString(6, imagename);
        ps.execute();
        updateartist(artist, song, movie);
        ps.close();
        conn.close();
    }

    public static void updateartist(ArrayList artist, String song, String movie)
        throws Exception
    {
        Connection conn = getConnection();
        for(int i = 0; i < artist.size(); i++)
        {
            int artist_id = 0;
            logger.debug(artist.get(i).toString());
            String sql = (new StringBuilder("select  artist_id from music_artist where artist_name='")).append(artist.get(i).toString()).append("'").toString();
            Statement stmt = conn.createStatement();
            for(ResultSet rs = stmt.executeQuery(sql); rs.next();)
            {
                artist_id = rs.getInt("artist_id");
                if(artist_id != 0)
                {
                    String sql2 = (new StringBuilder("select id,artist_id from music_catalog a ,music_artist b where a.song='")).append(song).append("'and a.movie='").append(movie).append("'  and b.artist_name='").append(artist.get(i).toString()).append("'").toString();
                    Statement stmt2 = conn.createStatement();
                    String sql3;
                    Statement stmt3;
                    for(ResultSet rs2 = stmt2.executeQuery(sql2); rs2.next(); stmt3.execute(sql3))
                    {
                        int id = rs2.getInt("id");
                        int aid = rs2.getInt("artist_id");
                        sql3 = (new StringBuilder("insert into music_catalog_m2m_artist values(")).append(id).append(",").append(aid).append(")").toString();
                        stmt3 = conn.createStatement();
                    }

                }
            }

            if(artist_id == 0)
            {
                String sql1 = (new StringBuilder("insert into music_artist values(null,'")).append(artist.get(i).toString()).append("',0)").toString();
                Statement stmt1 = conn.createStatement();
                stmt1.execute(sql1);
                String sql2 = (new StringBuilder("select id,artist_id from music_catalog a ,music_artist b where a.song='")).append(song).append("' and a.movie='").append(movie).append("'  and b.artist_name='").append(artist.get(i).toString()).append("'").toString();
                Statement stmt2 = conn.createStatement();
                String sql3;
                Statement stmt3;
                for(ResultSet rs2 = stmt2.executeQuery(sql2); rs2.next(); stmt3.execute(sql3))
                {
                    int id = rs2.getInt("id");
                    int aid = rs2.getInt("artist_id");
                    sql3 = (new StringBuilder("insert into music_catalog_m2m_artist values(")).append(id).append(",").append(aid).append(")").toString();
                    stmt3 = conn.createStatement();
                }

            }
        }

        conn.close();
    }

    public static ArrayList getArtistsList()
        throws Exception
    {
        Connection conn = getConnection();
        int i = 0;
        ArrayList songsList = new ArrayList();
        String sql = "SELECT DISTINCT artist_name FROM music_artist ORDER BY hitcount desc";
        Statement stmt = conn.createStatement();
        String artist;
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); songsList.add(artist))
        {
            artist = rs.getString("artist_name");
            logger.debug((new StringBuilder("Artist ")).append(i++).append(" : ").append(artist).toString());
        }

        conn.close();
        return songsList;
    }

    public static ArrayList getmovieSearchList(String s)
        throws Exception
    {
        Connection conn = getConnection();
        int i = 0;
        ArrayList songsList1 = new ArrayList();
        String sql = (new StringBuilder("SELECT DISTINCT movie FROM music_catalog where movie like '")).append(s).append("%' order by movie").toString();
        Statement stmt = conn.createStatement();
        String movie;
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); songsList1.add(movie))
        {
            movie = rs.getString("movie");
            logger.debug((new StringBuilder("movie ")).append(i++).append(" : ").append(movie).toString());
        }

        conn.close();
        return songsList1;
    }

    public static ArrayList getnumbmovieSearchList()
        throws Exception
    {
        Connection conn = getConnection();
        int i = 0;
        ArrayList songsList1 = new ArrayList();
        String sql = "SELECT DISTINCT movie FROM music_catalog WHERE movie regexp '^[0-9]+' order by movie";
        Statement stmt = conn.createStatement();
        String movie;
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); songsList1.add(movie))
        {
            movie = rs.getString("movie");
            logger.debug((new StringBuilder("movie ")).append(i++).append(" : ").append(movie).toString());
        }

        conn.close();
        return songsList1;
    }

    public static ArrayList getMovieList()
        throws Exception
    {
        Connection conn = getConnection();
        int i = 0;
        ArrayList songsList = new ArrayList();
        String sql = "SELECT DISTINCT movie FROM music_catalog ORDER BY movie";
        Statement stmt = conn.createStatement();
        String movie;
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); songsList.add(movie))
        {
            movie = rs.getString("movie");
            logger.debug((new StringBuilder("movie ")).append(i++).append(" : ").append(movie).toString());
        }

        conn.close();
        return songsList;
    }

    public static ArrayList getComposerList()
        throws Exception
    {
        Connection conn = getConnection();
        int i = 0;
        ArrayList songsList = new ArrayList();
        String sql = "SELECT DISTINCT composer, sum(hitcount) as total FROM music_catalog group by composer ORDER BY total desc";
        Statement stmt = conn.createStatement();
        String composer;
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); songsList.add(composer))
        {
            composer = rs.getString("composer");
            logger.debug((new StringBuilder("composer ")).append(i++).append(" : ").append(composer).toString());
        }

        conn.close();
        return songsList;
    }

    public static ArrayList getLyricsList()
        throws Exception
    {
        Connection conn = getConnection();
        int i = 0;
        ArrayList songsList = new ArrayList();
        String sql = "SELECT DISTINCT lyrics, sum(hitcount) as total FROM music_catalog group by lyrics ORDER BY total desc";
        Statement stmt = conn.createStatement();
        String lyrics;
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); songsList.add(lyrics))
        {
            lyrics = rs.getString("lyrics");
            logger.debug((new StringBuilder("lyrics ")).append(i++).append(" : ").append(lyrics).toString());
        }

        conn.close();
        return songsList;
    }

    public static String getSongsList(String artist)
        throws Exception
    {
        Connection conn = getConnection();
        StringBuffer myPlayList = new StringBuffer();
        String sql = null;
        ResultSet rs = null;
        if("all".equalsIgnoreCase(artist))
        {
            sql = "SELECT distinct song, movie, file_name FROM music_catalog order by hitcount desc limit 0, 100";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } else
        if("Monthly".equalsIgnoreCase(artist))
        {
            sql = "SELECT distinct song, movie, file_name FROM music_catalog order by month_hitcount desc limit 0, 100";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } else
        if("NewAdds".equalsIgnoreCase(artist))
        {
            sql = "SELECT distinct song, movie, file_name FROM music_catalog order by create_time desc limit 0, 100";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } else
        {
            sql = "SELECT distinct song, movie, file_name FROM music_catalog a, music_artist b, music_catalog_m2m_artist c, music_playlist d, music_catalog_m2m_playlist e WHERE (artist_name = ? or movie = ? or composer = ? or lyrics = ? or playlist_name = ?) and a.id = c.catalog_id and b.artist_id = c.artist_id and d.playlist_id = e.playlist_id and e.catalog_id = a.id order by a.hitcount desc limit 0, 100";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, artist);
            ps.setString(2, artist);
            ps.setString(3, artist);
            ps.setString(4, artist);
            ps.setString(5, artist);
            rs = ps.executeQuery();
        }
        myPlayList.append("<playlist version='1' xmlns='http://xspf.org/ns/0/'>\n");
        myPlayList.append("<trackList>\n");
        for(; rs.next(); myPlayList.append("\t</track>\n"))
        {
            myPlayList.append("\t<track>\n");
            myPlayList.append((new StringBuilder("\t\t<title>")).append(rs.getString("movie")).append(":").append(rs.getString("song")).append("</title>\n").toString());
            myPlayList.append("\t\t<location>");
            myPlayList.append(rs.getString("file_name").replaceAll("/home/imayam2/public_html", "http://www.imayam.org"));
            myPlayList.append("</location>\n");
        }

        myPlayList.append("</trackList>\n");
        myPlayList.append("</playlist>\n");
        conn.close();
        return myPlayList.toString();
    }

    public static String getSongsListRss(String artist)
        throws Exception
    {
        Connection conn = getConnection();
        StringBuffer myPlayList = new StringBuffer();
        String sql = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        if("all".equalsIgnoreCase(artist))
        {
            sql = "SELECT distinct id, song, movie, composer, lyrics, file_name, image_file_name FROM music_catalog order by hitcount desc limit 0, 100";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } else
        if("Monthly".equalsIgnoreCase(artist))
        {
            sql = "SELECT distinct id, song, movie, composer, lyrics, file_name, image_file_name FROM music_catalog order by month_hitcount desc limit 0, 100";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } else
        if("NewAdds".equalsIgnoreCase(artist))
        {
            sql = "SELECT distinct id, song, movie, composer, lyrics, file_name, image_file_name FROM music_catalog order by create_time desc limit 0, 100";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } else
        {
            sql = "SELECT distinct a.id, a.song, a.movie, a.composer, a.lyrics, a.file_name, a.image_file_name,a.hitcount FROM music_catalog a, music_artist b, music_catalog_m2m_artist c WHERE (artist_name = ? or movie = ? or composer = ? or lyrics = ?) and a.id = c.catalog_id and b.artist_id = c.artist_id order by hitcount desc limit 0, 100";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, artist);
            ps.setString(2, artist);
            ps.setString(3, artist);
            ps.setString(4, artist);
            rs = ps.executeQuery();
            String sql1 = (new StringBuilder("Select artist_id from music_artist where artist_name='")).append(artist).append("'").toString();
            Statement stmt = conn.createStatement();
            int artist_id;
            for(rs2 = stmt.executeQuery(sql1); rs2.next(); updateartistCount(artist_id))
                artist_id = rs2.getInt("artist_id");

        }
        myPlayList.append("<rss version='2.0' xmlns:media='http://search.yahoo.com/mrss/' xmlns:jwplayer='http://developer.longtailvideo.com/trac/'>\n");
        myPlayList.append("<channel>\n");
        myPlayList.append("<title>imayam.org playlist</title>");
        for(; rs.next(); updateCount(rs.getInt("id")))
        {
            myPlayList.append("\t<item>\n");
            myPlayList.append((new StringBuilder("\t\t<title>")).append(rs.getString("movie")).append(" : ").append(rs.getString("song")).append("</title>\n").toString());
            myPlayList.append((new StringBuilder("\t\t<description>")).append(rs.getString("composer")).append(" : ").append(rs.getString("lyrics")).append("\n"));
            int id = rs.getInt("id");
            String sql1 = (new StringBuilder("select b.artist_name,b.artist_id from music_catalog a, music_artist b, music_catalog_m2m_artist c WHERE id='")).append(id).append("' and a.id = c.catalog_id and b.artist_id = c.artist_id ; ").toString();
            Statement stmt = conn.createStatement();
            for(rs1 = stmt.executeQuery(sql1); rs1.next();)
            {
                String artist1 = rs1.getString("artist_name");
                if(rs1.isLast())
                    myPlayList.append(artist1);
                else
                    myPlayList.append(artist1).append(",");
            }

            myPlayList.append("</description>\n").toString();
            myPlayList.append("\t\t<media:content url='");
            myPlayList.append(rs.getString("file_name").replaceAll("/home/imayam2/public_html", "http://www.imayam.org"));
            myPlayList.append("' />\n");
            myPlayList.append("<media:thumbnail url='");
            myPlayList.append(rs.getString("image_file_name").replaceAll("/home/imayam2/public_html", "http://www.imayam.org"));
            myPlayList.append("' />\n");
            myPlayList.append("\t</item>\n");
        }

        myPlayList.append("</channel>\n");
        myPlayList.append("</rss>\n");
        conn.close();
        return myPlayList.toString();
    }

    private static StringBuffer append(String artist1)
    {
        return null;
    }

    public static String getSearch(String artist)
        throws Exception
    {
        Connection conn = getConnection();
        StringBuffer myPlayList = new StringBuffer();
        String sql = null;
        ResultSet rs = null;
        String per = "%";
        String artist2 = null;
        artist2 = artist.concat(per);
        artist = per.concat(artist2);
        sql = "SELECT distinct song,movie,file_name,image_file_name,composer,lyrics,id FROM music_catalog where  movie  like ? or song like ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, artist);
        ps.setString(2, artist);
        rs = ps.executeQuery();
        myPlayList.append("<rss version='2.0' xmlns:media='http://search.yahoo.com/mrss/' xmlns:jwplayer='http://developer.longtailvideo.com/trac/'>\n");
        myPlayList.append("<channel>\n");
        myPlayList.append("<title>imayam.org playlist</title>");
        for(; rs.next(); updateCount(rs.getInt("id")))
        {
            myPlayList.append("\t<item>\n");
            myPlayList.append((new StringBuilder("\t\t<title>")).append(rs.getString("movie")).append(":").append(rs.getString("song")).append("</title>\n").toString());
            myPlayList.append((new StringBuilder("\t\t<description>")).append(rs.getString("composer")).append(" : ").append(rs.getString("lyrics")).append("\n"));
            int id = rs.getInt("id");
            String sql1 = (new StringBuilder("select b.artist_name,b.artist_id from music_catalog a, music_artist b, music_catalog_m2m_artist c WHERE id='")).append(id).append("' and a.id = c.catalog_id and b.artist_id = c.artist_id ; ").toString();
            Statement stmt = conn.createStatement();
            for(ResultSet rs1 = stmt.executeQuery(sql1); rs1.next();)
            {
                String artist1 = rs1.getString("artist_name");
                int artist_id = rs1.getInt("artist_id");
                updateartistCount(artist_id);
                if(rs1.isLast())
                    myPlayList.append(artist1);
                else
                    myPlayList.append(artist1).append(",");
            }

            myPlayList.append("</description>\n").toString();
            myPlayList.append("\t\t<media:content url='");
            myPlayList.append(rs.getString("file_name").replaceAll("/home/imayam2/public_html", "http://www.imayam.org"));
            myPlayList.append("' />\n");
            myPlayList.append("<media:thumbnail url='");
            myPlayList.append(rs.getString("image_file_name").replaceAll("/home/imayam2/public_html", "http://www.imayam.org"));
            myPlayList.append("' />\n");
            myPlayList.append("\t</item>\n");
        }

        myPlayList.append("</channel>\n");
        myPlayList.append("</rss>\n");
        conn.close();
        return myPlayList.toString();
    }

    public static String getSongs(String artist)
        throws Exception
    {
        Connection conn = getConnection();
        StringBuffer myPlayList = new StringBuffer();
        String sql = null;
        ResultSet rs = null;
        sql = "SELECT DISTINCT song,movie,file_name,image_file_name,composer,lyrics,id FROM music_catalog where movie= ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, artist);
        rs = ps.executeQuery();
        myPlayList.append("<rss version='2.0' xmlns:media='http://search.yahoo.com/mrss/' xmlns:jwplayer='http://developer.longtailvideo.com/trac/'>\n");
        myPlayList.append("<channel>\n");
        myPlayList.append("<title>imayam.org playlist</title>");
        for(; rs.next(); updateCount(rs.getInt("id")))
        {
            myPlayList.append("\t<item>\n");
            myPlayList.append((new StringBuilder("\t\t<title>")).append(rs.getString("movie")).append(":").append(rs.getString("song")).append("</title>\n").toString());
            myPlayList.append((new StringBuilder("\t\t<description>")).append(rs.getString("composer")).append(" : ").append(rs.getString("lyrics")).append("\n"));
            int id = rs.getInt("id");
            String sql1 = (new StringBuilder("select b.artist_name,b.artist_id from music_catalog a, music_artist b, music_catalog_m2m_artist c WHERE id='")).append(id).append("' and a.id = c.catalog_id and b.artist_id = c.artist_id ; ").toString();
            Statement stmt = conn.createStatement();
            for(ResultSet rs1 = stmt.executeQuery(sql1); rs1.next();)
            {
                String artist1 = rs1.getString("artist_name");
                int artist_id = rs1.getInt("artist_id");
                updateartistCount(artist_id);
                if(rs1.isLast())
                    myPlayList.append(artist1);
                else
                    myPlayList.append(artist1).append(",");
            }

            myPlayList.append("</description>\n").toString();
            myPlayList.append("\t\t<media:content url='");
            myPlayList.append(rs.getString("file_name").replaceAll("/home/imayam2/public_html", "http://www.imayam.org"));
            myPlayList.append("' />\n");
            myPlayList.append("<media:thumbnail url='");
            myPlayList.append(rs.getString("image_file_name").replaceAll("/home/imayam2/public_html", "http://www.imayam.org"));
            myPlayList.append("' />\n");
            myPlayList.append("\t</item>\n");
        }

        myPlayList.append("</channel>\n");
        myPlayList.append("</rss>\n");
        conn.close();
        return myPlayList.toString();
    }

    public static String getCustomRssPlayList(String artist)
        throws Exception
    {
        Connection conn = getConnection();
        StringBuffer myPlayList = new StringBuffer();
        String sql = null;
        ResultSet rs = null;
        sql = "SELECT distinct id, song, movie, composer, lyrics, file_name, image_file_name FROM music_catalog a, music_playlist d, music_catalog_m2m_playlist e WHERE playlist_name = ? and d.playlist_id = e.playlist_id and e.catalog_id = a.id order by a.hitcount desc limit 0, 100";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, artist);
        rs = ps.executeQuery();
        myPlayList.append("<rss version='2.0' xmlns:media='http://search.yahoo.com/mrss/' xmlns:jwplayer='http://developer.longtailvideo.com/trac/'>\n");
        myPlayList.append("<channel>\n");
        myPlayList.append("<title>imayam.org playlist</title>");
        for(; rs.next(); updateCount(rs.getInt("id")))
        {
            myPlayList.append("\t<item>\n");
            myPlayList.append((new StringBuilder("\t\t<title>")).append(rs.getString("movie")).append(" : ").append(rs.getString("song")).append("</title>\n").toString());
            myPlayList.append((new StringBuilder("\t\t<description>")).append(rs.getString("composer")).append(" : ").append(rs.getString("lyrics")).append("\n"));
            int id = rs.getInt("id");
            String sql1 = (new StringBuilder("select b.artist_name,b.artist_id from music_catalog a, music_artist b, music_catalog_m2m_artist c WHERE id='")).append(id).append("' and a.id = c.catalog_id and b.artist_id = c.artist_id ; ").toString();
            Statement stmt = conn.createStatement();
            for(ResultSet rs1 = stmt.executeQuery(sql1); rs1.next();)
            {
                String artist1 = rs1.getString("artist_name");
                int artist_id = rs1.getInt("artist_id");
                updateartistCount(artist_id);
                if(rs1.isLast())
                    myPlayList.append(artist1);
                else
                    myPlayList.append(artist1).append(",");
            }

            myPlayList.append("</description>\n").toString();
            myPlayList.append("\t\t<media:content url='");
            myPlayList.append(rs.getString("file_name").replaceAll("/home/imayam2/public_html", "http://www.imayam.org"));
            myPlayList.append("' />\n");
            myPlayList.append("<media:thumbnail url='");
            myPlayList.append(rs.getString("image_file_name").replaceAll("/home/imayam2/public_html", "http://www.imayam.org"));
            myPlayList.append("' />\n");
            myPlayList.append("\t</item>\n");
        }

        myPlayList.append("</channel>\n");
        myPlayList.append("</rss>\n");
        conn.close();
        return myPlayList.toString();
    }

    public static void updateCount(int id)
        throws Exception
    {
        Connection conn = getConnection();
        String sql = "UPDATE music_catalog SET hitcount=hitcount+1, month_hitcount=month_hitcount+1 where id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        ps.close();
        conn.close();
    }

    public static void updateartistCount(int artist_id)
        throws Exception
    {
        Connection conn = getConnection();
        String sql = "UPDATE music_artist SET hitcount=hitcount+1 where artist_id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, artist_id);
        ps.execute();
        ps.close();
        conn.close();
    }

    public static void updatemonthCount()
        throws Exception
    {
        Connection conn = getConnection();
        String sql = "UPDATE music_catalog SET month_hitcount=0";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        ps.close();
        conn.close();
    }

    public static ArrayList getImage()
        throws Exception
    {
        Connection conn = getConnection();
        ArrayList getimage = new ArrayList();
        String sql = "select movie as m,image_file_name as i,sum(month_hitcount) as a,count(song) as b ,sum(month_hitcount)/count(song) as c from music_catalog group by movie order by c desc limit 0, 3";
        Statement stmt = conn.createStatement();
        GetMovie gv;
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); getimage.add(gv))
        {
            String image = rs.getString("i");
            String str1 = image.replace("/home/imayam2/public_html/", "http://www.imayam.org/");
            String movie = rs.getString("m");
            System.out.println(str1);
            gv = new GetMovie();
            gv.setMoviename(movie);
            gv.setImage(str1);
        }

        conn.close();
        return getimage;
    }

    public static void getArtistname(String id, String movie, String artist_id, String artist_name)
        throws Exception
    {
        Connection conn = getConnection();
        String sql = (new StringBuilder("select artist_id from music_artist where artist_name='")).append(artist_name).append("'").toString();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next())
        {
            String aid = rs.getString("artist_id");
            String sql1 = (new StringBuilder("select artist_id,catalog_id from music_catalog_m2m_artist where catalog_id='")).append(id).append("'").toString();
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            if(rs1.next())
            {
                String arid = rs1.getString("artist_id");
                if(!arid.equals(aid))
                {
                    String sql3 = (new StringBuilder("insert into music_catalog_m2m_artist values ('")).append(id).append("','").append(aid).append("')").toString();
                    Statement stmt3 = conn.createStatement();
                    stmt3.execute(sql3);
                }
            } else
            {
                String sql3 = (new StringBuilder("insert into music_catalog_m2m_artist values ('")).append(id).append("','").append(aid).append("')").toString();
                Statement stmt3 = conn.createStatement();
                stmt3.execute(sql3);
                System.out.println("Inserted");
            }
        } else
        {
            String sql1 = (new StringBuilder("insert into music_artist values(null,'")).append(artist_name).append("',0)").toString();
            Statement stmt1 = conn.createStatement();
            stmt1.execute(sql1);
            getArtistname(id, movie, artist_id, artist_name);
        }
        conn.close();
    }

    public static void deleteArtist(String arid, String cid)
        throws Exception
    {
        Connection conn = getConnection();
        String sql = (new StringBuilder("delete from music_catalog_m2m_artist where artist_id='")).append(arid).append("' and catalog_id='").append(cid).append("'").toString();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        System.out.println((new StringBuilder("Deleted")).append(arid).append(cid).toString());
        ps.close();
    }

    public static void updateImage(String movie, String image_file_name)
        throws Exception
    {
        Connection conn = getConnection();
        String sql = (new StringBuilder("update music_catalog set image_file_name='")).append(image_file_name).append("' where movie ='").append(movie).append("'").toString();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        ps.close();
    }

    public static void getDetails(String id, String artist_id, String movie, String song, String composer, String lyrics, String artist_name, String image_file_name)
        throws Exception
    {
        Connection conn = getConnection();
        String sql1 = (new StringBuilder("update music_catalog a set a.movie='")).append(movie).append("',a.song='").append(song).append("',a.composer='").append(composer).append("',a.lyrics='").append(lyrics).append("',a.image_file_name='").append(image_file_name).append("' where a.id='").append(id).append("'").toString();
        PreparedStatement ps = conn.prepareStatement(sql1);
        ps.execute();
        ps.close();
    }

    public static ArrayList getsongfields(String getmovie)
        throws Exception
    {
        Connection conn = getConnection();
        ArrayList songlist = new ArrayList();
        String sql = (new StringBuilder("select distinct a.id,a.movie,a.song,a.composer,a.lyrics,a.image_file_name,c.artist_id,c.artist_name from music_catalog a left join music_catalog_m2m_artist b on (a.id=b.catalog_id) left join music_artist c on( c.artist_id=b.artist_id) where a.movie='")).append(getmovie).append("'").toString();
        Statement stmt = conn.createStatement();
        SongVo av;
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); songlist.add(av))
        {
            String id = rs.getString("id");
            String movie = rs.getString("movie");
            String song = rs.getString("song");
            String composer = rs.getString("composer");
            String lyrics = rs.getString("lyrics");
            String artist = rs.getString("artist_name");
            String artist_id = rs.getString("artist_id");
            String image = rs.getString("image_file_name");
            av = new SongVo();
            av.setId(id);
            av.setMovieName(movie);
            av.setsongName(song);
            av.setComposerName(composer);
            av.setLyricistName(lyrics);
            av.setArtistName(artist);
            av.setArtistId(artist_id);
            av.setImage_file_name(image);
        }

        conn.close();
        return songlist;
    }

    public static ArrayList getimagefield(String getmovie)
        throws Exception
    {
        Connection conn = getConnection();
        ArrayList songlist = new ArrayList();
        String sql = (new StringBuilder("Select Distinct image_file_name,movie from music_catalog where movie='")).append(getmovie).append("'").toString();
        Statement stmt = conn.createStatement();
        SongVo av;
        for(ResultSet rs = stmt.executeQuery(sql); rs.next(); songlist.add(av))
        {
            String movie = rs.getString("movie");
            String image = rs.getString("image_file_name");
            av = new SongVo();
            av.setMovieName(movie);
            av.setImage_file_name(image);
        }

        conn.close();
        return songlist;
    }

    public static Connection getConnection()
        throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
   	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imayam2_phpbb1", "root","aasi");// local connection
   	//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imayam2_phpbb1", "imayam2_aasi","aasi");//Server connection
        return con;
    }


}
