package dhbw.spotify.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dhbw.pojo.result.search.SearchResult;
import dhbw.spotify.RequestCategory;
import dhbw.spotify.SpotifyRequest;
import dhbw.spotify.WrongRequestTypeException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dhbw.pojo.search.track.SearchTrack;
import dhbw.pojo.search.album.SearchAlbum;
import dhbw.pojo.search.artist.SearchArtist;

import java.io.IOException;
import java.util.Optional;

import static dhbw.spotify.RequestType.*;

@RestController
public class SearchWebservice {

    public SpotifyRequest spotifyRequest = new SpotifyRequest(SEARCH);


//    spotifyRequest.requestType = RequestType.SEARCH;

    @RequestMapping("/search")
    public SearchResult search (@RequestParam("query") String query, @RequestParam("type")RequestCategory requestCategory) throws WrongRequestTypeException, IOException {
        Optional<String> optional = spotifyRequest.performeRequestSearch(requestCategory, query);
        String json;
        if (optional.isPresent()) {
            json = optional.get();
        }
        SearchResultBuilder.buildSearchResult(requestCategory, json, query);

    }
}


