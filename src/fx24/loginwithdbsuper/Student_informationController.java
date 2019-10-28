
package fx24.loginwithdbsuper;

import com.jfoenix.controls.JFXTextField;
import static fx24.loginwithdbsuper.Dbconnect.connection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Student_informationController implements Initializable {

    @FXML
    private TableView<StudentTable> tableview;
    @FXML
    private TableColumn<StudentTable, Integer> t_id;
    @FXML
    private TableColumn<StudentTable, String> t_name;
    @FXML
    private TableColumn<StudentTable, String> t_email;
    @FXML
    private TableColumn<StudentTable, String> e_address;

    ObservableList<StudentTable> list=FXCollections.observableArrayList();
    @FXML
    private JFXTextField searchid;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection connect=Dbconnect.getInstance().getConnection();
        tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {

            Statement state=connect.createStatement();
            ResultSet res=state.executeQuery("select * from student");
            while(res.next()){
                list.add(new StudentTable(res.getInt("ID"),res.getString("Name"),res.getString("Email"),res.getString("Address")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_informationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        t_id.setCellValueFactory(new PropertyValueFactory<> ("id"));
        t_name.setCellValueFactory(new PropertyValueFactory<> ("name"));
        t_email.setCellValueFactory(new PropertyValueFactory<> ("emal"));
        e_address.setCellValueFactory(new PropertyValueFactory<> ("address"));
        
        tableview.setItems(list);
    }    

    double x=0,y=0;
    @FXML
    private void dragged(MouseEvent event) {
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
    }

    @FXML
    private void pressed(MouseEvent event) {
        x=event.getSceneX();
        y=event.getSceneY();
    }

    @FXML
    private void homebutton(ActionEvent event) throws IOException {
        Parent pane=FXMLLoader.load(getClass().getResource("View.fxml"));
        Node node=(Node) event.getSource();
        Stage stge=(Stage) node.getScene().getWindow();
        stge.setScene(new Scene(pane));
        
    }

    @FXML
    private void searchclick(MouseEvent event) {
        list.clear();
        Connection connect=Dbconnect.getInstance().getConnection();
        try {

            Statement state=connect.createStatement();
            ResultSet res=state.executeQuery("select * from student where ID='"+searchid.getText()+"' or Name='"+searchid.getText()+"'");
            while(res.next()){
                list.add(new StudentTable(res.getInt("ID"),res.getString("Name"),res.getString("Email"),res.getString("Address")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_informationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        t_id.setCellValueFactory(new PropertyValueFactory<> ("id"));
        t_name.setCellValueFactory(new PropertyValueFactory<> ("name"));
        t_email.setCellValueFactory(new PropertyValueFactory<> ("emal"));
        e_address.setCellValueFactory(new PropertyValueFactory<> ("address"));
        
        tableview.setItems(list);
    }

    @FXML
    private void deletebutton(ActionEvent event) throws SQLException {
        
        Connection connect=Dbconnect.getInstance().getConnection();
        try{
            StudentTable select=(StudentTable) tableview.getSelectionModel().getSelectedItem();
            String query="delete from student where Email=?";
            //PreparedStatement preparedStatement=new PreparedStatement();
            PreparedStatement preparedStatement=connect.prepareStatement(query);
            preparedStatement.setString(1, select.getEmal());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            loaddata();
            
                    
        }catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialogue");
            alert.setHeaderText(null);
            alert.setContentText("Please Select any row");
            alert.showAndWait();
        }
        
        
    }
    
    public void loaddata(){
        
        Connection connect=Dbconnect.getInstance().getConnection();
        try{
            list.clear();
            Statement state=connect.createStatement();
            ResultSet res=state.executeQuery("select * from student");
            while(res.next()){
                list.add(new StudentTable(res.getInt("ID"),res.getString("Name"),res.getString("Email"),res.getString("Address")));
            }
            tableview.setItems(list);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialogue");
            alert.setHeaderText(null);
            alert.setContentText("Please Select any row");
            alert.showAndWait();
        }
//        
        
        
    }
    
    
    
}
