package view;

public class View {
    /**
     * Muestra un mensaje
     * @param msg Mensaje a mostrar
     */
    public static void mostrarMsg(String msg){
        System.out.println(msg);
    }

    /**
     * Muestra un menu de opciones con su respectiva opcion
     * @param opciones Titulos de cada opcion
     */
    public static void mostrarMenu(String ... opciones){
        System.out.println("Introduce una de las siguientes opciones:");
        for (int i = 1; i <= opciones.length; i++){
            System.out.println(i + ": " + opciones[i - 1]);
        }
    }

    /**
     * Muestra un titulo con un formato mas decorado
     * @param titulo Titulo a mostrar
     */
    public static void mostrartitulo(String titulo){
        String r = "";
        for (int i = 0; i < titulo.length(); i++){
            r += "-";
        }
        System.out.println(r + "\n" + titulo.toUpperCase() + "\n" + r);
    }
}