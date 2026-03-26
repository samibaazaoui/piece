package org.example.hamdi;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

// ======================
// Gestion du temps pour scheduler
// ======================
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Duration;

// ======================
// Scheduler automatique
// ======================
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.time.*;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableCell;
import javafx.util.Callback;

import java.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Character.getType;

public class Agent implements Initializable {
    private double x=0 ;
    private double y=0 ;
    @FXML
    private Button logout;
    @FXML
    private Button inventory_addBtn;

    @FXML
    private Button inventory_clearBtn;

    @FXML
    private TableColumn<productdata,String> inventory_col_Type;

    @FXML
    private TableColumn<productdata,String> inventory_col_date;

    @FXML
    private TableColumn<productdata,String> vente_col_credit;

    @FXML
    private TableColumn<productdata,String> inventory_col_idProduct;

    @FXML
    private TableColumn<productdata,String> inventory_col_nameProduct;

    @FXML
    private TableColumn<productdata,String> inventory_col_priceBuy_u;

    @FXML
    private TableColumn<productdata,String> inventory_col_priceSell_u;



    @FXML
    private TableColumn<productdata,String> inventory_col_stock;
    @FXML
    private TableColumn<productdata,String> inventory_col_status;
    private int restant = 0;

    @FXML
    private Button inventory_deleteBtn;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private TextField inventory_idProduct;
    @FXML
    private TextField vente_search;

    @FXML
    private TextField inventory_nameProduct;

    @FXML
    private AreaChart<String, Number> Analyse_chart;

    @FXML
    private TextField inventory_priceBuy_u;
    @FXML
    private TextField historic_rem;
    @FXML
    private ComboBox<String> charge_comboDesc;

    @FXML
    private TextField historic_tva;

    @FXML
    private TextField inventory_priceSell_u;

    @FXML
    private TextField Analyse_idProduct;



    @FXML
    private TextField Analyse_nom;

    @FXML
    private TextField charge_search;
    @FXML
    private Label total;
    @FXML
    private AnchorPane vente_form;


    @FXML
    private Label Analyse_priceRem;
    @FXML
    private Label Analyse_price_buy;


    @FXML
    private Label Analyse_priceTva;

    @FXML
    private Label Analyse_stock;
    @FXML
    private Label total1;


    @FXML
    private Button analyse_analyseBtn;

    @FXML
    private ComboBox<String> inventory_status;

    @FXML
    private TextField inventory_stock;

    @FXML
    private TableView<productdata> inventory_tableview;

    @FXML
    private ComboBox<String> inventory_typeProduct;

    @FXML
    private Button inventory_updateBtn;
    @FXML
    private TableColumn<productdata,String> inventory_col_marque;
    @FXML
    private TextField inventory_marque;
    @FXML
    private TableColumn<productdata,String> historic_col_date;
    @FXML
    private AnchorPane charge_form;
    @FXML
    private ComboBox<String> Analyse_combo_month1;
    @FXML
    private ComboBox<Integer> Analyse_combo_year2;

    @FXML
    private ComboBox<Integer> Analyse_combo_year3;

    @FXML
    private Label Analyse_tot_rem;

    @FXML
    private Label Analyse_tot_tva;
    @FXML
    private Label Analyse_tot_rem1;
    @FXML
    private Label Analyse_tot_tva1;

    @FXML
    private TableColumn<productdata,String> historic_col_idProduct;

    @FXML
    private TableColumn<productdata,String> historic_col_marque;

    @FXML
    private TableColumn<productdata,String> historic_col_nameProduct;

    @FXML
    private TableColumn<productdata,String> historic_col_priceBuy;

    @FXML
    private TableColumn<productdata,String> historic_col_priceSell;

    @FXML
    private TableColumn<productdata,String> historic_col_priceTotal;
    @FXML
    private ComboBox<String> analyse_fournisseur;
    @FXML
    private TableColumn<productdata,String> rec_col_dateR;

    @FXML
    private TableColumn<productdata,String> rec_col_dateV;

    @FXML
    private TableColumn<productdata,String> rec_col_id;
    @FXML
    private TableView<productdata> rec_tableview;

    @FXML
    private TableColumn<productdata,String> rec_col_marque;

    @FXML
    private TableColumn<productdata, String> rec_col_nom;

    @FXML
    private TableColumn<productdata,String> rec_col_price;

    @FXML
    private TableColumn<productdata,String> rec_col_qt;
    @FXML
    private TextField rec_id;
    @FXML
    private DatePicker rec_dateV;

    @FXML
    private ComboBox<String> rec_marque;

    @FXML
    private TextField rec_nom;

    @FXML
    private TextField rec_price;
    @FXML
    private TextField rec_search;

    @FXML
    private Spinner<Integer> rec_qt;



    @FXML
    private TableColumn<productdata,String> historic_col_stockBuy;

    @FXML
    private TableColumn<productdata,String> historic_col_type;

    @FXML
    private ComboBox<Integer> Analyse_combo_year;
    @FXML
    private ComboBox<Integer> Analyse_combo_year1;
    @FXML
    private ComboBox<String> Analyse_combo_month;
    @FXML
    private Label Analyse_four_rem1;

    @FXML
    private Label Analyse_four_tva1;

    @FXML
    private AnchorPane historic_form;
    @FXML
    private Button inventory_btn;
    @FXML
    private Button historic_btn;
    @FXML
    private Button historic_deleteBtn;
    @FXML
    private TableColumn<productdata, String> historic_col_priceRemise;
    @FXML
    private TableColumn<productdata, String> historic_col_priceTva;
    @FXML
    private TextField inventory_rem;
    @FXML
    private TextField inventory_tva;
    @FXML
    private TextField historic_search;

    @FXML
    private Button analyse_btn;

    @FXML
    private AnchorPane analyse_form;

    @FXML
    private Label Analyse_four_buy;

    @FXML
    private Label Analyse_four_rem;

    @FXML
    private Label Analyse_four_tva;




    @FXML
    private TableView<productdata> historic_tableview;

    @FXML
    private ComboBox<String> Analyse_f;
    @FXML
    private TableView<productdata> vente_tableview;

    @FXML
    private Button ventBtn;

    @FXML
    private ComboBox<String> Analyse_marque;
    @FXML
    private AnchorPane main_form;

    @FXML
    private TableColumn<productdata,String> vente_col_date;

    @FXML
    private TableColumn<productdata,String> vente_col_idProd;

    @FXML
    private TableColumn<productdata,String> vente_col_marque;

    @FXML
    private TableColumn<productdata,String> vente_col_nom;

    @FXML
    private TableColumn<productdata,String> vente_col_price_e;

    @FXML
    private TableColumn<productdata,String> vente_col_price_r;

    @FXML
    private TableColumn<productdata,String> vente_col_stock;

    @FXML
    private TableColumn<productdata,String> vente_col_type;

    @FXML
    private Button vente_deleteBtn;


    @FXML
    private TextField vente_idVente;

    @FXML
    private ComboBox<String> vente_marque;

    @FXML
    private TextField vente_nom;

    @FXML
    private TextField vente_price_e;

    @FXML
    private TextField vente_price_r;

    @FXML
    private Spinner<Integer> vente_qty;
    @FXML
    private TableColumn<productdata,String> charge_col_date;

    @FXML
    private TableColumn<productdata,String> charge_col_description;

    @FXML
    private TableColumn<productdata,String> charge_col_num;

    @FXML
    private TableColumn<productdata,String> charge_col_price;



    @FXML
    private TextArea charge_description;
    @FXML
    private Button chargeBtn;

    @FXML
    private TextField charge_price;
    @FXML
    private TextField inventory_search2;

    @FXML
    private TableView<productdata> charge_tableview;

    @FXML
    private Button recBtn;

    @FXML
    private AnchorPane rec_form;



    private Alert alert ;
    private PreparedStatement prepare ;
    private Statement statement ;
    private Connection connection ;
    private ResultSet result ;
    public ObservableList<productdata> recListData() {

        ObservableList<productdata> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM rec";

        connection = database.connectDb();

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            productdata prodD;

            while (result.next()) {
                prodD =new productdata(result.getInt("id"),
                        result.getString("rec_id"),
                        result.getString("rec_name"),
                        result.getString("rec_marque"),
                        result.getInt("rec_qt"),
                        result.getDouble("rec_price"),
                        result.getDate("rec_dateV"),
                        result.getDate("rec_dateR"));
                listData.add(prodD);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connection != null) connection.close(); // ❗ مهم جداً
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return listData;
    }

    private ObservableList<productdata> recList;

    public void recShowListData () {
        recList = recListData();

        rec_col_id.setCellValueFactory(new PropertyValueFactory<>("productId"));
        rec_col_nom.setCellValueFactory(new PropertyValueFactory<>("productName"));
        rec_col_marque.setCellValueFactory(new PropertyValueFactory<>("productMarque"));
        rec_col_qt.setCellValueFactory(new PropertyValueFactory<>("qt"));
        rec_col_price.setCellValueFactory(new PropertyValueFactory<>("recPrice"));
        rec_col_dateV.setCellValueFactory(new PropertyValueFactory<>("dateV"));
        rec_col_dateR.setCellValueFactory(new PropertyValueFactory<>("dateR"));
        rec_tableview.setItems(recList);

    }
    public void remplirMarquesEtNomParId3(String idProduit) {
        String sqlMarques = "SELECT DISTINCT prod_marque FROM vente WHERE prod_id = ?";
        String sqlNom = "SELECT prod_name FROM vente WHERE prod_id = ?";

        connection = database.connectDb();

        try {
            // Remplir marques
            PreparedStatement prepareMarques = connection.prepareStatement(sqlMarques);
            prepareMarques.setString(1, idProduit);
            ResultSet resultMarques = prepareMarques.executeQuery();

            Set<String> marques = new HashSet<>();
            while (resultMarques.next()) {
                marques.add(resultMarques.getString("prod_marque"));
            }

            rec_marque.getItems().clear();
           rec_marque.getItems().addAll(marques);
            if (!marques.isEmpty()) {
                Platform.runLater(() -> {
                    if (!marques.isEmpty()) {
                       rec_marque.getSelectionModel().selectFirst();
                    }
                });}

            // Remplir nom
            PreparedStatement prepareNom = connection.prepareStatement(sqlNom);
            prepareNom.setString(1, idProduit);
            ResultSet resultNom = prepareNom.executeQuery();


            if (resultNom.next()) {
                rec_nom.setText(resultNom.getString("prod_name"));
            } else {
                //rec_nom.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connection != null) connection.close(); // ❗ مهم جداً
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
    public void remplirIdEtMarquesParNom3(String nomProduit) {
        String sql = "SELECT prod_id, prod_marque FROM vente WHERE prod_name = ?";
        connection = database.connectDb();

        try {
            PreparedStatement prepare = connection.prepareStatement(sql);
            prepare.setString(1, nomProduit);
            ResultSet result = prepare.executeQuery();

            Set<String> marques = new HashSet<>();
            String idTrouve = null;

            while (result.next()) {
                if (idTrouve == null)
                    idTrouve = result.getString("prod_id");
                marques.add(result.getString("prod_marque"));
            }

            if (idTrouve != null) {
               rec_id.setText(idTrouve);
                rec_marque.getItems().clear();
                rec_marque.getItems().addAll(marques);
                if (!marques.isEmpty()) {
                    Platform.runLater(() -> {
                        if (!marques.isEmpty()) {
                            rec_marque.getSelectionModel().selectFirst();
                        }
                    });}
            } else {
                //vente_idVente.setText("");
                ObservableList items = rec_marque.getItems();
                items.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connection != null) connection.close(); // ❗ مهم جداً
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    public void recComboData(){
        rec_id.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                remplirMarquesEtNomParId3(newValue);
            } else {
                rec_marque.getItems().clear();
                //rec_nom.clear();
            }
        });
        rec_nom.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.trim().isEmpty()) {
                remplirIdEtMarquesParNom3(newVal);
            } else {
                //rec_id.clear();
                rec_marque.getItems().clear();
            }
        });
    }
    public void spinQty2(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 1);
        rec_qt.setValueFactory(valueFactory);
    }
    public void recAddBtn() {
        if (rec_id.getText().isEmpty()
                || rec_marque.getSelectionModel().getSelectedItem() == null
                || rec_qt.getValue() == null || rec_qt.getValue() == 0
                || rec_price.getText() == null || rec_price.getText().isEmpty()
        )
        {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("SVP entrer tout les données correctement");
            alert.showAndWait();
        } else {
            connection = database.connectDb();

            try {

                // 1. vérifier si la quantité totale récupérée (ancienne + nouvelle) dépasse qty_v
                String checkStock = "SELECT qty_v FROM adminevente_backup WHERE prod_id = ? AND prod_marque = ?";
                prepare = connection.prepareStatement(checkStock);
                prepare.setString(1, rec_id.getText());
                prepare.setString(2, (String) rec_marque.getSelectionModel().getSelectedItem());
                result = prepare.executeQuery();

                int qtyVendue = 0;

                if (result.next()) {
                    qtyVendue = result.getInt("qty_v");
                }

// 🔄 Nouveau : calculer quantité déjà récupérée
                String checkRecTotal = "SELECT SUM(rec_qt) AS total_rec FROM rec WHERE rec_id = ? AND rec_marque = ?";
                prepare = connection.prepareStatement(checkRecTotal);
                prepare.setString(1, rec_id.getText());
                prepare.setString(2, (String) rec_marque.getSelectionModel().getSelectedItem());
                result = prepare.executeQuery();

                int qtyDejaRecuperee = 0;

                if (result.next()) {
                    qtyDejaRecuperee = result.getInt("total_rec");
                }

                int totalApresAjout = qtyDejaRecuperee + rec_qt.getValue();

                if (totalApresAjout > qtyVendue) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de quantité");
                    alert.setHeaderText(null);
                    alert.setContentText("La quantité récupérée totale dépasse la quantité vendue !");
                    alert.showAndWait();
                    return;
                }
                // 3. Insertion dans la table vente
                  String insertData = "INSERT INTO rec"
                        + "(rec_id, rec_name, rec_marque, rec_qt, rec_price,rec_dateV,rec_dateR) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";

                prepare = connection.prepareStatement(insertData);

// القيم العادية
                LocalDate dateV = rec_dateV.getValue();
// Récupérer la date actuelle
                LocalDate today = LocalDate.now();

// Vérifier si dateV est supérieure à today
                if (dateV != null && (dateV.isBefore(today) || dateV.isEqual(today))) {
                    prepare.setString(1, rec_id.getText());
                    prepare.setString(2, rec_nom.getText());
                    prepare.setString(3, (String) rec_marque.getSelectionModel().getSelectedItem());
                    prepare.setInt(4, rec_qt.getValue());
                    prepare.setString(5, rec_price.getText());

                    String formattedDate = dateV.toString(); // Format "yyyy-MM-dd"
                    prepare.setString(6, formattedDate);

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
                    prepare.setString(7, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    // Message succès
                    System.out.println("Insertion réussie !");
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("il ne faut pas la date de vente superieur a la date de recuperation ");
                    alert.showAndWait();
                    return;

                }

                // 4. Mise à jour du stock
                String updateStockRest = "UPDATE virtual_stock SET stock_rest = stock_rest + ? "
                        + "WHERE prod_id = ? AND prod_marque = ?";
                prepare = connection.prepareStatement(updateStockRest);
                prepare.setInt(1, rec_qt.getValue());
                prepare.setString(2, rec_id.getText());
                prepare.setString(3, (String) rec_marque.getSelectionModel().getSelectedItem());
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Vente ajoutée avec succès !");
                alert.showAndWait();

                recShowListData();
                String checkExist = "SELECT qty_v, price FROM adminvente WHERE prod_id = ? AND prod_marque = ?";
                prepare = connection.prepareStatement(checkExist);
                prepare.setString(1, rec_id.getText());
                prepare.setString(2, (String) rec_marque.getSelectionModel().getSelectedItem());
                result = prepare.executeQuery();

                if (result.next()) {
                    // جلب الكمية و السعر القديم
                    int oldQty = result.getInt("qty_v");
                    double oldPrice = result.getDouble("price");

                    // الكمية و السعر الجديدين
                    int newQty = oldQty - rec_qt.getValue();
                    double addedPrice = Double.parseDouble(rec_price.getText());
                    double newPrice = oldPrice - addedPrice;

                    // تحديث الكمية و السعر
                    String updateQty = "UPDATE adminvente SET qty_v = ?, price = ? WHERE prod_id = ? AND prod_marque = ?";
                    prepare = connection.prepareStatement(updateQty);
                    prepare.setInt(1, newQty);
                    prepare.setDouble(2, newPrice);
                    prepare.setString(3, rec_id.getText());
                    prepare.setString(4, (String) rec_marque.getSelectionModel().getSelectedItem());
                    prepare.executeUpdate();

                }
                String checkExist2 = "SELECT qty_v, price FROM adminvente2 WHERE prod_id = ? AND prod_marque = ?";
                prepare = connection.prepareStatement(checkExist2);
                prepare.setString(1, rec_id.getText());
                prepare.setString(2, (String) rec_marque.getSelectionModel().getSelectedItem());
                result = prepare.executeQuery();

                if (result.next()) {
                    // جلب الكمية و السعر القديم
                    int oldQty = result.getInt("qty_v");
                    double oldPrice = result.getDouble("price");

                    // الكمية و السعر الجديدين
                    int newQty = oldQty - rec_qt.getValue();
                    double addedPrice = Double.parseDouble(rec_price.getText());
                    double newPrice = oldPrice - addedPrice;
                    System.out.println(newQty);
                    System.out.println(newPrice);
                    System.out.println(oldQty);
                    System.out.println(oldPrice);

                    // تحديث الكمية و السعر
                    String updateQty2 = "UPDATE adminvente2 SET qty_v = ?, price = ? WHERE prod_id = ? AND prod_marque = ?";
                    prepare = connection.prepareStatement(updateQty2);
                    prepare.setInt(1, newQty);
                    prepare.setDouble(2, newPrice);
                    prepare.setString(3, rec_id.getText());
                    prepare.setString(4, (String) rec_marque.getSelectionModel().getSelectedItem());
                    prepare.executeUpdate();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (result != null) result.close();
                    if (prepare != null) prepare.close();
                    if (connection != null) connection.close(); // ❗ مهم جداً
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public ObservableList<productdata> chargeDataList(){
        ObservableList<productdata> data = FXCollections.observableArrayList();
        String sql = "select * from charge";
        connection = database.connectDb();
        try{
            prepare=connection.prepareStatement(sql);
            result =prepare.executeQuery();
            productdata prodData ;
            while(result.next()){
                prodData =new productdata(result.getInt("id"),
                        result.getInt("num"),
                        result.getString("description"),
                        result.getDouble("price_charge"),
                        result.getDate("date"));
                data.add(prodData);


            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connection != null) connection.close(); // ❗ مهم جداً
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return data;
    }
    private ObservableList<productdata> chargeListData ;
    public void chargeShowData(){
        chargeListData=chargeDataList();
        charge_col_num.setCellValueFactory(new PropertyValueFactory<>("number"));
        charge_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        charge_col_price.setCellValueFactory(new PropertyValueFactory<>("priceCharge"));
        charge_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        charge_tableview.setItems(chargeListData);
    }
    public void chargeAddBtn() {
        String description = null;
        String comboValue = charge_comboDesc.getValue();
        String textValue = charge_description.getText();

        if (comboValue != null && !comboValue.isEmpty() && !textValue.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("SVP, n'utilisez qu'un seul champ de description : soit la liste déroulante soit le champ de texte.");
            alert.showAndWait();
            return;
        }

        // ناخذ الوصف من ComboBox أو TextArea حسب اللي معبّي
        if (comboValue != null && !comboValue.isEmpty()) {
            description = comboValue;
        } else if (!textValue.isEmpty()) {
            description = textValue;
        }

        // التحقق من الحقول
        if (description == null || charge_price.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Remplir Les Champs");
            alert.showAndWait();

        } else {
            connection = database.connectDb();

            try {
                String getMaxNum = "SELECT MAX(num) AS max_num FROM charge";
                statement = connection.createStatement();
                result = statement.executeQuery(getMaxNum);

                int newNum = 1;
                if (result.next() && result.getInt("max_num") != 0) {
                    newNum = result.getInt("max_num") + 1;
                }

                String insertData = "INSERT INTO charge (num, description, price_charge, date) VALUES (?, ?, ?, ?)";
                prepare = connection.prepareStatement(insertData);
                prepare.setInt(1, newNum);
                prepare.setString(2, description);
                double price = Double.parseDouble(charge_price.getText());
                prepare.setDouble(3, price);
                java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
                prepare.setDate(4, sqlDate);

                prepare.executeUpdate();

                // ✅ نضيف للوصف للـComboBox إذا جديد
                if (!charge_comboDesc.getItems().contains(description)) {
                    charge_comboDesc.getItems().add(description);
                }

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Charge ajoutée avec succès !");
                alert.showAndWait();

                chargeShowData();

                // تنظيف الحقول
                charge_description.clear();
                charge_price.clear();
                charge_comboDesc.setValue(null);

            } catch (NumberFormatException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le prix doit être un nombre valide !");
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (result != null) result.close();
                    if (prepare != null) prepare.close();
                    if (connection != null) connection.close(); // ❗ مهم جداً
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public void chargeDeleteBtn() {
        productdata selectedCharge = charge_tableview.getSelectionModel().getSelectedItem();

        if (selectedCharge == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une ligne à supprimer !");
            alert.showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation de suppression");
        confirm.setHeaderText(null);
        confirm.setContentText("Êtes-vous sûr de vouloir supprimer cette charge ?");
        Optional<ButtonType> option = confirm.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            Connection connection = null;
            PreparedStatement prepare = null;

            try {
                connection = database.connectDb();

                String deleteQuery = "DELETE FROM charge WHERE num = ?";
                prepare = connection.prepareStatement(deleteQuery);
                prepare.setInt(1, selectedCharge.getNumber());

                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText(null);
                    alert.setContentText("Charge supprimée avec succès !");
                    alert.showAndWait();

                    // rafraîchir le tableau
                    chargeShowData();

                    // vider les champs
                    charge_description.clear();
                    charge_price.clear();
                    charge_comboDesc.setValue(null);
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Échec de suppression !");
                    alert.showAndWait();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (prepare != null) prepare.close();
                    if (connection != null) connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void ClearBtn5(){
        charge_description.setText("");
        charge_price.setText("");


        charge_comboDesc.getSelectionModel().clearSelection();
    }
    public void loadChargeDescriptions() {
        connection = database.connectDb();
        String query = "SELECT DISTINCT description FROM charge";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String desc = result.getString("description");
                if (desc != null && !desc.isEmpty() && !charge_comboDesc.getItems().contains(desc)) {
                    charge_comboDesc.getItems().add(desc);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void chargeNothing(){
        charge_description.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                charge_comboDesc.setValue(null);
            }
        });

        charge_comboDesc.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                charge_description.clear();
            }
        });
    }
    public void chargeSearch() {

        FilteredList<productdata> filter = new FilteredList<>(chargeListData, e -> true);

        charge_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateEmployeeData.getNumber().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDescription().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDate().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return  false;
                }
            });
        });

        SortedList<productdata> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(charge_tableview.comparatorProperty());
        charge_tableview.setItems(sortList);
    }


    public void inventoryAddBtn() {
        boolean invalidPriceR1 = inventory_stock.getText() == null
                || inventory_stock.getText().isEmpty()
                || !inventory_stock.getText().matches("\\d*(\\.\\d*)?");
        boolean invalidPriceR2 = inventory_priceBuy_u.getText() == null
                || inventory_priceBuy_u.getText().isEmpty()
                || !inventory_priceBuy_u.getText().matches("\\d*(\\.\\d*)?");
        boolean invalidPriceR3 = inventory_rem.getText() == null
                || inventory_rem.getText().isEmpty()
                || !inventory_rem.getText().matches("\\d*(\\.\\d*)?");
        boolean invalidPriceR4 = inventory_tva.getText() == null
                || inventory_tva.getText().isEmpty()
                || !inventory_tva.getText().matches("\\d*(\\.\\d*)?");
        if (inventory_idProduct.getText().isEmpty() || inventory_nameProduct.getText().isEmpty()
                || inventory_status.getSelectionModel().getSelectedItem() == null
                || inventory_stock.getText().isEmpty()
                || inventory_priceBuy_u.getText().isEmpty()
                || inventory_priceSell_u.getText().isEmpty()
                || inventory_typeProduct.getSelectionModel().getSelectedItem() == null
                || inventory_marque.getText().isEmpty()
                || inventory_rem.getText().isEmpty()
                || inventory_tva.getText().isEmpty()
        || invalidPriceR1||invalidPriceR2||invalidPriceR3||invalidPriceR4) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("SVP entrer tout les données correctement");
            alert.showAndWait();
        } else {
            String check = "SELECT prod_id FROM product WHERE prod_id = '"
                    + inventory_idProduct.getText() + "'";
            connection = database.connectDb();
            try {
                statement = connection.createStatement();
                result = statement.executeQuery(check);

                String insertData = "INSERT INTO product "
                        + "(prod_id,prod_name,prod_marque,stock,price_buy_u,price_sell_u,date,status,type) "
                        + "values(?,?,?,?,?,?,?,?,?)";

                prepare = connection.prepareStatement(insertData);
                prepare.setString(1, inventory_idProduct.getText());
                prepare.setString(2, inventory_nameProduct.getText());
                prepare.setString(3, inventory_marque.getText());
                prepare.setString(4, inventory_stock.getText());
                prepare.setString(5, inventory_priceBuy_u.getText());
                prepare.setString(6, inventory_priceSell_u.getText());
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(7, String.valueOf(sqlDate));

                prepare.setString(8,  inventory_status.getSelectionModel().getSelectedItem());
                prepare.setString(9, inventory_typeProduct.getSelectionModel().getSelectedItem());
                prepare.executeUpdate();

                String insertInfo = "INSERT INTO product_info "
                        + "(prod_id,prod_name,prod_marque,stock,price_buy_u,price_buy_u_rem,price_buy_u_TVA,price_sell_u,price_total,type,date) "
                        + "values(?,?,?,?,?,?,?,?,?,?,?)";

                int stock = Integer.parseInt(inventory_stock.getText());
                double priceBuy = Double.parseDouble(inventory_priceBuy_u.getText());
                double BuyRem = Double.parseDouble(inventory_rem.getText());
                double BuyTva = Double.parseDouble(inventory_tva.getText());
                double priceRem = (BuyRem * priceBuy) / 100;
                double priceRemise = priceBuy - priceRem;
                double priceTva = (BuyTva * priceRemise) / 100;
                double priceTV = priceRemise + priceTva;
                double totalPrice = stock * priceTV;

                prepare = connection.prepareStatement(insertInfo);
                prepare.setString(1, inventory_idProduct.getText());
                prepare.setString(2, inventory_nameProduct.getText());
                prepare.setString(3, inventory_marque.getText());
                prepare.setString(4, inventory_stock.getText());
                prepare.setString(5, inventory_priceBuy_u.getText());
                prepare.setString(6,String.valueOf(priceRemise) );
                prepare.setString(7,  String.valueOf(priceTV) );
                prepare.setString(8, inventory_priceSell_u.getText());
                prepare.setString(9, String.valueOf(totalPrice) );
                prepare.setString(10, inventory_typeProduct.getSelectionModel().getSelectedItem());
                prepare.setString(11, String.valueOf(sqlDate));
                prepare.executeUpdate();

                // Update virtual_stock
                String updateVirtualStock = "INSERT INTO virtual_stock (prod_id, prod_marque, stock_rest) "
                        + "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE stock_rest = stock_rest + ?";
                prepare = connection.prepareStatement(updateVirtualStock);
                prepare.setString(1, inventory_idProduct.getText());
                prepare.setString(2, inventory_marque.getText());
                prepare.setInt(3, stock);
                prepare.setInt(4, stock);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Ajout Avec Succes!");
                alert.showAndWait();

                inventoryShowData();
                inventoryClearBtn();

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (result != null) result.close();
                    if (prepare != null) prepare.close();
                    if (connection != null) connection.close(); //❗ مهم جداً
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public void inventoryDeleteBtn() {
        productdata selectedProduct = inventory_tableview.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un produit à supprimer.");
            alert.showAndWait();
            return;
        }

        String prodId = selectedProduct.getProductId();
        String prodMarque = selectedProduct.getProductMarque();
        int stockToRemove = selectedProduct.getStock();
        Date prodDateObj = selectedProduct.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String prodDate = dateFormat.format(prodDateObj); // تأكد أن productdata فيها هذا الحقل (String date)

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Es-tu sûr de vouloir supprimer : " + prodId + " ?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            connection = database.connectDb();

            try {
                connection.setAutoCommit(false); // commencer une transaction

                // 1️⃣ Supprimer le produit sélectionné dans 'product'
                String sqlProduct = "DELETE FROM product WHERE id = ?";
                prepare = connection.prepareStatement(sqlProduct);
                prepare.setInt(1, selectedProduct.getId());
                prepare.executeUpdate();

                // 2️⃣ Supprimer toutes les lignes correspondantes dans 'product_info'
                String sqlInfo = """
                DELETE FROM product_info
                WHERE prod_id = ?
                  AND prod_marque = ?
                  AND stock = ?
                  AND date = ?
            """;
                prepare = connection.prepareStatement(sqlInfo);
                prepare.setString(1, prodId);
                prepare.setString(2, prodMarque);
                prepare.setInt(3, stockToRemove);
                prepare.setString(4, prodDate);
                prepare.executeUpdate();

                // 3️⃣ Mettre à jour le virtual_stock
                String updateVirtualStock = """
                UPDATE virtual_stock
                SET stock_rest = stock_rest - ?
                WHERE prod_id = ? AND prod_marque = ?
            """;
                prepare = connection.prepareStatement(updateVirtualStock);
                prepare.setInt(1, stockToRemove);
                prepare.setString(2, prodId);
                prepare.setString(3, prodMarque);
                prepare.executeUpdate();

                // 4️⃣ Empêcher les valeurs négatives
                String fixNegative = "UPDATE virtual_stock SET stock_rest = 0 WHERE stock_rest < 0";
                prepare = connection.prepareStatement(fixNegative);
                prepare.executeUpdate();

                connection.commit();

                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Information");
                info.setHeaderText(null);
                info.setContentText("Produit supprimé avec succès !");
                info.showAndWait();

                inventoryShowData();
                historicShowListData();
                inventoryClearBtn();

            } catch (Exception e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void inventoryUpdateBtn() {
        productdata selectedProduct = inventory_tableview.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un produit à modifier.");
            alert.showAndWait();
            return;
        }

        // 🔹 nouvelles valeurs depuis les champs d’interface
        String newProdId = inventory_idProduct.getText();
        String newName = inventory_nameProduct.getText();
        String newMarque = inventory_marque.getText();
        String newPriceSell = inventory_priceSell_u.getText(); // String
        String newStatus = inventory_status.getSelectionModel().getSelectedItem();
        String newType = inventory_typeProduct.getSelectionModel().getSelectedItem();

        // 🔹 Vérification des champs numériques
        int newStock;
        double newPriceBuy;
        try {
            newStock = Integer.parseInt(inventory_stock.getText());
            newPriceBuy = Double.parseDouble(inventory_priceBuy_u.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer des valeurs numériques correctes pour stock et prix d'achat !");
            alert.showAndWait();
            return;
        }

        // 🔹 anciennes valeurs pour comparer
        String oldProdId = selectedProduct.getProductId();
        String oldName = selectedProduct.getProductName();
        String oldMarque = selectedProduct.getProductMarque();
        String oldPriceSell = selectedProduct.getPriceSellU(); // String
        int oldStock = selectedProduct.getStock();
        double oldPriceBuy = selectedProduct.getPriceBuyU();
        String oldType = selectedProduct.getType();
        Date prodDateObj = selectedProduct.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String prodDate = dateFormat.format(prodDateObj);

        connection = database.connectDb();

        try {
            connection.setAutoCommit(false);

            // 1️⃣ UPDATE sur product
            String sqlProduct = """
            UPDATE product 
            SET prod_id = ?, prod_name = ?, prod_marque = ?, stock = ?, 
                price_buy_u = ?, price_sell_u = ?, status = ?, type = ?
            WHERE id = ?
        """;

            prepare = connection.prepareStatement(sqlProduct);
            prepare.setString(1, newProdId);
            prepare.setString(2, newName);
            prepare.setString(3, newMarque);
            prepare.setInt(4, newStock);
            prepare.setDouble(5, newPriceBuy);
            prepare.setString(6, newPriceSell); // String
            prepare.setString(7, newStatus);
            prepare.setString(8, newType);
            prepare.setInt(9, selectedProduct.getId());
            prepare.executeUpdate();

            // 2️⃣ UPDATE sur product_info (WHERE avec toutes les anciennes valeurs)
            String sqlInfo = """
            UPDATE product_info
            SET prod_id = ?, prod_name = ?, prod_marque = ?, stock = ?, 
                price_buy_u = ?, price_sell_u = ?, type = ?
            WHERE prod_id = ? AND prod_name = ? AND prod_marque = ? AND stock = ? 
              AND price_buy_u = ? AND price_sell_u = ? AND type = ? AND date = ?
        """;

            prepare = connection.prepareStatement(sqlInfo);
            // nouvelles valeurs
            prepare.setString(1, newProdId);
            prepare.setString(2, newName);
            prepare.setString(3, newMarque);
            prepare.setInt(4, newStock);
            prepare.setDouble(5, newPriceBuy);
            prepare.setString(6, newPriceSell);
            prepare.setString(7, newType);

            // anciennes valeurs pour WHERE
            prepare.setString(8, oldProdId);
            prepare.setString(9, oldName);
            prepare.setString(10, oldMarque);
            prepare.setInt(11, oldStock);
            prepare.setDouble(12, oldPriceBuy);
            prepare.setString(13, oldPriceSell);
            prepare.setString(14, oldType);
            prepare.setString(15,prodDate);
            prepare.executeUpdate();

            // 3️⃣ Si id, marque ou stock changent, MAJ virtual_stock
            if (!newProdId.equals(oldProdId) || !newMarque.equals(oldMarque) || newStock != oldStock) {
                // supprimer l'ancien enregistrement
                String deleteOldVirtual = """
                DELETE FROM virtual_stock 
                WHERE prod_id = ? AND prod_marque = ?
            """;
                prepare = connection.prepareStatement(deleteOldVirtual);
                prepare.setString(1, oldProdId);
                prepare.setString(2, oldMarque);
                prepare.executeUpdate();

                // insérer le nouveau
                String insertNewVirtual = """
                INSERT INTO virtual_stock (prod_id, prod_marque, stock_rest)
                VALUES (?, ?, ?)
                ON DUPLICATE KEY UPDATE stock_rest = ?
            """;
                prepare = connection.prepareStatement(insertNewVirtual);
                prepare.setString(1, newProdId);
                prepare.setString(2, newMarque);
                prepare.setInt(3, newStock);
                prepare.setInt(4, newStock);
                prepare.executeUpdate();
            }

            connection.commit();

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Information");
            info.setHeaderText(null);
            info.setContentText("Produit modifié avec succès !");
            info.showAndWait();

            inventoryShowData();
            historicShowListData();
            inventoryClearBtn();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }



    public void inventoryClearBtn(){
        inventory_idProduct.setText("");
        inventory_nameProduct.setText("");
        inventory_marque.setText("");
        inventory_stock .setText("");
        inventory_priceBuy_u.setText("");
        inventory_priceSell_u .setText("");
        historic_tva.setText("");
        historic_rem.setText("");
        historic_search.setText("");

        inventory_typeProduct.getSelectionModel().clearSelection();
    }

    public ObservableList<productdata> inventoryDataList(){
         ObservableList<productdata> data = FXCollections.observableArrayList();
         String sql = "select * from product";
         connection = database.connectDb();
         try{
             prepare=connection.prepareStatement(sql);
             result =prepare.executeQuery();
             productdata prodData ;
             while(result.next()){
                 prodData =new productdata(result.getInt("id"),
                         result.getString("prod_id"),
                         result.getString("prod_name"),
                         result.getString("prod_marque"),
                         result.getInt("stock"),
                         result.getDouble("price_buy_u"),
                         result.getString("price_sell_u"),
                         result.getDate("date"),
                         result.getString("status"),
                         result.getString("type"));
                 data.add(prodData);


             }

         }
         catch(Exception e){
             e.printStackTrace();
         }
         return data;
    }
    private ObservableList<productdata> inventoryListData ;
    public void inventoryShowData(){
        inventoryListData=inventoryDataList();
        inventory_col_idProduct.setCellValueFactory(new PropertyValueFactory<>("productId"));
        inventory_col_nameProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        inventory_col_marque.setCellValueFactory(new PropertyValueFactory<>("productMarque"));
        inventory_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        inventory_col_priceBuy_u.setCellValueFactory(new PropertyValueFactory<>("priceBuyU"));
        inventory_col_priceSell_u.setCellValueFactory(new PropertyValueFactory<>("priceSellU"));
        inventory_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        inventory_col_status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        inventory_col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        inventory_tableview.setItems(inventoryListData);


    }
    public void addEmployeeSelect() {
        productdata data = inventory_tableview.getSelectionModel().getSelectedItem();
        int num = inventory_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        inventory_idProduct.setText(String.valueOf(data.getProductId()));
        inventory_nameProduct.setText(data.getProductName());
        inventory_marque.setText(data.getProductMarque());
        inventory_stock.setText(String.valueOf(data.getStock()));
        inventory_priceBuy_u.setText(String.valueOf(data.getPriceBuyU()));
        inventory_priceSell_u.setText(data.getPriceSellU());
        inventory_status.setValue(data.getStatus());
        inventory_typeProduct.setValue(data.getType());
    }

    private String[] typeList = {"Moteur et Composants", "Transmission", "Suspension et Direction", "Freinage", "Électricité et Électronique", "Refroidissement et Chauffage", "Échappement", "Carrosserie et Accessoires", "Entretien et Consommables", "Outillage et Équipement de garage", "Pneumatique et Roues", "Intérieur et Confort"};
    private String[] typeList2 = {"Disponible","Indisponible"};
    public void inventoryTypeList(){
        List<String> typeL = new ArrayList<String>();
        for(String s : typeList){
            typeL.add(s);
        }
        ObservableList typeList = FXCollections.observableArrayList(typeL);
        inventory_typeProduct.setItems(typeList);
    }
    public void inventoryStatusList(){
        List<String> typeL = new ArrayList<String>();
        for(String s : typeList2){
            typeL.add(s);
        }
        ObservableList typeList = FXCollections.observableArrayList(typeL);
        inventory_status.setItems(typeList);
    }
    public void historicDeleteBtn() {
        productdata data = historic_tableview.getSelectionModel().getSelectedItem();

        if (data == null) {
            System.out.println("Aucune ligne sélectionnée !");
            return;
        }

        String prodId = data.getProductId(); // هذي القيمة prod_id حسب الـ model

        String sql = "DELETE FROM product_info WHERE prod_id = ?";

        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, prodId);
            prepare.executeUpdate();

            System.out.println("Produit supprimé avec succès.");
            historicShowListData(); // باش تحدث الجدول بعد الحذف

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<productdata> stockMListData() {

        ObservableList<productdata> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product_info";

        connection = database.connectDb();

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            productdata prodD;

            while (result.next()) {
                        prodD =new productdata(result.getInt("id"),
                                result.getString("prod_id"),
                                result.getString("prod_name"),
                                result.getString("prod_marque"),
                                result.getInt("stock"),
                                result.getDouble("price_buy_u"),
                                result.getDouble("price_buy_u_rem"),
                                result.getDouble("price_buy_u_TVA"),
                                result.getString("price_sell_u"),
                                result.getDouble("price_total"),
                                result.getString("type"),
                                result.getDate("date"));
                listData.add(prodD);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return listData;
    }
    private ObservableList<productdata> salaryList;
    private ObservableList<productdata> salaryList2;

    public void historicShowListData() {
        salaryList = stockMListData();

        historic_col_idProduct.setCellValueFactory(new PropertyValueFactory<>("productId"));
        historic_col_nameProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        historic_col_marque.setCellValueFactory(new PropertyValueFactory<>("productMarque"));
        historic_col_stockBuy.setCellValueFactory(new PropertyValueFactory<>("stock"));
        historic_col_priceBuy.setCellValueFactory(new PropertyValueFactory<>("priceBuyU"));
        historic_col_priceRemise.setCellValueFactory(new PropertyValueFactory<>("priceBuyUrem"));
        historic_col_priceTva.setCellValueFactory(new PropertyValueFactory<>("priceBuyUtva"));
        historic_col_priceSell.setCellValueFactory(new PropertyValueFactory<>("priceSellU"));

        historic_col_priceTotal.setCellValueFactory(new PropertyValueFactory<>("priceTotal"));
        historic_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        historic_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        historic_tableview.setItems(salaryList);

    }



    public void inventorySearch() {

        FilteredList<productdata> filter = new FilteredList<>(salaryList, e -> true);

        historic_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateEmployeeData.getProductId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getProductMarque().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPriceSellU().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDate().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateEmployeeData.getStock()).toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return  false;
                }
            });
        });

        SortedList<productdata> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(historic_tableview.comparatorProperty());
        historic_tableview.setItems(sortList);
    }
    public void inventorySearch3() {

        FilteredList<productdata> filter = new FilteredList<>(inventoryListData, e -> true);

        inventory_search2.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateEmployeeData.getProductId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getProductMarque().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPriceSellU().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDate().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateEmployeeData.getStock()).toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return  false;
                }
            });
        });

        SortedList<productdata> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(inventory_tableview.comparatorProperty());
        inventory_tableview.setItems(sortList);
    }

    public void historicSelect() {
        productdata data = historic_tableview.getSelectionModel().getSelectedItem();
        int num = historic_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

    //  Analyse_price_buy.setText(String.format("%.3f DT", totalBuy));
        double remise = ((data.getPriceBuyU() - data.getPriceBuyUrem()) / data.getPriceBuyU()) * 100;
        historic_rem.setText(String.format("%.2f %%", remise));
        double tvaDiff = ((data.getPriceBuyUtva() - data.getPriceBuyUrem()) / data.getPriceBuyUrem()) * 100;
        historic_tva.setText(String.format("%.2f %%", tvaDiff));
    }



    public void analyseComboData(){
        int currentYear = Year.now().getValue();
        for (int i = currentYear - 3; i <= currentYear + 10; i++) {
            Analyse_combo_year.getItems().add(i);
            Analyse_combo_year1.getItems().add(i);
            Analyse_combo_year2.getItems().add(i);
            Analyse_combo_year3.getItems().add(i);

        }
        Analyse_combo_year.setValue(currentYear);
        Analyse_combo_year1.setValue(currentYear);
        Analyse_combo_year2.setValue(currentYear);
        Analyse_combo_year3.setValue(currentYear);

        String sql = "SELECT DISTINCT price_sell_u FROM product_info"; // عدّل "fourn" بالاسم الصحيح للعمود
        connection = database.connectDb();

        ObservableList<String> listFournisseurs = FXCollections.observableArrayList();

        try (PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String f = rs.getString("price_sell_u"); // ولا اسمه autrement في الداتابيز
                if (f != null && !f.isEmpty()) {
                    listFournisseurs.add(f);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur BD");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors du chargement des fournisseurs:\n" + e.getMessage());
            alert.showAndWait();
        }

        analyse_fournisseur.setItems(listFournisseurs);
        Analyse_f.setItems(listFournisseurs);


    }
    public void analyseFournisseurParAnnee() {
        Integer selectedYear = Analyse_combo_year.getValue();
        String selectedFournisseur = analyse_fournisseur.getValue();

        if (selectedYear == null || selectedFournisseur == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une année et un fournisseur.");
            alert.showAndWait();
            return;
        }

        String sql = "SELECT " +
                "SUM(stock * price_buy_u_rem) AS total_remise, " +
                "SUM(stock * price_buy_u_TVA) AS total_tva " +
                "FROM product_info " +
                "WHERE price_sell_u = ? AND YEAR(date) = ?";

        connection = database.connectDb();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, selectedFournisseur);
            pst.setInt(2, selectedYear);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                double totalRemise = rs.getDouble("total_remise");
                double totalTva = rs.getDouble("total_tva");



                Analyse_four_rem.setText(String.format("%.3f DT", totalRemise));
                Analyse_four_tva.setText(String.format("%.3f DT", totalTva));



            } else {
                Analyse_four_tva.setText("0.000 DT");

                Analyse_four_rem.setText("0.000 DT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur BD");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors du calcul : " + e.getMessage());
            alert.showAndWait();
        }
    }

      public void analyseFournisseurParMois(){

          Map<String, Integer> moisMap = new LinkedHashMap<>();
          moisMap.put("Janvier", 1);
          moisMap.put("Février", 2);
          moisMap.put("Mars", 3);
          moisMap.put("Avril", 4);
          moisMap.put("Mai", 5);
          moisMap.put("Juin", 6);
          moisMap.put("Juillet", 7);
          moisMap.put("Août", 8);
          moisMap.put("Septembre", 9);
          moisMap.put("Octobre", 10);
          moisMap.put("Novembre", 11);
          moisMap.put("Décembre", 12);


          ObservableList<String> frenchMonths = FXCollections.observableArrayList(moisMap.keySet());
          Analyse_combo_month.setItems(frenchMonths);
          int currentMonthNumber = LocalDate.now().getMonthValue();
          String currentMonthInFrench = frenchMonths.get(currentMonthNumber - 1);
          Analyse_combo_month.setValue(currentMonthInFrench);

        String selectedMonthName = Analyse_combo_month.getValue();
        Integer selectedYear = Analyse_combo_year1.getValue();
        String selectedFournisseur = Analyse_f.getValue();


        if (selectedYear == null|| selectedFournisseur == null|| selectedMonthName == null ) {
            Analyse_four_rem1.setText("0.000 DT");
            Analyse_four_tva1.setText("0.000 DT");
            return;
        }

        // تحويل الشهر من نص إلى رقم
        Integer selectedMonthNumber = moisMap.get(selectedMonthName);

        // Requête SQL
        String sql = """
        SELECT 
            SUM(stock * price_buy_u_rem) AS total_remise,
            SUM(stock * price_buy_u_TVA) AS total_tva 
        FROM product_info 
        WHERE price_sell_u = ? AND YEAR(date) = ? AND MONTH(date) = ?
    """;

        // تنفيذ الاستعلام
        connection = database.connectDb();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, selectedFournisseur);
            pst.setInt(2, selectedYear);
            pst.setInt(3, selectedMonthNumber);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double totalRemise = rs.getDouble("total_remise");
                double totalTva = rs.getDouble("total_tva");

                Analyse_four_rem1.setText(String.format("%.3f DT", totalRemise));
                Analyse_four_tva1.setText(String.format("%.3f DT", totalTva));
            } else {
                Analyse_four_rem1.setText("0.000 DT");
                Analyse_four_tva1.setText("0.000 DT");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur BD");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors du calcul : " + e.getMessage());
            alert.showAndWait();
        }
    }
    public void analyseFournisseurParAnnee1() {
        Integer selectedYear = Analyse_combo_year2.getValue();

        if (selectedYear == null ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une année .");
            alert.showAndWait();
            return;
        }

        String sql = "SELECT " +
                "SUM(stock * price_buy_u_rem) AS total_remise, " +
                "SUM(stock * price_buy_u_TVA) AS total_tva " +
                "FROM product_info " +
                "WHERE YEAR(date) = ?";

        connection = database.connectDb();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, selectedYear);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                double totalRemise = rs.getDouble("total_remise");
                double totalTva = rs.getDouble("total_tva");



                Analyse_tot_rem.setText(String.format("%.3f DT", totalRemise));
                Analyse_tot_tva.setText(String.format("%.3f DT", totalTva));



            } else {
                Analyse_four_tva.setText("0.000 DT");

                Analyse_four_rem.setText("0.000 DT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur BD");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors du calcul : " + e.getMessage());
            alert.showAndWait();
        }
    }
    public void analyseFournisseurParMois1(){
        // تعيين الشهر الحالي كقيمة افتراضية
        Map<String, Integer> moisMap = new LinkedHashMap<>();
        moisMap.put("Janvier", 1);
        moisMap.put("Février", 2);
        moisMap.put("Mars", 3);
        moisMap.put("Avril", 4);
        moisMap.put("Mai", 5);
        moisMap.put("Juin", 6);
        moisMap.put("Juillet", 7);
        moisMap.put("Août", 8);
        moisMap.put("Septembre", 9);
        moisMap.put("Octobre", 10);
        moisMap.put("Novembre", 11);
        moisMap.put("Décembre", 12);

        // ComboBox تعمير
        ObservableList<String> frenchMonths = FXCollections.observableArrayList(moisMap.keySet());
        Analyse_combo_month1.setItems(frenchMonths);
        int currentMonthNumber = LocalDate.now().getMonthValue();
        String currentMonthInFrench = frenchMonths.get(currentMonthNumber - 1);
        Analyse_combo_month1.setValue(currentMonthInFrench);
        // استخراج القيم المختارة
        String selectedMonthName = Analyse_combo_month1.getValue();
        Integer selectedYear = Analyse_combo_year3.getValue();

        // التثبت من أن القيم موش null
        if (selectedYear == null|| selectedMonthName == null ) {
            Analyse_tot_rem1.setText("0.000 DT");
            Analyse_tot_tva1.setText("0.000 DT");
            return;
        }

        // تحويل الشهر من نص إلى رقم
        Integer selectedMonthNumber = moisMap.get(selectedMonthName);

        // Requête SQL
        String sql = """
        SELECT 
            SUM(stock * price_buy_u_rem) AS total_remise,
            SUM(stock * price_buy_u_TVA) AS total_tva 
        FROM product_info 
        WHERE  YEAR(date) = ? AND MONTH(date) = ?
    """;

        // تنفيذ الاستعلام
        connection = database.connectDb();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, selectedYear);
            pst.setInt(2, selectedMonthNumber);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double totalRemise = rs.getDouble("total_remise");
                double totalTva = rs.getDouble("total_tva");

                Analyse_tot_rem1.setText(String.format("%.3f DT", totalRemise));
                Analyse_tot_tva1.setText(String.format("%.3f DT", totalTva));
            } else {
                Analyse_tot_rem1.setText("0.000 DT");
                Analyse_tot_tva1.setText("0.000 DT");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur BD");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors du calcul : " + e.getMessage());
            alert.showAndWait();
        }
    }
    public void analyseTotale() {
        String sql = "SELECT p.prod_name, "
                + "SUM(p.price_buy_u * vs.stock_rest) AS priceTot, "
                + "SUM(p.price_buy_u_rem * vs.stock_rest) AS priceTotRem, "
                + "SUM(p.price_buy_u_TVA * vs.stock_rest) AS priceTotTva, "
                + "SUM(vs.stock_rest) AS totalStock, "
                + "COUNT(*) AS n "
                + "FROM product_info p "
                + "JOIN virtual_stock vs ON p.prod_id = vs.prod_id AND p.prod_marque = vs.prod_marque "
                + "GROUP BY vs.stock_rest ";

        connection = database.connectDb();

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();  

            int totalStock = 0;
            double totalBuy = 0, totalRem = 0, totalTva = 0;
            int count = 0;

            while (result.next()) {
                int stockRest = result.getInt("totalStock");
                double rem = result.getDouble("priceTotRem");
                double tva = result.getDouble("priceTotTva");
                totalRem = rem;
                totalTva = tva;
                count++;
            }

            if (count > 0) {

               total.setText(String.format("%.3f DT", totalRem / count));
                total1.setText(String.format("%.3f DT", totalTva / count));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aucune donnée");
                alert.setHeaderText(null);
                alert.setContentText("Il n'y a aucun produit dans le stock virtuel.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dashboardChart() {
        Analyse_chart.getData().clear();
        String sql = "SELECT SUM(price_buy_u_TVA * stock) AS total, date FROM product_info GROUP BY  MONTH(date), YEAR(date) ORDER BY date";

        connection = database.connectDb();
        XYChart.Series<String, Number> chart = new XYChart.Series<>();

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                double total = result.getDouble("total");
                Date date = result.getDate("date");
                String label = new SimpleDateFormat("yyyy-MM").format(date); // دقيقة دقيقة

                chart.getData().add(new XYChart.Data<>(label, total));
            }

            Analyse_chart.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void AnalyseClearBtn1(){
        Analyse_idProduct.setText("");
        Analyse_nom.setText("");
        Analyse_stock.setText("");
        Analyse_price_buy .setText("");
        Analyse_priceRem.setText("");
        Analyse_priceTva .setText("");

        Analyse_marque.getSelectionModel().clearSelection();
    }
    public void AnalyseClearBtn2(){

        Analyse_tot_rem1.setText("");
        Analyse_tot_tva1.setText("");
        Analyse_price_buy .setText("");


        Analyse_tot_rem.setText("");
        Analyse_tot_tva.setText("");
    }
    public void AnalyseClearBtn3(){

        Analyse_four_rem.setText("");
        Analyse_four_tva.setText("");
        Analyse_four_rem1 .setText("");


        Analyse_four_tva1.setText("");

    }

    public void analyseQuantity() {
        String sql = "SELECT p.prod_name, "
                + "SUM(p.price_buy_u * vs.stock_rest) AS priceTot, "
                + "SUM(p.price_buy_u_rem * vs.stock_rest) AS priceTotRem, "
                + "SUM(p.price_buy_u_TVA * vs.stock_rest) AS priceTotTva, "
                + "COUNT(*) AS n, "
                + "vs.stock_rest "
                + "FROM product_info p "
                + "JOIN virtual_stock vs ON p.prod_id = vs.prod_id AND p.prod_marque = vs.prod_marque "
                + "WHERE p.prod_id = ? AND p.prod_marque = ? "
                + "GROUP BY p.prod_name, vs.prod_marque";

        connection = database.connectDb();

        try {
            if (Analyse_idProduct.getText().isEmpty() || Analyse_marque.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner un produit et une marque à analyser.");
                alert.showAndWait();
                return;
            }
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, Analyse_idProduct.getText());
            prepare.setString(2, Analyse_marque.getValue());
            result = prepare.executeQuery();

            int totalStock = 0;
            double totalBuy = 0, totalRem = 0, totalTva = 0;
            String prodName = "";

            while (result.next()) {
                int stockRest = result.getInt("stock_rest");
                double buy = result.getDouble("priceTot");
                double rem = result.getDouble("priceTotRem");
                double tva = result.getDouble("priceTotTva");
                int n = result.getInt("n");

                totalStock = stockRest;
                totalBuy = buy / n;
                totalRem = rem / n;
                totalTva = tva / n;

                prodName = result.getString("prod_name");
            }

            if (!prodName.isEmpty()) {
                Analyse_stock.setText(String.valueOf(totalStock));
                Analyse_nom.setText(prodName);
                Analyse_price_buy.setText(String.format("%.3f DT", totalBuy));
                Analyse_priceRem.setText(String.format("%.3f DT", totalRem));
                Analyse_priceTva.setText(String.format("%.3f DT", totalTva));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Aucun produit trouvé avec cet ID et cette marque.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void venteAddBtn() {
        // التحقق على les prix (doivent être numériques)
        boolean invalidPriceR = vente_price_r.getText() == null
                || vente_price_r.getText().isEmpty()
                || !vente_price_r.getText().matches("\\d*(\\.\\d*)?");

        boolean invalidPriceE = vente_price_e.getText() == null
                || vente_price_e.getText().isEmpty()
                || !vente_price_e.getText().matches("\\d*(\\.\\d*)?");

        if (vente_idVente.getText().isEmpty()
                || vente_marque.getSelectionModel().getSelectedItem() == null
                || vente_qty.getValue() == null || vente_qty.getValue() == 0
                || invalidPriceR
                || invalidPriceE
        ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("SVP entrer toutes les données correctement (les prix doivent être numériques)");
            alert.showAndWait();
            return;
        }else {
            connection = database.connectDb();

            try {
                // 1. Vérifier le stock restant
                String checkStock = "SELECT stock_rest FROM virtual_stock WHERE prod_id = ? AND prod_marque = ?";
                prepare = connection.prepareStatement(checkStock);
                prepare.setString(1, vente_idVente.getText());
                prepare.setString(2, (String) vente_marque.getSelectionModel().getSelectedItem());
                result = prepare.executeQuery();

                int stockRestant = 0;
                if (result.next()) {
                    stockRestant = result.getInt("stock_rest");
                }

                if (vente_qty.getValue() > stockRestant) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Stock Insuffisant");
                    alert.setHeaderText(null);
                    alert.setContentText("La quantité demandée dépasse le stock disponible (" + stockRestant + ").");
                    alert.showAndWait();
                    return;
                }

                // 2. Récupérer type
                String queryType = "SELECT type FROM product_info WHERE prod_id = ?";
                PreparedStatement getTypeStmt = connection.prepareStatement(queryType);
                getTypeStmt.setString(1, vente_idVente.getText());
                ResultSet resultType = getTypeStmt.executeQuery();

                String typeProduit = "";
                if (resultType.next()) {
                    typeProduit = resultType.getString("type");
                }

                // 3. Insertion dans la table vente
                String insertData = "INSERT INTO vente "
                        + "(prod_id, prod_name, prod_marque, qty, price_sell_e, price_sell_r, date, type, credit) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                prepare = connection.prepareStatement(insertData);

// القيم العادية
                prepare.setString(1, vente_idVente.getText());
                prepare.setString(2, vente_nom.getText());
                prepare.setString(3, (String) vente_marque.getSelectionModel().getSelectedItem());
                prepare.setInt(4, vente_qty.getValue());
                prepare.setString(5, vente_price_e.getText());
                prepare.setString(6, vente_price_r.getText());


                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
                prepare.setString(7, String.valueOf(sqlDate));

                prepare.setString(8, typeProduit);


                double priceE = Double.parseDouble(vente_price_e.getText());
                double priceR = Double.parseDouble(vente_price_r.getText());
                String creditStatus = (priceE - priceR <= 0) ? "pas de crédit" : "avec crédit";
                System.out.println("credit: " + creditStatus);
                prepare.setString(9, creditStatus);

// تنفيذ
                prepare.executeUpdate();

                // 4. Mise à jour du stock
                String updateStockRest = "UPDATE virtual_stock SET stock_rest = stock_rest - ? "
                        + "WHERE prod_id = ? AND prod_marque = ?";
                prepare = connection.prepareStatement(updateStockRest);
                prepare.setInt(1, vente_qty.getValue());
                prepare.setString(2, vente_idVente.getText());
                prepare.setString(3, (String) vente_marque.getSelectionModel().getSelectedItem());
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Vente ajoutée avec succès !");
                alert.showAndWait();

                venteShowData();
                String checkExist = "SELECT qty_v, price FROM adminvente WHERE prod_id = ? AND prod_marque = ?";
                prepare = connection.prepareStatement(checkExist);
                prepare.setString(1, vente_idVente.getText());
                prepare.setString(2, (String) vente_marque.getSelectionModel().getSelectedItem());
                result = prepare.executeQuery();

                if (result.next()) {
                    // جلب الكمية و السعر القديم
                    int oldQty = result.getInt("qty_v");
                    double oldPrice = result.getDouble("price");

                    // الكمية و السعر الجديدين
                    int newQty = oldQty + vente_qty.getValue();
                    double addedPrice = Double.parseDouble(vente_price_r.getText());
                    double newPrice = oldPrice + addedPrice;


                    // تحديث الكمية و السعر
                    String updateQty = "UPDATE adminvente SET qty_v = ?, price = ? WHERE prod_id = ? AND prod_marque = ?";
                    prepare = connection.prepareStatement(updateQty);
                    prepare.setInt(1, newQty);
                    prepare.setDouble(2, newPrice);
                    prepare.setString(3, vente_idVente.getText());
                    prepare.setString(4, (String) vente_marque.getSelectionModel().getSelectedItem());
                    prepare.executeUpdate();


                } else {
                    // 3. Sinon -> insert ligne nouvelle
                    String insertAdmin = "INSERT INTO adminvente "
                            + "(prod_id, prod_name, prod_marque, qty_v, price) "
                            + "VALUES (?, ?, ?, ?, ?)";
                    prepare = connection.prepareStatement(insertAdmin);
                    prepare.setString(1, vente_idVente.getText());
                    prepare.setString(2, vente_nom.getText());
                    prepare.setString(3, (String) vente_marque.getSelectionModel().getSelectedItem());
                    prepare.setInt(4, vente_qty.getValue());
                    prepare.setString(5, vente_price_r.getText());
                    prepare.executeUpdate();;
                }
                String checkExiste = "SELECT qty_v, price FROM adminevente_backup WHERE prod_id = ? AND prod_marque = ?";
                prepare = connection.prepareStatement(checkExiste);
                prepare.setString(1, vente_idVente.getText());
                prepare.setString(2, (String) vente_marque.getSelectionModel().getSelectedItem());
                result = prepare.executeQuery();

                if (result.next()) {
                    // جلب الكمية و السعر القديم
                    int oldQty = result.getInt("qty_v");
                    double oldPrice = result.getDouble("price");

                    // الكمية و السعر الجديدين
                    int newQty = oldQty + vente_qty.getValue();
                    double addedPrice = Double.parseDouble(vente_price_r.getText());
                    double newPrice = oldPrice + addedPrice;

                    // تحديث الكمية و السعر
                    String updateQtye = "UPDATE adminevente_backup SET qty_v = ?, price = ? WHERE prod_id = ? AND prod_marque = ?";
                    prepare = connection.prepareStatement(updateQtye);
                    prepare.setInt(1, newQty);
                    prepare.setDouble(2, newPrice);
                    prepare.setString(3, vente_idVente.getText());
                    prepare.setString(4, (String) vente_marque.getSelectionModel().getSelectedItem());
                    prepare.executeUpdate();


                } else {
                    // 3. Sinon -> insert ligne nouvelle
                    String insertAdmine = "INSERT INTO adminevente_backup "
                            + "(prod_id, prod_name, prod_marque, qty_v, price) "
                            + "VALUES (?, ?, ?, ?, ?)";
                    prepare = connection.prepareStatement(insertAdmine);
                    prepare.setString(1, vente_idVente.getText());
                    prepare.setString(2, vente_nom.getText());
                    prepare.setString(3, (String) vente_marque.getSelectionModel().getSelectedItem());
                    prepare.setInt(4, vente_qty.getValue());
                    prepare.setString(5, vente_price_r.getText());
                    prepare.executeUpdate();

                }
                String checkExist2 = "SELECT qty_v, price FROM adminvente2 WHERE prod_id = ? AND prod_marque = ?";
                prepare = connection.prepareStatement(checkExist2);
                prepare.setString(1, vente_idVente.getText());
                prepare.setString(2, (String) vente_marque.getSelectionModel().getSelectedItem());
                result = prepare.executeQuery();

                if (result.next()) {
                    // جلب الكمية و السعر القديم
                    int oldQty = result.getInt("qty_v");
                    double oldPrice = result.getDouble("price");

                    // الكمية و السعر الجديدين
                    int newQty = oldQty + vente_qty.getValue();
                    double addedPrice = Double.parseDouble(vente_price_r.getText());
                    double newPrice = oldPrice + addedPrice;
                    System.out.println("newPrice: " + newPrice);
                    System.out.println("newQty: " + newQty);
                    System.out.println("oldPrice: " + oldPrice);
                    System.out.println("oldQty: " + oldQty);

                    // تحديث الكمية و السعر
                    String updateQty2 = "UPDATE adminvente2 SET qty_v = ?, price = ? WHERE prod_id = ? AND prod_marque = ?";
                    prepare = connection.prepareStatement(updateQty2);
                    prepare.setInt(1, newQty);
                    prepare.setDouble(2, newPrice);
                    prepare.setString(3, vente_idVente.getText());
                    prepare.setString(4, (String) vente_marque.getSelectionModel().getSelectedItem());
                    prepare.executeUpdate();

                } else {
                    // 3. Sinon -> insert ligne nouvelle
                    String insertAdmin2 = "INSERT INTO adminvente2 "
                            + "(prod_id, prod_name, prod_marque, qty_v, price) "
                            + "VALUES (?, ?, ?, ?, ?)";
                    prepare = connection.prepareStatement(insertAdmin2);
                    prepare.setString(1, vente_idVente.getText());
                    prepare.setString(2, vente_nom.getText());
                    prepare.setString(3, (String) vente_marque.getSelectionModel().getSelectedItem());
                    prepare.setInt(4, vente_qty.getValue());
                    prepare.setString(5, vente_price_r.getText());
                    prepare.executeUpdate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void venteDeleteBtn() {
        productdata selectedVente = vente_tableview.getSelectionModel().getSelectedItem();

        if (selectedVente == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une vente à supprimer !");
            alert.showAndWait();
            return;
        }

        connection = database.connectDb();
        Date prodDateObj = selectedVente.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String prodDate = dateFormat.format(prodDateObj);

        try {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmation");
            confirm.setHeaderText(null);
            confirm.setContentText("Êtes-vous sûr de vouloir supprimer cette vente ?");
            Optional<ButtonType> option = confirm.showAndWait();

            if (option.isPresent() && option.get() == ButtonType.OK) {

                // 🧩 Suppression de la ligne exacte dans la table vente
                String deleteVente = "DELETE FROM vente WHERE id = ? AND prod_id = ? AND prod_marque = ? AND date = ?";
                prepare = connection.prepareStatement(deleteVente);
                prepare.setInt(1, selectedVente.getId());
                prepare.setString(2, selectedVente.getProductId());
                prepare.setString(3, selectedVente.getProductMarque());
                prepare.setString(4, prodDate);
                prepare.executeUpdate();

                int deletedQty = selectedVente.getQuantity();
                double deletedPrice = selectedVente.getPriceR();

                // 🔁 Mettre à jour les tables admin (soustraction, pas suppression totale)
                updateAdminTablesAfterDelete(selectedVente, deletedQty, deletedPrice);

                // 🔁 Mettre à jour le stock virtuel
                String updateStock = "UPDATE virtual_stock SET stock_rest = stock_rest + ? WHERE prod_id = ? AND prod_marque = ?";
                prepare = connection.prepareStatement(updateStock);
                prepare.setInt(1, deletedQty);
                prepare.setString(2, selectedVente.getProductId());
                prepare.setString(3, selectedVente.getProductMarque());
                prepare.executeUpdate();
                if (result != null) result.close();
                if (prepare != null) prepare.close();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Vente supprimée avec succès !");
                alert.showAndWait();

                venteShowData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void updateAdminTablesAfterDelete(productdata vente, int deletedQty, double deletedPrice) throws SQLException {
        String[] adminTables = {"adminvente", "adminevente_backup", "adminvente2"};

        for (String table : adminTables) {
            // 🔍 Vérifier si la ligne existe
            String checkExist = "SELECT qty_v, price FROM " + table + " WHERE prod_id = ? AND prod_marque = ?";
            prepare = connection.prepareStatement(checkExist);
            prepare.setString(1, vente.getProductId());
            prepare.setString(2, vente.getProductMarque());
            result = prepare.executeQuery();

            if (result.next()) {
                int oldQty = result.getInt("qty_v");
                double oldPrice = result.getDouble("price");

                int newQty = oldQty - deletedQty;
                double newPrice = oldPrice - deletedPrice;

                if (newQty <= 0 || newPrice <= 0) {
                    // 🚮 Supprimer seulement cette ligne si les valeurs deviennent 0
                    String deleteRow = "DELETE FROM " + table + " WHERE prod_id = ? AND prod_marque = ?";
                    prepare = connection.prepareStatement(deleteRow);
                    prepare.setString(1, vente.getProductId());
                    prepare.setString(2, vente.getProductMarque());
                    prepare.executeUpdate();
                } else {
                    // 🔄 Sinon, mettre à jour les valeurs
                    String updateRow = "UPDATE " + table + " SET qty_v = ?, price = ? WHERE prod_id = ? AND prod_marque = ?";
                    prepare = connection.prepareStatement(updateRow);
                    prepare.setInt(1, newQty);
                    prepare.setDouble(2, newPrice);
                    prepare.setString(3, vente.getProductId());
                    prepare.setString(4, vente.getProductMarque());
                    prepare.executeUpdate();
                }
            }
        }
    }


    public void venteUpdateBtn() {
        productdata selectedProduct = vente_tableview.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            System.out.println("يرجى اختيار منتج من الجدول أولاً.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un produit à modifier.");
            alert.showAndWait();
            return;
        }

        String id = String.valueOf(selectedProduct.getId());
        String newPriceText = vente_price_r.getText();
        boolean invalidPriceR = vente_price_r.getText() == null
                || vente_price_r.getText().isEmpty()
                || !vente_price_r.getText().matches("\\d*(\\.\\d*)?");

        if (newPriceText == null || newPriceText.trim().isEmpty()||invalidPriceR) {
            System.out.println("يرجى إدخال السعر الجديد.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Entrer le prix reel correctement SVP");
            alert.showAndWait();
            return;
        }

        try {
            String selectProdSQL = "SELECT prod_id, prod_marque, price_sell_r FROM vente WHERE id = ?";
            PreparedStatement selectStmt = connection.prepareStatement(selectProdSQL);
            selectStmt.setString(1, id);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                String idProd = rs.getString("prod_id");
                String marque = rs.getString("prod_marque");
                double oldPrice = rs.getDouble("price_sell_r");  // السعر القديم الأصلي

                // 2. نجيبو السعر الجديد من TextField

                double newPrice =  Double.parseDouble(newPriceText);
                // 3. نحسبو الفرق
                double diff2 = newPrice - oldPrice;
                System.out.println("Ancien prix: " + oldPrice);
                System.out.println("Nouveau prix: " + newPrice);
                System.out.println("Différence: " + diff2);

                // 4. تحديث adminvente
                String updateAdmin1 = "UPDATE adminvente SET price = price + ? WHERE prod_id = ? AND prod_marque = ?";
                PreparedStatement ps1 = connection.prepareStatement(updateAdmin1);
                ps1.setDouble(1, diff2);
                ps1.setString(2, idProd);
                ps1.setString(3, marque);
                int rowsAffected1 = ps1.executeUpdate();

                // 5. تحديث adminvente2
                String updateAdmin2 = "UPDATE adminvente2 SET price = price + ? WHERE prod_id = ? AND prod_marque = ?";
                PreparedStatement ps2 = connection.prepareStatement(updateAdmin2);
                ps2.setDouble(1, diff2);
                ps2.setString(2, idProd);
                ps2.setString(3, marque);
                int rowsAffected2 = ps2.executeUpdate();

                // 6. بعد ما نحسب الفرق و نحدّث adminvente، نعدّلو السعر في vente
                String updateVenteSQL = "UPDATE vente SET price_sell_r = ? WHERE id = ?";
                PreparedStatement updateVente = connection.prepareStatement(updateVenteSQL);
                updateVente.setDouble(1, newPrice);
                updateVente.setString(2, id);
                updateVente.executeUpdate();

                if (rowsAffected1 > 0 || rowsAffected2 > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText(null);
                    alert.setContentText("La modification a été effectuée avec succès.");
                    alert.showAndWait();
                } else {
                    System.out.println("فشل التحديث: لم يتم العثور على المنتج.");
                }

            }

            double newPrice = Double.parseDouble(newPriceText);
            if (newPrice < 0) {
                System.out.println("السعر يجب أن يكون رقم موجب.");
                return;
            }

            String currentText = vente_price_e.getText();
            double currentPrice = Double.parseDouble(currentText);

            double diff = newPrice - currentPrice;

            // تحديث السعر في vente
            String sql = "UPDATE vente SET price_sell_r = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, newPrice);
            ps.setString(2, id);
            int rowsAffected = ps.executeUpdate();

            // تحديث credit حسب الفرق
            String creditUpdateSQL;
            if (newPrice >= currentPrice) {
                creditUpdateSQL = "UPDATE vente SET credit = 'pas de crédit' WHERE id = ?";
            } else {
                creditUpdateSQL = "UPDATE vente SET credit = 'avec crédit' WHERE id = ?";
            }
            PreparedStatement creditStmt = connection.prepareStatement(creditUpdateSQL);
            creditStmt.setString(1, id);
            creditStmt.executeUpdate();


            venteShowData();

        } catch (NumberFormatException e) {
            System.out.println("السعر غير صالح. يرجى إدخال رقم.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void venteClearBtn(){
        vente_idVente.setText("");
        vente_nom.setText("");
        vente_price_r.setText("");
        vente_price_e .setText("");
        vente_qty.getValueFactory().setValue(1);
        vente_marque.getSelectionModel().clearSelection();
    }


    public void venteSelect2() {
        productdata data =vente_tableview.getSelectionModel().getSelectedItem();
        int num = vente_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        vente_idVente.setText(String.valueOf(data.getProductId()));
        vente_nom.setText(data.getProductName());
        vente_marque.setValue(data.getProductMarque());
        vente_qty.getValueFactory().setValue(data.getQuantity());
        vente_price_e.setText(String.valueOf(data.getPriceE()));
        vente_price_r.setText(String.valueOf(data.getPriceR()));
    }
    private String selectedProdId; // نخزنو الـ ID وقت الكتابة

    public void remplirMarquesEtNomParId(String idProduit) {
        String sqlMarques = "SELECT DISTINCT prod_marque FROM product_info WHERE prod_id = ?";
        String sqlNom = "SELECT prod_name FROM product_info WHERE prod_id = ?";

        connection = database.connectDb();

        try {
            // Remplir marques
            PreparedStatement prepareMarques = connection.prepareStatement(sqlMarques);
            prepareMarques.setString(1, idProduit);
            ResultSet resultMarques = prepareMarques.executeQuery();

            Set<String> marques = new HashSet<>();
            while (resultMarques.next()) {
                marques.add(resultMarques.getString("prod_marque"));
            }

            vente_marque.getItems().clear();
            vente_marque.getItems().addAll(marques);
            if (!marques.isEmpty()) {
                Platform.runLater(() -> {
                    if (!marques.isEmpty()) {
                        vente_marque.getSelectionModel().selectFirst();
                    }
                });}

            // Remplir nom
            PreparedStatement prepareNom = connection.prepareStatement(sqlNom);
            prepareNom.setString(1, idProduit);
            ResultSet resultNom = prepareNom.executeQuery();


            if (resultNom.next()) {
                vente_nom.setText(resultNom.getString("prod_name"));
            } else {
                vente_nom.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void remplirIdEtMarquesParNom(String nomProduit) {
        String sql = "SELECT prod_id, prod_marque FROM product_info WHERE prod_name = ?";
        connection = database.connectDb();

        try {
            PreparedStatement prepare = connection.prepareStatement(sql);
            prepare.setString(1, nomProduit);
            ResultSet result = prepare.executeQuery();

            Set<String> marques = new HashSet<>();
             String idTrouve = null;

            while (result.next()) {
                if (idTrouve == null)
                    idTrouve = result.getString("prod_id");
                marques.add(result.getString("prod_marque"));
            }

            if (idTrouve != null) {
                vente_idVente.setText(idTrouve);
                vente_marque.getItems().clear();
                vente_marque.getItems().addAll(marques);
                if (!marques.isEmpty()) {
                    Platform.runLater(() -> {
                        if (!marques.isEmpty()) {
                            vente_marque.getSelectionModel().selectFirst();
                        }
                    });}
            } else {
                //vente_idVente.setText("");
                ObservableList items = vente_marque.getItems();
                items.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<productdata> venteDataList(){
        ObservableList<productdata> data = FXCollections.observableArrayList();
        String sql = "select * from vente";
        connection = database.connectDb();
        try{
            prepare=connection.prepareStatement(sql);
            result =prepare.executeQuery();
            productdata prodData ;
            while(result.next()){
                prodData =new productdata(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("prod_marque"),
                        result.getInt("qty"),
                        result.getDouble("price_sell_e"),
                        result.getDouble("price_sell_r"),
                        result.getDate("date"),
                        result.getString("type"),
                        result.getString("credit"));
                data.add(prodData);


            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    private FilteredList<productdata> filter;
    private SortedList<productdata> sortList;
    private ObservableList<productdata> venteListData ;
    public void venteShowData() {
        // Configuration des cellules comme avant...
        venteListData = venteDataList();

        vente_col_idProd.setCellValueFactory(new PropertyValueFactory<>("productId"));
        vente_col_nom.setCellValueFactory(new PropertyValueFactory<>("productName"));
        vente_col_marque.setCellValueFactory(new PropertyValueFactory<>("productMarque"));
        vente_col_stock.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        vente_col_price_e.setCellValueFactory(new PropertyValueFactory<>("priceE"));
        vente_col_price_r.setCellValueFactory(new PropertyValueFactory<>("priceR"));
        vente_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        vente_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        vente_col_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        // Obtenir la date d'aujourd'hui

        vente_tableview.setItems(venteListData);
    }
    public void venteComboData(){
        vente_idVente.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                remplirMarquesEtNomParId(newValue);
            } else {
                vente_marque.getItems().clear();
                //vente_nom.clear();
            }
        });
        vente_nom.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.trim().isEmpty()) {
                remplirIdEtMarquesParNom(newVal);
            } else {
                //vente_idVente.clear();
                vente_marque.getItems().clear();
            }
        });
    }
    public void spinQty(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 1);
        vente_qty.setValueFactory(valueFactory);
    }
    public void remplirMarquesEtNomParId1(String idProduit) {
        String sqlMarques = "SELECT DISTINCT prod_marque FROM product_info WHERE prod_id = ?";
        String sqlNom = "SELECT prod_name FROM product_info WHERE prod_id = ?";

        connection = database.connectDb();

        try {
            // Remplir marques
            PreparedStatement prepareMarques = connection.prepareStatement(sqlMarques);
            prepareMarques.setString(1, idProduit);
            ResultSet resultMarques = prepareMarques.executeQuery();

            Set<String> marques = new HashSet<>();
            while (resultMarques.next()) {
                marques.add(resultMarques.getString("prod_marque"));
            }

            Analyse_marque.getItems().clear();
            Analyse_marque.getItems().addAll(marques);

            if (!marques.isEmpty()) {
                Platform.runLater(() -> {
                            if (!marques.isEmpty()) {
                                Analyse_marque.getSelectionModel().selectFirst();
                            }
                        });}


            // Remplir nom
            PreparedStatement prepareNom = connection.prepareStatement(sqlNom);
            prepareNom.setString(1, idProduit);
            ResultSet resultNom = prepareNom.executeQuery();

            if (resultNom.next()) {
                Analyse_nom.setText(resultNom.getString("prod_name"));
            } else {
                //Analyse_nom.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void remplirIdEtMarquesParNom1(String nomProduit) {
        String sql = "SELECT prod_id, prod_marque FROM product_info WHERE prod_name = ?";
        connection = database.connectDb();

        try {
            PreparedStatement prepare = connection.prepareStatement(sql);
            prepare.setString(1, nomProduit);
            ResultSet result = prepare.executeQuery();

            Set<String> marques = new HashSet<>();
            String idTrouve = null;

            while (result.next()) {
                if (idTrouve == null)
                    idTrouve = result.getString("prod_id");
                marques.add(result.getString("prod_marque"));
            }

            if (idTrouve != null) {
                Analyse_idProduct.setText(idTrouve);
                Analyse_marque.getItems().clear();
                Analyse_marque.getItems().addAll(marques);
                if (!marques.isEmpty()) {
                    Platform.runLater(() -> {
                        if (!marques.isEmpty()) {
                            Analyse_marque.getSelectionModel().selectFirst();
                        }
                    });}
            } else {
                //Analyse_idProduct.clear();
                Analyse_marque.getItems().clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void venteComboData1(){
        Analyse_idProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                remplirMarquesEtNomParId1(newValue);
            } else {
                Analyse_marque.getItems().clear();
                Analyse_nom.clear();
            }
        });
        Analyse_nom.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.trim().isEmpty()) {
                remplirIdEtMarquesParNom1(newVal);
            } else {
               // Analyse_idProduct.clear();
                Analyse_marque.getItems().clear();
            }
        });
    }
    public void venteSearch() {

        FilteredList<productdata> filter = new FilteredList<>(venteListData, e -> true);

        vente_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateEmployeeData.getProductId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getProductMarque().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getCredit().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDate().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateEmployeeData.getQuantity()).toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return  false;
                }
            });
        });

        SortedList<productdata> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(vente_tableview.comparatorProperty());
        vente_tableview.setItems(sortList);
    }
    public void rempPriceR(){
        vente_price_e.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!vente_price_r.isFocused()) {
                vente_price_r.setText(newValue);
            }
        });
    }
    public void venteSearch3() {

        FilteredList<productdata> filter = new FilteredList<>(recList, e -> true);

        rec_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateEmployeeData.getProductId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getProductMarque().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return  false;
                }
            });
        });

        SortedList<productdata> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(rec_tableview.comparatorProperty());
        rec_tableview.setItems(sortList);
    }


    public void switchForm(ActionEvent event) {



       if (event.getSource() == inventory_btn) {
            inventory_form.setVisible(true);

            historic_form.setVisible(false);
            analyse_form.setVisible(false);
            vente_form.setVisible(false);
            charge_form.setVisible(false);
            rec_form.setVisible(false);
            inventoryTypeList();
            inventoryStatusList();


        }
        else if (event.getSource() == historic_btn) {
            inventory_form.setVisible(false);
            analyse_form.setVisible(false);
            historic_form.setVisible(true);
            vente_form.setVisible(false);
            charge_form.setVisible(false);
            rec_form.setVisible(false);

            historicShowListData();
            inventorySearch();}

        else if (event.getSource() == analyse_btn) {

            inventory_form.setVisible(false);
            analyse_form.setVisible(true);
            historic_form.setVisible(false);
            vente_form.setVisible(false);
            charge_form.setVisible(false);
            rec_form.setVisible(false);

            analyseComboData();
            analyseFournisseurParMois();
            analyseFournisseurParMois1();
            dashboardChart();
            venteComboData1();


        } else if (event.getSource()==ventBtn) {
            inventory_form.setVisible(false);
            analyse_form.setVisible(false);
            historic_form.setVisible(false);
            vente_form.setVisible(true);
            charge_form.setVisible(false);
            rec_form.setVisible(false);
            venteComboData();




        } else if (event.getSource()==chargeBtn) {
            inventory_form.setVisible(false);
            analyse_form.setVisible(false);
            historic_form.setVisible(false);
            vente_form.setVisible(false);
            charge_form.setVisible(true);
            rec_form.setVisible(false);
            chargeShowData();
            loadChargeDescriptions();
        }
        else if (event.getSource()==recBtn){
            inventory_form.setVisible(false);
            analyse_form.setVisible(false);
            historic_form.setVisible(false);
            vente_form.setVisible(false);
            charge_form.setVisible(false);
            rec_form.setVisible(true);


            recComboData();

        }
    }
    public void logout() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Est ce que T'est sure de se Deconnecter?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                System.out.println("OK click");
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });



                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de agent.fxml");
        }

    }

    // Valeurs dynamiques calculées par ton app

    public void initialize(URL location, ResourceBundle resources) {
        switchForm(new ActionEvent(inventory_btn, null));
        inventoryTypeList();
        inventoryStatusList();
        inventoryShowData();
        inventorySearch3();

        historicShowListData();
        venteShowData();
        venteComboData();
        venteComboData1();
        spinQty();
        spinQty2();
        chargeNothing();
        rempPriceR();
        recComboData();
        recShowListData();
        System.out.println("🕒 Heure système : " + LocalDateTime.now());
        ;

    }
}
