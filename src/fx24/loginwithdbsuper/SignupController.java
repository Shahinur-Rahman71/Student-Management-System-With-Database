
package fx24.loginwithdbsuper;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class SignupController implements Initializable{

    @FXML
    private JFXTextField s_username;
    @FXML
    private JFXTextField s_email;
    @FXML
    private JFXPasswordField s_password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
    }

    @FXML
    private void signupbutton(MouseEvent event) throws SQLException {
        
        Connection connect=Dbconnect.getInstance().getConnection();
        try{

                Statement statement=connect.createStatement();
                String username=s_username.getText();
                String email=s_email.getText();
                String password=s_password.getText();

                String sql="insert into login values('"+username+"','"+email+"','"+password+"')";
                
                if(username.isEmpty()||email.isEmpty()||password.isEmpty()){
                    //System.out.println("User Registered");
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
                    alert.setContentText("You account has created");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){

                            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                            Node node=(Node) event.getSource();

                            Stage stage=(Stage) node.getScene().getWindow();
                            stage.setScene(new Scene(root));
                    } 
                }
         }
        catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialogue");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid password");
                    alert.showAndWait();
         }
        
    }

    @FXML
    private void loginbutton(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Node node=(Node) event.getSource();
        
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }
    
    double x=0,y=0;
    @FXML
    private void pressed(MouseEvent event) {
        x=event.getSceneX();
        y=event.getSceneY();
    }

    @FXML
    private void dragged(MouseEvent event) {
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
    }

   
    
}
