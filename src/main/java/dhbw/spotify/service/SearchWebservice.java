package dhbw.spotify.service;

import dhbw.pojo.result.search.SearchResult;
import dhbw.spotify.RequestCategory;
import dhbw.spotify.SpotifyRequest;
import dhbw.spotify.WrongRequestTypeException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Optional;
import static dhbw.spotify.RequestType.*;
import static dhbw.spotify.service.SearchResultBuilder.buildSearchResult;

@RestController
public class SearchWebservice {

    public SpotifyRequest spotifyRequest = new SpotifyRequest(SEARCH);

    @RequestMapping("/search")
    public SearchResult search (@RequestParam("query") String query, @RequestParam("type")RequestCategory requestCategory) throws WrongRequestTypeException, IOException {
        Optional<String> optional = spotifyRequest.performeRequestSearch(requestCategory, query);
        String json = null;
        if (optional.isPresent()) {
            json = optional.get();
        }
        return buildSearchResult(requestCategory, json, query);
    }
}


