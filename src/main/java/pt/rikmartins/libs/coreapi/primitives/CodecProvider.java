package pt.rikmartins.libs.coreapi.primitives;

public interface CodecProvider {
    Codec get(String codecName);

    interface Codec {
        String getName();
        String encode();
    }
}
