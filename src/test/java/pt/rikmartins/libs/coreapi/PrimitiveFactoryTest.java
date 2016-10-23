package pt.rikmartins.libs.coreapi;

import org.junit.Test;
import pt.rikmartins.libs.coreapi.PrimitiveFactory;
import pt.rikmartins.libs.coreapi.primitives.Document;
import pt.rikmartins.libs.coreapi.primitives.Error;
import pt.rikmartins.libs.coreapi.primitives.Link;

import static org.junit.Assert.*;

public class PrimitiveFactoryTest {
    @Test
    public void getInstance() throws Exception {
        assertEquals(PrimitiveFactory.getInstance(), PrimitiveFactory.getInstance());
    }

    @Test
    public void getPrimitive() throws Exception {
        assertTrue(PrimitiveFactory.getInstance().getPrimitive(Document.META_TYPE) instanceof Document);
        assertTrue(PrimitiveFactory.getInstance().getPrimitive(Link.META_TYPE) instanceof Link);
        assertTrue(PrimitiveFactory.getInstance().getPrimitive(Error.META_TYPE) instanceof Error);
    }
}