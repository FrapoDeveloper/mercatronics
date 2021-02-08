package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_controller implements Initializable {
    @FXML
    private Button Boton_saludo;
    @FXML
    private ImageView img_login;
    @FXML
    private TextField tf_email;
    @FXML
    private CheckBox cb_conditions;

    @FXML private PasswordField pf_password;
    Login_controller login_controller;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Image image = new Image("resources/welcome.png");
      img_login.setImage(image);
    }    

    public boolean validarEmail(String email, String pass){
        Pattern pattern_email = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather_email = pattern_email.matcher(email);
        Pattern pattern_password = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
        Matcher matcher_password = pattern_password.matcher(pass);
        if (mather_email.find() == true && matcher_password.find() ==true ) {
            System.out.println("Bien");
            return true;
        } else {
            return false;
        }

    }


    @FXML
    private void click(ActionEvent event) throws SQLException {

        if(tf_email.getText().length() ==0 || pf_password.getText().length() ==0  ){
            JOptionPane.showMessageDialog(null,"Porfavor llenar todos los campos.");

        }else if(cb_conditions.isSelected() == true){
            if(validarEmail(tf_email.getText(),pf_password.getText()) == true){
                try{
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/mercatronics","root","");
                    PreparedStatement ps = cn.prepareStatement("SELECT  * FROM usuarios WHERE pass_usuario = ? AND email_usuario = ?");
                    ps.setString(1, pf_password.getText());
                    ps.setString(2,tf_email.getText());

                    ResultSet r = ps.executeQuery();
                    if(r.next()){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Menu_view.fxml"));
                        try {
                            Parent root  = loader.load();
                            Menu_controller controller = loader.getController();
                            controller.recibeParametros(tf_email.getText());
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setScene(scene);
                            stage.showAndWait();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }

        }else{
                JOptionPane.showMessageDialog(null,"Acepte los terminos y condiciones.");
            }
    }


    
    
}
