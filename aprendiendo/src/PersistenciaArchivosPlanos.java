import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaArchivosPlanos {
    private static final String FILE_NAME = "nombres.txt";

    public static void main(String[] args) {
        List<String> nombres = new ArrayList<>();
        solicitarNombres(nombres);
        boolean informacion = guardarNombres(nombres);
        if (informacion){
            JOptionPane.showMessageDialog(null,"Información guardad exitosamente");
        }else{
            JOptionPane.showMessageDialog(null,"Hubo un error al intentar guardar!");
        }

        String mensaje = leerNombres();
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static void solicitarNombres(List<String> nombres){
        String opcion;
        do{
            String nombre = JOptionPane.showInputDialog("Digite el nombre:");
            if (nombre != null && !nombre.trim().isEmpty()){
                nombres.add(nombre);
            }else{
                JOptionPane.showMessageDialog(null, "No ingreso nada");
            }
            opcion = JOptionPane.showInputDialog("¿Desea ingresar otro nombre? (s/n)");
        } while (opcion != null && opcion.equalsIgnoreCase("s"));
    }

    public static boolean guardarNombres(List<String> nombres){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (String n : nombres){
                writer.write(n);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String leerNombres(){
        StringBuilder recuperado = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String linea;
            while((linea = reader.readLine()) != null){
                recuperado.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recuperado.toString();
    }
}
