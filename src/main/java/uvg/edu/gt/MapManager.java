package uvg.edu.gt;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Clase que gestiona un mapa de cartas y una colección de cartas.
 */
public class MapManager {

    protected Map<String, String> cardMap; // Almacena la relación entre la carta y su tipo
    private Map<String, Integer> cardCollection = new HashMap<>(); // Colección del usuario

    /**
     * Constructor que inicializa el Mapa de Cartas.
     *
     * @param map Mapa de cartas
     */
    public MapManager(Map<String, String> map) {
        this.cardMap = map;
    }

    /**
     * Carga las cartas desde un archivo y las añade al mapa de cartas.
     *
     * @param filePath Ruta del archivo de cartas
     */
    public void loadCardsFromFile(String filePath) {
        // Carga las cartas desde un archivo
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|"); // Cambiado a usar "|" como delimitador
                if (parts.length == 2) {
                    cardMap.put(parts[0].trim(), parts[1].trim());
                } else {
                    // Puedes manejar el error o imprimir para depuración aquí
                    System.out.println("Línea con formato inesperado: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Añade una nueva carta al mapa de cartas.
     *
     * @param cardName Nombre de la carta
     * @param cardType Tipo de la carta
     */
    public void addCard(String cardName, String cardType) {
        // Añade una nueva carta al mapa
        cardMap.put(cardName, cardType);
    }

    /**
     * Obtiene el tipo de una carta específica.
     *
     * @param cardName Nombre de la carta
     * @return Tipo de la carta o mensaje de error si la carta no existe
     */
    public String getCardType(String cardName) {
        // Devuelve el tipo de una carta específica
        return cardMap.getOrDefault(cardName, "La carta no existe.");
    }

    /*
     * Muestra todas las cartas y sus tipos.
     */
    public void showAllCards() {
        // Muestra todas las cartas y sus tipos
        cardMap.forEach((name, type) -> System.out.println(name + ": " + type));
    }

    /**
     * Muestra todas las cartas de un tipo específico.
     *
     * @param type Tipo de carta
     */
    public void showCardsOfType(String type) {
        // Muestra todas las cartas de un tipo específico
        String cards = cardMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(type))
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
        System.out.println("Cartas de tipo " + type + ": " + cards);
    }

    /*
     * Añade una carta a la colección del usuario.
     *
     * @param cardName Nombre de la carta
     */
    public void addCardToCollection(String cardName) {
        if (cardMap.containsKey(cardName)) {
            int currentCount = cardCollection.getOrDefault(cardName, 0);
            cardCollection.put(cardName, currentCount + 1);
        } else {
            System.out.println("La carta no se encuentra entre las cartas disponibles.");
        }
    }

    /*
     * Muestra la colección de cartas del usuario.
     */
    public void showCollection() {
        if (cardMap.isEmpty()) {
            System.out.println("No hay cartas para mostrar.");
            return;
        }
        for (Map.Entry<String, String> entry : cardMap.entrySet()) {
            System.out.println("Carta: " + entry.getKey() + " - Tipo: " + entry.getValue());
        }
    }

    /*
     * Muestra el nombre, tipo y cantidad de cada carta en la colección del usuario, ordenadas por tipo.
     */
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

    /**
     * Muestra todas las cartas ordenadas por tipo.
     */
    public void showAllCardsSortedByType() {
        if (cardMap.isEmpty()) {
            System.out.println("No hay cartas para mostrar.");
            return;
        }
        
        cardMap.entrySet().stream()
               .sorted(Map.Entry.comparingByValue())
               .forEach(entry -> {
                   System.out.println(entry.getKey() + ": " + entry.getValue());
               });
    }
}

