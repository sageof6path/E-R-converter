/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ernsqlconverter1;

import java.beans.Statement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author govilkar
 */
class sudda implements Serializable
{
    String ud;
    double x,y;
    int type;
    sudda(String ud,double x,double y,int type)
    {
        this.ud=ud;
        this.x=x;
        this.y=y;
        this.type=type;
    }
}
class saving implements Serializable
{
    ArrayList<sudda> obj;
    HashMap<String,ArrayList<String>> hmp;
    HashMap<String,ArrayList<String>> hmp1;
    ArrayList<String> primaryKey=new ArrayList<>();
    ArrayList<String> weakL=new ArrayList<>();
    saving()
    {
        obj=new ArrayList<sudda>();
    }
}
public class ERnSQLConverter1 extends Application {
    
    double orgSceneX,orgSceneY,orgTranslateX,orgTranslateY;
    String s1,s2,s3;
    static int ent=-1;
    Scene scene;
    boolean a=true;
    VBox vbe,vbe1;
    BorderPane border;
    Pane border1;
    double newTranslateY;
    double newTranslateX;
    Stage st;
    static void setEn(int i)
    {
        ent=i;
    }
    ArrayList<String> entity;
    @Override
    public void start(Stage mystage) throws IOException
    {
        st=mystage;
        st.getIcons().add(new Image(ERnSQLConverter1.class.getResourceAsStream("images.jfif")));
        HashMap<String,ArrayList<String>> hmp=new HashMap();
        HashMap<String,ArrayList<String>> hmp1=new HashMap();
        HashMap<String,ArrayList<String>> hmp2=new HashMap();
        ArrayList<String> primaryKey=new ArrayList<>();
        ArrayList<String> weakL=new ArrayList<>();
        saving sa=new saving();
        border = new BorderPane();
        HBox hbox2 = new HBox();
        HBox hbox = new HBox();
        Button open=new Button("Open");
        VBox vbox = new VBox();
        border1 = new Pane();
        border.setTop(hbox);
        border.setOnMouseClicked((e3)->{
        System.out.println(e3.getSceneX()+" "+e3.getSceneY());
        });
        border.setBottom(hbox2);
        border.setLeft(vbox);
        border.setCenter(border1);
        //orgSceneX=border1.getLayoutX();
        //orgSceneY=border1.getLayoutY();
        border1.setOnMouseClicked((MouseEvent e)->{
        int x=(int) e.getSceneX();
        int y=(int) e.getSceneY();
        entity=new ArrayList<String>();
        if(ent==0)
        {
            Button b1=new Button("Entity");
            boolean b2=false;
            b1.setUserData("entity");
            b1.setId("entity");
            b1.setTranslateX(x-220);
            b1.setTranslateY(y-70);
            border1.getChildren().add(b1);
            b1.setOnMousePressed((e1)->{
            vbe.getChildren().clear();
            String str=b1.getText();
            TextField tf = new TextField(str);
            String imageResource ="Delete"+".png";
            Image myImage = null;
            try {
                myImage = new Image(this.getClass().getResource(imageResource).toURI().toString());
            } catch (URISyntaxException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageView img1 = new ImageView(myImage);
            Button dell = new Button("Delete",img1);
            dell.setId("buttdel");
            dell.setMaxSize(150, 200);
            dell.setOnMouseClicked((e4)->{
                if(weakL.contains(b1.getUserData().toString()))
                    weakL.remove(b1.getUserData().toString());
                if(primaryKey.contains(b1.getUserData().toString()))
                    primaryKey.remove(b1.getUserData().toString());
                ArrayList<Node> arr2=new ArrayList<>();
                /*for(String s4:hmp.get(b1.getUserData().toString()))
                {
                    for(Node n:border1.getChildren())
                    {
                        if(n.getUserData().toString().contains(s4))
                            arr2.add(n);
                    }
                }*/
                if(hmp.containsKey(b1.getUserData().toString()))
                    hmp.remove(b1.getUserData().toString());
                try{
                for(Node n:border1.getChildren())
                {
                    if(n.getUserData().toString().contains(b1.getUserData()+"-")||n.getUserData().toString().contains("-"+b1.getUserData()))
                    {
                        arr2.add(n);
                    }
                }}catch(Exception e2)
                {
                    System.out.println(e2);
                }
                for(Node n:arr2)
                {
                    border1.getChildren().remove(n);
                }
                if(hmp1.containsValue(b1.getUserData().toString()))
                {
                    Set<String> ste=hmp1.keySet();
                    for(String s4:ste)
                    {
                        ArrayList<String> al4=new ArrayList<>(hmp1.get(s4));
                        if(al4.contains(b1.getUserData().toString()))
                        {
                            al4.remove(b1.getUserData().toString());
                            hmp1.replace(s4,hmp1.get(s4) ,al4);
                        }
                    }
                }
                if(hmp2.containsValue(b1.getUserData().toString()))
                {
                    Set<String> ste=hmp2.keySet();
                    for(String s4:ste)
                    {
                        ArrayList<String> al4=new ArrayList<>(hmp2.get(s4));
                        if(al4.contains(b1.getUserData().toString()))
                        {
                            al4.remove(b1.getUserData().toString());
                            hmp1.replace(s4,hmp2.get(s4) ,al4);
                        }
                    }
                }
                border1.getChildren().remove(b1);
            });
            dell.setStyle("-fx-background-color:red");
            tf.setOnKeyPressed((e2)->{
                if(e2.getCode().equals(KeyCode.ENTER))
                {
                    String s=tf.getText();
                    b1.setText(s);
                    entity.add(s);
                    Bounds boundsInScene = ((Node)b1).localToScene(b1.getBoundsInLocal());
                    sudda sad=new sudda(s,boundsInScene.getMinX(),boundsInScene.getMinY(),0);
                    sa.obj.add(sad);
                    b1.setUserData(s);
                }
            });
            Label le=new Label("Entity");
            le.setId("le");
            Label ee=new Label("Edit Entity");
            ee.setId("ee");
            Label type=new Label("Type");
            RadioButton reg =new RadioButton("regular");
            reg.setUserData("regular");
            reg.setId("reg");
            RadioButton weak=new RadioButton("weak");
            weak.setUserData("weak");
            weak.setId("weak");
            RadioButton asso=new RadioButton("Associative");
            asso.setUserData("Associative");
            asso.setId("asso");
            ToggleGroup tog=new ToggleGroup();
            reg.setToggleGroup(tog);
            weak.setToggleGroup(tog);
            asso.setToggleGroup(tog);
            Label lea =new Label("Attribute");
            lea.setId("lea");
            TextField tfa = new TextField("Attribute");
            tfa.setId("tfa");
            tfa.setOnKeyPressed((e2)->{
                if(e2.getCode().equals(KeyCode.ENTER))
                {
                    String s=tfa.getText();
                    try{for(Node n:border1.getChildren())
                    {
                        if(n.getUserData().equals(s))
                        {
                             Line line = new Line();
                             ArrayList<String> al=new ArrayList();
                             ArrayList<String> a2=hmp.get(b1.getText());
                             if(a2!=null)
                             {
                                 for(String s2:a2)
                                 {
                                     al.add(s2);
                                 }
                                 al.add(s);
                                 hmp.remove(b1.getText());
                             }
                             else
                                 al.add(s);
                             hmp.put(b1.getText(), al);
                             hmp2.put(b1.getText(),al);
                             a2=hmp.get(b1.getText());
                             for(String s2:a2)
                             {
                                 System.out.println(s2);
                             }
                             //line.setStartX(b1.getLayoutX());
                             //line.setScaleY(b1.getLayoutY());
                             
                               line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n.getBoundsInParent();
                                return b.getMinX() + b.getWidth() / 2 ;
                            }, n.boundsInParentProperty()));
                            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n.getBoundsInParent() ;
                                return b.getMinY() + b.getHeight() / 2 ;
                            }, n.boundsInParentProperty()));
                                       border1.getChildren().add(line);
                               Node n1=(Node)b1;
                               line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n1.getBoundsInParent();
                                return b.getMinX() + b.getWidth() / 2 ;
                            }, n1.boundsInParentProperty()));
                            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n1.getBoundsInParent() ;
                                return b.getMinY() + b.getHeight() / 2 ;
                            }, n1.boundsInParentProperty()));
                            String uds=b1.getUserData()+"-"+n.getUserData();
                            System.out.println(uds);
                            line.setUserData(uds);
                                       border1.getChildren().add(line);
                        }
                    }}catch(Exception E)
                    {
                        System.out.println("bye");
                    }
                }
            });
            if(weakL.contains(b1.getText()))
                weak.setSelected(true);
            vbe.getChildren().addAll(ee,le,tf,type,reg,weak,asso,lea,tfa,dell);
            border.setRight(vbe);
            a=false;
            tog.selectedToggleProperty().addListener(ty->{
            if(tog.getSelectedToggle().getUserData().toString()=="weak")
            {
                weakL.add(b1.getText());
                b1.setId("weak");
            }
            else if(tog.getSelectedToggle().getUserData().toString()=="regular")
            {
                if(weakL.contains(b1.getText()))
                    weakL.remove(b1.getText());
                b1.setId("entity");
            }
            });
            });
            
            b1.setOnMouseDragged(ed);
        }
        if(ent==1)
        {
            boolean arr[]=new boolean[4]; 
            Button b1 = new Button("Attribute");
            b1.setUserData("attribute");
            b1.setId("att");
            b1.setTranslateX(x-220);
            b1.setTranslateY(y-70);
            b1.setShape(new Ellipse(100,75));
            border1.getChildren().add(b1);
            b1.setOnMousePressed((e1)->{
            vbe.getChildren().clear();
            String str=b1.getText();
            TextField tf = new TextField(str);
            tf.setOnKeyPressed((e2)->{
                if(e2.getCode().equals(KeyCode.ENTER))
                {
                    String s=tf.getText();
                    b1.setUserData(s);
                    Bounds boundsInScene = ((Node)b1).localToScene(b1.getBoundsInLocal());
                    sudda sad=new sudda(s,boundsInScene.getMinX(),boundsInScene.getMinY(),1);
                    sa.obj.add(sad);
                    b1.setText(s);
                }
            });
            String imageResource ="Delete"+".png";
            Image myImage = null;
            try {
                myImage = new Image(this.getClass().getResource(imageResource).toURI().toString());
            } catch (URISyntaxException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageView img1 = new ImageView(myImage);
            Button dell = new Button("Delete",img1);
            dell.setId("butt");
            dell.setMaxSize(150, 200);
            dell.setOnMouseClicked((e4)->{
                if(primaryKey.contains(b1.getUserData()))
                    primaryKey.remove(b1.getUserData());
                ArrayList<Node> arr2=new ArrayList<>();
                try{
                for(Node n:border1.getChildren())
                {
                    if(n.getUserData().toString().contains(b1.getUserData()+"-")||n.getUserData().toString().contains("-"+b1.getUserData()))
                    {
                        arr2.add(n);
                    }
                }}catch(Exception e3)
                {
                    System.out.println(e3);
                }
                for(Node n:arr2)
                {
                    border1.getChildren().remove(n);
                }
                if(hmp.containsValue(b1.getUserData().toString()))
                {
                    for(String s4:hmp.keySet())
                    {
                        ArrayList<String> al4=new ArrayList<>(hmp1.get(s4));
                        if(al4.contains(b1.getUserData().toString()))
                        {
                            al4.remove(b1.getUserData().toString());
                            hmp.remove(s4);
                            hmp.put(s4, al4);
                        }
                    }
                }
                border1.getChildren().remove(b1);
            });
            dell.setStyle("-fx-background-color:red");
            Label lr=new Label("Attribute");
            lr.setId("lr");
            Label er=new Label("Edit Attribute");
            er.setId("er");
            Label type=new Label("Type");
            type.setId("type");
            CheckBox un=new CheckBox("Unique");
            un.setId("un");
            if(arr[0])
                un.setSelected(arr[0]);
            CheckBox ml=new CheckBox("Multivalued");
            ml.setId("ml");
            CheckBox cm=new CheckBox("Composite");
            cm.setId("cm");
            CheckBox d=new CheckBox("Derived");
            d.setId("d");
            vbe.getChildren().addAll(er,lr,tf,type,un,ml,cm,d,dell);
            EventHandler eh = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (event.getSource() instanceof CheckBox) {
                        CheckBox chk = (CheckBox) event.getSource();
                        System.out.println("Action performed on checkbox " + chk.getText());
                        primaryKey.add(b1.getText());
                        for(String s:primaryKey)
                        {
                            System.out.println(s);
                        }
                        if ("Unique".equals(chk.getText())) {
                            un.setSelected(un.isSelected());
                            arr[0]=true;
                        } else if ("Composite".equals(chk.getText())) {
                            cm.setSelected(cm.isSelected());
                        }
                    }
                }
            };
            un.setOnAction(eh);
            cm.setOnAction(eh);
            border.setRight(vbe);
            });
            //a=false;
            b1.setOnMouseDragged(ed);
            System.out.println("yz");
        }
        if(ent==2)
        {
            Label t=new Label("Relation");
            t.setRotate(-45);
           // ChoiceBox c=new ChoiceBox();
            //ArrayList<String> l=new ArrayList<String>();
            //for(Button b:entity)
            //{
                //l.add(b.getText());
                //System.out.println(b.getText());
            //}
            //c.setItems(FXCollections.observableList((List)l));
            
            
            Button b1=new Button();
            b1.setUserData("Relation");
            b1.setGraphic(t);
            b1.setId("rel");
            Rectangle r=new Rectangle(200,200);
            b1.setShape(r);
            b1.setMaxSize(100, 100);
            b1.setMinSize(100, 100);
            b1.setTranslateX(x-220);
            b1.setTranslateY(y-70);
            border1.getChildren().add(b1);
            b1.setOnMouseDragged(ed);
            System.out.println("yz");
            b1.setOnMousePressed((e1)->{
            vbe.getChildren().clear();
            String str=t.getText();
            TextField tf = new TextField(str);
            tf.setOnKeyPressed((e2)->{
                if(e2.getCode().equals(KeyCode.ENTER))
                {
                    String s=tf.getText();
                    t.setText(s);
                    Bounds boundsInScene = ((Node)b1).localToScene(b1.getBoundsInLocal());
                    sudda sad=new sudda(s,boundsInScene.getMinX(),boundsInScene.getMinY(),2);
                    sa.obj.add(sad);
                }
            });
            Label lr=new Label("Relationship");
            lr.setId("lr");
            Label er=new Label("Edit Relationship");
            er.setId("er");
            Label ent1 = new Label("Entity One");
            ent1.setId("ent1");
            TextField tent1 = new TextField("Entity one");
            tent1.setOnKeyPressed((e2)->{
                if(e2.getCode().equals(KeyCode.ENTER))
                {
                    String s=tent1.getText();
                    try{for(Node n:border1.getChildren())
                    {
                        if(n.getUserData().equals(s))
                        {
                             Line line = new Line();
                             ArrayList<String> al=new ArrayList();
                             ArrayList<String> a2=hmp1.get(b1.getText());
                             if(a2!=null)
                             {
                                 for(String s2:a2)
                                 {
                                     al.add(s2);
                                 }
                                 al.add(s);
                                 hmp1.remove(t.getText());
                             }
                             else
                                 al.add(s);
                             hmp1.put(t.getText(), al);
                             a2=hmp1.get(t.getText());
        // bind ends of line:
                             for(String s2:a2)
                                 System.out.println(s2);
                             line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n.getBoundsInParent();
                                return b.getMinX() + b.getWidth() / 2 ;
                            }, n.boundsInParentProperty()));
                            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n.getBoundsInParent() ;
                                return b.getMinY() + b.getHeight() / 2 ;
                            }, n.boundsInParentProperty()));
                                       border1.getChildren().add(line);
                               Node n1=(Node)b1;
                               line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n1.getBoundsInParent();
                                return b.getMinX() + b.getWidth() / 2 ;
                            }, n1.boundsInParentProperty()));
                            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n1.getBoundsInParent() ;
                                return b.getMinY() + b.getHeight() / 2 ;
                            }, n1.boundsInParentProperty()));
                            String usd=b1.getUserData()+"-"+n.getUserData();
                            line.setUserData(usd);
                                       border1.getChildren().add(line);
                        }
                    }}catch(Exception E)
                    {
                        System.out.println("bye2");
                    }
                }
            });
            tent1.setId("tent1");
            Label card1 = new Label("Cardinality");
            card1.setId("card1");
            RadioButton one =new RadioButton("one");
            one.setId("one");
            Label ent2 = new Label("Entity Two");
            ent2.setId("ent2");
            TextField tent2 = new TextField("Entity two");
            tent2.setOnKeyPressed((e2)->{
                if(e2.getCode().equals(KeyCode.ENTER))
                {
                    String s=tent2.getText();
                    try{for(Node n:border1.getChildren())
                    {
                        if(n.getUserData().equals(s))
                        {
                             Line line = new Line();
                             ArrayList<String> al=new ArrayList();
                             ArrayList<String> a2=hmp1.get(t.getText());
                             if(a2!=null)
                             {
                                 for(String s2:a2)
                                 {
                                     al.add(s2);
                                 }
                                 al.add(s);
                                 hmp1.remove(t.getText());
                             }
                             else
                                 al.add(s);
                             hmp1.put(t.getText(), al);
                             a2=hmp1.get(t.getText());
                             for(String s2:a2)
                             {
                                 System.out.println(s2);
                             }
        // bind ends of line:
                             Set <String>k=hmp1.keySet();
                            for(String s2:k)
                                System.out.println(s2);
                               line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n.getBoundsInParent();
                                return b.getMinX() + b.getWidth() / 2 ;
                            }, n.boundsInParentProperty()));
                            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n.getBoundsInParent() ;
                                return b.getMinY() + b.getHeight() / 2 ;
                            }, n.boundsInParentProperty()));
                                       border1.getChildren().add(line);
                               Node n1=(Node)b1;
                               line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n1.getBoundsInParent();
                                return b.getMinX() + b.getWidth() / 2 ;
                            }, n1.boundsInParentProperty()));
                            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                                Bounds b = n1.getBoundsInParent() ;
                                return b.getMinY() + b.getHeight() / 2 ;
                            }, n1.boundsInParentProperty()));
                            String uds=b1.getUserData().toString()+"-"+n.getUserData().toString();
                            line.setUserData(uds);
                                       border1.getChildren().add(line);
                               border1.getChildren().add(line);
                        }
                    }
                }catch(Exception E)
                        {
                            System.out.println("bye3");
                        }
            }
            });
            tent2.setId("tent2");
            Label card2 = new Label("Cardinality");
            card2.setId("card2");
            RadioButton one1 =new RadioButton("one");
            one1.setId("one1");
            ToggleGroup tog=new ToggleGroup();
            one.setToggleGroup(tog);
            one1.setToggleGroup(tog);
            String imageResource ="Delete"+".png";
            Image myImage = null;
            try {
                myImage = new Image(this.getClass().getResource(imageResource).toURI().toString());
            } catch (URISyntaxException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageView img1 = new ImageView(myImage);
            Button dell = new Button("Delete",img1);
            dell.setId("butt");
            dell.setMaxSize(150, 200);
            dell.setOnMouseClicked((e4)->{
                if(hmp1.containsKey(b1.getUserData()))
                    hmp1.remove(b1.getUserData());
                if(hmp2.containsKey(b1.getUserData()))
                    hmp2.remove(b1.getUserData());
                ArrayList<Node> arr2=new ArrayList<>();
                try{
                for(Node n:border1.getChildren())
                {
                    if(n.getUserData().toString().contains(b1.getUserData()+"-")||n.getUserData().toString().contains("-"+b1.getUserData()))
                    {
                        arr2.add(n);
                    }
                }}catch(Exception e3)
                {
                    System.out.println(e3);
                }
                for(Node n:arr2)
                {
                    border1.getChildren().remove(n);
                }
                border1.getChildren().remove(b1);
            });
            dell.setStyle("-fx-background-color:red");
            vbe.getChildren().addAll(er,lr,tf,ent1,tent1,card1,one,ent2,tent2,card2,one1,dell);
            border.setRight(vbe);
            a=false;
            tog.selectedToggleProperty().addListener(ty->{System.out.println("Clicked "+tog.getSelectedToggle().getUserData().toString());});
});
        }
        ent=-1;});
        Button ercon = new Button("E-R To Database Table");
        ercon.setStyle("-fx-font:bold italic 12pt \"serif\";");
        Button sqltoeng = new Button("SQL To Englsih");
        hbox2.setPadding(new Insets(25,25,25,250));
        hbox2.setSpacing(200);
        hbox.getStyleClass().add("hbox");
        hbox2.getStyleClass().add("hbox");
        vbox.getStyleClass().add("vbox");
        File imgfile1 = new File("C:\\Users\\govilkar\\Desktop\\ERnSQLConverter1\\src\\ernsqlconverter1\\Save.png");
        File imgfile2 = new File("C:\\Users\\govilkar\\Desktop\\ERnSQLConverter1\\src\\ernsqlconverter1\\Database.png");
        ImageView i1=new ImageView(imgfile1.toURI().toString());
        ImageView i2=new ImageView(imgfile2.toURI().toString());
        Button save = new Button("Save",i1);
        hbox2.setStyle("-fx-background-color:#d3e2e2;");
        Button contodata = new Button("Convert to Database",i2);
        contodata.setOnMouseClicked((e)->{
            final Stage dialog = new Stage();
            HashMap<String,String> userd = new HashMap<>();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(st);
                Button b1 = new Button("Yes");
                b1.setId("popy");
                HBox hb1 = new HBox();
                HBox hb2 = new HBox();
                HBox hb3 = new HBox();
                HBox hb4 = new HBox();
                Label tu = new Label("  Username     ");
                tu.setAlignment(Pos.CENTER_LEFT);
                tu.setStyle("-fx-font:bold italic 12pt \"serif\";");
                TextField tfu = new TextField("");
                tfu.setOnKeyPressed((e1)->{
                    if(e1.getCode().equals(KeyCode.ENTER))
                        userd.put("username",tfu.getText());
                });
                
                tfu.setAlignment(Pos.CENTER);
                Label tdn = new Label("Database name");
                tdn.setAlignment(Pos.CENTER_LEFT);
                tdn.setStyle("-fx-font:bold italic 12pt \"serif\";");
                TextField tfdn = new TextField("");
                tfdn.setOnKeyPressed((e1)->{
                    if(e1.getCode().equals(KeyCode.ENTER))
                        userd.put("databasename",tfdn.getText());
                });
                tfdn.setAlignment(Pos.CENTER);
                Label tp = new Label("  Password     ");
                tp.setAlignment(Pos.CENTER_LEFT);
                tp.setStyle("-fx-font:bold italic 12pt \"serif\";");
                TextField tfp = new TextField("");
                tfp.setOnKeyPressed((KeyEvent e1) -> {
                    if(e1.getCode().equals(KeyCode.ENTER))
                        userd.put("password",tfp.getText());
            });
                tfp.setAlignment(Pos.CENTER);
                Label turl = new Label("  Paste URL   ");
                turl.setAlignment(Pos.CENTER_LEFT);
                turl.setStyle("-fx-font:bold italic 12pt \"serif\";");
                TextField tfurl = new TextField("");
                tfurl.setAlignment(Pos.CENTER);
                tfurl.setOnKeyPressed((e1)->{
                    if(e1.getCode().equals(KeyCode.ENTER))
                        userd.put("url",tfurl.getText());
                });
                hb1.getChildren().addAll(tu,tfu);
                hb1.setSpacing(10);
                hb1.setPadding(new Insets(10,0,0,0));
                hb2.setSpacing(10);
                hb3.setSpacing(10);
                hb4.setSpacing(10);
                hb2.getChildren().addAll(tp,tfp);
                hb3.getChildren().addAll(turl,tfurl);
                hb4.getChildren().addAll(tdn,tfdn);
                b1.setMinHeight(30);
                b1.setMinWidth(40);
                Button b2 = new Button("No");
                b2.setId("popn");
                b2.setMinHeight(30);
                b2.setMinWidth(40);
                final ProgressBar pb = new ProgressBar();
                //pb.setVisible(false);
                pb.setProgress(-1.0);
                pb.setPrefWidth(340);
                b1.setOnMouseClicked((z)->{
                    pb.setVisible(true);
                    pb.setProgress(-1.0);
                    Set<String> it = (Set<String>)hmp.keySet();
                    for (String ky:it) {
                        String query="",q2="";
                        query+="create table "+ky+"(";
                        ArrayList<String> a3=(ArrayList) hmp.get(ky);
                        for(String s:a3)
                        {
                            query+=s+" varchar(50)"+",";
                        }
                        String s4="";
                        for(String s:a3)
                        {
                            if(primaryKey.contains(s))
                            s4+=s+",";
                        }
                        if(s4!="")
                        {
                            query+="primary key(";
                            for(int i=0;i<s4.length()-1;i++)
                                query+=s4.charAt(i);
                            query+="),";
                        }
                        for(int i=0;i<query.length()-1;i++)
                            q2+=String.valueOf(query.charAt(i));
                        q2+=")";
                    System.out.println(q2);
                URL url=null;  
                pb.setProgress(0.0);
                    try {
                        url = new URL("http://clubbulletin.000webhostapp.com/project.php");
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 HttpURLConnection httpURLConnection = null;  
                    try {
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {  
                        httpURLConnection.setRequestMethod("POST");
                    } catch (ProtocolException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 httpURLConnection.setDoOutput(true);  
                 //httpURLConnection.setDoInput(true);  
                 OutputStream OS = null;  
                    try {
                        OS = httpURLConnection.getOutputStream();
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    BufferedWriter bufferedWriter=null;
                    try {
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        String data = "query" + "=" + q2;
                    try {  
                        String data1 = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userd.get("username"), "UTF-8") + "&" +  
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(userd.get("password"), "UTF-8")+ "&" +  
                        URLEncoder.encode("url", "UTF-8") + "=" + URLEncoder.encode(userd.get("url"), "UTF-8") + "&" + 
                        URLEncoder.encode("databasename", "UTF-8") + "=" + URLEncoder.encode(userd.get("databasename"), "UTF-8") ;
                        System.out.println(userd.get("password"));
                        bufferedWriter.write(data1);
                        bufferedWriter.write("&"+data);
                        System.out.println(data);
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        bufferedWriter.flush();  
                        bufferedWriter.close();  
                        OS.close();  
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    InputStream inputStream = null;  
                    try {
                        inputStream = httpURLConnection.getInputStream();
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 BufferedReader bufferedReader = null;  
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 String response = "";  
                 String line = "";  
                    try {
                        while ((line = bufferedReader.readLine())!=null)
                        {
                            response+= line;  
                        }  } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {  
                        bufferedReader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {  
                        inputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pb.setProgress(0.35);
                    System.out.println(response);
                    httpURLConnection.disconnect();
                }
                    Set<String> it2 = hmp1.keySet();
                    HashMap<String,String> enp;
                    for(String ky:it2)
                    {
                        String k=(String) ky;
                        ArrayList<String> arr=(ArrayList<String>) hmp1.get(ky);
                        for(String str:arr)
                        {
                            if(primaryKey.contains(str))
                                System.out.println(k+" "+str);
                        }
                    }
                    for (String ky:it2) {
                        String query="",q2="";
                        query+="create table "+ky+"(";
                        String rq="primary key(";
                        String fk="";
                        String xy="";
                        ArrayList<String> en=(ArrayList<String>) hmp1.get(ky);
                        int n=0;
                        for(String str:en)
                        {
                            System.out.println(str);
                            if(hmp2.containsKey(str)&&!(weakL.contains(str)))
                            {
                                ArrayList<String> val=(ArrayList<String>)hmp2.get(str);
                                for(String s6:val)
                                {
                                    if(primaryKey.contains(s6))
                                    {
                                        n++;
                                        rq+=s6+",";
                                        fk+="FOREIGN KEY "+"("+s6+")"+ " REFERENCES " + str+"("+s6+"),";
                                        xy+=s6+" varchar(50) "+",";
                                    }
                                }
                            }
                        }
                        query+=xy;
                        query+=rq;
                        query=query.substring(0,query.length()-1);
                        query+="),";
                        query+=fk;
                        for(int i=0;i<query.length()-1;i++)
                            q2+=String.valueOf(query.charAt(i));
                        q2+=")";
                        if(n<=1)
                            q2="";
                    System.out.println(q2);
                URL url=null;  
                    try {
                        url = new URL("http://clubbulletin.000webhostapp.com/project.php");
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 HttpURLConnection httpURLConnection = null;  
                    try {
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {  
                        httpURLConnection.setRequestMethod("POST");
                    } catch (ProtocolException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 httpURLConnection.setDoOutput(true);  
                 //httpURLConnection.setDoInput(true);  
                 OutputStream OS = null;  
                    try {
                        OS = httpURLConnection.getOutputStream();
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    BufferedWriter bufferedWriter=null;
                    try {
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pb.setProgress(1.0);
                    try {
                        String data = "query" + "=" + q2;
                    try { 
                        String data1 = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userd.get("username"), "UTF-8") + "&" +  
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(userd.get("password"), "UTF-8")+ "&" +  
                        URLEncoder.encode("url", "UTF-8") + "=" + URLEncoder.encode(userd.get("url"), "UTF-8") + "&" + 
                        URLEncoder.encode("databasename", "UTF-8") + "=" + URLEncoder.encode(userd.get("databasename"), "UTF-8") ;
                        System.out.println(userd.get("password"));
                        bufferedWriter.write(data1);
                        bufferedWriter.write("&"+data);
                        System.out.println(data);
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        bufferedWriter.flush();  
                        bufferedWriter.close();  
                        OS.close();  
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    InputStream inputStream = null;  
                    try {
                        inputStream = httpURLConnection.getInputStream();
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 BufferedReader bufferedReader = null;  
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 String response = "";  
                 String line = "";  
                    try {
                        while ((line = bufferedReader.readLine())!=null)
                        {
                            response+= line;  
                        }  } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {  
                        bufferedReader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {  
                        inputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(response);
                    httpURLConnection.disconnect();
                }
                    dialog.close();
                });
                b2.setOnMouseClicked((z)->{
                    dialog.close();
                });
                HBox dialogHbox = new HBox(20);
                dialogHbox.getChildren().addAll(b1,b2);
                b1.setAlignment(Pos.CENTER);
                b1.setId("butt");
                b2.setId("butt");
                b2.setAlignment(Pos.CENTER);
                dialogHbox.setAlignment(Pos.CENTER);
                VBox dialogVbox = new VBox(20);
                Text t = new Text("  Do You wan to convert to the database?");
                t.setStyle("-fx-font:bold italic 12pt \"serif\";");
                dialogVbox.getChildren().addAll(t,hb1,hb2,hb3,hb4,dialogHbox,pb);
                Scene dialogScene = new Scene(dialogVbox, 350, 300);
                dialog.setScene(dialogScene);
                dialog.setTitle("Final");
                dialog.show();
        });
        open.setStyle("-fx-font:bold italic 12pt \"serif\";");
        open.setId("butt");
        hbox2.getChildren().addAll(save,contodata,open);
        save.setId("butt");
        save.setOnAction((e)->{
            FileChooser fileChooser = new FileChooser();
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
              fileChooser.getExtensionFilters().add(extFilter);
              File file1 = fileChooser.showSaveDialog(st);
            sa.hmp1=hmp1;
            sa.hmp=hmp;
            sa.primaryKey=primaryKey;
            sa.weakL=weakL;
            //String filename="xyz.txt";
            FileOutputStream file = null;
            try {
                file = new FileOutputStream(file1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectOutputStream out=null;
            try {
               out = new ObjectOutputStream(file);
            } catch (IOException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                out.writeObject(sa);
            } catch (IOException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        open.setOnMouseClicked((e1)->{
            FileChooser fileChooser = new FileChooser();
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
              fileChooser.getExtensionFilters().add(extFilter);
              File file1 = fileChooser.showOpenDialog(st);
            FileInputStream file = null;
            try {
                file=new FileInputStream(file1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObjectInputStream oi=null;
            try {
                oi=new ObjectInputStream(file);
            } catch (IOException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                saving obj=(saving)oi.readObject();
                ArrayList<sudda> opps=obj.obj;
                hmp.putAll(obj.hmp);
                Set<String> sett=hmp.keySet();
                for(String ks:sett)
                {
                    System.out.println(ks+":");
                    ArrayList<String> alu=hmp.get(ks);
                    for(String sk:alu)
                        System.out.println(sk);
                }
                hmp1.putAll(obj.hmp1);
                hmp2.putAll(obj.hmp1);
                primaryKey.addAll(obj.primaryKey);
                weakL.addAll(obj.weakL);
                for(sudda op1:opps)
                {
                    if(op1.type==0)
                    {
                        Button b1=new Button(op1.ud);
                        boolean b2=false;
                        b1.setUserData(op1.ud);
                        b1.setId("entity");
                        b1.setTranslateX(op1.x);
                        b1.setTranslateY(op1.y);
                        border1.getChildren().add(b1);
                        b1.setOnMousePressed((e2)->{
                        vbe.getChildren().clear();
                        String str=b1.getText();
                        TextField tf = new TextField(str);
                        tf.setOnKeyPressed((e3)->{
                            if(e3.getCode().equals(KeyCode.ENTER))
                            {
                                String s=tf.getText();
                                b1.setText(s);
                                entity.add(s);
                                Bounds boundsInScene = ((Node)b1).localToScene(b1.getBoundsInLocal());
                                sudda sad=new sudda(s,boundsInScene.getMinX(),boundsInScene.getMinY(),0);
                                sa.obj.add(sad);
                                b1.setUserData(s);
                            }
                        });
                        Label le=new Label("Entity");
                        le.setId("le");
                        Label ee=new Label("Edit Entity");
                        ee.setId("ee");
                        Label type=new Label("Type");
                        RadioButton reg =new RadioButton("regular");
                        reg.setUserData("regular");
                        reg.setId("reg");
                        RadioButton weak=new RadioButton("weak");
                        weak.setUserData("weak");
                        weak.setId("weak");
                        RadioButton asso=new RadioButton("Associative");
                        asso.setUserData("Associative");
                        asso.setId("asso");
                        ToggleGroup tog=new ToggleGroup();
                        reg.setToggleGroup(tog);
                        weak.setToggleGroup(tog);
                        asso.setToggleGroup(tog);
                        Label lea =new Label("Attribute");
                        lea.setId("lea");
                        TextField tfa = new TextField("Attribute");
                        tfa.setId("tfa");
                        tfa.setOnKeyPressed((e4)->{
                            if(e4.getCode().equals(KeyCode.ENTER))
                            {
                                String s=tfa.getText();
                                try{for(Node n:border1.getChildren())
                                {
                                    if(n.getUserData().equals(s))
                                    {
                                         Line line = new Line();
                                         ArrayList<String> al=new ArrayList();
                                         ArrayList<String> a2=hmp.get(b1.getText());
                                         if(a2!=null)
                                         {
                                             for(String s2:a2)
                                             {
                                                 al.add(s2);
                                             }
                                             al.add(s);
                                             hmp.remove(b1.getText());
                                         }
                                         else
                                             al.add(s);
                                         hmp.put(b1.getText(), al);
                                         hmp2.put(b1.getText(),al);
                                         a2=hmp.get(b1.getText());
                                         for(String s2:a2)
                                         {
                                             System.out.println(s2);
                                         }
                                         //line.setStartX(b1.getLayoutX());
                                         //line.setScaleY(b1.getLayoutY());

                                           line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                                            Bounds b = n.getBoundsInParent();
                                            return b.getMinX() + b.getWidth() / 2 ;
                                        }, n.boundsInParentProperty()));
                                        line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                                            Bounds b = n.getBoundsInParent() ;
                                            return b.getMinY() + b.getHeight() / 2 ;
                                        }, n.boundsInParentProperty()));
                                                   border1.getChildren().add(line);
                                           Node n1=(Node)b1;
                                           line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                                            Bounds b = n1.getBoundsInParent();
                                            return b.getMinX() + b.getWidth() / 2 ;
                                        }, n1.boundsInParentProperty()));
                                        line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                                            Bounds b = n1.getBoundsInParent() ;
                                            return b.getMinY() + b.getHeight() / 2 ;
                                        }, n1.boundsInParentProperty()));
                                                   border1.getChildren().add(line);
                                    }
                                }}catch(Exception E)
                                {
                                    System.out.println("bye");
                                }
                            }
                        });
                        if(weakL.contains(b1.getText()))
                            weak.setSelected(true);
                        vbe.getChildren().addAll(ee,le,tf,type,reg,weak,asso,lea,tfa);
                        border.setRight(vbe);
                        a=false;
                        tog.selectedToggleProperty().addListener(ty->{
                        if(tog.getSelectedToggle().getUserData().toString()=="weak")
                        {
                            weakL.add(b1.getText());
                            b1.setId("weak");
                        }
                        else if(tog.getSelectedToggle().getUserData().toString()=="regular")
                        {
                            if(weakL.contains(b1.getText()))
                                weakL.remove(b1.getText());
                            b1.setId("entity");
                        }
                        });
                        });

                        b1.setOnMouseDragged(ed);
                    }
                    else if(op1.type==1)
                    {
                        boolean arr[]=new boolean[4]; 
                        if(primaryKey.contains(op1.ud))
                            arr[0]=true;
                        Button b1 = new Button(op1.ud);
                        b1.setUserData(op1.ud);
                        b1.setId("att");
                        b1.setTranslateX(op1.x);
                        b1.setTranslateY(op1.y);
                        b1.setShape(new Ellipse(100,75));
                        border1.getChildren().add(b1);
                        b1.setOnMousePressed((e)->{
                        vbe.getChildren().clear();
                        String str=b1.getText();
                        TextField tf = new TextField(str);
                        tf.setOnKeyPressed((e2)->{
                            if(e2.getCode().equals(KeyCode.ENTER))
                            {
                                String s=tf.getText();
                                b1.setUserData(s);
                                Bounds boundsInScene = ((Node)b1).localToScene(b1.getBoundsInLocal());
                                sudda sad=new sudda(s,boundsInScene.getMinX(),boundsInScene.getMinY(),1);
                                sa.obj.add(sad);
                                b1.setText(s);
                            }
                        });
                        Label lr=new Label("Attribute");
                        lr.setId("lr");
                        Label er=new Label("Edit Attribute");
                        er.setId("er");
                        Label type=new Label("Type");
                        type.setId("type");
                        CheckBox un=new CheckBox("Unique");
                        un.setId("un");
                        if(arr[0])
                            un.setSelected(arr[0]);
                        CheckBox ml=new CheckBox("Multivalued");
                        ml.setId("ml");
                        CheckBox cm=new CheckBox("Composite");
                        cm.setId("cm");
                        CheckBox d=new CheckBox("Derived");
                        d.setId("d");
                        vbe.getChildren().addAll(er,lr,tf,type,un,ml,cm,d);
                        EventHandler eh = new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (event.getSource() instanceof CheckBox) {
                                    CheckBox chk = (CheckBox) event.getSource();
                                    System.out.println("Action performed on checkbox " + chk.getText());
                                    primaryKey.add(b1.getText());
                                    for(String s:primaryKey)
                                    {
                                        System.out.println(s);
                                    }
                                    if ("Unique".equals(chk.getText())) {
                                        un.setSelected(un.isSelected());
                                        arr[0]=true;
                                    } else if ("Composite".equals(chk.getText())) {
                                        cm.setSelected(cm.isSelected());
                                    }
                                }
                            }
                        };
                        un.setOnAction(eh);
                        cm.setOnAction(eh);
                        border.setRight(vbe);
                        });
                        //a=false;
                        b1.setOnMouseDragged(ed);
                    }
                    else if(op1.type==2)
                    {
                            Label t=new Label(op1.ud);
                            t.setRotate(-45);
                            Button b1=new Button();
                            b1.setUserData(op1.ud);
                            b1.setGraphic(t);
                            b1.setId("rel");
                            Rectangle r=new Rectangle(200,200);
                            b1.setShape(r);
                            b1.setMaxSize(100, 100);
                            b1.setMinSize(100, 100);
                            b1.setTranslateX(op1.x);
                            b1.setTranslateY(op1.y);
                            border1.getChildren().add(b1);
                            b1.setOnMouseDragged(ed);
                            System.out.println("yz");
                            b1.setOnMousePressed((e)->{
                            vbe.getChildren().clear();
                            String str=t.getText();
                            TextField tf = new TextField(str);
                            tf.setOnKeyPressed((e2)->{
                                if(e2.getCode().equals(KeyCode.ENTER))
                                {
                                    String s=tf.getText();
                                    t.setText(s);
                                    Bounds boundsInScene = ((Node)b1).localToScene(b1.getBoundsInLocal());
                                    sudda sad=new sudda(s,boundsInScene.getMinX(),boundsInScene.getMinY(),0);
                                    sa.obj.add(sad);
                                }
                            });
                            Label lr=new Label("Relationship");
                            lr.setId("lr");
                            Label er=new Label("Edit Relationship");
                            er.setId("er");
                            Label ent1 = new Label("Entity One");
                            ent1.setId("ent1");
                            TextField tent1 = new TextField("Entity one");
                            tent1.setOnKeyPressed((e2)->{
                                if(e2.getCode().equals(KeyCode.ENTER))
                                {
                                    String s=tent1.getText();
                                    try{for(Node n:border1.getChildren())
                                    {
                                        if(n.getUserData().equals(s))
                                        {
                                             Line line = new Line();
                                             ArrayList<String> al=new ArrayList();
                                             ArrayList<String> a2=hmp1.get(b1.getText());
                                             if(a2!=null)
                                             {
                                                 for(String s2:a2)
                                                 {
                                                     al.add(s2);
                                                 }
                                                 al.add(s);
                                                 hmp1.remove(t.getText());
                                             }
                                             else
                                                 al.add(s);
                                             hmp1.put(t.getText(), al);
                                             a2=hmp1.get(t.getText());
                        // bind ends of line:
                                             for(String s2:a2)
                                                 System.out.println(s2);
                                             line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                                                Bounds b = n.getBoundsInParent();
                                                return b.getMinX() + b.getWidth() / 2 ;
                                            }, n.boundsInParentProperty()));
                                            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                                                Bounds b = n.getBoundsInParent() ;
                                                return b.getMinY() + b.getHeight() / 2 ;
                                            }, n.boundsInParentProperty()));
                                                       border1.getChildren().add(line);
                                               Node n1=(Node)b1;
                                               line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                                                Bounds b = n1.getBoundsInParent();
                                                return b.getMinX() + b.getWidth() / 2 ;
                                            }, n1.boundsInParentProperty()));
                                            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                                                Bounds b = n1.getBoundsInParent() ;
                                                return b.getMinY() + b.getHeight() / 2 ;
                                            }, n1.boundsInParentProperty()));
                                            String uds=b1.getUserData().toString()+"-"+n.getUserData().toString();
                                            line.setUserData(uds);
                                                       border1.getChildren().add(line);
                                        }
                                    }}catch(Exception E)
                                    {
                                        System.out.println("bye2");
                                    }
                                }
                            });
                            tent1.setId("tent1");
                            Label card1 = new Label("Cardinality");
                            card1.setId("card1");
                            RadioButton one =new RadioButton("one");
                            one.setId("one");
                            Label ent2 = new Label("Entity Two");
                            ent2.setId("ent2");
                            TextField tent2 = new TextField("Entity two");
                            tent2.setOnKeyPressed((e2)->{
                                if(e2.getCode().equals(KeyCode.ENTER))
                                {
                                    String s=tent2.getText();
                                    try{for(Node n:border1.getChildren())
                                    {
                                        if(n.getUserData().equals(s))
                                        {
                                             Line line = new Line();
                                             ArrayList<String> al=new ArrayList();
                                             ArrayList<String> a2=hmp1.get(t.getText());
                                             if(a2!=null)
                                             {
                                                 for(String s2:a2)
                                                 {
                                                     al.add(s2);
                                                 }
                                                 al.add(s);
                                                 hmp1.remove(t.getText());
                                             }
                                             else
                                                 al.add(s);
                                             hmp1.put(t.getText(), al);
                                             a2=hmp1.get(t.getText());
                                             for(String s2:a2)
                                             {
                                                 System.out.println(s2);
                                             }
                        // bind ends of line:
                                             Set <String>k=hmp1.keySet();
                                            for(String s2:k)
                                                System.out.println(s2);
                                               line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                                                Bounds b = n.getBoundsInParent();
                                                return b.getMinX() + b.getWidth() / 2 ;
                                            }, n.boundsInParentProperty()));
                                            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                                                Bounds b = n.getBoundsInParent() ;
                                                return b.getMinY() + b.getHeight() / 2 ;
                                            }, n.boundsInParentProperty()));
                                                       border1.getChildren().add(line);
                                               Node n1=(Node)b1;
                                               line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                                                Bounds b = n1.getBoundsInParent();
                                                return b.getMinX() + b.getWidth() / 2 ;
                                            }, n1.boundsInParentProperty()));
                                            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                                                Bounds b = n1.getBoundsInParent() ;
                                                return b.getMinY() + b.getHeight() / 2 ;
                                            }, n1.boundsInParentProperty()));
                                                       border1.getChildren().add(line);
                                               border1.getChildren().add(line);
                                        }
                                    }
                                }catch(Exception E)
                                        {
                                            System.out.println("bye3");
                                        }
                            }
                            });
                            tent2.setId("tent2");
                            Label card2 = new Label("Cardinality");
                            card2.setId("card2");
                            RadioButton one1 =new RadioButton("one");
                            one1.setId("one1");
                            ToggleGroup tog=new ToggleGroup();
                            one.setToggleGroup(tog);
                            one1.setToggleGroup(tog);
                            vbe.getChildren().addAll(er,lr,tf,ent1,tent1,card1,one,ent2,tent2,card2,one1);
                            border.setRight(vbe);
                            a=false;
                            tog.selectedToggleProperty().addListener(ty->{System.out.println("Clicked "+tog.getSelectedToggle().getUserData().toString());});
                });
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            }
            Set<String> ks=hmp.keySet();
            for(String sk:ks)
            {
                Node n1 = null;
                for(Node n:border1.getChildren())
                {
                    if(n.getUserData().equals(sk))
                    {
                        n1=n;
                        break;
                    }
                }
                final Node n2=n1;
                ArrayList<String> ram=hmp.get(sk);
                for(String sks:ram)
                {
                    try{
                    for(Node n:border1.getChildren())
                    {
                        
                        if(n.getUserData().equals(sks))
                        {
                            Line line=new Line();
                            line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                            Bounds b = n.getBoundsInParent();
                            return b.getMinX() + b.getWidth() / 2 ;
                            }, n.boundsInParentProperty()));
                            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                            Bounds b = n.getBoundsInParent() ;
                            return b.getMinY() + b.getHeight() / 2 ;
                            }, n.boundsInParentProperty()));
                            line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                            Bounds b = n2.getBoundsInParent();
                            return b.getMinX() + b.getWidth() / 2 ;
                            }, n2.boundsInParentProperty()));
                            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                            Bounds b = n2.getBoundsInParent() ;
                            return b.getMinY() + b.getHeight() / 2 ;
                            }, n2.boundsInParentProperty()));
                            border1.getChildren().add(line);
                        }
                        
                    }
                    }catch(Exception e)
                        {
                            System.out.println("fck");
                        }
                }
            }
            Set<String> ks1=hmp1.keySet();
            for(String sk:ks1)
            {
                Node n1 = null;
                for(Node n:border1.getChildren())
                {
                    if(n.getUserData().equals(sk))
                    {
                        n1=n;
                        break;
                    }
                }
                final Node n2=n1;
                ArrayList<String> ram=hmp1.get(sk);
                for(String sks:ram)
                {
                    try{
                    for(Node n:border1.getChildren())
                    {
                        
                        if(n.getUserData().equals(sks))
                        {
                            Line line=new Line();
                            line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                            Bounds b = n.getBoundsInParent();
                            return b.getMinX() + b.getWidth() / 2 ;
                            }, n.boundsInParentProperty()));
                            line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                            Bounds b = n.getBoundsInParent() ;
                            return b.getMinY() + b.getHeight() / 2 ;
                            }, n.boundsInParentProperty()));
                            line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                            Bounds b = n2.getBoundsInParent();
                            return b.getMinX() + b.getWidth() / 2 ;
                            }, n2.boundsInParentProperty()));
                            line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                            Bounds b = n2.getBoundsInParent() ;
                            return b.getMinY() + b.getHeight() / 2 ;
                            }, n2.boundsInParentProperty()));
                            String uds=n2.getUserData().toString()+"-"+n.getUserData().toString();
                            line.setUserData(uds);
                            border1.getChildren().add(line);
                        }
                        
                    }
                    }catch(Exception e)
                        {
                            System.out.println("fck");
                        }
                }
            }
        });
        save.setMaxSize(150, 200);
        contodata.setId("butt1");
        vbe = new VBox();
        vbe1 = new VBox();
        vbe.getStyleClass().add("vbox");
        //mystage.setResizable(false);
        contodata.setMaxSize(250, 200);
        hbox.setSpacing(500);
        vbox.setSpacing(30);
        sqltoeng.setStyle("-fx-font:bold italic 12pt \"serif\";");
        hbox.setPadding(new Insets(10,200,10,200));
        vbox.setPadding(new Insets(10,10,10,10));
        vbe.setPadding(new Insets(10,10,10,10));
        vbe1.setPadding(new Insets(10,10,10,10));
        hbox.setStyle("-fx-background-color:#d3e2e2;");
        vbox.setStyle("-fx-background-color:#d3e2e2;");
        vbe.setStyle("-fx-background-color:#d3e2e2;");
        vbe1.setStyle("-fx-background-color:#d3e2e2;");
        hbox.getChildren().addAll(ercon);
        scene = new Scene(border,680,680);
        mystage.setScene(scene);
        scene.getStylesheets().add(ERnSQLConverter1.class.getResource("stylesheet.css").toExternalForm());
        File imageFile[]=new File[8];
        border.setRight(vbe);
        boolean value[]={false,false,false,false,false,false,false,false};
        String str[]={"Entity","Attribute","Relationship","Label"};
        for(int i=0;i<4;i++)
        {
            String imageResource =str[i]+".png";
            Image myImage = null;
            try {
                myImage = new Image(this.getClass().getResource(imageResource).toURI().toString());
            } catch (URISyntaxException ex) {
                Logger.getLogger(ERnSQLConverter1.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageView img = new ImageView(myImage);
            Button b = new Button(str[i],img);
            final int x=i;
            b.setId("butt");
            b.setMaxSize(150, 200);
            b.setOnAction((e)->{
                value[x]=true;
            });
            final int a=i;
            b.setOnMouseClicked((MouseEvent e) -> {
                setEn(a);
            });
            value[i]=false;
            vbox.getChildren().add(b);
            vbox.setMargin(b, new Insets(8, 0, 8, 0));
        }
        Screen scr=Screen.getPrimary();
        Rectangle2D b=scr.getVisualBounds();
        mystage.setWidth(b.getWidth());
        mystage.setHeight(b.getHeight());
        mystage.setTitle("ERConverter");
        mystage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    EventHandler<MouseEvent> ed = 
        (MouseEvent t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            if(!(t.getSceneX()>180&&t.getSceneX()<1100&&t.getSceneY()>70&&t.getSceneY()<550))
            {
                return;
            }
            newTranslateX = orgTranslateX + offsetX;
            newTranslateY = orgTranslateY + offsetY;
            ((Button)(t.getSource())).setTranslateX(t.getSceneX()-220);
            ((Button)(t.getSource())).setTranslateY(t.getSceneY()-70);
    };
    EventHandler<MouseEvent> ad = 
        (MouseEvent t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            if(!(t.getSceneX()>500&&t.getSceneX()<1100&&t.getSceneY()>70&&t.getSceneY()<550))
            {
                return;
            }
            newTranslateX = orgTranslateX + offsetX;
                newTranslateY = orgTranslateY + offsetY;
                ((Circle)(t.getSource())).setTranslateX(t.getSceneX()-70);
                ((Circle)(t.getSource())).setTranslateY(t.getSceneX()-180);
    };
}
