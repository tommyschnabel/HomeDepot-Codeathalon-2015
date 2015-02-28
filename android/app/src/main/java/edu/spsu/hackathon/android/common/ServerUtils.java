package edu.spsu.hackathon.android.common;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ServerUtils {

    private static String CLASSNAME = "edu.spsu.hackathon.android.common.ServerUtils";

    private static String domain = "http://hdrestfulservice.azurewebsites.net";
    private static ObjectMapper objectMapper;

    public static String getDomain() {
        return domain;
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static String makeRequestAndReadResponse(HttpRequestBase request) throws IOException {

        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(request);
        InputStream in = new BufferedInputStream(response.getEntity().getContent());

        return readInputStream(in);
    }

    public static void makeRequestAndVerifyNoResponse(HttpRequestBase request) throws IOException {

        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(request);

        if (response.getEntity() != null) {
            Log.e(CLASSNAME, "There was a response when none was expected");
            throw new IOException(readInputStream(response.getEntity().getContent()));
        }

        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() != 204) {
            throw new IOException("Expected response status 204 No Content, but got " + statusLine.toString());
        }
    }

    public static String readInputStream(InputStream in) throws IOException {
        byte[] bytes = new byte[1024];
        List<byte[]> responseBytes = new ArrayList<byte[]>();

        int length = 1;
        while(length > 0) {
            length = in.read(bytes, 0, 1024);
            responseBytes.add(bytes);
            bytes = new byte[1024];
        }

        String response = "";
        for (byte[] b : responseBytes) {
            response += new String(b, Charset.forName("UTF-8"));
        }

        response = response.trim();

        Log.i(CLASSNAME, "Response: " + response);

        return response;
    }

}
