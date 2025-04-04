import java.io.*;
import java.util.*;

class Vendedor {
    private String idVendedor;
    private boolean esJefe;
    private int salario;
    private String jornadaLaboral;
    private static List<Vendedor> vendedores = new ArrayList<>();

    public Vendedor(String idVendedor, boolean esJefe, int salario, String jornadaLaboral) {
        this.idVendedor = idVendedor;
        this.esJefe = esJefe;
        this.salario = salario;
        this.jornadaLaboral = jornadaLaboral;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public boolean getisEsJefe() {
        return esJefe;
    }

    public int getSalario() {
        return salario;
    }

    public String getJornadaLaboral() {
        return jornadaLaboral;
    }

    public void setEsJefe(boolean esJefe) {
        this.esJefe = esJefe;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void setJornadaLaboral(String jornadaLaboral) {
        this.jornadaLaboral = jornadaLaboral;
    }

    private static int findVendedorIndex(String id) {
        for (int i = 0; i < vendedores.size(); i++) {
            if (vendedores.get(i).getIdVendedor().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean addNewVendedor(Vendedor v) {
        if (findVendedorIndex(v.getIdVendedor()) == -1) {
            vendedores.add(v);
            return true;
        }
        return false;
    }

    public static boolean removeVendedor(String id) {
        int index = findVendedorIndex(id);
        if (index != -1) {
            vendedores.remove(index);
            return true;
        }
        return false;
    }

    public static boolean updateVendedor(String id, boolean esJefe, int salario, String jornadaLaboral) {
        int index = findVendedorIndex(id);
        if (index != -1) {
            vendedores.get(index).setEsJefe(esJefe);
            vendedores.get(index).setSalario(salario);
            vendedores.get(index).setJornadaLaboral(jornadaLaboral);
            return true;
        }
        return false;
    }


    public static Vendedor queryVendedor(String idVendedor) {
        for (Vendedor v : vendedores) {
            if (v.getIdVendedor().equalsIgnoreCase(idVendedor)) {
                return v;
            }
        }
        return null;
    }


    public static void printVendedor() {
        if (vendedores.isEmpty()) {
            System.out.println("No hay vendedores registrados.");
            return;
        }
        for (Vendedor v : vendedores) {
            System.out.println(v);
        }
    }



    @Override
    public String toString() {
        return "<Vendedor>\n" +
                "   <IDVendedor>" + idVendedor + "</IDVendedor>\n" +
                "   <EsJefe>" + esJefe + "</EsJefe>\n" +
                "   <Salario>" + salario + "</Salario>\n" +
                "   <JornadaLaboral>" + jornadaLaboral + "</JornadaLaboral>\n" +
                "</Vendedor>";
    }

    public static List<Vendedor> getListaVendedores() {
        return vendedores;
    }


}