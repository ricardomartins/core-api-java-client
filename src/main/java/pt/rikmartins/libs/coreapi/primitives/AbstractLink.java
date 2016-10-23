package pt.rikmartins.libs.coreapi.primitives;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import pt.rikmartins.libs.utils.ListenedHashMap;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractLink extends AbstractSuccessPrimitive
        implements Link {
    private String url;
    private transient boolean urlAsserted = false;
    private String action;
    private transient boolean actionAsserted = false;
    private String transform;
    private transient boolean transformAsserted = false;
    private Collection<Field> fields;
    private transient boolean fieldsAsserted = false;
    private String encoding;
    private transient boolean encodingAsserted = false;

    @Override
    @NotNull
    public String getUrl() {
        if (!urlAsserted) {
            if (url == null) url = "";
            if (url.startsWith("/")) url = url.substring(1); // All urls are relative
            urlAsserted = true;
        }
        return url;
    }

    public void setUrl(String url) {
        if (!urlAsserted) this.url = url;
    }

    @Override
    @NotNull
    public String getMethod() {
        if (!actionAsserted) {
            if (action == null) {
                action = DEFAULT_HTTP_METHOD;
                actionAsserted = true;
            }

            if (!actionAsserted) {
                String lowerCaseAction = action.toLowerCase();
                for (String httpMethod : HTTP_METHODS)
                    if (lowerCaseAction.equals(httpMethod)) {
                        action = httpMethod;
                        actionAsserted = true;
                        break;
                    }
            }
            if (!actionAsserted) {
                // TODO: throw exception
            }
        }
        return action;
    }

    public void setAction(String action) {
        this.action = action;
        actionAsserted = false;
    }

    @Override
    @NotNull
    public String getTransform() {
        if (!transformAsserted) {
            if (transform != null) {
                String lowerCaseTransform = transform.toLowerCase();
                for (String availableTransform : AVAILABLE_TRANSFORMS)
                    if (lowerCaseTransform.equals(availableTransform)) {
                        transform = availableTransform;
                        transformAsserted = true;
                        break;
                    }
            }

            if (!transformAsserted) {
                String action = getMethod();
                for (String httpMethod : METHODS_DEFAULT_TO_NEW)
                    if (action.equals(httpMethod)) {
                        transform = TRANSFORM_NEW;
                        break;
                    }
                if (transform == null) transform = TRANSFORM_INPLACE;
                // TODO: Avisar da utilização dum valor por omissão
                transformAsserted = true;
            }
            if (!transformAsserted) {
                // TODO: Lançar excepção
            }
        }
        return transform;
    }

    @Override
    public String getEncoding() { // Based on no documentation at all
        if (!encodingAsserted) {
            if (encoding == null) encoding = "application/json";
            encodingAsserted = true;
        }
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
        encodingAsserted = false;
    }

    @Override
    @NotNull
    public Collection<Field> getFields() {
        if (!fieldsAsserted) {
            if (fields == null) fields = new ArrayList<>();

            for (Iterator<Field> iterator = fields.iterator(); iterator.hasNext(); ) {
                Field next = iterator.next();
                if (next.isInvalid()) iterator.remove();
            }
            fieldsAsserted = true;
        }
        return fields;
    }

    @Override
    public String getType() {
        return Link.META_TYPE;
    }

    @Override
    public URI getUriPiece(@Nullable Map<String, Serializable> fieldValues) throws IllegalArgumentException, URISyntaxException {
        String url = getUrl();

        if (fieldValues == null) fieldValues = new HashMap<>();

        StringBuilder queryBuilder = new StringBuilder();
        Set<String> fieldValuesKeys = fieldValues.keySet();
        for (Field field : getFields()) {
            String fieldName = field.getName();
            if (fieldValues.containsKey(fieldName)) {
                fieldValuesKeys.remove(fieldName);
                if (Field.FIELD_LOCATION_PATH.equals(field.getLocation())) {
                    String nameInPath = "{" + fieldName + "}";
                    if(url.contains(nameInPath))
                        url = url.replace(nameInPath, fieldValues.get(fieldName).toString());
                } else if (Field.FIELD_LOCATION_QUERY.equals(field.getLocation())) {
                    if (queryBuilder.length() != 0) queryBuilder.append("&");
                    queryBuilder.append(fieldName);
                    queryBuilder.append("=");
                    queryBuilder.append(fieldValues.get(fieldName));
                }
            }
            else if (field.getRequired())
                throw new IllegalArgumentException("Required field \"" + fieldName + "\" was not supplied.");
        }

        for (String inexistentField : fieldValuesKeys) {
            // TODO: Warn about ignored fieldValues that were supplied
        }

        Matcher matches = Pattern.compile("^([^#?]*)(\\?[^#]*)?(#.*)?$").matcher(url);
        if (matches.find() && queryBuilder.length() > 0)
            url = matches.replaceFirst("$1$2" + (matches.group(2) == null ? "?" : "&") + queryBuilder.toString() + "$3");

        return new URI(url);
    }

    @Override
    public void setContent(ListenedHashMap content) {} // Do nothing

    public static abstract class AbstractField implements Link.Field {
        private String name;
        private Boolean required;
        private transient boolean requiredAsserted = false;
        private String location;
        private transient boolean locationAsserted = false;

        private transient Link parent;

        @Override
        @NotNull
        public Link getParent() {
            return parent;
        }

        public void setParent(Link parent) {
            this.parent = parent;
        }

        @Override
        @NotNull
        public String getName() {
            // Can not be null
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setRequired(Boolean required) {
            this.required = required;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        @Override
        @NotNull
        public Boolean getRequired() {
            if (!requiredAsserted) {
                if (required == null) required = false;
                requiredAsserted = true;
            }
            return required;
        }

        @Override
        @NotNull
        public String getLocation() {
            if (!locationAsserted) {
                if (location != null) {
                    String lowerCaseLocation = location.toLowerCase();
                    for (String availableLocation : FIELD_LOCATIONS)
                        if (lowerCaseLocation.equals(availableLocation)) {
                            location = availableLocation;
                            locationAsserted = true;
                            break;
                        }
                }

                if (!locationAsserted) {
                    String parentAction = getParent().getMethod();
                    for (String httpMethod : METHODS_DEFAULT_TO_QUERY)
                        if (parentAction.equals(httpMethod)) {
                            location = FIELD_LOCATION_QUERY;
                            break;
                        }
                    if (location == null) location = FIELD_LOCATION_FORM;
                    // TODO: Avisar da utilização dum valor por omissão
                    locationAsserted = true;
                }

                if (!locationAsserted) {
                    // TODO: Lançar excepção
                }
            }
            return location;
        }

        @Override
        public boolean isInvalid() {
            return name == null;
        }
    }
}
