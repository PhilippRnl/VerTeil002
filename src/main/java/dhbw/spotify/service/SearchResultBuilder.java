package dhbw.spotify.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import dhbw.pojo.result.search.SearchResult;
import dhbw.pojo.search.album.SearchAlbum;
import dhbw.pojo.search.track.SearchTrack;
import dhbw.pojo.search.artist.SearchArtist;
import dhbw.spotify.service.SearchWebservice;
import dhbw.spotify.RequestCategory;
import dhbw.spotify.SpotifyRequest;
import dhbw.spotify.WrongRequestTypeException;

import static dhbw.spotify.RequestCategory.ALBUM;

public class SearchResultBuilder {
    public static SearchResult buildSearchResult (RequestCategory requestCategory) {
        ObjectMapper mapper = new ObjectMapper();
        switch(requestCategory){
            case ALBUM:
                SearchAlbum searchAlbum = mapper.readValue(json, SearchAlbum.class);
                searchAlbum.getAlbums();
                return searchAlbum;
                break;

            case TRACK:
                SearchTrack searchTrack = mapper.readValue(json, SearchTrack.class);
                searchTrack.getTracks();
                return searchTrack;
            break;

            case ARTIST:
                SearchArtist searchArtist= mapper.readValue(json, SearchTrack.class);
                searchTrack.getTracks();
                return searchTrack;
            break;

        }

    }

    public static buildSearchResultAlbum(){
        


    }
}
