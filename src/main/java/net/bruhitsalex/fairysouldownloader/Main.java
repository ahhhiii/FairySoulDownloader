package net.bruhitsalex.fairysouldownloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://hypixel-skyblock.fandom.com/wiki/Fairy_Souls").get();
        System.out.println(doc.body().toString());
    }

}
