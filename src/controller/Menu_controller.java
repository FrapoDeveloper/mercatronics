package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu_controller implements Initializable {


    @FXML
    private ImageView img_calculator;

    @FXML
    private Label email_user;
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    public  void recibeParametros(String  user){
        email_user.setText(user);
    }




}
