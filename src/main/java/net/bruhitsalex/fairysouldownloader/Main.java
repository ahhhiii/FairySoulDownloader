package net.bruhitsalex.fairysouldownloader;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.bruhitsalex.fairysouldownloader.obj.Entry;
import net.bruhitsalex.fairysouldownloader.obj.Location;
import net.bruhitsalex.fairysouldownloader.obj.Position;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final LinkedHashMap<Location, List<Entry>> locToEntries = new LinkedHashMap<>();
    private static List<Element> elements;

    public static void main(String[] args) throws IOException {
        downloadData();
        parseData();
        reportData();
        writeFile();
    }

    private static void downloadData() throws IOException {
        Document doc = Jsoup.connect("https://hypixel-skyblock.fandom.com/wiki/Fairy_Souls").get();

        elements = doc.getAllElements()
                .stream()
                .filter(element -> element.id().startsWith("mw-customcollapsible-"))
                .collect(Collectors.toList());
    }

    private static void parseData() {
        for (Element element : elements) {
            String elementId = element.id().substring(element.id().lastIndexOf("-") + 1);
            
            Location location = null;
            for (Location loc : Location.values()) {
                if (loc.getElementId().equals(elementId)) {
                    location = loc;
                    break;
                }
            }

            if (location == null) {
                System.out.println("Location " + elementId + " is not a valid location type, maybe its new or doesn't confirm to a proper Position.");
                continue;
            }

            locToEntries.put(location, new LinkedList<>());

            Element tableBody = element.getElementsByClass("article-table").get(0).children()
                    .get(1);

            parseTable(tableBody, location);
        }
    }

    private static void parseTable(Element tableBody, Location location) {
        tableBody.children().forEach(element -> {
            parseTR(element, location);
        });
    }

    private static void parseTR(Element tr, Location location) {
        try {
            int x = Integer.parseInt(tr.children().get(1).text());
            int y = Integer.parseInt(tr.children().get(2).text());
            int z = Integer.parseInt(tr.children().get(3).text());
            String area = tr.children().get(4).text().replace("?", " ");
            String extraInfo = tr.children().get(5).text().replace("?", " ");

            Entry entry = new Entry(new Position(x, y, z), location, area, extraInfo);
            locToEntries.get(location).add(entry);
        } catch (Exception ignored) {}
    }

    private static void reportData() {
        locToEntries.forEach((location, entries) -> {
            System.out.println("Found " + entries.size() + " for location: " + location);
        });
    }

    private static void writeFile() throws IOException {
        File file = new File("fairy-souls.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        Files.write(file.toPath(), new GsonBuilder().setPrettyPrinting().create().toJson(locToEntries).getBytes());
    }

}
