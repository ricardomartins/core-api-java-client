package pt.rikmartins.libs.coreapi;

import pt.rikmartins.libs.coreapi.primitives.meta.UrlMeta;

/**
 * Created by ricardo on 22-10-2016.
 */
public class UrlMetaImpl extends MetaImpl implements UrlMeta {
    private String url;
    private transient boolean urlAsserted = false;

    @Override
    public String getUrl() {
        if (!urlAsserted) {
            if (url == null) url = "";
            if (url.startsWith("/")) url = url.substring(1);
            urlAsserted = true;
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        urlAsserted = false;
    }
}
