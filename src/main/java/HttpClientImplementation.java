import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.lang.System;

public class HttpClientImplementation {
  public static void main(String[] args) throws IOException {

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost uploadURL = new HttpPost("http://localhost:8080/raw");
    uploadURL.setHeader("Content-Disposition","form-data; name=document; filename=boute.pdf");
    uploadURL.setHeader("Content-Type","multipart/form-data; boundary=---something");
    uploadURL.setHeader("Authorization", "");
    MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

    File pdfFile = new File("C:\\Users\\Muhammad\\Desktop\\projects\\docs\\apiCodes\\alefba\\boute.pdf");
    byte[] out = new byte[(int)pdfFile.length()];

    FileInputStream fileInputStream = new FileInputStream(pdfFile);
    fileInputStream.close();
    multipartEntityBuilder.addBinaryBody("document", out);
    multipartEntityBuilder.addTextBody("fake", "thing");
    HttpEntity multipart = multipartEntityBuilder.build();
    uploadURL.setEntity(multipart);
//
    CloseableHttpResponse response = httpClient.execute(uploadURL);
    HttpEntity responseEntity = response.getEntity();
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost("http://localhost:8080/raw");
//        httpPost.setHeader("Content-Type","multipart/form-data; boundary=---something");
//        httpPost.setHeader("Authorization", "Token 7c990ee0c9aa0ddd7307d9bdaa76ac8d682be233");
//        File pdfFile = new File("C:\\Users\\Muhammad\\Desktop\\projects\\docs\\apiCodes\\alefba\\boute.pdf");
//        FileBody uploadFilePart = new FileBody(pdfFile);
//        MultipartEntity reqEntity = new MultipartEntity();
//        reqEntity.addPart("document", uploadFilePart);
//        httpPost.setEntity(reqEntity);
//
//        HttpResponse responseEntity = httpclient.execute(httpPost);
    BufferedReader inputStream = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
    String inputLine;
    while ((inputLine = inputStream.readLine()) != null){
      System.out.println(inputLine);
    }
    inputStream.close();
  }
}


