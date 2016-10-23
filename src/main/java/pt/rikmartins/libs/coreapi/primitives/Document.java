package pt.rikmartins.libs.coreapi.primitives;

import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface Document
        extends SuccessPrimitive {
    String META_TYPE = "document";

    @Nullable
    Link.Action action(List<String> query, Map<String, Serializable> arguments) throws CoreApiException;

    Object show(List<String> query) throws CoreApiException;
}
