package dhbw.spotify.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import dhbw.pojo.detail.album.DetailsAlbum;
import dhbw.pojo.detail.artist.DetailsArtist;
import dhbw.pojo.detail.track.DetailsTrack;
import dhbw.pojo.result.detail.DetailResult;
import dhbw.pojo.result.search.SearchResult;
import dhbw.pojo.result.search.SearchResultList;
import dhbw.pojo.search.album.Albums;
import dhbw.pojo.search.album.Item;
import dhbw.pojo.search.album.SearchAlbum;
import dhbw.pojo.search.track.Album;
import dhbw.pojo.search.track.SearchTrack;
import dhbw.pojo.search.artist.SearchArtist;
import dhbw.spotify.service.SearchWebservice;
import dhbw.spotify.RequestCategory;
import dhbw.spotify.SpotifyRequest;
import dhbw.spotify.WrongRequestTypeException;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dhbw.spotify.RequestCategory.ALBUM;

public class DetailResultBuilder {
    static ObjectMapper mapper = new ObjectMapper();
    public static String buildDetailResult (RequestCategory requestCategory, String id, String json) throws IOException {
        DetailResult dr = new DetailResult();

        switch(requestCategory){
            case ALBUM:
                dr = detailAlbum(id, json);
                break;

            case TRACK:

                dr = detailTrack(id, json);
                break;

            case ARTIST:
                dr = detailArtist(id, json);
                break;

        }
        return mapper.writeValueAsString(dr);
    }

    public static DetailResult detailAlbum(String id, String json) throws IOException {
        DetailsAlbum da = mapper.readValue(json, DetailsAlbum.class);
        DetailResult dr = new DetailResult();
        dr.setTitle(da.getName());
        dr.setInfo(da.getType());
        return dr;
    }

    public static DetailResult detailTrack(String id, String json)throws  IOException {
        DetailsTrack dt = mapper.readValue(json, DetailsTrack.class);
        DetailResult dr = new DetailResult();
        dr.setTitle(dt.getName());
        dr.setInfo(dt.getType());
        return dr;
    }

    public static DetailResult detailArtist(String id, String json) throws IOException {
        DetailsArtist da = mapper.readValue(json, DetailsArtist.class);
        DetailResult dr = new DetailResult();
        dr.setTitle(da.getName());
        dr.setInfo(da.getType());
        return dr;
    }
}
