package ernsqlconverter1;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author govilkar
 */
public class ERnSQLConverter1 extends Application {
    
    double orgSceneX,orgSceneY,orgTranslateX,orgTranslateY;
    String s1,s2,s3;
    static int ent=-1;
    Scene scene;
    boolean a=true;
    VBox vbe,vbe1;
    BorderPane border;
    GridPane border1;
    double newTranslateY;
    double newTranslateX;
    Stage st;
    static void setEn(int i)
    {
        ent=i;
    }
    @Override
    public void start(Stage mystage)
    {
        st=mystage;
        border = new BorderPane();
        HBox hbox2 = new HBox();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        border1 = new GridPane();
        border.setTop(hbox);
        border.setBottom(hbox2);
        border.setLeft(vbox);
        border.setCenter(border1);
        orgSceneX=border1.getLayoutX();
        orgSceneY=border1.getLayoutY();
        border1.setOnMouseClicked((e)->{
        int x=(int) e.getSceneX() -150;
        int y=(int) e.getSceneY() -100;
        if(ent==0)
        {
            Button b1=new Button("Entity");
            b1.setId("entity");
            b1.setTranslateX(x);
            b1.setTranslateY(y);
            border1.getChildren().add(b1);
            b1.setOnMousePressed((e1)->{
            vbe.getChildren().clear();
            TextField tf = new TextField("Entity");
            tf.setId("tf");
            Label le=new Label("Entity");
            le.setId("le");
            Label ee=new Label("Edit Entity");
            ee.setId("ee");
            Label type=new Label("Type");
            type.setId("type");
            RadioButton reg =new RadioButton("regular");
            reg.setId("reg");
            RadioButton weak=new RadioButton("weak");
            weak.setId("weak");
            RadioButton asso=new RadioButton("Associative");
            asso.setId("asso");
            ToggleGroup tog=new ToggleGroup();
            reg.setToggleGroup(tog);
            weak.setToggleGroup(tog);
            asso.setToggleGroup(tog);
            vbe.getChildren().addAll(ee,le,tf,type,reg,weak,asso);
            border.setRight(vbe);
            a=false;
            tog.selectedToggleProperty().addListener(ty->{System.out.println("Clicked "+tog.getSelectedToggle().getUserData().toString());});
            });
            b1.setOnMouseDragged(ed);
        }
        if(ent==1)
        {
            Button b1 = new Button("Attribute");
            b1.setId("att");
            b1.setTranslateX(x);
            b1.setTranslateY(y);
            b1.setShape(new Ellipse(100,75));
            border1.getChildren().add(b1);
            b1.setOnMousePressed((e1)->{
            vbe.getChildren().clear();
            TextField tf = new TextField("Attribute");
            tf.setId("tf");
            Label la=new Label("Attribute");
            la.setId("la");
            Label ea=new Label("Edit Attribute");
            ea.setId("ea");
            Label type=new Label("Type");
            type.setId("type");
            CheckBox un=new CheckBox("Unique");
            un.setId("un");
            CheckBox ml=new CheckBox("Multivalued");
            ml.setId("ml");
            CheckBox cm=new CheckBox("Composite");
            cm.setId("cm");
            CheckBox d=new CheckBox("Derived");
            d.setId("d");
            vbe.getChildren().addAll(ea,la,tf,type,un,ml,cm,d);
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
            Button b1=new Button();
            b1.setGraphic(t);
            b1.setId("rel");
            Rectangle r=new Rectangle(200,200);
            b1.setShape(r);
            b1.setMaxSize(100, 100);
            b1.setMinSize(100, 100);
            b1.setTranslateX(x);
            b1.setTranslateY(y);
            border1.getChildren().add(b1);
            b1.setOnMouseDragged(ed);
            System.out.println("yz");
            b1.setOnMousePressed((e1)->{
            vbe.getChildren().clear();
           // b1.setMouseTransparent(true);
            TextField tf = new TextField("Relationship");
            Label lr=new Label("Relationship");
            lr.setId("lr");
            Label er=new Label("Edit Relationship");
            er.setId("er");
            Label ent1 = new Label("Entity One");
            ent1.setId("ent1");
            TextField tent1 = new TextField("Entity one");
            tent1.setId("tent1");
            Label card1 = new Label("Cardinality");
            card1.setId("card1");
            RadioButton one =new RadioButton("one");
            one.setId("one");
            Label ent2 = new Label("Entity Two");
            ent2.setId("ent2");
            TextField tent2 = new TextField("Entity two");
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
        ent=-1;});
        Button ercon = new Button("E-R To Database Table");
        ercon.setStyle("-fx-font:bold italic 12pt \"serif\";");
        Button sqltoeng = new Button("SQL To Englsih");
        hbox2.setPadding(new Insets(25,25,25,250));
        hbox2.setSpacing(200);
        hbox.getStyleClass().add("hbox");
        hbox2.getStyleClass().add("hbox");
        vbox.getStyleClass().add("vbox");
        File imgfile1 = new File("C:\\Users\\It\\Documents\\NetBeansProjects\\ernsqlconverter1\\src\\ernsqlconverter1\\Save.png");
        File imgfile2 = new File("C:\\Users\\It\\Documents\\NetBeansProjects\\ernsqlconverter1\\src\\ernsqlconverter1\\Database.png");
        ImageView i1=new ImageView(imgfile1.toURI().toString());
        ImageView i2=new ImageView(imgfile2.toURI().toString());
        Button save = new Button("Save",i1);
        hbox2.setStyle("-fx-background-color:#f2ffff;");
        Button contodata = new Button("Convert to Database",i2);
        hbox2.getChildren().addAll(save,contodata);
        save.setId("butt");
        save.setMaxSize(150, 200);
        contodata.setId("butt1");
        vbe = new VBox();
        vbe1 = new VBox();
        vbe.getStyleClass().add("vbox");
        //mystage.setResizable(false);
        contodata.setMaxSize(250, 200);
        hbox.setSpacing(10);
        sqltoeng.setStyle("-fx-font:bold italic 12pt \"serif\";");
        hbox.setPadding(new Insets(10,10,10,180));
        vbox.setPadding(new Insets(10,10,10,10));
        vbe.setPadding(new Insets(10,10,10,10));
        vbe1.setPadding(new Insets(10,10,10,10));
        hbox.setStyle("-fx-background-color:#f2ffff;;");
        vbox.setStyle("-fx-background-color:#f2ffff;");
        vbe.setStyle("-fx-background-color:#f2ffff;");
        vbe1.setStyle("-fx-background-color:#f2ffff;");
        hbox.getChildren().addAll(ercon,sqltoeng);
        scene = new Scene(border,680,680);
        mystage.setScene(scene);
        scene.getStylesheets().add(ERnSQLConverter1.class.getResource("stylesheet.css").toExternalForm());
        File imageFile[]=new File[8];
        border.setRight(vbe);
        boolean value[]={false,false,false,false,false,false,false,false};
        String str[]={"Entity","Attribute","Relationship","Connect","Select","Delete","Undo","Label"};
        for(int i=0;i<8;i++)
        {
            imageFile[i] = new File("C:\\Users\\It\\Documents\\NetBeansProjects\\ernsqlconverter1\\src\\ernsqlconverter1"+str[i]+".png");
            ImageView img = new ImageView(new Image(imageFile[i].toURI().toString()));
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
        
        mystage.setTitle("Mini");
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
            double offsetX = t.getSceneX() - (orgSceneX +150);
            double offsetY = t.getSceneY() - (orgSceneY +100);
            newTranslateX = orgTranslateX + offsetX;
            newTranslateY = orgTranslateY + offsetY;
            ((Button)(t.getSource())).setTranslateX(newTranslateX);
            ((Button)(t.getSource())).setTranslateY(newTranslateY);
            
    };
    EventHandler<MouseEvent> ad = 
        (MouseEvent t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            newTranslateX = orgTranslateX + offsetX;
            newTranslateY = orgTranslateY + offsetY;
            ((StackPane)(t.getSource())).setTranslateX(newTranslateX);
            ((StackPane)(t.getSource())).setTranslateY(newTranslateY);
    };
}
