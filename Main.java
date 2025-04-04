import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    //David García Antón
    public static void main(String[] args) {
        boolean menu = true;

        Vendedor vendedor1 = new Vendedor("1111", false, 1030, "38,5");
        Vendedor vendedor2 = new Vendedor("2222", true, 1200, "38,5");
        Vendedor vendedor3 = new Vendedor("3333", true, 1010, "38,5");
        Vendedor vendedor4 = new Vendedor("4444", false, 1350, "38,5");
        Vendedor vendedor5 = new Vendedor("5555", true, 1010, "38,5");
        Vendedor.addNewVendedor(vendedor1);
        Vendedor.addNewVendedor(vendedor2);
        Vendedor.addNewVendedor(vendedor3);
        Vendedor.addNewVendedor(vendedor4);
        Vendedor.addNewVendedor(vendedor5);


        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.println("0. Salir");
                System.out.println("1. Insertar");
                System.out.println("2. Eliminar");
                System.out.println("3. Actualizar");
                System.out.println("4. Consultar");
                System.out.println("5. Imprimir");
                System.out.println("6. Generar fichero txt en formato xml");
                System.out.print("Elige una opción: ");


                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 0:
                        System.out.println("Saliendo del programa");
                        menu = false;
                        break;
                    case 1:
                        System.out.print("ID: ");
                        String id = sc.nextLine();

                        System.out.print("¿Es jefe? (true/false): ");
                        String entrada = sc.nextLine().toLowerCase();
                        boolean esJefe;

                        if (entrada.equals("true")) {
                            esJefe = true;
                        } else if (entrada.equals("false")) {
                            esJefe = false;
                        } else {
                            System.out.println("Entrada inválida. Debes escribir 'true' o 'false'.");
                            continue;
                        }

                        System.out.print("Salario: ");
                        int salario = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Jornada Laboral: ");
                        String jornada = sc.nextLine();

                        if (Vendedor.addNewVendedor(new Vendedor(id, esJefe, salario, jornada))) {
                            System.out.println("Vendedor agregado.");
                        } else {
                            System.out.println("ID ya existente.");
                        }
                        break;
                    case 2:
                        System.out.print("ID a eliminar: ");
                        id = sc.nextLine();
                        if (Vendedor.removeVendedor(id)) {
                            System.out.println("Vendedor eliminado.");
                        } else {
                            System.out.println("No encontrado.");
                        }
                        break;
                    case 3:
                        System.out.print("ID a actualizar: ");
                        id = sc.nextLine();

                        System.out.print("¿Es jefe? (true/false): ");
                        entrada = sc.nextLine().toLowerCase();

                        if (entrada.equals("true")) {
                            esJefe = true;
                        } else if (entrada.equals("false")) {
                            esJefe = false;
                        } else {
                            System.out.println("Entrada inválida. Debes escribir 'true' o 'false'.");
                            continue;
                        }

                        System.out.print("Salario: ");
                        salario = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Jornada Laboral: ");
                        jornada = sc.nextLine();

                        if (Vendedor.updateVendedor(id, esJefe, salario, jornada)) {
                            System.out.println("Vendedor actualizado.");
                        } else {
                            System.out.println("No encontrado.");
                        }
                        break;
                    case 4:
                        System.out.print("ID del vendedor a consultar: ");
                        id = sc.nextLine();
                        Vendedor vendedor = Vendedor.queryVendedor(id);
                        if (vendedor != null) {
                            System.out.println("Vendedor encontrado: " + vendedor);
                        } else {
                            System.out.println("Vendedor no encontrado.");
                        }
                        break;
                    case 5:
                        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                        System.out.println("<Vendedores>");
                        Vendedor.printVendedor();
                        System.out.println("</Vendedores>");
                        break;
                    case 6:
                        crearFicheroXMLVendedores();

                        break;



                    default:
                        System.out.println("Opción inválida. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un número válido.");
                sc.nextLine();
            }
        } while (menu);

        sc.close();
    }

    public static void crearFicheroXMLVendedores() {
        String archivo = "/home/davgarant/Escritorio/vendedores.txt";

        try (FileWriter writer = new FileWriter(archivo)) {

            writer.write("<VENDEDORES>\n");

            for (Vendedor v : Vendedor.getListaVendedores()) {
                writer.write("   <vendedor>\n");
                writer.write("      <idvendedor>" + v.getIdVendedor() + "</idvendedor>\n");
                writer.write("      <esjefe>" + v.getisEsJefe() + "</esjefe>\n");
                writer.write("      <salario>" + v.getSalario() + "</salario>\n");
                writer.write("      <jornadalaboral>" + v.getJornadaLaboral() + "</jornadalaboral>\n");
                writer.write("   </vendedor>\n");
            }

            writer.write("</VENDEDORES>\n");

            System.out.println("✅ El fichero XML de vendedores ha sido creado con éxito.");
        } catch (IOException e) {
            System.out.println("❌ Error al crear el archivo de texto");
            e.printStackTrace();
        }
    }


}