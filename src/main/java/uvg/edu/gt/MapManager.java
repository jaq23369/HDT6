package uvg.edu.gt;

import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class MapManager {

    private Map<String, String> cardMap; // Almacena la relación entre la carta y su tipo
    private MapFactory mapFactory; // Factory para crear la instancia de Map deseada

    public MapManager(String mapType) {
        // Selecciona el tipo de Map basado en el input del usuario
        this.mapFactory = MapFactorySelector.getFactory(mapType);
        this.cardMap = mapFactory.createMap();
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
}

