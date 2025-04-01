package view;

public class View {
    public static void mostrarMsg(String msg){
        System.out.println(msg);
    }

    public static void mostrarMenu(String ... opciones){
        System.out.println("Introdueix una de les opcions seguents:");
        for (int i = 1; i <= opciones.length; i++){
            System.out.println(i + ": " + opciones[i - 1]);
        }
    }
}