package net.vrakin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        String spec = "https://dou.ua/";

        String html = NetworkService.getStringFromUrl(spec);

        List<String> sites = new ArrayList<String>();
        for (int i = 0; i < html.length();i++) {
            int indexNextA = html.indexOf("<a", i);
            int indexNextHref = html.indexOf("href=\"", indexNextA)+"href=\"".length();
            int indexEndHref = html.indexOf("\"", indexNextHref);
            System.out.println(html.substring(indexNextHref, indexEndHref));
            i+=indexEndHref;
            sites.add("https://dou.ua/");
        }

        System.out.println("List addresses");

        sites.add("https://dou1.ua/");
        sites.add("https://dou2.ua/");
        sites.add("https://dou3.ua/");

        try{
            for (String site : sites) {
                Map<String, List<String>> headers = new HashMap<>();
                System.out.printf("The site %s is ", site);
                headers = NetworkService.getHeaders(site);
                if (headers.containsKey("Content-Type")) {
                    System.out.println("available");
                    System.out.println(headers);
                } else {
                    System.out.println("unavailable");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
