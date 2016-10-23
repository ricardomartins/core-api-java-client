package pt.rikmartins.libs.coreapi;

import pt.rikmartins.libs.coreapi.primitives.*;
import pt.rikmartins.libs.coreapi.primitives.meta.NoMeta;
import pt.rikmartins.libs.utils.ListenedHashMap;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;

public class LinkImpl extends AbstractLink {
    public LinkImpl() {}

    public LinkImpl(String url) {
        this(url, null);
    }

    public LinkImpl(String url, String action) {
        setUrl(url);
        setAction(action);
    }

    public void addField(Field field) {
        getFields().add(field);
    }

    @Override
    protected NoMeta createEmptyMeta() {
        return null;
    }

    @Override
    public ListenedHashMap getContent() {
        return null; // Links have no content (at least for now)
    }

    @Override
    public Action action(Map<String, Serializable> arguments) {
        return null; // TODO: Now
    }

    public static class FieldImpl extends AbstractField {
        public FieldImpl(String name, Boolean required, String location) {
            setName(name);
            setRequired(required);
            setLocation(location);
        }

        public FieldImpl(String name, String location) {
            this(name, null, location);
        }
    }

    public static class ActionImpl implements Action {
        private Link link;
        private Map<String, Serializable> fieldValues;
        private CodecProvider codecProvider;

        public ActionImpl(LinkImpl link, Map<String, Serializable> fieldValues) {
            this.link = link;
            this.fieldValues = fieldValues;
        }

        public void setCodecProvider(CodecProvider codecProvider) {
            this.codecProvider = codecProvider;
        }

        CodecProvider getCodecProvider() { // Not sure about this
            return codecProvider;
        }

        Link getLink() {
            return link;
        }

        Map<String, Serializable> getFieldValues() {
            return fieldValues;
        }

        @Override
        public String getMethod() {
            return getLink().getMethod();
        }

        @Override
        public String getUrl() throws URISyntaxException {
            return getLink().getResolvedUri(getFieldValues()).toString();
        }

        @Override
        public String getContentType() {
            return getLink().getEncoding();
        }

        @Override
        public String getBody() {
            Collection<Field> fields = getLink().getFields();

            for (Field field : fields) {
                if (Field.FIELD_LOCATION_BODY.equals(field.getLocation())) {
                    if (getFieldValues().containsKey(field.getName())) {
                        // Codecs should be nearby
                        // JSON body for now
                        getCodecProvider().get(getLink().getEncoding()).encode();

                    } else if (field.getRequired()) {
                        // TODO: Throw some error
                    }
                }
            }
            return null;
        }
    }
}
