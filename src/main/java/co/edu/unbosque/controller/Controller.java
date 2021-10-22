package co.edu.unbosque.controller;


import co.edu.unbosque.model.dtos.PetCase;
import co.edu.unbosque.model.dtos.Visit;
import co.edu.unbosque.model.services.PetCaseService;
import co.edu.unbosque.model.services.UserAppService;
import co.edu.unbosque.model.services.VetService;
import co.edu.unbosque.model.services.VisitService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Controller {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "5u8e2u3u";

    public Controller() {
        int op = 0;
        try {
            do {
                funcionar();
                System.out.println("Digite 1 si quiere volver a ejecutar las opciones");
                Scanner lecturaop = new Scanner(System.in);
                op = lecturaop.nextInt();

            } while (op == 1);
        } catch (Exception e) {
            System.out.println("Opcion ingresada incorrecta");
        }
    }

    public void funcionar() {

        // Objects for handling connection
        Connection conn = null;

        try {
            // Registering the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Opening database connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("""
                    Seleccione una opcion:

                    1:  Consultar la lista de usuarios registrados para un rol dado.
                    2:  Contar la lista de veterinarias registradas en la plataforma.
                    3:  Consultar las visitas que se han registrado para un ID de mascota dado.
                    4:  Registrar un nuevo caso de robo de una mascota dado su ID.

                    """);
            Scanner lectura = new Scanner(System.in);
            int opciones = lectura.nextInt();
            switch (opciones) {
                case 1:
                    System.out.println("Ingrese el rol de los usuarios a buscar");
                    UserAppService usersService = new UserAppService(conn);
                    Scanner sc = new Scanner(System.in);
                    String rol = sc.nextLine();
                    usersService.readForRol(rol);
                    break;
                case 2:
                    VetService vetService = new VetService(conn);
                    vetService.listVets();
                    break;
                case 3:
                    VisitService visitS = new VisitService(conn);
                    DateTimeFormatter dtfnow = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    String strDate = dtfnow.format(LocalDateTime.now());
                    System.out.println(" Ingrese el pet_id de la mascotoa para realizar la consulta");
                    Scanner sclec = new Scanner(System.in);
                    String Pet_id = sclec.nextLine();

                    visitS.setPet_id(Pet_id);
                    visitS.updateDateVisit(new Visit(null, strDate, null, null, null, Pet_id));
                    visitS.readForRol();
                    break;
                case 4:
                    PetCaseService petCaseService = new PetCaseService(conn);
                    String case_id = null;

                    System.out.println("Ingrese el tipo de caso que quiere registrar");
                    System.out.println("1:Robo\n2:Pérdida\n3:Fallecimiento");
                    Scanner sclectura = new Scanner(System.in);
                    int tipo = sclectura.nextInt();
                    if (tipo == 1) {
                        String type = "Robo";
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        String created_at = dtf.format(LocalDateTime.now());
                        System.out.println("Ingrese la descripción del caso");
                        Scanner op = new Scanner(System.in);
                        String description = op.nextLine();
                        System.out.println("Ingrese el id de la mascota");
                        String pet_id = op.nextLine();
                        petCaseService.insertNewPetCase(new PetCase(created_at, type, description, pet_id));
                    } else if (tipo == 2) {
                        String type = "Perdida";
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        String created_at = dtf.format(LocalDateTime.now());
                        System.out.println("Ingrese la descripción del caso");
                        Scanner op = new Scanner(System.in);
                        String description = op.nextLine();
                        System.out.println("Ingrese el id de la mascota");
                        String pet_id = op.nextLine();
                        petCaseService.insertNewPetCase(new PetCase(created_at, type, description, pet_id));
                    } else if (tipo == 3) {
                        String type = "Fallecimiento";
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        String created_at = dtf.format(LocalDateTime.now());
                        System.out.println("Ingrese la descripción del caso");
                        Scanner op = new Scanner(System.in);
                        String description = op.nextLine();
                        System.out.println("Ingrese el id de la mascota");
                        String pet_id = op.nextLine();
                        petCaseService.insertNewPetCase(new PetCase(created_at, type, description, pet_id));
                    } else {
                        System.out.println("Tipo de caso no soportado");
                    }
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta");
                    break;


            }

            // Closing database connection
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }
}
