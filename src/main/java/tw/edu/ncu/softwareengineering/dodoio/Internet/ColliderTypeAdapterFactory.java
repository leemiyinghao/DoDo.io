package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import tw.edu.ncu.softwareengineering.dodoio.Collide.Collider;

public class ColliderTypeAdapterFactory implements TypeAdapterFactory
{
	private static final String CLASS_META_KEY="clz";
    Gson gson;
    TypeToken<?> type;
    TypeAdapter<Collider> fieldAdapter;
    TypeAdapter<JsonElement> elementAdapter;
    TypeAdapterFactory taf;

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!Collider.class.isAssignableFrom(type.getRawType()))
            return null; // this class only serializes 'Field' and its subtypes

        this.type=type;
        this.gson=gson;
        this.taf=this;
        fieldAdapter = gson.getDelegateAdapter(taf, TypeToken.get(Collider.class));
        elementAdapter = gson.getAdapter(JsonElement.class);
        TypeAdapter<T> result = new FieldTypeAdapter<T>();
        result.nullSafe();
        return result;
    }

    class FieldTypeAdapter<T> extends TypeAdapter<T> {

        public FieldTypeAdapter() {
        }

        @Override
        public void write(JsonWriter out, Object value) throws IOException {
            if(value instanceof Collider) {
                JsonObject object = fieldAdapter.toJsonTree((Collider )value).getAsJsonObject();
                object.addProperty(CLASS_META_KEY, value.getClass().getCanonicalName());
                elementAdapter.write(out, object);
            }
            else {
                elementAdapter.write(out, (JsonElement) value);
            }
        }

        @Override
        public T read(JsonReader in) throws IOException {
            JsonObject object = elementAdapter.read(in).getAsJsonObject();
            if (object.has(CLASS_META_KEY)) {
                String className=object.get(CLASS_META_KEY).getAsString();
                try {
                    Class<?> clz = Class.forName(className);
                    TypeAdapter<?> adapter = gson.getDelegateAdapter(taf, TypeToken.get(clz));
                    return (T) adapter.fromJsonTree(object);
                }
                catch (Exception e) {
                    return (T )fieldAdapter.fromJsonTree(object);
                }
            }
            else
                return (T )elementAdapter.fromJsonTree(object);
        }

    }

}

