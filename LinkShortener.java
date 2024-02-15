import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 7;
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    private final String FILE_PATH = "link_mappings.txt";

    public LinkShortener() {
        shortToLongMap = new HashMap<>();
        longToShortMap = new HashMap<>();
        loadMappingsFromFile();
    }

    public String shortenUrl(String longUrl) {
        if (longToShortMap.containsKey(longUrl)) {
            return longToShortMap.get(longUrl);
        }

        String shortUrl = generateShortUrl();
        while (shortToLongMap.containsKey(shortUrl)) {
            shortUrl = generateShortUrl();
        }

        shortToLongMap.put(shortUrl, longUrl);
        longToShortMap.put(longUrl, shortUrl);

        saveMappingsToFile();

        return shortUrl;
    }

    public String expandUrl(String shortUrl) {
        return shortToLongMap.getOrDefault(shortUrl, "Short URL not found");
    }

    private String generateShortUrl() {
        Random random = new Random();
        StringBuilder shortUrlBuilder = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            shortUrlBuilder.append(CHARACTERS.charAt(index));
        }
        return shortUrlBuilder.toString();
    }

    private void loadMappingsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    shortToLongMap.put(parts[0], parts[1]);
                    longToShortMap.put(parts[1], parts[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private void saveMappingsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, String> entry : shortToLongMap.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Welcome to Link Shortener!");
            while (true) {
                System.out.println("Select an option:");
                System.out.println("1. Shorten a URL");
                System.out.println("2. Expand a URL");
                System.out.println("3. Exit");

                int choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter long URL: ");
                        String longUrl = reader.readLine();
                        String shortUrl = linkShortener.shortenUrl(longUrl);
                        System.out.println("Shortened URL: " + shortUrl);
                        break;
                    case 2:
                        System.out.print("Enter short URL: ");
                        String inputShortUrl = reader.readLine();
                        String expandedUrl = linkShortener.expandUrl(inputShortUrl);
                        System.out.println("Expanded URL: " + expandedUrl);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
