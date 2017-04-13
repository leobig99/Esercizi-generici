/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morracinese2.pkg0;

import ModelloMorraCinese.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author andrea
 */
public class MorraCinese20 extends Application {

    public static Stage stag;
    private static Stage controller;
    private static Stage help;
    private static Stage Online;
    private static Stage GraficaOnline;
    
    public static MorraCineseMultyPlayer m=new MorraCineseMultyPlayer();
    

    public String nome = "";

    @Override
    public void start(Stage stage) throws Exception {
        stag = stage;
        stage.setTitle("Morra Cinese");
        Parent root = FXMLLoader.load(getClass().getResource("StartFXML.fxml"));
//        root.setStyle("-fx-background-color: #FFFFFF;");
		root.setStyle("-fx-background-image: url(file:sfondo.jpg);");
                
        Scene scene = new Scene(root);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    public void ReStart() throws Exception {
        Stage stage = new Stage();
        stag = stage;
        controller.close();
        stage.setTitle("Morra Cinese");
        Parent root = FXMLLoader.load(getClass().getResource("StartFXML.fxml"));
        //root.setStyle("-fx-background-color: #FFFFFF;");
		root.setStyle("-fx-background-image: url(file:sfondo.jpg);");
        Scene scene = new Scene(root);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    public void Help() throws IOException {
        Stage stg = new Stage();
        help = stg;
        stg.setTitle("Morra Cinese-HELP");
        Parent root = FXMLLoader.load(getClass().getResource("HelpFXML.fxml"));
        root.setStyle("-fx-background-color: #FFFFFF;");
        Scene scene = new Scene(root);
        stg.resizableProperty().setValue(Boolean.FALSE);
        stg.setScene(scene);
        stg.show();
    }

    public void startGioco(String s) throws IOException {
        Stage stg = new Stage();
        controller = stg;
        stg.setTitle("Morra Cinese");
        Parent root = FXMLLoader.load(getClass().getResource(s));
		//root.setStyle("-fx-background-color: #FFFFFF;");
		root.setStyle("-fx-background-image: url(file:sfondo.jpg);");
        Scene scene = new Scene(root);
        stg.resizableProperty().setValue(Boolean.FALSE);
        stg.setScene(scene);
        stg.show();
    }
    
    public void startOnline(String s) throws IOException{
        Stage stg = new Stage();
        Online = stg;
        stg.setTitle("Morra Cinese");
        Parent root = FXMLLoader.load(getClass().getResource(s));
	root.setStyle("-fx-background-image: url(file:sfondo.jpg);");
        Scene scene = new Scene(root);
        stg.resizableProperty().setValue(Boolean.FALSE);
        stg.setScene(scene);
        stg.show();
    }
    
     public void startMulty(String s) throws IOException{
        Stage stg = new Stage();
        GraficaOnline = stg;
        stg.setTitle("Morra Cinese");
        Parent root = FXMLLoader.load(getClass().getResource(s));
	root.setStyle("-fx-background-image: url(file:sfondo.jpg);");
        Scene scene = new Scene(root);
        stg.resizableProperty().setValue(Boolean.FALSE);
        stg.setScene(scene);
        stg.show();
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public String getNome() {
        return nome;
    }

    public void closeStart() {
        stag.close();
    }

    public void closeHelp() {
        help.close();
    }
    
    public void closeOnline() {
        Online.close();
    }

    public static void main(String[] args) throws Exception {
		
        launch(args);
    }

}
