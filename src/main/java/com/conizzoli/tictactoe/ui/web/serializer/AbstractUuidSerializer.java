package com.conizzoli.tictactoe.ui.web.serializer;

import com.conizzoli.shared.domain.AbstractUuid;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class AbstractUuidSerializer extends JsonSerializer<AbstractUuid> implements CustomJsonSerializerInterface {
  @Override
  public void serialize(AbstractUuid value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString(value.toString());
  }

  @Override
  public Class<AbstractUuid> handledType() {
    return AbstractUuid.class;
  }
}
