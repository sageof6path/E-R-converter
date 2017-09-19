/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ernsqlconverter;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author govilkar
 */
public class ERnSQLConverter extends Application {
    
    @Override
    public void start(Stage mystage)
    {
        BorderPane border =  new BorderPane();
        HBox hbox2 = new HBox();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        border.setTop(hbox);
        border.setBottom(hbox2);
        border.setLeft(vbox);
        Button ercon = new Button("E-R To Database Table");
        ercon.setStyle("-fx-font:bold italic 12pt \"serif\";");
        Button sqltoeng = new Button("SQL To Englsih");
        hbox2.setPadding(new Insets(25,25,25,250));
        hbox2.setSpacing(200);
        hbox.getStyleClass().add("hbox");
        hbox2.getStyleClass().add("hbox");
        vbox.getStyleClass().add("vbox");
        File imgfile1 = new File("C:\\Users\\govilkar\\Desktop\\ERnSQLConverter\\src\\ernsqlconverter\\Save.png");
        File imgfile2 = new File("C:\\Users\\govilkar\\Desktop\\ERnSQLConverter\\src\\ernsqlconverter\\Database.png");
        ImageView i1=new ImageView(imgfile1.toURI().toString());
        ImageView i2=new ImageView(imgfile2.toURI().toString());
        Button save = new Button("Save",i1);
        hbox2.setStyle("-fx-background-color:#f2ffff;");
        Button contodata = new Button("Convert to Database",i2);
        hbox2.getChildren().addAll(save,contodata);
        save.setId("butt");
        save.setMaxSize(150, 200);
        contodata.setId("butt");
        contodata.setMaxSize(250, 200);
        hbox.setSpacing(10);
        sqltoeng.setStyle("-fx-font:bold italic 12pt \"serif\";");
        hbox.setPadding(new Insets(10,10,10,180));
        vbox.setPadding(new Insets(10,10,10,10));
        hbox.setStyle("-fx-background-color:#f2ffff;;");
        vbox.setStyle("-fx-background-color:#f2ffff;");
        hbox.getChildren().addAll(ercon,sqltoeng);
        Scene scene = new Scene(border,700,700);
        mystage.setScene(scene);
        scene.getStylesheets().add(ERnSQLConverter.class.getResource("stylesheet.css").toExternalForm());
        File imageFile[]=new File[8];
        String str[]={"Entity","Attribute","Relationship","Connect","Select","Delete","Undo","Label"};
        for(int i=0;i<8;i++)
        {
            imageFile[i] = new File("C:\\Users\\govilkar\\Desktop\\ERnSQLConverter\\src\\ernsqlconverter\\"+str[i]+".png");
            ImageView img = new ImageView(new Image(imageFile[i].toURI().toString()));
            Button b = new Button(str[i],img);
            b.setId("butt");
            b.setMaxSize(150, 200);
            vbox.getChildren().add(b);
            vbox.setMargin(b, new Insets(8, 0, 8, 0));
        }
        mystage.setTitle("Mini");
        mystage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
    
