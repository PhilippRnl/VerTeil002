package dhbw.spotify.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dhbw.pojo.result.detail.DetailResult;
import dhbw.pojo.result.search.SearchResult;
import dhbw.spotify.RequestCategory;
import dhbw.spotify.SpotifyRequest;
import dhbw.spotify.WrongRequestTypeException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dhbw.pojo.search.track.SearchTrack;
import dhbw.pojo.search.album.SearchAlbum;
import dhbw.pojo.search.artist.SearchArtist;

import java.io.IOException;
import java.util.Optional;

import static dhbw.spotify.RequestType.*;
import static dhbw.spotify.service.DetailResultBuilder.buildDetailResult;



@RestController
public class DetailWebservice {

    public SpotifyRequest spotifyRequest = new SpotifyRequest(DETAIL);

    @RequestMapping("/detail/{id}")
    public String detailResult(@PathVariable String id, @RequestParam("type")RequestCategory requestCategory) throws WrongRequestTypeException, IOException {
        Optional<String> optional = spotifyRequest.performeRequestDetail(requestCategory, id);
        String json = null;
        if (optional.isPresent()) {
            json = optional.get();
        }
        return buildDetailResult(requestCategory, id, json);
    }
}


