package main.broker.domain.message;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class Message {

    private Integer id;
//    private final Map<String, String> headers;
    private final String topic;
    private final String body;

    public Message(final String message, final Integer id) {
        this.id = id;
        this.topic = parseTopic(message);
        this.body = parseBody(message);
    }

    public Message(final String message) {
        this.topic = parseTopic(message);
        this.body = parseBody(message);
    }

//    public Message(final String message) {
//        this.headers = parseHeaders(message);
//        this.topic = headers.getOrDefault("topic", "default");
//        this.body = parseBody(message);
//    }
//
//    public Message(final String message, final Integer id) {
//        this.id = id;
//        this.headers = parseHeaders(message);
//        this.topic = headers.getOrDefault("topic", "default");
//        this.body = parseBody(message);
//    }

    public Integer getId() {
        return id;
    }

//    public Map<String, String> getHeaders() {
//        return headers;
//    }

    public String getTopic() {
        return topic;
    }

    public String getBody() {
        return body;
    }

    private String parseTopic(final String message) {
        return message.split(";")[0];
    }

    private String parseBody(final String message) {
        return message.split(";")[1];
    }

    @Override
    public String toString() {
        if (id != null) {
            return topic + ";" + body;
        }

        return id + ";" + topic + ";" + body;
    }


//    public String getHeadersAsString() {
//        return "headers=" + headers.toString();
//    }
//
//    private Map<String, String> parseHeaders(final String message) {
//        final Map<String, String> mapHeaders = new LinkedHashMap<>();
//
//        if (!message.contains("datetime=")) {
//            mapHeaders.put("datetime", LocalDateTime.now().toString());
//        }
//
//        final String headersStr = message.split(";")[0];
//        final String[] headersArr = headersStr.split(",");
//
//        if (headersArr.length == 1 || headersArr[0].isEmpty()) return mapHeaders;
//
//        for (String param : headersArr) {
//            final String key = param.split("=")[0];
//            final String value = param.split("=")[1];
//
//            mapHeaders.put(key, value);
//        }
//
//        return mapHeaders;
//    }
//
//    private String parseBody(String message) {
//        return message.substring(message.indexOf(';') + 1);
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder();
//
//        if (id != null) {
//            sb.append(id);
//        }
//
//        for (String key : headers.keySet()) {
//            sb.append(key).append("=").append(headers.get(key)).append(",");
//        }
//
//        int a = sb.lastIndexOf(",");
//        sb.replace(a, a + 1, ";");
//
//        sb.append(body);
//
//        return sb.toString();
//    }
}
