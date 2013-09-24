// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SongVo.java

package com.imayam.music;


public class SongVo
{

    public SongVo()
    {
    }

    public String getMovieName()
    {
        return movieName;
    }

    public void setMovieName(String movieName)
    {
        this.movieName = movieName;
    }

    public String getImage_file_name()
    {
        return image_file_name;
    }

    public void setImage_file_name(String image_file_name)
    {
        this.image_file_name = image_file_name;
    }

    public String getArtistId()
    {
        return artistId;
    }

    public void setArtistId(String artistId)
    {
        this.artistId = artistId;
    }

    public String getArtistName()
    {
        return artistName;
    }

    public void setArtistName(String artistName)
    {
        this.artistName = artistName;
    }

    public String getsongName()
    {
        return songName;
    }

    public void setsongName(String songName)
    {
        this.songName = songName;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getComposerName()
    {
        return composerName;
    }

    public void setComposerName(String composerName)
    {
        this.composerName = composerName;
    }

    public String getLyricistName()
    {
        return lyricistName;
    }

    public void setLyricistName(String lyricistName)
    {
        this.lyricistName = lyricistName;
    }

    String id;
    String movieName;
    String songName;
    String composerName;
    String lyricistName;
    String artistName;
    String artistId;
    String image_file_name;
}
