package pt.rikmartins.libs.coreapi;

import pt.rikmartins.libs.coreapi.primitives.meta.SimpleMeta;

/**
 * Created by ricardo on 22-10-2016.
 */
public class MetaImpl implements SimpleMeta {
    private String title;
    private transient boolean titleAsserted = false;

    @Override
    public String getTitle() {
        if (!titleAsserted) {
            if (title == null) title = "";
            titleAsserted = true;
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        titleAsserted = false;
    }
}
