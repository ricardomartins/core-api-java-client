package pt.rikmartins.libs.coreapi.primitives;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;

public interface Link extends SuccessPrimitive {
    String META_TYPE = "link";

    String HTTP_METHOD_GET = "get";
    String HTTP_METHOD_POST = "post";
    String HTTP_METHOD_PUT = "put";
    String HTTP_METHOD_PATCH = "patch";
    String HTTP_METHOD_DELETE = "delete";
    String[] HTTP_METHODS =
            {AbstractLink.HTTP_METHOD_GET, AbstractLink.HTTP_METHOD_POST, AbstractLink.HTTP_METHOD_PUT, AbstractLink.HTTP_METHOD_PATCH,
                    AbstractLink.HTTP_METHOD_DELETE};
    String DEFAULT_HTTP_METHOD = AbstractLink.HTTP_METHOD_GET;
    String TRANSFORM_NEW = "new";
    String TRANSFORM_INPLACE = "inplace";
    String[] AVAILABLE_TRANSFORMS = {AbstractLink.TRANSFORM_NEW, AbstractLink.TRANSFORM_INPLACE};
    String[] METHODS_DEFAULT_TO_NEW = {AbstractLink.HTTP_METHOD_GET, AbstractLink.HTTP_METHOD_POST};
    String[] METHODS_DEFAULT_TO_INPLACE =
            {AbstractLink.HTTP_METHOD_PUT, AbstractLink.HTTP_METHOD_PATCH, AbstractLink.HTTP_METHOD_DELETE};

    @NotNull
    String getMethod();

    @NotNull
    String getTransform();

    @NotNull
    String getEncoding(); // Found documents generated with this, otherwise is undocumented

    @NotNull
    Collection<Field> getFields();

    @Nullable
    Action action(Map<String, Serializable> arguments);

    interface Field {
        String FIELD_LOCATION_PATH = "path";
        String FIELD_LOCATION_QUERY = "query";
        String FIELD_LOCATION_FORM = "form";
        String FIELD_LOCATION_BODY = "body"; // I have doubts whether this is ever used, reference in http://www.django-rest-framework.org/api-guide/schemas/#core-api
        String[] FIELD_LOCATIONS =
                {FIELD_LOCATION_PATH, FIELD_LOCATION_QUERY, FIELD_LOCATION_FORM, FIELD_LOCATION_BODY};
        String[] METHODS_DEFAULT_TO_QUERY = {HTTP_METHOD_GET, HTTP_METHOD_DELETE};
        String[] METHODS_DEFAULT_TO_FORM =
                {HTTP_METHOD_PUT, HTTP_METHOD_PATCH, HTTP_METHOD_POST};

        @NotNull
        Link getParent();

        @NotNull
        String getName();

        @NotNull
        Boolean getRequired();

        @NotNull
        String getLocation();

        boolean isInvalid();
    }

    interface Action {
        String getMethod();

        String getUrl() throws URISyntaxException;

        String getContentType(); // Is this really needed?

        String getBody();
    }
}
