/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

//import com.sun.jdi.connect.spi.Connection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javax.swing.JOptionPane;


/**
 *
 * @author wku-cslab1
 */
public class Assignment extends Application {

    Connection con = null;
    Statement stmt = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
//String applicationTitle="Student list";
    @Override

    public void start(Stage primaryStage) throws SQLException {

        Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();
        Button btn5 = new Button();
        btn2.setText("UPDATE");
        btn.setText("INSERT");
        btn3.setText("VIEW");
        btn4.setText("DISTINCT");
        btn5.setText("select");
        
        
        Label lbl = new Label("SID :");
        Label lbl1 = new Label("STUDID :");
        Label lbl2 = new Label("FIRST NAME :");
        Label lbl3 = new Label("LAST NAME :");
        Label lbl4 = new Label("SECTION :");
        Label lbl5 = new Label("DEPARTMENT :");

        TextField txt = new TextField();
        TextField txt1 = new TextField();
        TextField txt2 = new TextField();
        TextField txt3 = new TextField();
        TextField txt4 = new TextField();
        TextField txt5 = new TextField();

        

        TableView table = new TableView<Table1>();

        //DBConnectionC db = new DBConnectionC();
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBConnectionC db = new DBConnectionC();
                String sql = "Insert into DEPT_TB1 (SID, STUDID, FIRSTNAME, LASTNAME,SECTION, DEPARTMENT ) Values (?,?,?,?,?,?)";
                String lbl = txt.getText();
                String lbl1 = txt1.getText();
                String lbl2 = txt2.getText();
                String lbl3 = txt3.getText();
                String lbl4 = txt4.getText();
                String lbl5 = txt5.getText();
                try {
                    con = db.connMethod();
                    try {
                        pst = con.prepareStatement(sql);
                        pst.setString(1, lbl);
                        pst.setString(2, lbl1);
                        pst.setString(3, lbl2);
                        pst.setString(4, lbl3);
                        pst.setString(5, lbl4);
                        pst.setString(6, lbl5);
                        int i = pst.executeUpdate();
                        if (i == 1) {

                            JOptionPane.showMessageDialog(null, "Data Inserted succsecfully");
                        }
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(Assignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(Assignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBConnectionC db = new DBConnectionC();
                Connection con = null;

                try {

                    con = db.connMethod();
                    String lbl2 = txt2.getText();
                    String txx = "Etsubdink";
                    String sql = "UPDATE DEPT_TB1 SET FIRSTNAME='" + txx + "' WHERE FIRSTNAME='" + lbl2 + "'";

                    PreparedStatement statement = con.prepareStatement(sql);

                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "The Data Is Updated successfully!");
                } catch (Exception ex) {

                    System.out.println(ex.getMessage());
                }
            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {

            private ObservableList<ObservableList> data;
            private TableView tbl;

            @Override
            public void handle(ActionEvent event) {

                DBConnectionC obj1;
                Connection c;
                ResultSet rs;
                data = FXCollections.observableArrayList();
                try {

                    // table.setStyle("-fx-background-color:white; -fx-font-color:black");
                    obj1 = new DBConnectionC();
                    c = obj1.connMethod();
                    String SQL = "SELECT * from DEPT_TB1";
                    rs = c.createStatement().executeQuery(SQL);
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                        table.getColumns().addAll(col);
                        System.out.println("Column [" + i + "] ");

                    }

                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        System.out.println("Row[1]added " + row);
                        data.add(row);

                    }

                    table.setItems(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error ");
                }
            }
        });
        btn4.setOnAction(new EventHandler<ActionEvent>() {

            private ObservableList<ObservableList> data;
            private TableView tbl;

            @Override
            public void handle(ActionEvent event) {

                DBConnectionC obj1;
                Connection c;
                ResultSet rs;
                data = FXCollections.observableArrayList();
                try {

                    
                    obj1 = new DBConnectionC();
                    c = obj1.connMethod();
                    String SQL = "SELECT distinct SECTION from DEPT_TB1";
                    rs = c.createStatement().executeQuery(SQL);
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                        table.getColumns().addAll(col);
                        System.out.println("Column [" + i + "] ");

                    }

                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        System.out.println("Row[1]added " + row);
                        data.add(row);

                    }

                    table.setItems(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error ");
                }
            }
        });
        
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            private ObservableList<ObservableList> data;
            private TableView tbl;

            @Override
            public void handle(ActionEvent event) {

                DBConnectionC obj1;
                Connection c;
                ResultSet rs;
                data = FXCollections.observableArrayList();
                try {

                   
                    obj1 = new DBConnectionC();
                    c = obj1.connMethod();
                    String SQL = "SELECT DEPARTMENT FROM DEPT_TB1 WHERE FIRSTNAME ='Elias' and SECTION ='SecA'";
                    rs = c.createStatement().executeQuery(SQL);
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                        table.getColumns().addAll(col);
                        System.out.println("Column [" + i + "] ");

                    }

                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        System.out.println("Row[1]added " + row);
                        data.add(row);

                    }

                    table.setItems(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error ");
                }
            }
        });

        GridPane root = new GridPane();
        root.addRow(0, lbl, txt);
        root.addRow(1, lbl1, txt1);
        root.addRow(2, lbl2, txt2);
        root.addRow(3, lbl3, txt3);
        root.addRow(4, lbl4, txt4);
        root.addRow(5, lbl5, txt5);
        root.addRow(6, btn, btn2, btn3);
        root.addRow(7, btn4, btn5);
        root.addColumn(2, table);

        root.setHgap(10);
        root.setVgap(10);

        Scene scene = new Scene(root, 800, 340);
        primaryStage.setTitle("List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
