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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author govilkar
 */
public class ERnSQLConverter extends Application {
    double orgSceneX,orgSceneY,orgTranslateX,orgTranslateY;
    String e;
    Text t1;
    BorderPane border;
    double newTranslateY;
    double newTranslateX;
    @Override
    public void start(Stage mystage)
    {
        border = new BorderPane();
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
        contodata.setId("butt1");
        mystage.setResizable(false);
        contodata.setMaxSize(250, 200);
        hbox.setSpacing(10);
        sqltoeng.setStyle("-fx-font:bold italic 12pt \"serif\";");
        hbox.setPadding(new Insets(10,10,10,180));
        vbox.setPadding(new Insets(10,10,10,10));
        hbox.setStyle("-fx-background-color:#f2ffff;;");
        vbox.setStyle("-fx-background-color:#f2ffff;");
        hbox.getChildren().addAll(ercon,sqltoeng);
        Scene scene = new Scene(border,680,680);
        mystage.setScene(scene);
        scene.getStylesheets().add(ERnSQLConverter.class.getResource("stylesheet.css").toExternalForm());
        File imageFile[]=new File[8];
        Canvas can=new Canvas(1500,550);
        GraphicsContext gc=can.getGraphicsContext2D();
        gc.setFill(Color.CORNSILK);
        gc.fillRect(0,0,1500,550);
        border.setCenter(can);
        boolean value[]={false,false,false,false,false,false,false,false};
        String str[]={"Entity","Attribute","Relationship","Connect","Select","Delete","Undo","Label"};
        for(int i=0;i<8;i++)
        {
            imageFile[i] = new File("C:\\Users\\govilkar\\Desktop\\ERnSQLConverter\\src\\ernsqlconverter\\"+str[i]+".png");
            ImageView img = new ImageView(new Image(imageFile[i].toURI().toString()));
            Button b = new Button(str[i],img);
            final int x=i;
            b.setOnAction(e->{fun(x);});
            b.setId("butt");
            b.setMaxSize(150, 200);
            b.setOnAction((e)->{
                value[x]=true;
            });
            value[i]=false;
            vbox.getChildren().add(b);
            vbox.setMargin(b, new Insets(8, 0, 8, 0));
        }
        can.setOnMouseEntered((a) -> System.out.println("hi"));
        can.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                int x=(int) e.getSceneX();
                int y=(int) e.getSceneY();
                for(int i=0;i<8;i++)
                {
                    if(value[i])
                    {
                        if(i==0)
                        {
                            Rectangle r = new Rectangle(x,y,100,50);
                            r.setFill(Color.TRANSPARENT);
                            r.setStroke(Color.BLACK);
                            r.setOnMousePressed(rp);
                            r.setOnMouseDragged(rd);
                            r.setOnDragDone(rdo);
                            t1 = new Text(x+25,y+25,"Entity");
                            border.getChildren().addAll(r,t1);
                            
                        }
                        if(i==1)
                        {
                            Ellipse r=new Ellipse(x,y,50,30);
                            r.setFill(Color.TRANSPARENT);
                            r.setStroke(Color.BLACK);
                            gc.strokeText("Attribute", x-200, y-60);
                            border.getChildren().add(r);
                        }
                        if(i==2)
                        {
                            Rectangle r = new Rectangle(x,y,70,70);
                            r.setFill(Color.TRANSPARENT);
                            r.setStroke(Color.BLACK);
                            r.getTransforms().add(new Rotate(45,x,y));
                            r.setOnMousePressed(rp);
                            r.setOnMouseDragged(rd);
                            gc.strokeText("Relation", x-200, y-10);
                            border.getChildren().add(r);
                        }
                    }
                    value[i]=false;
                }
            }
        });
        can.setOnKeyReleased(new EventHandler<KeyEvent>() {
         @Override
            public void handle(KeyEvent event) {
                System.out.println("Handled");
            }
        });
        mystage.setTitle("Mini");
        mystage.show();
    }
    void fun(int i)
    {
        if(i==0)
        {
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    EventHandler<MouseEvent> rp = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            e=t1.toString();
            t1.setText("");
            orgTranslateX = ((Rectangle)(t.getSource())).getTranslateX();
            orgTranslateY = ((Rectangle)(t.getSource())).getTranslateY();
        }
    };
     
    EventHandler<MouseEvent> rd = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            newTranslateX = orgTranslateX + offsetX;
            newTranslateY = orgTranslateY + offsetY;
            ((Rectangle)(t.getSource())).setTranslateX(newTranslateX);
            ((Rectangle)(t.getSource())).setTranslateY(newTranslateY);
            
        }
    };
    EventHandler<DragEvent> rdo = 
        new EventHandler<DragEvent>() {
 
        @Override
        public void handle(DragEvent t) {
            t1=new Text(newTranslateX+25,newTranslateY+25,""+e);
            border.getChildren().add(t1);
        }

    };
    
}
    
