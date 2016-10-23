package pt.rikmartins.libs.coreapi;

import pt.rikmartins.libs.coreapi.primitives.AbstractDocument;

public class DocumentImpl extends AbstractDocument {
    public DocumentImpl() {}

    public DocumentImpl(String title) {
        getMeta().setTitle(title);
    }

    public DocumentImpl(String title, String url) {
        getMeta().setTitle(title);
        getMeta().setUrl(url);
    }

    @Override
    public UrlMetaImpl getMeta() {
        return (UrlMetaImpl) super.getMeta();
    }

    public void putContent(String key, Object value) {
        getContent().put(key, value);
    }

    @Override
    protected UrlMetaImpl createEmptyMeta() {
        return new UrlMetaImpl();
    }
}
