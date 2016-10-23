package pt.rikmartins.libs.utils;

import java.util.HashMap;
import java.util.Map;

public class ListenedHashMap extends HashMap<String, Object> {
    private MapListener mapListener = null;

    public ListenedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ListenedHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public ListenedHashMap() {
        super();
    }

    public ListenedHashMap(Map<? extends String, ?> m) {
        super(m);
    }

    public void setMapListener(MapListener mapListener) {
        this.mapListener = mapListener;
    }

    @Override
    public Object put(String key, Object value) {
        if (mapListener != null) mapListener.onPut(value);
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        if (mapListener != null) for (Object obj : m.values()) mapListener.onPut(obj);
        super.putAll(m);
    }

    public interface MapListener {
        void onPut(Object obj);
    }
}
