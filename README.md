<p align="center">
    <img src="docs/images/iit.png" name="Picture 6" align="bottom" hspace="1" width="207" height="228" border="0"/>
</p>
<p align="center">
    <strong>Indian Institute of Technology Indore</strong>
</p>
<p align="center">
    <strong>Computer Science &amp; Engineering</strong>
</p>
<p align="center">
    <strong>CS 257: Lab Assignment on Case Study</strong>
</p>
<p align="center">
    <strong>Music Player &amp; Music Management System</strong>
</p>
<p>
    <u>Submitted By:</u>
</p>
<p align="right">
    Kunal Gupta 150001015
</p>
<p align="right">
    Punit Lakshwani 150001025
</p>
<p align="right">
    Shivam Tayal 150001034
</p>
<p align="center">
    <strong>STEP-WISE ANALYSIS OF CASE STUDY </strong>
</p>
<h1>
    <em>1. Identification of Entity Sets:</em>
</h1>
<ul>
    <li>
        <p>
            Track – Songs that will played in the Music Player.
        </p>
    </li>
    <li>
        <p>
            Artist – Artists of the songs in the track entity.
        </p>
    </li>
    <li>
        <p>
            Album – Album of the songs in track entity.
        </p>
    </li>
    <li>
        <p>
            Playlist – Playlists created by user.
        </p>
    </li>
    <li>
        <p>
            Directories – Location of folder where tracks are located.
        </p>
    </li>
</ul>
<p>
    <h1>
    <em>2. Modeling Attributes and Keys:</em>
    </h1>
</p>
<ol>
    <li>
        <p>
            <strong>Track:</strong>
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            TrackId
        </p>
    </li>
    <li>
        <p>
            Trackname
        </p>
    </li>
    <li>
        <p>
            Location
        </p>
    </li>
    <li>
        <p>
            Genre
        </p>
    </li>
    <li>
        <p>
            DateAdded
        </p>
    </li>
    <li>
        <p>
            Favourite
        </p>
    </li>
</ul>
<p>
    <strong>Primary key:</strong>
    (TrackId)
</p>
<p>
    Tracks are the songs and music files in the system. Each track will be given unique TrackID.
</p>
<ul>
    <li>
        <p>
            TrackId : Track ID of the track.
        </p>
    </li>
    <li>
        <p>
            Trackname : Track name of the track.
        </p>
    </li>
    <li>
        <p>
            Location : Location of track in the system.
        </p>
    </li>
    <li>
        <p>
            Genre : Genre of the track (like pop, dance, jazz, etc.).
        </p>
    </li>
    <li>
        <p>
            DateAdded : The date on which the track was added to the database.
        </p>
    </li>
    <li>
        <p>
            Favourite : Boolean flag which is true if song is added to favourites else false.
        </p>
    </li>
</ul>
<ol start="2">
    <li>
        <p>
            <strong>Artist:</strong>
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            ArtistId
        </p>
    </li>
    <li>
        <p>
            Artistname
        </p>
    </li>
</ul>
<p>
    <strong>Primary key:</strong>
    (ArtistId)
</p>
<p>
    Artist entity contains the different artist ids and artist names.
</p>
<ul>
    <li>
        <p>
            ArtistId : Unique Id given to the artist.
        </p>
    </li>
    <li>
        <p>
            Artistname : Name of the Artist.
        </p>
    </li>
</ul>
<ol start="3">
    <li>
        <p>
            <strong>Album:</strong>
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            AlbumId
        </p>
    </li>
    <li>
        <p>
            Albumname
        </p>
    </li>
</ul>
<p>
    <strong>Primary key:</strong>
    (AlbumId)
</p>
<p>
    Album entity contains the different album names and their unique ids.
</p>
<ul>
    <li>
        <p>
            AlbumId : Unique id given to a album.
        </p>
    </li>
    <li>
        <p>
            Albumname : Name of the Album.
        </p>
    </li>
</ul>
<ol start="4">
    <li>
        <p>
            <strong>Playlist:</strong>
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            PlaylistId
        </p>
    </li>
    <li>
        <p>
            Playlistname
        </p>
    </li>
</ul>
<p>
    <strong>Primary key:</strong>
    (PlaylistId)
</p>
<p>
    Playlist entity contains the all the playlists created by users.
</p>
<ul>
    <li>
        <p>
            PlaylistId : Unique id given to each playlist.
        </p>
    </li>
    <li>
        <p>
            Playlistname : Name of the playlist given by user.
        </p>
    </li>
</ul>
<ol start="5">
    <li>
        <p>
            <strong>Directories:</strong>
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            FolderLocation
        </p>
    </li>
</ul>
<p>
    <strong>Primary key: </strong>
    (FolderLocation)
</p>
<p>
    Directories contains the address of the the select directories.
</p>
<ul>
    <li>
        <p>
            FolderLocation : Location of the directory.
        </p>
    </li>
</ul>
<p>
    <h1>
    <em>3. Identification of Relationships:</em>
        </h1>
</p>
<p>
    Track, Artist and Album are related to each other. <strong>TrackInfo </strong>contains the artist and album of each track.
</p>
<p>
    PlayList and Track are related to each other. <strong>PlayListInfo </strong>contains the track and playlist ids along with the order of track in playlist.
</p>
<p>
    Directories contains tracks. TrackLocation relates track to its directory.
</p>
<center>
    <table width="531" cellpadding="7" cellspacing="0">
        <colgroup>
            <col width="58"/>
            <col width="147"/>
            <col width="282"/>
        </colgroup>
        <tbody>
            <tr valign="top">
                <td width="58" height="26">
                    <p align="center">
                        <strong>S.No.</strong>
                    </p>
                </td>
                <td width="147">
                    <p align="center">
                        <strong>Relationship</strong>
                    </p>
                </td>
                <td width="282">
                    <p align="center">
                        <strong>Entity Sets</strong>
                    </p>
                </td>
            </tr>
            <tr valign="top">
                <td width="58" height="27">
                    <p align="center">
                        <strong>1</strong>
                    </p>
                </td>
                <td width="147">
                    <p align="center">
                        TrackInfo
                    </p>
                </td>
                <td width="282">
                    <p align="center">
                        Track, Artist, Album
                    </p>
                </td>
            </tr>
            <tr valign="top">
                <td width="58" height="29">
                    <p align="center">
                        <strong>4</strong>
                    </p>
                </td>
                <td width="147">
                    <p align="center">
                        PlaylistInfo
                    </p>
                </td>
                <td width="282">
                    <p align="center">
                        Playlist, Track
                    </p>
                </td>
            </tr>
            <tr valign="top">
                <td width="58" height="28">
                    <p align="center">
                        <strong>5</strong>
                    </p>
                </td>
                <td width="147">
                    <p align="center">
                        TrackLocation
                    </p>
                </td>
                <td width="282">
                    <p align="center">
                        Directories,Track
                    </p>
                </td>
            </tr>
        </tbody>
    </table>
</center>
<h5>
    <em><strong>5. Description Of Relations:</strong></em>
</h5>
<ol type="A">
    <li>
        <p>
            <strong>TrackInfo </strong>
            : (Track, Artist, Album)
        </p>
    </li>
</ol>
<p>
    <strong>Type </strong>
    : Ternary Relationship
</p>
<p>
    Mapping cardinality
</p>
<p>
    Track  Artist : (m : 1)
</p>
<p>
    Track  Album : (m : 1)
</p>
<p>
    Artist  Album : (1 : m)
</p>
<p>
    Track : Total participation
</p>
<p>
    Artist : Total participation
</p>
<p>
    Album : Total participation
</p>
<ol type="A" start="2">
    <li>
        <p>
            <strong>PlaylistInfo</strong>
            : (PlayList, Track)
        </p>
    </li>
</ol>
<p>
    <strong>Type </strong>
    : Binary Relationship
</p>
<p>
    Mapping cardinality : (m : n)
</p>
<p>
    Playlist : Total participation
</p>
<p>
    Track : Partial participation
</p>
<ol type="A" start="3">
    <li>
        <p>
            <strong>TrackLocation</strong>
            : (Directories, Track)
        </p>
    </li>
</ol>
<p>
    <strong>Type </strong>
    : Binary Relationship
</p>
<p>
    Mapping cardinality : (1 : m)
</p>
<p>
    Directories : Partial participation
</p>
<p>
    Track : Total participation
</p>
<h5>
    <em><strong>6.Transforming Entities and Relations into Tables:</strong></em>
</h5>
<ul>
    <li>
        <p>
            <strong>Entity sets into tables :</strong>
        </p>
    </li>
</ul>
<ol>
    <li>
        <p>
            Track :
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            TrackId int PRIMARY KEY NOT NULL,
        </p>
    </li>
    <li>
        <p>
            Trackname varchar(50) NOT NULL,
        </p>
    </li>
    <li>
        <p>
            Location varchar(500) NOT NULL UNIQUE,
        </p>
    </li>
    <li>
        <p>
            DateAdded timestamp CURRENTTIMESTAMP,
        </p>
    </li>
    <li>
        <p>
            Genre varchar(30) NOT NULL,
        </p>
    </li>
    <li>
        <p>
            Favourite boolean DEFAULT (0)
        </p>
    </li>
</ul>
<ol start="2">
    <li>
        <p>
            Artist :
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            ArtistId int PRIMARY KEY NOT NULL,
        </p>
    </li>
    <li>
        <p>
            Artistname varchar(50) NOT NULL UNIQUE
        </p>
    </li>
</ul>
<ol start="3">
    <li>
        <p>
            Album :
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            AlbumId int PRIMARY KEY NOT NULL,
        </p>
    </li>
    <li>
        <p>
            Albumname varchar(50) NOT NULL UNIQUE
        </p>
    </li>
</ul>
<ol start="4">
    <li>
        <p>
            Playlist :
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            PlaylistId int PRIMARY KEY NOT NULL,
        </p>
    </li>
    <li>
        <p>
            Playlistname varchar(100) NOT NULL UNIQUE
        </p>
    </li>
</ul>
<ol start="5">
    <li>
        <p>
            Directories :
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            FolderLocation varchar(500) NOT NULL UNIQUE
        </p>
    </li>
</ul>
<ul>
    <li>
        <p>
            <strong>Relationships into tables :</strong>
        </p>
    </li>
</ul>
<ol>
    <li>
        <p>
            TrackInfo :
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            Primary Key (TrackId)
        </p>
    </li>
    <li>
        <p>
            Foriegn Key (ArtistId, AlbumId)
        </p>
    </li>
</ul>
<ol start="2">
    <li>
        <p>
            PlayListInfo:
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            Primary Key (PlayListId, TrackId)
        </p>
    </li>
    <li>
        <p>
            Trackorder int NOT NULL
        </p>
    </li>
</ul>
<ol start="3">
    <li>
        <p>
            TrackLocation:
        </p>
    </li>
</ol>
<ul>
    <li>
        <p>
            Primary Key (Track.Location)
        </p>
    </li>
    <li>
        <p>
            Foriegn Key (FolderLocation)
        </p>
    </li>
</ul>
<h5>
    <br/>
</h5>
<h5>
    <em><strong>5. Functions:</strong></em>
</h5>
<p>
    1) Search for a artist
</p>
<p>
    CREATE FUNCTION searchartist (name varchar(50)) RETURNS int(11)
</p>
<p>
    BEGIN
</p>
<p>
    declare id int;
</p>
<p>
    set id = (select artistid from artist where artistname = name);
</p>
<p>
    if (id &gt; 0) then
</p>
<p>
    return id;
</p>
<p>
    end if;
</p>
<p>
    RETURN -1;
</p>
<p>
    END
</p>
<p>
    2) Search for a album
</p>
<p>
    CREATE FUNCTION searchalbum (name varchar(50)) RETURNS int(11)
</p>
<p>
    BEGIN
</p>
<p>
    declare id int;
</p>
<p>
    set id = (select albumid from artist where albumname = name);
</p>
<p>
    if (id &gt; 0) then
</p>
<p>
    return id;
</p>
<p>
    end if;
</p>
<p>
    RETURN -1;
</p>
<p>
    END
</p>
<h5>
    <br/>
</h5>
<h5>
    <em><strong>6. Procedures:</strong></em>
</h5>
<ol>
    <li>
        <p>
            Adding a Directory
        </p>
    </li>
</ol>
<p>
    CREATE PROCEDURE adddirectory (in location varchar(500))
</p>
<p>
    BEGIN
</p>
<p>
    insert into directories values (location);
</p>
<p>
    END
</p>
<ol start="2">
    <li>
        <p>
            Adding a Track
        </p>
    </li>
</ol>
<p>
    CREATE PROCEDURE addtrack(in trackname varchar(50),in artistname varchar(50),in albumname varchar(100),in location varchar(500),in genre varchar(30))
</p>
<p>
    Begin
</p>
<p>
    declare artistid int;
</p>
<p>
    declare albumid int;
</p>
<p>
    declare trackid int;
</p>
<p>
    set trackid = (select newid from id);
</p>
<p>
    set artistid = searchartist(artistname);
</p>
<p>
    set albumid = searchalbum(albumname);
</p>
<p>
    insert into track(trackid,trackname,location,genre) values(trackid, trackname, location, genre);
</p>
<p>
    if (artistid = -1) then
</p>
<p>
    set artistid = (select newid from id);
</p>
<p>
    insert into artist values (artistid, artistname);
</p>
<p>
    end if;
</p>
<p>
    if (albumid = -1) then
</p>
<p>
    set albumid = (select newid from id);
</p>
<p>
    insert into album values (albumid, albumname);
</p>
<p>
    end if;
</p>
<p>
    insert into trackinfo values (trackid, artistid, albumid);
</p>
<p>
    end
</p>
<p>
    3) Adding a Track to a Playlist
</p>
<p>
    CREATE PROCEDURE addtracktoplaylist (in pid int,in tid int)
</p>
<p>
    BEGIN
</p>
<p>
    declare torder int;
</p>
<p>
    set torder = (select count(*) from playlistinfo where playlistid=pid);
</p>
<p>
    set torder = torder + 1;
</p>
<p>
    insert into playlistinfo values(pid,tid,torder);
</p>
<p>
    END
</p>
<p>
    4) Creating a PlayList
</p>
<p>
    CREATE PROCEDURE createplaylist (in PlaylistName varchar(100))
</p>
<p>
    BEGIN
</p>
<p>
    declare pid int;
</p>
<p>
    set pid = (select playlistid from id);
</p>
<p>
    insert into playlist values(pid,PlaylistName);
</p>
<p>
    END
</p>
<p>
    5) Deleting a Directory
</p>
<p>
    CREATE PROCEDURE deletedirectory (in location varchar(500))
</p>
<p>
    BEGIN
</p>
<p>
    declare tid int;
</p>
<p>
    declare trackids cursor for select trackid from track where track.location like concat(location, '%'); delete from directories where folderlocation =
    location;
</p>
<p>
    open trackids;
</p>
<p>
    get_trackid : LOOP
</p>
<p>
    fetch trackids into tid;
</p>
<p>
    call deletetrack(tid);
</p>
<p>
    end loop get_trackid;
</p>
<p>
    close trackids;
</p>
<p>
    END
</p>
<p>
    6) Deleting a track from a playlist
</p>
<p>
    CREATE PROCEDURE deletefromplaylist (in pid int,in tid int)
</p>
<p>
    BEGIN
</p>
<p>
    declare torder int;
</p>
<p>
    set torder=(select trackorder from playlistinfo where playlistid=pid and trackid=tid);
</p>
<p>
    delete from playlistinfo where playlistid=pid and trackid=tid;
</p>
<p>
    update playlistinfo set trackorder=trackorder-1 where playlistid=pid and trackorder&gt;torder;
</p>
<p>
    END
</p>
<p>
    7) Deleting a playlist
</p>
<p>
    CREATE PROCEDURE deleteplaylist (in pid int)
</p>
<p>
    BEGIN
</p>
<p>
    delete from playlist where playlistid=pid;
</p>
<p>
    delete from playlistinfo where playlistid=pid;
</p>
<p>
    END
</p>
<p>
    8) Deleting a track
</p>
<p>
    CREATE PROCEDURE deletetrack (in TrackId int)
</p>
<p>
    BEGIN
</p>
<p>
    delete from track where track.trackid=TrackId;
</p>
<p>
    delete from trackinfo where trackinfo.trackid=TrackId;
</p>
<p>
    delete from playlistinfo where playlistinfo.trackid=TrackId;
</p>
<p>
    END
</p>
<p>
    9) Adding a track to Favourites
</p>
<p>
    CREATE PROCEDURE favourite (in TrackId int)
</p>
<p>
    BEGIN
</p>
<p>
    update track set favourite=True where track.trackid=TrackId;
</p>
<p>
    END
</p>
<p>
    10) Moving a track in playlist (up or down)
</p>
<p>
    CREATE PROCEDURE movetrack (in pid int, in tid int, in direction int)
</p>
<p>
    BEGIN
</p>
<p>
    declare swaptid int;
</p>
<p>
    declare torder int;
</p>
<p>
    set torder = (select trackorder from playlistinfo where playlistid = pid and trackid = tid);
</p>
<p>
    if(direction = 0) then
</p>
<p>
    set swaptid = (select trackid from playlistinfo where playlistid = pid and trackorder = torder - 1);
</p>
<p>
    update playlistinfo set trackorder = torder where playlistid = pid and trackid = swaptid;
</p>
<p>
    update playlistinfo set trackorder = torder - 1 where playlistid = pid and trackid = tid;
</p>
<p>
    else
</p>
<p>
    set swaptid = (select trackid from playlistinfo where playlistid = pid and trackorder = torder + 1);
</p>
<p>
    update playlistinfo set trackorder = torder where playlistid = pid and trackid = swaptid;
</p>
<p>
    update playlistinfo set trackorder = torder + 1 where playlistid = pid and trackid = tid;
</p>
<p>
    end if;
</p>
<p>
    END
</p>
<p>
    11) Removing a track from Favourites
</p>
<p>
    CREATE PROCEDURE unfavourite (in TrackId int)
</p>
<p>
    BEGIN
</p>
<p>
    update track set favourite=false where track.trackid=TrackId;
</p>
<p>
    END
</p>
<p>
    12) Update a playlist name
</p>
<p>
    CREATE PROCEDURE updateplaylist (in pid int, in newname varchar(50))
</p>
<p>
    BEGIN
</p>
<p>
    update playlist set playlistname = newname where playlistid = pid;
</p>
<p>
    END
</p>
<p>
    13) Updating a track information
</p>
<p>
    CREATE PROCEDURE updatetrack (in TrackId int,in newtrack varchar(50),in newartist varchar(50),in newalbum varchar(100),in newgenre varchar(30))
</p>
<p>
    BEGIN
</p>
<p>
    declare ArtistId int;
</p>
<p>
    declare AlbumId int;
</p>
<p>
    update track set trackname = newtrack, genre = newgenre where track.trackid = TrackId;
</p>
<p>
    set ArtistId=searchartist(newartist);
</p>
<p>
    set AlbumId=searchalbum(newalbum);
</p>
<p>
    if (ArtistId = -1) then
</p>
<p>
    set ArtistId=(select newid from id);
</p>
<p>
    insert into artist values (ArtistId,newartist);
</p>
<p>
    end if;
</p>
<p>
    if (AlbumId = -1) then
</p>
<p>
    set AlbumId = (select newid from id);
</p>
<p>
    insert into album values (AlbumId,newalbum);
</p>
<p>
    end if;
</p>
<p>
    update trackinfo set artistid=ArtistId, albumid=AlbumId where trackinfo.trackid = TrackId;
</p>
<p>
    END
</p>
<h5>
    <br/>
</h5>
<h5>
    <br/>
</h5>
<h5>
    <br/>
</h5>
<h5>
    <br/>
</h5>
<h5>
    <br/>
</h5>
<h5>
    <em><strong>7. Triggers:</strong></em>
</h5>
<p>
    1) Refresh artist and album after delete in trackinfo
</p>
<p>
    CREATE TRIGGER deleterefresh AFTER DELETE ON trackinfo
</p>
<p>
    FOR EACH ROW
</p>
<p>
    BEGIN
</p>
<p>
    DELETE FROM album WHERE albumid NOT IN (SELECT DISTINCT albumid FROM trackinfo);
</p>
<p>
    DELETE FROM artist WHERE artistid NOT IN (SELECT DISTINCT artistid FROM trackinfo);
</p>
<p>
    END
</p>
<p>
    2) Refresh artist and album after update in trackinfo
</p>
<p>
    CREATE TRIGGER deleterefresh AFTER UPDATE ON trackinfo
</p>
<p>
    FOR EACH ROW
</p>
<p>
    BEGIN
</p>
<p>
    DELETE FROM album WHERE albumid NOT IN (SELECT DISTINCT albumid FROM trackinfo);
</p>
<p>
    DELETE FROM artist WHERE artistid NOT IN (SELECT DISTINCT artistid FROM trackinfo);
</p>
<p>
    END
</p>
<h5>
    <br/>
</h5>
<h5>
    <em><strong>8. </strong></em>
    <em><strong>ER-DIAGRAM OF MUSIC MANAGEMENT SYSTEM</strong></em>
</h5>
<p align="center">
    <a name="_GoBack"></a>
    <img src="docs/images/er.png" name="Picture 1" align="bottom" width="624" height="596" border="0"/>
</p>
<p>
