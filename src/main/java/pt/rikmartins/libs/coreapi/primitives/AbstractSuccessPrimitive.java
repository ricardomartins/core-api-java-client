package pt.rikmartins.libs.coreapi.primitives;

import com.sun.istack.internal.Nullable;
import pt.rikmartins.libs.coreapi.primitives.meta.Meta;
import pt.rikmartins.libs.utils.ListenedHashMap;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public abstract class AbstractSuccessPrimitive
        extends AbstractPrimitive
        implements SuccessPrimitive {
    private transient SuccessPrimitive parent;
    private transient boolean contentAsserted = false;

    @Override
    public URI getResolvedUri(@Nullable Map<String, Serializable> fieldValues) throws URISyntaxException {
        SuccessPrimitive parent = getParent();
        if (parent != null) return parent.getResolvedUri(fieldValues).resolve(getUriPiece(fieldValues));
        return getUriPiece(fieldValues);
    }

    @Override
    public SuccessPrimitive getParent() {
        return parent;
    }

    public void setParent(SuccessPrimitive parent) {
        this.parent = parent;
    }

    @Override
    public ListenedHashMap getContent() {
        ListenedHashMap content = super.getContent();
        if (!contentAsserted) {
            content.setMapListener(new ListenedHashMap.MapListener() {
                @Override
                public void onPut(Object obj) {
                    if (obj instanceof AbstractSuccessPrimitive)
                        ((AbstractSuccessPrimitive) obj).setParent(AbstractSuccessPrimitive.this);
                }
            });
            for (Object o : content.values())
                if (o instanceof AbstractSuccessPrimitive) ((AbstractSuccessPrimitive) o).setParent(this);
            contentAsserted = true;
        }
        return content;
    }

    @Override
    public void setContent(ListenedHashMap content) {
        if (contentAsserted) getContent().setMapListener(null);
        super.setContent(content);
        contentAsserted = false;
    }
}
