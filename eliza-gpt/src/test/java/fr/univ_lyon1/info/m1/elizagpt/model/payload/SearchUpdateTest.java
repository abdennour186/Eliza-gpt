package fr.univ_lyon1.info.m1.elizagpt.model.payload;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

class SearchUpdateTest {
    private SearchUpdate searchUpdate;
    private List<Message> searchResult;
    private String searchText;
    @BeforeEach
    void setup() {
        searchText = "a test search";
        searchUpdate = new SearchUpdate(searchText, searchResult);
    }
    @Test
    void getSearchText() {
        String result = searchUpdate.getSearchText();

        assertSame(searchText, result);
    }

    @Test
    void getSearchResult() {
        List<Message> result = searchUpdate.getSearchResult();

        assertSame(searchResult, result);
    }
}
