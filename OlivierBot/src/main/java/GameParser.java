import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameParser {

    public static final String REGEX = "[%0-9a-fA-F]+";
    public List<String> gamesLinks = new ArrayList<>();

    public void parser() throws IOException {

        String url = "https://summercamp.ru/Категория:Игры";

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

        for (Element element : links) {
            String linkAddress = element.attr("href");

            if (linkAddress.substring(1).matches(REGEX)) {

                gamesLinks.add(linkAddress);
            }
        }
    }

    public Game getGame() throws IOException {

        Random random = new Random();
        int randomIndex = random.nextInt(gamesLinks.size());

        String link = gamesLinks.get(randomIndex);
        link = URLDecoder.decode(link);
        String gameLink = "https://summercamp.ru" + link;

        Document doc = Jsoup.connect(gameLink).get();

        Element headCode = doc.getElementById("firstHeading");
        String head = headCode.text();

        Element textCode = doc.getElementById("mw-content-text");
        String text = textCode.text();

        if (text.length() > 4096) {
            text = text.substring(0,4000) + "..." + System.lineSeparator() + "Продолжение по ссылке: " + gameLink;
        }

        String category = null;

        Game game = new Game(head, text, category);

        return game;
    }
}
