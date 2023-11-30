package com.conizzoli.tictactoe;

import com.conizzoli.tictactoe.ui.web.serializer.CustomJsonSerializerInterface;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.ConfigurableApplicationContext;

public class CustomBeansFactory {
  @Autowired
  ConfigurableApplicationContext context;

  public BufferedReader createBufferedReader() {
    return new BufferedReader(new InputStreamReader(System.in));
  }

  public ResourceBundle createResourceBundle() {
    return ResourceBundle.getBundle("TicTacToeBundle");
  }

  public Jackson2ObjectMapperBuilderCustomizer addCustomJsonSerializers() {
    var customSerializers = this.context.getBeanNamesForType(CustomJsonSerializerInterface.class);
    return builder -> {
      for (var bean : customSerializers) {
        builder.serializers((JsonSerializer<?>) this.context.getBean(bean));
      }
    };
  }
}
