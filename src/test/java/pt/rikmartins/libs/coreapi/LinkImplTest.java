package pt.rikmartins.libs.coreapi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkImplTest {
    private SimpleDocTest simpleDocTest;

    @Before
    public void setUp() throws Exception {
        simpleDocTest = new SimpleDocTest();
    }

    @Test
    public void addField() throws Exception {

    }

    @Test
    public void createEmptyMeta() throws Exception {
        assertNull(simpleDocTest.snippetsCreate.createEmptyMeta());
        assertNull(simpleDocTest.snippetsDestroy.createEmptyMeta());
        assertNull(simpleDocTest.snippetsHighlight.createEmptyMeta());
        assertNull(simpleDocTest.usersList.createEmptyMeta());
    }

    @Test
    public void getContent() throws Exception {
        assertNull(simpleDocTest.snippetsCreate.getContent());
        assertNull(simpleDocTest.snippetsDestroy.getContent());
        assertNull(simpleDocTest.snippetsHighlight.getContent());
        assertNull(simpleDocTest.usersList.getContent());
    }

    @Test
    public void action() throws Exception {

    }

    @Test
    public void getUrl() throws Exception {

    }

    @Test
    public void setUrl() throws Exception {

    }

    @Test
    public void getAction() throws Exception {

    }

    @Test
    public void setAction() throws Exception {

    }

    @Test
    public void getTransform() throws Exception {

    }

    @Test
    public void getEncoding() throws Exception {

    }

    @Test
    public void setEncoding() throws Exception {

    }

    @Test
    public void getFields() throws Exception {

    }

    @Test
    public void getType() throws Exception {

    }

    @Test
    public void getUriPiece() throws Exception {

    }

    @Test
    public void setContent() throws Exception {

    }

    @Test
    public void getResolvedUri() throws Exception {

    }

    @Test
    public void getParent() throws Exception {

    }

    @Test
    public void setParent() throws Exception {

    }

    @Test
    public void getContent1() throws Exception {

    }

    @Test
    public void setContent1() throws Exception {

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
    public void getContent2() throws Exception {

    }

    @Test
    public void setContent2() throws Exception {

    }

}