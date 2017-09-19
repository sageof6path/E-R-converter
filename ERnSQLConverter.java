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
import java.lang.String;

/**
 *
 * @author govilkar
 */
public class ERnSQLConverter extends Application {
    
    @Override
    public void start(Stage mystage)
    {
        BorderPane border =  new BorderPane();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        border.setTop(hbox);
        border.setLeft(vbox);
        Button ercon = new Button("E-R To Database Table");
        Button sqltoeng = new Button("SQL To Englsih");
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10));
        vbox.setPadding(new Insets(10,10,10,10));
        hbox.setStyle("-fx-background-color:Brown;");
        vbox.setStyle("-fx-background-color:Pink;");
        hbox.getChildren().addAll(ercon,sqltoeng);
        Scene scene = new Scene(border,700,700);
        File imageFile[]=new File[3];
        String str[]={"Entity","Attribute","Relation"/*,"Connect","Select","Delete","Undo","Label"*/};
        for(int i=0;i<3;i++)
        {
            imageFile[i] = new File("C:\\Users\\govilkar\\Desktop\\ERnSQLConverter\\src\\ernsqlconverter\\"+str[i]+".png");
            ImageView img = new ImageView(new Image(imageFile[i].toURI().toString()));
            Button b = new Button(str[i],img);
            vbox.getChildren().add(b);
            vbox.setMargin(b, new Insets(8, 0, 8, 0));
        }
        mystage.setScene(scene);
        mystage.setTitle("Mini");
        mystage.setScene(scene);
        mystage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
    
