package com.ljf.web_scaffolding.xss;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;

/**
 * Created by mr.lin on 2020/7/23
 * 出参处理
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {

    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null)
        {
            String encodedValue = StringEscapeUtils.escapeHtml4(value);
            gen.writeString(encodedValue);
        }
    }
}
