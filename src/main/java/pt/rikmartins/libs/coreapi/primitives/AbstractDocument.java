package pt.rikmartins.libs.coreapi.primitives;

import com.sun.istack.internal.Nullable;
import pt.rikmartins.libs.coreapi.primitives.meta.UrlMeta;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public abstract class AbstractDocument
        extends AbstractSuccessPrimitive
        implements Document {
    @Override
    public URI getUriPiece(@Nullable Map<String, Serializable> fieldValues) throws IllegalArgumentException, URISyntaxException {
        return new URI(getUrl());
    }

    @Override
    public UrlMeta getMeta() {
        return (UrlMeta) super.getMeta();
    }

    @Override
    public String getUrl() {
        return getMeta().getUrl();
    }

    @Override
    public String getType() {
        return Document.META_TYPE;
    }

    @Override
    public Link.Action action(List<String> query, Map<String, Serializable> arguments) throws CoreApiException {
        return showLink(query).action(arguments);
    }

    public Document showDocument(List<String> query) throws CoreApiException {
        try {
            return (Document) show(query);
        } catch (ClassCastException cce) {
            throw new CoreApiException("Query does not return a document primitive", cce);
        }
    }

    public Link showLink(List<String> query) throws CoreApiException {
        try {
            return (Link) show(query);
        } catch (ClassCastException cce) {
            throw new CoreApiException("Query does not return a link primitive", cce);
        }
    }

    public Map showMap(List<String> query) throws CoreApiException {
        try {
            return (Map) show(query);
        } catch (ClassCastException cce) {
            throw new CoreApiException("Query does not return a map", cce);
        }
    }

    public List showList(List<String> query) throws CoreApiException {
        try {
            return (List) show(query);
        } catch (ClassCastException cce) {
            throw new CoreApiException("Query does not return a list", cce);
        }
    }

    @Override
    public Object show(List<String> query) throws CoreApiException {
        return showFromObject(query, this);
    }

    private Object showFromObject(List<String> query, Object o) throws CoreApiException {
        if (query.isEmpty()) return o;

        if (o instanceof Document) return showFromMap(query, ((Document) o).getContent());
        if (o instanceof Map) return showFromMap(query, (Map) o);
        if (o instanceof List) return showFromList(query, (List) o);

        throw new CoreApiException("Document is badly constructed");
    }

    private Object showFromList(List<String> query, List list) throws CoreApiException {
        try {
            int index = Integer.parseInt(query.get(0));
            return showFromObject(query.subList(1, query.size()), list.get(index));
        } catch (NumberFormatException e) {
            throw new CoreApiException("Index expected, instead found \"" + query.get(0) + "\"", e);
        } catch (IndexOutOfBoundsException e) {
            throw new CoreApiException("Index is out of bounds", e);
        }
    }

    private Object showFromMap(List<String> query, Map map) throws CoreApiException {
        if (!map.containsKey(query.get(0)))
            throw new CoreApiException("Key \"" + query.get(0) + "\" unavailable at this point");
        Object o = map.get(query.get(0));
        if (o == null) throw new CoreApiException("Document is badly constructed");
        return showFromObject(query.subList(1, query.size()), o);
    }
}
