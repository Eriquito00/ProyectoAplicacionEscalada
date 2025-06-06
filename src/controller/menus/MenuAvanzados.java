package controller.menus;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import controller.Main;
import view.View;
import controller.classes.advanced.advancedData;

import java.sql.Connection;
import java.sql.SQLException;

public class MenuAvanzados {
    public static void menuConsultasAvanzadas(String titulo, Connection c){
        boolean seguir = true;

        while (seguir){

            View.mostrartitulo(titulo);
            View.mostrarMenu("Mostra les vies d'una Escola que estan disponibles",
                    "Cercar vies per dificultat en un rang específic (via, grau, sector, escola)",
                    "Cercar vies segons estat (Apte, Construcció, Tancada)",
                    "Consultar escoles amb restriccions actives actualment",
                    "Mostrar sectors amb més de X vies disponibles",
                    "Mostrar escaladors amb el mateix nivell màxim assolit",
                    "Mostrar les vies que han passat a \"Apte\" recentment",
                    "Mostrar les vies més llargues d’una escola determinada",
                    "Salir");
            int opcion = Main.aplicaOpcio(Main.scan, 1, 9);

            try {
                switch (opcion){
                    case 1:
                        View.mostrarMsg(advancedData.escolesDisponibles(c));
                        break;
                    case 2:
                        View.mostrarMsg(advancedData.viesDificultat(c));
                        break;
                    case 3:
                        View.mostrarMsg(advancedData.viesEstat(c));
                        break;
                    case 4:
                        View.mostrarMsg(advancedData.escolesRestriccions(c));
                        break;
                    case 5:
                        View.mostrarMsg(advancedData.sectorsNumVies(c));
                        break;
                    case 6:
                        View.mostrarMsg(advancedData.escaladorsDificultat(c));
                        break;
                    case 7:
                        View.mostrarMsg(advancedData.viesAptesRecents(c));
                        break;
                    case 8:
                        View.mostrarMsg(advancedData.viesescolaLlargues(c));
                        break;
                    case 9:
                        seguir = false;
                        View.mostrarMsg("Volviendo al menu principal...");
                        break;
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
}