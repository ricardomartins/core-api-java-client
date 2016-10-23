package pt.rikmartins.libs.coreapi.primitives;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public interface SuccessPrimitive extends Primitive {
    @Nullable
    SuccessPrimitive getParent();

    @NotNull
    String getUrl();

    URI getUriPiece(Map<String, Serializable> fieldValues) throws URISyntaxException; // Serializable may not be the best choice

    URI getResolvedUri(Map<String, Serializable> fieldValues) throws URISyntaxException; // Serializable may not be the best choice
}
