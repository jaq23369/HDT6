package uvg.edu.gt;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class MapManager {

    private Map<String, String> cardMap; // Almacena la relación entre la carta y su tipo
    private Map<String, Integer> cardCollection = new HashMap<>(); // Colección del usuario

    public MapManager(Map<String, String> map) {
        this.cardMap = map;
    }
      
    public void loadCardsFromFile(String filePath) {
        // Carga las cartas desde un archivo
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    cardMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void addCard(String cardName, String cardType) {
        // Añade una nueva carta al mapa
        cardMap.put(cardName, cardType);
    }

    public String getCardType(String cardName) {
        // Devuelve el tipo de una carta específica
        return cardMap.getOrDefault(cardName, "La carta no existe.");
    }

    public void showAllCards() {
        // Muestra todas las cartas y sus tipos
        cardMap.forEach((name, type) -> System.out.println(name + ": " + type));
    }

    public void showCardsOfType(String type) {
        // Muestra todas las cartas de un tipo específico
        String cards = cardMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(type))
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
        System.out.println("Cartas de tipo " + type + ": " + cards);
    }

    public void addCardToCollection(String cardName) {
        if (cardMap.containsKey(cardName)) {
            int currentCount = cardCollection.getOrDefault(cardName, 0);
            cardCollection.put(cardName, currentCount + 1);
        } else {
            System.out.println("La carta no se encuentra entre las cartas disponibles.");
        }
    }

    // Mostrar el nombre, tipo y cantidad de cada carta en la colección del usuario
    public void showCollection() {
        cardCollection.forEach((cardName, count) -> {
            String type = cardMap.get(cardName);
            System.out.println(cardName + " - " + type + " - " + count);
        });
    }

    // Mostrar el nombre, tipo y cantidad de cada carta en la colección del usuario, ordenadas por tipo
    public void showCollectionSortedByType() {
        cardCollection.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByKey())
                .forEach(entry -> {
                    String cardName = entry.getKey();
                    String type = cardMap.get(cardName);
                    Integer count = entry.getValue();
                    System.out.println(cardName + " - " + type + " - " + count);
                });
    }

    public void showAllCardsSortedByType() {
        cardMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry ->
                        System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}

