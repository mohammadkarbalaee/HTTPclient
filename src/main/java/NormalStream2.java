//import java.io.*;
//import java.lang.System;
//import java.net.HttpURLConnection;
//import java.net.ProtocolException;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class MyRequest2 {
//
//  public static void main(String[] args) throws IOException {
//
//    URL alefbaServerUrl = new URL("https://alefba.roshan-ai.ir/api/read_document/");
//    HttpURLConnection urlConnection = (HttpURLConnection) alefbaServerUrl.openConnection();
//
//    String filePath = "C:\\Users\\Muhammad\\Desktop\\projects\\docs\\apiCodes\\alefba\\boute.pdf";
//    File fileToUpload = new File(filePath);
//    byte[] out = new byte[(int)fileToUpload.length()];
//    urlConnection.setFixedLengthStreamingMode(out.length);
//    urlConnection.setRequestMethod("POST");
//    urlConnection.setDoOutput(true);
//    urlConnection.setRequestProperty("Content-Type", "multipart/form-data");
//    urlConnection.setRequestProperty("Authorization", "Token 7c990ee0c9aa0ddd7307d9bdaa76ac8d682be233");
//
//    OutputStream outputStreamToRequestBody = urlConnection.getOutputStream();
//    BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));
//    FileInputStream inputStreamToLogFile = new FileInputStream(fileToUpload);
//
//    int bytesRead;
//
//    while((bytesRead = inputStreamToLogFile.read(out)) != -1) {
//      outputStreamToRequestBody.write(out, 0, bytesRead);
//    }
//
//    BufferedReader httpResponseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//    String lineRead;
//    while((lineRead = httpResponseReader.readLine()) != null) {
//      System.out.println(lineRead);
//    }
//
//    outputStreamToRequestBody.flush();
//    httpRequestBodyWriter.flush();
//    outputStreamToRequestBody.close();
//    httpRequestBodyWriter.close();
//  }
//}

import java.lang.System;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

class NormalStream2 {

  public static void main(String[] args){
    try{
      URL url = new URL("http://localhost:8080/raw");
      URLConnection con = url.openConnection();
      HttpURLConnection http = (HttpURLConnection)con;
      http.setRequestMethod("POST");
      http.setDoOutput(true);

      File file = new File("C:\\Users\\Muhammad\\Desktop\\projects\\docs\\apiCodes\\alefba\\boute.pdf");
      byte[] out = new byte[(int)file.length()];

      FileInputStream fileInputStream = new FileInputStream(file.getPath());
      fileInputStream.read(out);
      fileInputStream.close();

      int length = out.length;

      http.setFixedLengthStreamingMode(length);
      http.setRequestProperty("Content-Type", "multipart/form-data; boundary=---something");
      http.setRequestProperty("Authorization", "Token TOKEN_KEY");
      http.connect();
      try(OutputStream os = http.getOutputStream()) {
        os.write(out);
      }

      BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null)
        System.out.println(inputLine);
      in.close();
    }

    catch(Exception e){
      System.err.println(e);
    }
  }
}