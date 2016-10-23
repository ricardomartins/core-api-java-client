package pt.rikmartins.libs.coreapi;

import pt.rikmartins.libs.coreapi.primitives.Primitive;

public class PrimitiveFactory {
    private static final PrimitiveFactory INSTANCE = new PrimitiveFactory();

    public static PrimitiveFactory getInstance() {
        return INSTANCE;
    }

    private PrimitiveFactory() {}

    public Primitive getPrimitive(String type) {
        if (type != null) {
            String lowerCaseType = type.toLowerCase();
            if (lowerCaseType.equals(DocumentImpl.META_TYPE)) return new DocumentImpl();
            if (lowerCaseType.equals(LinkImpl.META_TYPE)) return new LinkImpl();
//            if (lowerCaseType.equals(Error.META_TYPE)) return new Error();
        }
        return null;
    }
}
