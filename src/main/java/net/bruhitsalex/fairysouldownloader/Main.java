package net.bruhitsalex.fairysouldownloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://hypixel-skyblock.fandom.com/wiki/Fairy_Souls").get();

        List<Element> elements = doc.getAllElements()
                .stream()
                .filter(element -> element.id().startsWith("mw-customcollapsible-"))
                .collect(Collectors.toList());

        for (Element element : elements) {
            System.out.println(element.id());
        }
    }

}
