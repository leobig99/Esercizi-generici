/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import entity.Persona;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author andrea.zoccarato
 */
public class Agenda extends Application {

    public static Stage login;
    public static Stage register;
    public static Stage mainPage;
    public static Stage visualizzaProfilo;
    public static Stage modificaProfilo;
    public static Stage cancellaPromemoria;
    public static Stage regMod;
    public static Stage visAppProm;

    @Override
    public void start(Stage stage) throws Exception {
        login = stage;
        stage.setTitle("Login Page");
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void mainPage(String username, String password) throws IOException {
        Stage stage = new Stage();
        mainPage = stage;
        stage.setTitle("Main Page");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLMainPage.fxml"));

        Parent root = (Parent) fxmlLoader.load();

        FXMLMainPageController controller = fxmlLoader.<FXMLMainPageController>getController();
        System.out.println("Dentro Agenda.java->"+username+" "+password);
        controller.setUsePassword(username, password);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void register() throws IOException {
        Stage stage = new Stage();
        register = stage;
        stage.setTitle("Register Page");
        Parent root = FXMLLoader.load(getClass().getResource("FXMLRegister.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void vediProfilo(Persona p) throws IOException {
        Stage stage = new Stage();
        visualizzaProfilo = stage;
        stage.setTitle("Profilo");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLVisualizzaProfilo.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLVisualizzaProfiloController controller = fxmlLoader.<FXMLVisualizzaProfiloController>getController();
        controller.setPersona(p);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void modProfilo(Persona p) throws IOException{
        Stage stage = new Stage();
        modificaProfilo = stage;
        stage.setTitle("Profilo");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLModificaProfilo.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLModificaProfiloController controller = fxmlLoader.<FXMLModificaProfiloController>getController();
        controller.setPersona(p);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void eliminaPromemoria(Persona p) throws IOException{
        Stage stage = new Stage();
        cancellaPromemoria = stage;
        stage.setTitle("Cancella Promemoria");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLCancellaPromemoria.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLCancellaPromemoriaController controller = fxmlLoader.<FXMLCancellaPromemoriaController>getController();
        controller.setPersona(p);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void registrazioneModifica(Persona p) throws IOException{
        Stage stage = new Stage();
        regMod = stage;
        stage.setTitle("Reg. Mod. Promemoria");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLRegistraAppProm.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLRegistraAppPromController controller = fxmlLoader.<FXMLRegistraAppPromController>getController();
        controller.setPersona(p);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void visualizzaPromApp(Persona p) throws IOException{
        Stage stage = new Stage();
        visAppProm = stage;
        stage.setTitle("Visualizza Prom. App.");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLVisualizzaPromApp.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLVisualizzaPromAppController controller = fxmlLoader.<FXMLVisualizzaPromAppController>getController();
        controller.setPersona(p);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void closeLogin() {
        login.close();
    }

    public void mainClose() {
        mainPage.close();
    }

    public void registerClose() {
        register.close();
    }

    public void closeVisualizzaProfilo() {
        visualizzaProfilo.close();
    }
    
    public void closeModificaProfilo() {
        modificaProfilo.close();
    }
    
    public void closeCancellaPromemoria(){
        cancellaPromemoria.close();
    }
    
    public void closeRegMod(){
        regMod.close();
    }
    
    public void closeAppProm(){
        visAppProm.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
