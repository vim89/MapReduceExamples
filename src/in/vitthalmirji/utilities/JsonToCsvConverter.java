package in.vitthalmirji.utilities;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;

/**
 * @author VitthalMirji
 * Visit: http://www.vitthalmirji.in
 * Email: vitthalmirji@gmail.com
 */

public class JsonToCsvConverter {

    public static void main(String[] args) throws Exception {
        System.out.println(JsonToCsvConverter.convert());
    }

    public static String convert() throws Exception {
        JsonFactory f = new JsonFactory();
        JsonParser jp = f.createJsonParser(new File("src/main/java/utils/milano_temp.json"));
        StringBuilder builder = new StringBuilder();
        while (jp.nextToken() != JsonToken.END_ARRAY) {
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                jp.nextToken();
                while (jp.nextToken() != JsonToken.END_ARRAY) {

                    builder.append(getRow(jp));
                }
            }
        }
        jp.close();
        return builder.toString();
    }

    private static String getRow(JsonParser jp) throws Exception {

        StringBuilder builder = new StringBuilder();

        jp.nextToken();
        jp.nextValue();
        builder.append(jp.getValueAsString()).append(",");
        jp.nextToken();
        jp.nextToken();
        jp.nextValue();
        builder.append(jp.getValueAsDouble()).append(",");
        jp.nextToken();
        jp.nextToken();
        jp.nextValue();
        builder.append(jp.getValueAsDouble()).append("\n");
        jp.nextToken();

        return builder.toString();
    }
}
