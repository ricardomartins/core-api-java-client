package pt.rikmartins.libs.coreapi.primitives;

import pt.rikmartins.libs.coreapi.MetaImpl;
import pt.rikmartins.libs.coreapi.primitives.meta.Meta;
import pt.rikmartins.libs.coreapi.primitives.meta.UrlMeta;
import pt.rikmartins.libs.utils.ListenedHashMap;

public abstract class AbstractPrimitive
        implements Primitive {
    private Meta meta;
    private transient boolean metaAsserted = false;
    private ListenedHashMap content;
    private transient boolean contentAsserted = false;

    protected abstract Meta createEmptyMeta();

    @Override
    public Meta getMeta() {
        if (!metaAsserted) {
            if (meta == null) meta = createEmptyMeta();
            metaAsserted = true;
        }
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
        metaAsserted = false;
    }

    @Override
    public ListenedHashMap getContent() {
        if (!contentAsserted) {
            if (content == null) content = new ListenedHashMap();
            contentAsserted = true;
        }

        return content;
    }

    public void setContent(ListenedHashMap content) {
        this.content = content;
        contentAsserted = false;
    }

}
