
package fx24.loginwithdbsuper;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginController implements Initializable {

    @FXML
    private JFXTextField l_userfield;
    @FXML
    private JFXPasswordField l_passfield;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void l_loginbutton(MouseEvent event) throws SQLException, IOException {
        
        String user=l_userfield.getText();
        String pas=l_passfield.getText();
        
        Connection con=Dbconnect.getInstance().getConnection();
        try{
            Statement state=con.createStatement();
            ResultSet res=state.executeQuery("select * from login where Name='"+user+"' and Password='"+pas+"'");
            if(res.next()){
                Parent pane=FXMLLoader.load(getClass().getResource("View.fxml"));
                Node node=(Node) event.getSource();
                Stage stage=(Stage) node.getScene().getWindow();
                stage.setScene(new Scene(pane));
            }if(user.isEmpty()||pas.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialogue");
                alert.setHeaderText(null);
                alert.setContentText("Please fillup all the fill");
                alert.showAndWait();
            }
                
            
            
        }catch(Exception e){
             
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialogue");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            
        }
           
   
    }

    @FXML
    private void l_signup(MouseEvent event) throws IOException {
        
        Parent pane=FXMLLoader.load(getClass().getResource("Signup.fxml"));
        Node node=(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setScene(new Scene(pane));
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
    
}
