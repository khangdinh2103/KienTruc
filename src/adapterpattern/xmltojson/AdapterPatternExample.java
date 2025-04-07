package adapterpattern.xmltojson;

import org.json.JSONObject;
import org.json.XML;

interface JsonProcessor {
    String processJson(String jsonData);

}

class XmlProcessor {
    public String processXml(String xmlData) {
        return "Processed XML Data: " + xmlData;

    }
}

class DataAdapter implements JsonProcessor {
    private XmlProcessor xmlProcessor;



    public DataAdapter(XmlProcessor xmlProcessor) {
        this.xmlProcessor = xmlProcessor;
    }

    @Override
    public String processJson(String jsonData) {
        String xmlData = jsonToXml(jsonData);
        return xmlProcessor.processXml(xmlData);
    }

    public String jsonToXml(String jsonData) {
        JSONObject json = new JSONObject(jsonData);
        return XML.toString(json);
    }

    public String xmlToJson(String xmlData) {
        JSONObject json = XML.toJSONObject(xmlData);
        return json.toString(4);
    }
}

public class AdapterPatternExample {
    public static void main(String[] args) {
        XmlProcessor xmlProcessor = new XmlProcessor();
        DataAdapter adapter = new DataAdapter(xmlProcessor);

        // Test chuyển JSON → XML
        System.out.println("----- JSON to XML Test -----");
        String jsonData = "{\"name\": \"John\", \"age\": 30}";
        String convertedXml = adapter.jsonToXml(jsonData);
        System.out.println("Converted XML:\n" + convertedXml);

        // Test xử lý XML trong hệ thống cũ
        System.out.println("Processing XML: " + xmlProcessor.processXml(convertedXml));

        // Test chuyển XML → JSON
        System.out.println("\n----- XML to JSON Test -----");
        String xmlData = "<name>John</name><age>30</age>";
        String convertedJson = adapter.xmlToJson(xmlData);
        System.out.println("Converted JSON:\n" + convertedJson);
    }
}
