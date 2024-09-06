package net.vrakin;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class NetworkService {
    public static String getStringFromUrl(String spec) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(spec);
        URLConnection con = url.openConnection();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            for(;;){
                String line = in.readLine();
                if(line == null) break;
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static long getFileFromUrl(File folder, String spec) throws  IOException{
        URL url = new URL(spec);
        URLConnection con = url.openConnection();
        int n = spec.lastIndexOf("/");
        String filename = spec.substring(n+1);
        try(InputStream is = con.getInputStream();OutputStream os = Files.newOutputStream(new File(folder, filename).toPath())){
            return is.transferTo(os);
        }
    }

    public static Map<String, List<String>> getHeaders(String spec) throws IOException {
        URL url = new URL(spec);
        URLConnection con = url.openConnection();
        return con.getHeaderFields();
    }
}
