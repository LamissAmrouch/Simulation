package sample;

import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import jdk.nashorn.internal.objects.annotations.Function;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public Double ROCalculer;
    public int nbserver ;
    public ArrayList ProbStationnaire = new ArrayList();


    @FXML
    public TextField IDLamda = null;
    public TextField IDMu = null;
    public Button MMS;
    public Button calculmms;
    public VBox VBOX2;
    public TextField NbService;
    public VBox VBox4;
    public Button Plus;
    public Button reinitialisation;
    public GridPane Grid1;


    @FXML
    public void CalculMMS(ActionEvent event){

        calculmms.setDisable(false);
        IDLamda.setDisable(false);
        IDMu.setDisable(false);
        NbService.setDisable(false);


            calculmms.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    Label remarq = new Label();
                    nbserver = Integer.valueOf(NbService.getText());
                    Double lamda = Double.valueOf(IDLamda.getText());

                    /****************  tester si le regime est stationnaire *****************/
                    Double denomRO = Double.valueOf(IDMu.getText()) * Double.valueOf(NbService.getText());
                    Double inverse = Math.pow(denomRO, -1);
                    ROCalculer = inverse * lamda;
                    DecimalFormat df = new DecimalFormat("0.000000");

                        Label RO = new Label();
                        RO.setText(String.valueOf(df.format(ROCalculer)));
                        RO.setFont(new Font("Calibri", 18.0));
                        RO.setTextFill(Paint.valueOf("#009c6a"));
                        RO.setPadding(new Insets(10, 30, 0, 30));
                        VBOX2.getChildren().add(RO);

                    if (ROCalculer < 1) {
                        remarq.setText("R.Stationnaire");
                        remarq.setFont(new Font("Calibri", 18.0));
                        remarq.setTextFill(Paint.valueOf("#0cab3e"));
                        remarq.setPadding(new Insets(0, 0, 0, 5));
                        VBOX2.getChildren().add(remarq);

                        /************ recuperer et afficher les probas stationnaire  ************/
                        ProbStationnaire = CalculProbaStationnaire();
                        /**** P0 ****/
                        Label p0 = new Label();
                        p0.setText(String.valueOf(df.format(ProbStationnaire.get(0))));
                        p0.setTextFill(Paint.valueOf("#009c6a"));
                        p0.setFont(new Font("Calibri", 18.0));
                        p0.setPadding(new Insets(4, 30, 0, 30));
                        VBox4.getChildren().add(p0);

                        /**** P1 ****/
                        Label p1 = new Label();
                        p1.setText(String.valueOf(df.format(ProbStationnaire.get(1))));
                        p1.setTextFill(Paint.valueOf("#009c6a"));
                        p1.setFont(new Font("Calibri", 18.0));
                        p1.setPadding(new Insets(20, 30, 0, 30));
                        VBox4.getChildren().add(p1);

                        /**** P2 ****/
                        Label p2 = new Label();
                        p2.setText(String.valueOf(df.format(ProbStationnaire.get(2))));
                        p2.setTextFill(Paint.valueOf("#009c6a"));
                        p2.setFont(new Font("Calibri", 18.0));
                        p2.setPadding(new Insets(20, 30, 0, 30));
                        VBox4.getChildren().add(p2);

                        /**** P3 ****/
                        Label p3 = new Label();
                        p3.setText(String.valueOf(df.format(ProbStationnaire.get(3))));
                        p3.setTextFill(Paint.valueOf("#009c6a"));
                        p3.setFont(new Font("Calibri", 18.0));
                        p3.setPadding(new Insets(19, 30, 0, 30));
                        VBox4.getChildren().add(p3);

                        /**** P4 ****/
                        Label p4 = new Label();
                        p4.setText(String.valueOf(df.format(ProbStationnaire.get(4))));
                        p4.setTextFill(Paint.valueOf("#009c6a"));
                        p4.setFont(new Font("Calibri", 18.0));
                        p4.setPadding(new Insets(19, 30, 0, 30));
                        VBox4.getChildren().add(p4);

                        /**** P5 ****/
                        Label p5 = new Label();
                        p5.setText(String.valueOf(df.format(ProbStationnaire.get(5))));
                        p5.setTextFill(Paint.valueOf("#009c6a"));
                        p5.setFont(new Font("Calibri", 18.0));
                        p5.setPadding(new Insets(19, 30, 0, 30));
                        VBox4.getChildren().add(p5);

                        Plus.setDisable(false);

                        /*************  recuperer et afficher les moyens et les temps moyens ***************/
                        /******  NF ******/
                        Double PS = (Double) ProbStationnaire.get(nbserver);
                        System.out.println("doubelllle    :   " + PS);
                        Label nf = new Label();
                        Double NF = CalculNf(ROCalculer,PS);
                        nf.setText(String.valueOf(df.format(NF)));
                        nf.setTextFill(Paint.valueOf("#009c6a"));
                        nf.setFont(new Font("Calibri", 18.0));
                        nf.setPadding(new Insets(12, 30, 0, 30));
                        VBOX2.getChildren().add(nf);

                        /****** TF ******/
                        Label tf = new Label();
                        Double TF = CalculTf(NF,lamda);
                        tf.setText(String.valueOf(df.format(TF)));
                        tf.setTextFill(Paint.valueOf("#009c6a"));
                        tf.setFont(new Font("Calibri", 18.0));
                        tf.setPadding(new Insets(15, 30, 0, 30));
                        VBOX2.getChildren().add(tf);

                        /****** NS ******/
                        Label ns = new Label();
                        Double NS = CalculNS(ROCalculer,PS,nbserver);
                        ns.setText(String.valueOf(df.format(NS)));
                        ns.setTextFill(Paint.valueOf("#009c6a"));
                        ns.setFont(new Font("Calibri", 18.0));
                        ns.setPadding(new Insets(15, 30, 0, 30));
                        VBOX2.getChildren().add(ns);

                        /****** TS ******/
                        Label ts = new Label();
                        Double TS = CalculTs(NS,lamda);
                        ts.setText(String.valueOf(df.format(TS)));
                        ts.setTextFill(Paint.valueOf("#009c6a"));
                        ts.setFont(new Font("Calibri", 18.0));
                        ts.setPadding(new Insets(15, 30, 0, 30));
                        VBOX2.getChildren().add(ts);


                    } else {


                        remarq.setText("R.N.Stationnaire");
                        remarq.setFont(new Font("Calibri", 18.0));
                        remarq.setTextFill(Paint.valueOf("#f40e0e"));
                        remarq.setPadding(new Insets(0, 0, 0, 5));
                        VBOX2.getChildren().add(remarq);

                    }
                    int i = desableBoutton();

                }

            });

    }

    @Function
    public ArrayList CalculProbaStationnaire(){

        ArrayList ResltProba = new ArrayList();
        int nbserv = Integer.valueOf(NbService.getText());
        Double Mu = Double.valueOf(IDMu.getText());
        System.out.println("Mu = "+Mu );
        Double lamda = Double.valueOf(IDLamda.getText());
        System.out.println("lamda = "+lamda);

          /***************************  Calculer P0 ******************************/
            Double res0 = 0.0;
            double lamdadivMU = lamda * Math.pow(Mu,-1) ;
        System.out.println("lamdasSurMU = "+lamdadivMU);
            for (int k = 0 ; k < nbserv ; k++ ){
                res0 = res0 + ( Math.pow(fact(k),-1) * Math.pow(lamdadivMU , k ));
            }
            Double res1 = 0.0;
            Double SUdivSULamda = (nbserver * Mu) * Math.pow(((nbserver * Mu) - lamda),-1);
            res1 = Math.pow(fact(nbserv),-1) * Math.pow(lamdadivMU , nbserv ) * SUdivSULamda ;
            Double P0 = Math.pow((res0 + res1) , -1 );
            System.out.println("p0 = "+P0);
            ResltProba.add(P0);

            /***************** calculer les 5 premiers proba ***********************/
            Double Pk;
            for (int k=1 ; k <= 5 ; k++){  /**  1 <= k <= S  **/
                if(k <= nbserv){
                    Pk = ( Math.pow(fact(k),-1) * Math.pow(lamdadivMU , k )) * P0;
                }else {
                    Pk = ( Math.pow(fact(nbserv),-1) * Math.pow(lamdadivMU , k )
                            * Math.pow(Math.pow(nbserv,(k-nbserv)) , -1 )) * P0;
                }
                ResltProba.add(Pk);
            System.out.println("P"+k +"=" +Pk);
            }

           /******************* ce tableau contient les proba stationnaire ************/
            return ResltProba;
    }


    public int fact(int n){

        if(n == 0){ return 1; }
        else {
            int res = 1;
            for (int i = 1; i <= n; i++) {
                res = res * i;
            }
            return res ;
        }

    }

    @Function
    public Double CalculNf(Double RO , Double Ps){
        Double Nf = 0.0;
        Nf = RO * Ps * Math.pow((Math.pow((1-RO),2)),-1);

        return Nf;
    }

    @Function
    public Double CalculNS(Double RO , Double Ps , int S){
        Double Ns = null;
        Ns = ( RO * Ps * Math.pow(Math.pow((1-RO),2),-1) ) + (S * RO);

        return Ns;
    }

    @Function
    public Double CalculTf(Double nf,Double lamda){
        return (nf * Math.pow(lamda,-1));
    }


    @Function
    public Double CalculTs(Double ns,Double lamda){
        return (ns * Math.pow(lamda,-1));
    }




    @FXML
    public void Reinitialiser(){

        VBox4.getChildren().clear();
        VBOX2.getChildren().clear();
        //VBOX2.getChildren().add(IDLamda);
        //VBOX2.getChildren().add(IDMu);
        IDLamda.clear();
        IDMu.clear();
        NbService.clear();
        calculmms.setDisable(true);
        MMS.setDisable(false);
        Plus.setDisable(true);
        IDLamda.setDisable(true);
        IDMu.setDisable(true);
        NbService.setDisable(true);


    }

    @Function
    public int desableBoutton(){
        calculmms.setDisable(true);
        MMS.setDisable(true);
        reinitialisation.setDisable(false);

        return 0;
    }

    @FXML
    public void PlusDeProbas () {
        int nbserv = Integer.valueOf(NbService.getText());
        Double Mu = Double.valueOf(IDMu.getText());
        System.out.println("Mu = "+Mu );
        Double lamda = Double.valueOf(IDLamda.getText());
        System.out.println("lamda = "+lamda);
        double lamdadivMU = lamda * Math.pow(Mu,-1) ;

        ArrayList<Double> probass = CalculProbaStationnaire();

        Double Pk;
        for (int k=5 ; k <= 20 ; k++){                   /**  1 <= k <= S  **/
            if(k <= nbserv){
                Pk = ( Math.pow(fact(k),-1) * Math.pow(lamdadivMU , k )) * probass.get(0);
            }else {
                Pk = ( Math.pow(fact(nbserv),-1) * Math.pow(lamdadivMU , k )
                        * Math.pow(Math.pow(nbserv,(k-nbserv)) , -1 )) * probass.get(0);
            }
            probass.add(Pk);
            System.out.println("P"+k +"=" +Pk);
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Distribution Stationnaire");
        alert.setHeaderText(null);

            alert.setContentText("P0 = "+probass.get(0)+ "\n"+
                                 "P1 = "+probass.get(1)+ "\n"+
                                 "P2 = "+probass.get(2)+ "\n"+
                                 "P3 = "+probass.get(3)+ "\n"+
                                 "P4 = "+probass.get(4)+ "\n"+
                                 "P5 = "+probass.get(5)+ "\n"+
                                "P6 = "+probass.get(6)+ "\n"+
                                "P7 = "+probass.get(7)+ "\n"+
                                "P8 = "+probass.get(8)+ "\n"+
                                "P9 = "+probass.get(9)+ "\n"+
                                "P10 = "+probass.get(10)+ "\n"+
                                "P11 = "+probass.get(11)+ "\n"+
                                "P12 = "+probass.get(12)+ "\n"+
                                "P13 = "+probass.get(13)+ "\n"+
                                "P14 = "+probass.get(14)+ "\n"+
                                "P15 = "+probass.get(15)+ "\n"+
                                "P16 = "+probass.get(16)+ "\n"+
                                "P17 = "+probass.get(17)+ "\n"+
                                "P18 = "+probass.get(18)+ "\n"+
                                "P19 = "+probass.get(19)+ "\n"+
                                "P20 = "+probass.get(20)+ "\n"
            );

        alert.showAndWait();

        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        calculmms.setDisable(true);
        IDLamda.setDisable(true);
        IDMu.setDisable(true);
        NbService.setDisable(true);
        Plus.setDisable(true);
        reinitialisation.setDisable(true);

    }
}
