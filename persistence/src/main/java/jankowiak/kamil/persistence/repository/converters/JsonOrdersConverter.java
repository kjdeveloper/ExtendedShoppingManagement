package jankowiak.kamil.persistence.repository.converters;

import jankowiak.kamil.persistence.model.Order;

import java.util.List;

public class JsonOrdersConverter extends JsonConverter<List<Order>> {
    public JsonOrdersConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
