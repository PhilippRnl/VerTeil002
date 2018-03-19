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

import java.io.IOException;

import static dhbw.spotify.RequestCategory.ALBUM;

public class SearchResultBuilder {
    static ObjectMapper mapper = new ObjectMapper();
    public static SearchResult buildSearchResult (RequestCategory requestCategory, String json, String query) throws IOException {

        switch(requestCategory){
            case ALBUM:
                return buildAlbum(json);

                break;

            case TRACK:

                return buildTrack(json);
            break;

            case ARTIST:
                return buildArtist(json);
            break;

        }

        SearchResult searchResult = new SearchResult(query, requestCategory, resultList);
        return searchResult;

    }

    public static SearchResult buildAlbum(String json) throws IOException {
        SearchAlbum searchAlbum = mapper.readValue(json, SearchAlbum.class);


    }

    public static SearchResult buildTrack(String json) throws IOException {
        SearchTrack searchTrack = mapper.readValue (json, SearchTrack.class);

    }
    public static SearchResult buildArtist(String json) throws IOException {
        SearchArtist searchArtist = mapper.readValue(json, SearchArtist.class);

    }
}
