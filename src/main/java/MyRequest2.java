import java.io.*;
import java.lang.System;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

public class MyRequest2 {

  public static void main(String[] args) throws IOException {

    URL alefbaServerUrl = new URL("https://alefba.roshan-ai.ir/api/read_document/");
    HttpURLConnection urlConnection = (HttpURLConnection) alefbaServerUrl.openConnection();

    String filePath = "C:\\Users\\Muhammad\\Desktop\\projects\\docs\\apiCodes\\alefba\\boute.pdf";
    File fileToUpload = new File(filePath);
    byte[] out = new byte[(int)fileToUpload.length()];
    urlConnection.setFixedLengthStreamingMode(out.length);
    urlConnection.setRequestMethod("POST");
    urlConnection.setDoOutput(true);
    urlConnection.setRequestProperty("Content-Type", "multipart/form-data");
    urlConnection.setRequestProperty("Authorization", "Token 7c990ee0c9aa0ddd7307d9bdaa76ac8d682be233");

    OutputStream outputStreamToRequestBody = urlConnection.getOutputStream();
    BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));
    FileInputStream inputStreamToLogFile = new FileInputStream(fileToUpload);

    int bytesRead;

    while((bytesRead = inputStreamToLogFile.read(out)) != -1) {
      outputStreamToRequestBody.write(out, 0, bytesRead);
    }

    BufferedReader httpResponseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    String lineRead;
    while((lineRead = httpResponseReader.readLine()) != null) {
      System.out.println(lineRead);
    }

    outputStreamToRequestBody.flush();
    httpRequestBodyWriter.flush();
    outputStreamToRequestBody.close();
    httpRequestBodyWriter.close();
  }
}