
package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;

public class MainClass {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Seleccione el tipo de MAP que desea utilizar:");
            System.out.println("1) HashMap");
            System.out.println("2) TreeMap");
            System.out.println("3) LinkedHashMap");
            System.out.print("Ingrese su elección: ");
            String eleccion = reader.readLine();

            MapFactory factory = null;
            switch (eleccion) {
                case "1":
                    factory = new HashMapFactory();
                    break;
                case "2":
                    factory = new TreeMapFactory();
                    break;
                case "3":
                    factory = new LinkedHashMapFactory();
                    break;
                default:
                    System.out.println("Elección no válida. Usando HashMap por defecto.");
                    factory = new HashMapFactory();
                    break;
            }

            MapManager mapManager = new MapManager(factory.createMap());
            String archivoCartas = "cartas.txt"; // Asume la existencia de este archivo
            long inicioCarga = System.nanoTime();
            mapManager.loadCardsFromFile(archivoCartas);
            long finCarga = System.nanoTime();

            System.out.println("Tiempo total de carga de cartas: " + (finCarga - inicioCarga) + " nanosegundos.");

            boolean continuar = true;
            while (continuar) {
                System.out.println("\n¿Qué operación desea realizar?");
                System.out.println("1) Agregar una carta a la colección");
                System.out.println("2) Mostrar el tipo de una carta específica");
                System.out.println("3) Mostrar el nombre, tipo y cantidad de cada carta en la colección");
                System.out.println("4) Mostrar el nombre, tipo y cantidad de cada carta en la colección, ordenadas por tipo");
                System.out.println("5) Mostrar el nombre y tipo de todas las cartas existentes");
                System.out.println("6) Mostrar el nombre y tipo de todas las cartas existentes, ordenadas por tipo");
                System.out.println("7) Salir");
                System.out.print("Ingrese el número de la operación que desea realizar: ");
                String operacion = reader.readLine();

                long inicioOperacion, finOperacion;

                switch (operacion) {
                    case "1":
                        System.out.print("Ingrese el nombre de la carta a agregar: ");
                        String nombreCarta = reader.readLine();
                        inicioOperacion = System.nanoTime();
                        mapManager.addCardToCollection(nombreCarta);
                        finOperacion = System.nanoTime();
                        break;
                    case "2":
                        System.out.print("Ingrese el nombre de la carta para mostrar su tipo: ");
                        nombreCarta = reader.readLine();
                        inicioOperacion = System.nanoTime();
                        System.out.println("Tipo: " + mapManager.getCardType(nombreCarta));
                        finOperacion = System.nanoTime();
                        break;
                    case "3":
                        inicioOperacion = System.nanoTime();
                        mapManager.showCollection();
                        finOperacion = System.nanoTime();
                        break;
                    case "4":
                        inicioOperacion = System.nanoTime();
                        mapManager.showCollectionSortedByType();
                        finOperacion = System.nanoTime();
                        break;
                    case "5":
                        inicioOperacion = System.nanoTime();
                        mapManager.showAllCards();
                        finOperacion = System.nanoTime();
                        break;
                    case "6":
                        inicioOperacion = System.nanoTime();
                        mapManager.showAllCardsSortedByType();
                        finOperacion = System.nanoTime();
                        break;
                    case "7":
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        continue;
                }

                if (!operacion.equals("7")) {
                    System.out.println("Tiempo de ejecución de la operación: " + (finOperacion - inicioOperacion) + " nanosegundos.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer la entrada: " + e.getMessage());
        }
    }
}