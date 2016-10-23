package pt.rikmartins.libs.coreapi.primitives.meta;

import com.sun.istack.internal.NotNull;

public interface UrlMeta extends SimpleMeta {
    @NotNull
    String getUrl();
}
