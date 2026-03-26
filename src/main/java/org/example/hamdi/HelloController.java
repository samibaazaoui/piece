package org.example.hamdi;
import org.example.hamdi.Admin ;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController  {
private Connection connect;
private PreparedStatement ps;
private ResultSet rs;
private double x=0;
private double y=0;
    @FXML
   private Label welcomeText;

    @FXML
    private Button agentbtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button adminBtn;
    Admin admin = new Admin();
    public void login() {
        String sql = "select * from admin where username=? and password=?";
        connect = database.connectDb();
        try {
            ps=connect.prepareStatement(sql);
            ps.setString(1, username.getText());
            ps.setString(2, password.getText());
            rs=ps.executeQuery();
            Alert alert ;
            if(username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setContentText("SVP Entrer Tout Les Donnes Demandés");
                alert.showAndWait();
            }
            else {
                if(rs.next()) {
                    data.username=rs.getString("username");

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setContentText("connexion avec succes!");
                    alert.showAndWait();
                    adminBtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    //stage.setFullScreen(true);
                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.setScene(scene);


                    stage.show();

                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText("Votre Nom_Utulisateur ou Mot de passe incorrect");
                    alert.showAndWait();


                }

            }

        }

        catch (Exception e){
            e.printStackTrace();
        }


    }


    public void agentBtn(ActionEvent event) {
        try {



            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("agent.fxml"));



            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Agent");
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendMail(ActionEvent event) {

        // 📩 contenu du "champ texte" داخل الإيميل
        String contenuEmail = """
            Rapport automatique

            Capital : 12 500 DT
            Vente du jour : 850 DT
            Bénéfice : 320 DT

            Cordialement,
            Application JavaFX
        """;

        final String username = "baazaouisami136@gmail.com";
        final String password = "ENOF HLKD PBDK QHVH"; // App Password Gmail
        String destinataire = "Sami.Baazaoui@esprit.tn";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "BAAZAOUI AUTO PIECE"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinataire)
            );
            message.setSubject("📊 Rapport automatique");

            // 🧾 النص يظهر كـ "champ" داخل الإيميل
            message.setText(contenuEmail);

            Transport.send(message);

            alert("Succès", "Email envoyé avec succès ✅");

        } catch (Exception e) {
            e.printStackTrace();
            alert("Erreur", "Échec de l'envoi ❌");
        }
    }
    private void alert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}