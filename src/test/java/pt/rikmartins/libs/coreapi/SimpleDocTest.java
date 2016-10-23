package pt.rikmartins.libs.coreapi;

import pt.rikmartins.libs.coreapi.primitives.Link;

import java.util.HashMap;
import java.util.Map;

class SimpleDocTest {
    DocumentImpl document;
    LinkImpl snippetsCreate;
    LinkImpl snippetsDestroy;
    LinkImpl snippetsHighlight;
    LinkImpl snippetsList;
    LinkImpl snippetsPartialUpdate;
    LinkImpl snippetsRetrieve;
    LinkImpl snippetsUpdate;
    LinkImpl usersList;
    LinkImpl usersRetrieve;

    SimpleDocTest() {
        document = new DocumentImpl("Pastebin API");

        Map<String, Object> snippets = new HashMap<>();
        document.putContent("snippets", snippets);

        snippetsCreate = new LinkImpl("/snippets/", LinkImpl.HTTP_METHOD_POST);
        snippets.put("create", snippetsCreate);
        snippetsCreate.setEncoding("application/json");
        snippetsCreate.addField(new LinkImpl.FieldImpl("title", Link.Field.FIELD_LOCATION_FORM));
        snippetsCreate.addField(new LinkImpl.FieldImpl("code", true, Link.Field.FIELD_LOCATION_FORM));
        snippetsCreate.addField(new LinkImpl.FieldImpl("linenos", Link.Field.FIELD_LOCATION_FORM));
        snippetsCreate.addField(new LinkImpl.FieldImpl("language", Link.Field.FIELD_LOCATION_FORM));
        snippetsCreate.addField(new LinkImpl.FieldImpl("style", Link.Field.FIELD_LOCATION_FORM));

        snippetsDestroy = new LinkImpl("/snippets/{pk}/", LinkImpl.HTTP_METHOD_DELETE);
        snippets.put("destroy", snippetsDestroy);
        snippetsDestroy.addField(new LinkImpl.FieldImpl("pk", true, Link.Field.FIELD_LOCATION_PATH));

        snippetsHighlight = new LinkImpl("/snippets/{pk}/highlight/", LinkImpl.HTTP_METHOD_GET);
        snippets.put("highlight", snippetsHighlight);
        snippetsHighlight.addField(new LinkImpl.FieldImpl("pk", true, Link.Field.FIELD_LOCATION_PATH));

        snippetsList = new LinkImpl("/snippets/", LinkImpl.HTTP_METHOD_GET);
        snippets.put("list", snippetsList);
        snippetsList.addField(new LinkImpl.FieldImpl("page", Link.Field.FIELD_LOCATION_QUERY));

        snippetsPartialUpdate = new LinkImpl("/snippets/{pk}/", LinkImpl.HTTP_METHOD_PATCH);
        snippets.put("partial_update", snippetsPartialUpdate);
        snippetsPartialUpdate.setEncoding("application/json");
        snippetsPartialUpdate.addField(new LinkImpl.FieldImpl("pk", true, Link.Field.FIELD_LOCATION_PATH));
        snippetsPartialUpdate.addField(new LinkImpl.FieldImpl("title", Link.Field.FIELD_LOCATION_FORM));
        snippetsPartialUpdate.addField(new LinkImpl.FieldImpl("code", Link.Field.FIELD_LOCATION_FORM));
        snippetsPartialUpdate.addField(new LinkImpl.FieldImpl("linenos", Link.Field.FIELD_LOCATION_FORM));
        snippetsPartialUpdate.addField(new LinkImpl.FieldImpl("language", Link.Field.FIELD_LOCATION_FORM));
        snippetsPartialUpdate.addField(new LinkImpl.FieldImpl("style", Link.Field.FIELD_LOCATION_FORM));

        snippetsRetrieve = new LinkImpl("/snippets/{pk}/", LinkImpl.HTTP_METHOD_GET);
        snippets.put("retrieve", snippetsRetrieve);
        snippetsRetrieve.addField(new LinkImpl.FieldImpl("pk", true, Link.Field.FIELD_LOCATION_PATH));

        snippetsUpdate = new LinkImpl("/snippets/{pk}/", LinkImpl.HTTP_METHOD_PUT);
        snippets.put("update", snippetsUpdate);
        snippetsUpdate.setEncoding("application/json");
        snippetsUpdate.addField(new LinkImpl.FieldImpl("pk", true, Link.Field.FIELD_LOCATION_PATH));
        snippetsUpdate.addField(new LinkImpl.FieldImpl("title", Link.Field.FIELD_LOCATION_FORM));
        snippetsUpdate.addField(new LinkImpl.FieldImpl("code", true, Link.Field.FIELD_LOCATION_FORM));
        snippetsUpdate.addField(new LinkImpl.FieldImpl("linenos", Link.Field.FIELD_LOCATION_FORM));
        snippetsUpdate.addField(new LinkImpl.FieldImpl("language", Link.Field.FIELD_LOCATION_FORM));
        snippetsUpdate.addField(new LinkImpl.FieldImpl("style", Link.Field.FIELD_LOCATION_FORM));

        Map<String, Object> users = new HashMap<>();
        document.putContent("users", users);

        usersList = new LinkImpl("/users/", LinkImpl.HTTP_METHOD_GET);
        users.put("list", usersList);
        usersList.addField(new LinkImpl.FieldImpl("page", Link.Field.FIELD_LOCATION_QUERY));

        usersRetrieve = new LinkImpl("/users/{pk}/", LinkImpl.HTTP_METHOD_GET);
        users.put("retrieve", usersRetrieve);
        usersRetrieve.addField(new LinkImpl.FieldImpl("pk", true, Link.Field.FIELD_LOCATION_PATH));
    }
}
