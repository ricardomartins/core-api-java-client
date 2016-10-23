package pt.rikmartins.libs.coreapi.primitives;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import pt.rikmartins.libs.coreapi.primitives.meta.Meta;
import pt.rikmartins.libs.utils.ListenedHashMap;

public interface Primitive {
    @NotNull
    String getType();

    @Nullable
    Meta getMeta();

    @Nullable
    ListenedHashMap getContent();
}
