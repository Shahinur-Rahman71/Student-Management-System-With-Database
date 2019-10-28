
package fx24.loginwithdbsuper;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewController {
    double x=0,y=0;
    @FXML
    private JFXTextField v_id;
    @FXML
    private JFXTextField v_name;
    @FXML
    private JFXTextField v_email;
    @FXML
    private JFXTextField v_address;

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
    private void st_information(ActionEvent event) throws IOException {
        
        Parent pane=FXMLLoader.load(getClass().getResource("Student_information.fxml"));
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }

    @FXML
    private void insertbutton(ActionEvent event) {
            String id=v_id.getText();
            String name=v_name.getText();
            String email=v_email.getText();
            String address=v_address.getText();
            
            Connection con=Dbconnect.getInstance().getConnection();
        
        try {
            
            
            Statement statement=con.createStatement();
            String sql="insert into student values('"+id+"','"+name+"','"+email+"','"+address+"')";
           
            if(id.isEmpty()||name.isEmpty()||email.isEmpty()||address.isEmpty()){
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialogue");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fillup all the fill");
                    alert.showAndWait();
            }else{
                statement.executeUpdate(sql);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialogue");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully done");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                       
                        v_id.clear();
                        v_name.clear();
                        v_email.clear();
                        v_address.clear();
                    } 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
