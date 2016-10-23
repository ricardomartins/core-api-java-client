package pt.rikmartins.libs.coreapi;

import org.junit.Before;
import org.junit.Test;
import pt.rikmartins.libs.coreapi.primitives.CoreApiException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DocumentImplTest {
    private SimpleDocTest simpleDocTest;

    @Before
    public void setUp() throws Exception {
        simpleDocTest = new SimpleDocTest();
    }

    @Test
    public void getUriPiece() throws Exception {
        assertEquals(simpleDocTest.document.getUriPiece(null), new URI(""));
    }

    @Test
    public void getResolvedUri() throws Exception {
        assertEquals(simpleDocTest.document.getResolvedUri(null), new URI(""));
    }

    @Test
    public void getUrl() throws Exception {
        assertEquals(simpleDocTest.document.getUrl(), "");
    }

    @Test
    public void showDocument() throws Exception {
        assertEquals(simpleDocTest.document.showDocument(new ArrayList<String>()), simpleDocTest.document);
    }

    @Test(expected = CoreApiException.class)
    public void showDocumentException() throws Exception {
        List<String> query = new ArrayList<>();
        query.add("snippets");
        query.add("create");
        simpleDocTest.document.showDocument(query);
    }

    @Test
    public void showLink() throws Exception {
        List<String> query = new ArrayList<>();
        query.add("snippets");
        query.add("create");
        assertEquals(simpleDocTest.document.showLink(query), simpleDocTest.snippetsCreate);

        query.set(1, "destroy");
        assertEquals(simpleDocTest.document.showLink(query), simpleDocTest.snippetsDestroy);

        query.set(1, "highlight");
        assertEquals(simpleDocTest.document.showLink(query), simpleDocTest.snippetsHighlight);

        query.set(1, "list");
        assertEquals(simpleDocTest.document.showLink(query), simpleDocTest.snippetsList);

        query.set(1, "partial_update");
        assertEquals(simpleDocTest.document.showLink(query), simpleDocTest.snippetsPartialUpdate);

        query.set(1, "retrieve");
        assertEquals(simpleDocTest.document.showLink(query), simpleDocTest.snippetsRetrieve);

        query.set(1, "update");
        assertEquals(simpleDocTest.document.showLink(query), simpleDocTest.snippetsUpdate);

        query.set(0, "users");
        query.set(1, "list");
        assertEquals(simpleDocTest.document.showLink(query), simpleDocTest.usersList);

        query.set(1, "retrieve");
        assertEquals(simpleDocTest.document.showLink(query), simpleDocTest.usersRetrieve);
    }

    @Test
    public void show() throws Exception {

    }

    @Test
    public void getParent() throws Exception {

    }

    @Test
    public void setParent() throws Exception {

    }

    @Test
    public void getContent() throws Exception {

    }

    @Test
    public void setContent() throws Exception {

    }

    @Test
    public void createEmptyMeta1() throws Exception {

    }

    @Test
    public void getMeta() throws Exception {

    }

    @Test
    public void setMeta() throws Exception {

    }

    @Test
    public void getContent1() throws Exception {

    }

    @Test
    public void setContent1() throws Exception {

    }
}
