package JSON;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Nika on 16.08.2017.
 */
public class Vuvod {

    public static void main(String[] args) throws IOException {
        try {
            URL url = new URL("https://en.wikipedia.org/wiki/Kiev");
            ArrayList<String> list = new ArrayList<String>();

            try {
               LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
               String string = reader.readLine();
               String text ="";

               StringBuilder sb =new StringBuilder();

                while (string != null) {

                    text=html2text(string);
                    System.out.println(text);
                   if (!(text.equals("")))
                   {
                      list.add(text);
                    }
                    string = reader.readLine();
                }

                for( String vuvod :list)
                {
                   System.out.println(vuvod);
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}

