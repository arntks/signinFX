package sample;
	
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import klasser.Skilt;
import klasser.SkiltFart;
import klasser.Skilt_uten_tekst;
import klasser.Splitt;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private ArrayList<ImageView> pictures = new ArrayList<ImageView>();
	private ArrayList<Label> labels = new ArrayList <Label>();
	private ArrayList<Label> fartLabels = new ArrayList <Label>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			String nokkel = lesKort() + ".txt";
	        Splitt splitt = new Splitt(nokkel);
	        File fil = splitt.getFile();
	        splitt.dele(fil);
	        ArrayList<Skilt> skiltGruppe = splitt.getSkiltGruppe();
			
			
			AnchorPane root = FXMLLoader.load(getClass().getResource("/sample/bilder.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
	
			
			if(skiltGruppe.size()==2){
				TwoImage mp = new TwoImage(skiltGruppe);
				mp.makeP(); 
				pictures = mp.getPictures();
			}
			else if(skiltGruppe.size()==3){
				ThreeImage mp = new ThreeImage(skiltGruppe);
				mp.makeP(); 	
				mp.makeT();
				pictures = mp.getPictures();
				labels = mp.getLabels();
			}
			else if(skiltGruppe.size()==4){
				FourImage mp = new FourImage(skiltGruppe);
				mp.makeP(); 	
				mp.makeT();
				pictures = mp.getPictures();
				labels = mp.getLabels();
			}
			else if (skiltGruppe.size()==5){
				FiveImage mp = new FiveImage(skiltGruppe);
				mp.makeP();
				mp.makeT();
				pictures = mp.getPictures();
				labels = mp.getLabels();
			}
			else if(skiltGruppe.size()==6){
				SixImage mp = new SixImage(skiltGruppe);
				mp.makeP(); 	
				mp.makeT();
				pictures = mp.getPictures();
				labels = mp.getLabels();
			}
			else{
				throw new IllegalArgumentException();
			}
			
			UpdateSpeed us = new UpdateSpeed();
			fartLabels = us.makeLabel();			
			root.getChildren().addAll(fartLabels.get(0), fartLabels.get(1));
			
			TopLine topLine = new TopLine();
			Label topLabel = topLine.makeTopLabel();
			root.getChildren().add(topLabel);
			
			
			for (int i=0; i<pictures.size(); i++){ 
				root.getChildren().add(pictures.get(i));
				if (i > 1) root.getChildren().add(labels.get(i-2));
			}
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String lesKort(){
        Scanner scanner = new Scanner(System.in);
        String kortnr = scanner.next();
        scanner.close();
        return kortnr;
    }
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
