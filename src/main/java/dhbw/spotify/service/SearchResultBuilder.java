package dhbw.spotify.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import dhbw.pojo.result.search.SearchResult;
import dhbw.pojo.result.search.SearchResultList;
import dhbw.pojo.search.album.Item;
import dhbw.pojo.search.album.SearchAlbum;
import dhbw.pojo.search.track.SearchTrack;
import dhbw.pojo.search.artist.SearchArtist;
import dhbw.spotify.RequestCategory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchResultBuilder {
    static ObjectMapper mapper = new ObjectMapper();
        public static SearchResult buildSearchResult (RequestCategory requestCategory, String json, String query) throws IOException {
        SearchResult r = new SearchResult();
        List<SearchResultList> list = new ArrayList<SearchResultList> ();
        r.setSearchTerm(null);
        r.setSearchCategory(null);
        r.setResults(null);
        r.setSearchCategory(requestCategory.toString());
        r.setSearchTerm(query);

        switch(requestCategory){
            case ALBUM:
                list = buildAlbum(json);
                break;
            case TRACK:
                list = buildTrack(json);
                break;
            case ARTIST:
                list = buildArtist(json);
                break;
        }
        r.setResults(list);
        return r;
    }

    public static List<SearchResultList> buildAlbum(String json) throws IOException {
        List<SearchResultList> hilfsliste = new ArrayList<SearchResultList> ();
        SearchAlbum searchAlbum = mapper.readValue(json, SearchAlbum.class);
        for (Item a : searchAlbum.getAlbums().getItems()) {
            SearchResultList searchResultList = new SearchResultList();
            searchResultList.setId(a.getId());
            searchResultList.setTitle(a.getName());
            searchResultList.setDescription(a.getType());
            searchResultList.setPlayLink(a.getUri());
            hilfsliste.add(searchResultList);
        }
        return hilfsliste;
    }

    public static List<SearchResultList> buildTrack(String json) throws IOException {
        SearchTrack searchTrack = mapper.readValue (json, SearchTrack.class);
        List<SearchResultList> hilfsliste = new ArrayList<SearchResultList> ();
        for (dhbw.pojo.search.track.Item a : searchTrack.getTracks().getItems()) {
            SearchResultList searchResultList = new SearchResultList();
            searchResultList.setId(a.getId());
            searchResultList.setTitle(a.getName());
            searchResultList.setDescription(a.getType());
            searchResultList.setPlayLink(a.getUri());
            hilfsliste.add(searchResultList);
        }
        return hilfsliste;
    }

    public static List<SearchResultList> buildArtist(String json) throws IOException {
        SearchArtist searchArtist = mapper.readValue(json, SearchArtist.class);
        List<SearchResultList> hilfsliste = new ArrayList<SearchResultList> ();
        for (dhbw.pojo.search.artist.Item a : searchArtist.getArtists().getItems()) {
            SearchResultList searchResultList = new SearchResultList();
            searchResultList.setId(a.getId());
            searchResultList.setTitle(a.getName());
            searchResultList.setDescription(a.getType());
            searchResultList.setPlayLink(a.getUri());
            hilfsliste.add(searchResultList);
        }
        return hilfsliste;
    }
}