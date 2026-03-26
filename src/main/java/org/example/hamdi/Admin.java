package org.example.hamdi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

public class Admin implements Initializable {
    @FXML
    private Button logoutAdmin;
    @FXML
    private TableColumn<productdata,String> admin_col1_idProd;

    @FXML
    private TableColumn<productdata,String> admin_col1_marque;
    @FXML
    private ComboBox<Integer> admin_year1;

    @FXML
    private ComboBox<Integer> admin_year2;

    @FXML
    private ComboBox<Integer> admin_year3;
    @FXML
    private TableColumn<productdata,String> admin_col1_nom;

    @FXML
    private TableColumn<productdata,String> admin_col1_price;

    @FXML
    private TableColumn<productdata,String> admin_col1_stock;
    @FXML
    private Button admin_btn4;
    @FXML
    private ComboBox<Integer> ben_combo1;

    @FXML
    private ComboBox<String> ben_combo2;

    @FXML
    private ComboBox<Integer> ben_combo3;

    @FXML
    private Label ben_label;
    @FXML
    private TextField vente_search;
    @FXML
    private TableColumn<productdata,String> admin_col2_idProd;
    @FXML
    private ComboBox<String> admin_month1;
    @FXML
    private ComboBox<String> admin_month2;

    @FXML
    private Text date;
    @FXML
    private TableColumn<productdata,String> admin_col2_marque;

    @FXML
    private TableColumn<productdata,String> admin_col2_nom;
    @FXML
    private TableColumn<productdata,String> admin_col2_stock;

    @FXML
    private TableColumn<productdata,String> admin_col2_price;

    @FXML
    private TableView<productdata> admin_tableview1;

    @FXML
    private Label Capital;

    @FXML
    private AnchorPane form4;
    @FXML
    private TextField admin_search1;
    @FXML
    private TextField admin_search2;


    @FXML
    private TableView<productdata> admin_tableview2;
    @FXML
    private Button admin_btn1;

    @FXML
    private Button admin_btn2;
    @FXML
    private Button admin_btn3;
    @FXML
    private AreaChart<String, Number> chart4;
    @FXML
    private AreaChart<String, Number> admin_chart2;
    @FXML
    private AreaChart<String, Number> admin_chart1;

    @FXML
    private AnchorPane admin_form1;

    @FXML
    private AnchorPane admin_form2;
    @FXML
    private AnchorPane admin_form3;

    @FXML
    private Label admin_valeur1;

    @FXML
    private Label admin_valeur2;
    @FXML
    private ComboBox<Integer> admin_jour;
    @FXML
    private Label admin_valeur3;
    @FXML
    private BarChart<String,Number> admin_barchart1;

    @FXML
    private BarChart<String,Number> admin_barchart2;





    Connection connection ;
    private PreparedStatement  prepare;
    private ResultSet result;
    public void adminDeleteBtn1() {
        productdata data = admin_tableview1.getSelectionModel().getSelectedItem();

        if (data == null) {
            System.out.println("Aucune ligne sélectionnée !");
            return;
        }

        String prodId = data.getProductId();
        String prodMarque = data.getProductMarque();

        String sql = "DELETE FROM adminvente WHERE prod_id = ? AND prod_marque = ?";

        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, prodId);
            prepare.setString(2, prodMarque);
            prepare.executeUpdate();

            System.out.println("Produit supprimé avec succès.");
            adminShowListData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void adminDeleteBtn2() {
        productdata data = admin_tableview2.getSelectionModel().getSelectedItem();

        if (data == null) {
            System.out.println("Aucune ligne sélectionnée !");
            return;
        }

        String prodId = data.getProductId();
        String prodMarque = data.getProductMarque();

        String sql = "DELETE FROM adminvente2 WHERE prod_id = ? AND prod_marque = ?";

        try {
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, prodId);
            prepare.setString(2, prodMarque);
            prepare.executeUpdate();

            System.out.println("Produit supprimé avec succès.");
            adminShowListData2();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<productdata> aVenteListData() {

        ObservableList<productdata> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM adminvente order by qty_v desc ";

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
                        result.getInt("qty_v"),
                        result.getDouble("price"));

                listData.add(prodD);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return listData;
    }

    private ObservableList<productdata> salaryList;

    public void adminShowListData() {
        salaryList = aVenteListData();

        admin_col1_idProd.setCellValueFactory(new PropertyValueFactory<>("productId"));
        admin_col1_nom.setCellValueFactory(new PropertyValueFactory<>("productName"));
        admin_col1_marque.setCellValueFactory(new PropertyValueFactory<>("productMarque"));
        admin_col1_stock.setCellValueFactory(new PropertyValueFactory<>("quantity1"));
        admin_col1_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        admin_tableview1.setItems(salaryList);

    }
    public ObservableList<productdata> aVenteListData2() {

        ObservableList<productdata> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM adminvente2 order by price desc ";

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
                        result.getInt("qty_v"),
                        result.getDouble("price"));

                listData.add(prodD);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return listData;
    }

    private ObservableList<productdata> salaryList2;

    public void adminShowListData2() {
        salaryList2 = aVenteListData2();

        admin_col2_idProd.setCellValueFactory(new PropertyValueFactory<>("productId"));
        admin_col2_nom.setCellValueFactory(new PropertyValueFactory<>("productName"));
        admin_col2_marque.setCellValueFactory(new PropertyValueFactory<>("productMarque"));
        admin_col2_stock.setCellValueFactory(new PropertyValueFactory<>("quantity1"));
        admin_col2_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        admin_tableview2.setItems(salaryList2);

    }
    public void adminComboData(){
        int currentYear = Year.now().getValue();
        for (int i = currentYear - 3; i <= currentYear + 10; i++) {
            admin_year1.getItems().add(i);
            admin_year2.getItems().add(i);
            admin_year3.getItems().add(i);
            ben_combo3.getItems().add(i);
        }
        admin_year1.setValue(currentYear);
        admin_year2.setValue(currentYear);
        admin_year3.setValue(currentYear);
        ben_combo3.setValue(currentYear);
    }
    public void aadminParAnnee() {
        Integer selectedYear = admin_year3.getValue();

        if (selectedYear == null ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une année .");
            alert.showAndWait();
            return;
        }

        String sqlVente = "SELECT IFNULL(SUM(price_sell_r), 0) FROM vente WHERE YEAR(date) = ?";
        String sqlRec = "SELECT IFNULL(SUM(rec_price ), 0) FROM rec WHERE YEAR(rec_dateR) = ?";

        double totalVente = 0;
        double totalRec = 0;

        try (
                PreparedStatement pstVente = connection.prepareStatement(sqlVente);
                PreparedStatement pstRec = connection.prepareStatement(sqlRec)
        ) {
            pstVente.setInt(1, selectedYear);
            pstRec.setInt(1, selectedYear);

            ResultSet rsVente = pstVente.executeQuery();
            if (rsVente.next()) {
                totalVente = rsVente.getDouble(1);
            }

            ResultSet rsRec = pstRec.executeQuery();
            if (rsRec.next()) {
                totalRec = rsRec.getDouble(1);
            }

            double profit = totalVente - totalRec;
            System.out.println("Vente: " + totalVente + " | Rec: " + totalRec + " | Profit: " + profit);

            admin_valeur1.setText(String.format("%.3f DT", profit));
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur BD");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors du calcul : " + e.getMessage());
            alert.showAndWait();
        }
    }
    public void adminParMois(){

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
        admin_month1.setItems(frenchMonths);
        int currentMonthNumber = LocalDate.now().getMonthValue();
        String currentMonthInFrench = frenchMonths.get(currentMonthNumber - 1);

        String selectedMonthName = admin_month1.getValue();
        Integer selectedYear = admin_year2.getValue();


        if (selectedYear == null|| selectedMonthName == null ) {
            admin_valeur2.setText("0.000 DT");

            return;
        }


        Integer selectedMonthNumber = moisMap.get(selectedMonthName);

        // Requête SQL
        String sql = """
    SELECT 
        (
            SELECT IFNULL(SUM(price_sell_r), 0) 
            FROM vente 
            WHERE YEAR(date) = ? AND MONTH(date) = ?
        ) -
        (
            SELECT IFNULL(SUM(rec_price), 0) 
            FROM rec 
            WHERE YEAR(rec_dateR) = ? AND MONTH(rec_dateR) = ?
        ) AS profit_mensuel
""";

        connection = database.connectDb();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, selectedYear);
            pst.setInt(2, selectedMonthNumber);
            pst.setInt(3, selectedYear);
            pst.setInt(4, selectedMonthNumber);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double profit = rs.getDouble("profit_mensuel");
                admin_valeur2.setText(String.format("%.3f DT", profit));
            } else {
                admin_valeur2.setText("0.000 DT");
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
    public void adminParJour() {

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
        admin_month2.setItems(frenchMonths);

        ObservableList<Integer> days = FXCollections.observableArrayList();
        for (int i = 1; i <= 31; i++) {
            days.add(i);
        }
        admin_jour.setItems(days);

        String selectedMonthName = admin_month2.getValue();
        Integer selectedYear = admin_year1.getValue();
        Integer selectDay = admin_jour.getValue();

        if (selectedYear == null || selectedMonthName == null || selectDay == null) {
            admin_valeur3.setText("0.000 DT");

            return;
        }

        Integer selectedMonthNumber = moisMap.get(selectedMonthName);

        String sql = """
        SELECT 
            IFNULL((SELECT SUM(price_sell_r) FROM vente WHERE YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ?), 0) AS total_vente,
            IFNULL((SELECT SUM(rec_price) FROM rec WHERE YEAR(rec_dateR) = ? AND MONTH(rec_dateR) = ? AND DAY(rec_dateR) = ?), 0) AS total_rec
    """;

        connection = database.connectDb();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, selectedYear);
            pst.setInt(2, selectedMonthNumber);
            pst.setInt(3, selectDay);
            pst.setInt(4, selectedYear);
            pst.setInt(5, selectedMonthNumber);
            pst.setInt(6, selectDay);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double totalVente = rs.getDouble("total_vente");
                double totalRec = rs.getDouble("total_rec");
                double profit = totalVente - totalRec;
                System.out.println(totalVente);
                System.out.println(totalRec);
                System.out.println(profit);

                // لون الربح
                if (profit >= 0) {
                    admin_valeur3.setStyle("-fx-text-fill: green;");
                } else {
                    admin_valeur3.setStyle("-fx-text-fill: red;");
                }

                // عرض القيم
                admin_valeur3.setText(String.format("%.3f DT", profit));
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
    public void adminParJour2() {
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
        ben_combo2.setItems(frenchMonths);

        int currentMonthNumber = LocalDate.now().getMonthValue();
        String currentMonthInFrench = frenchMonths.get(currentMonthNumber - 1);

        ObservableList<Integer> days = FXCollections.observableArrayList();
        for (int i = 1; i <= 31; i++) {
            days.add(i);
        }
        ben_combo1.setItems(days);

        String selectedMonthName = ben_combo2.getValue();
        Integer selectedYear = ben_combo3.getValue();
        Integer selectDay = ben_combo1.getValue();

        if (selectedYear == null || selectedMonthName == null || selectDay == null) {
            ben_label.setText("0.000 DT");
            return;
        }

        Integer selectedMonthNumber = moisMap.get(selectedMonthName);

        // Requête SQL combinée
        String sql = """
       SELECT\s
           COALESCE(SUM((v.price_sell_r - p.price_buy_u_TVA * v.qty)), 0) AS total_vente,
           (
               SELECT COALESCE(SUM((r.rec_price - p2.price_buy_u_TVA * r.rec_qt)), 0)
               FROM rec r
               JOIN (
                   SELECT prod_id, ((MAX(price_buy_u_TVA) - MIN(price_buy_u_TVA)) * 0.85 + MIN(price_buy_u_TVA)) AS price_buy_u_TVA
                   FROM product_info
                   GROUP BY prod_id
               ) p2 ON r.rec_id = p2.prod_id
               WHERE YEAR(r.rec_dateR) = ? AND MONTH(r.rec_dateR) = ? AND DAY(r.rec_dateR) = ?
           ) AS total_rec
       FROM vente v
       JOIN (
           SELECT prod_id, ((MAX(price_buy_u_TVA) - MIN(price_buy_u_TVA)) * 0.85 + MIN(price_buy_u_TVA)) AS price_buy_u_TVA
           FROM product_info
           GROUP BY prod_id
       ) p ON v.prod_id = p.prod_id
       WHERE YEAR(v.date) = ? AND MONTH(v.date) = ? AND DAY(v.date) = ?
       
    """;

        connection = database.connectDb();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            // paramètres pour les deux WHERE (vente et rec)
            pst.setInt(1, selectedYear);
            pst.setInt(2, selectedMonthNumber);
            pst.setInt(3, selectDay);
            pst.setInt(4, selectedYear);
            pst.setInt(5, selectedMonthNumber);
            pst.setInt(6, selectDay);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double totalVente = rs.getDouble("total_vente");
                double totalRec = rs.getDouble("total_rec");

                double profit = totalVente - totalRec;
                System.out.println(totalVente);
                System.out.println(totalRec);
                System.out.println(profit);

                ben_label.setText(String.format("%.3f DT", profit));
            } else {
                ben_label.setText("0.000 DT");
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
    public void dashboardChart4(LocalDate selectedDate) {
        chart4.getData().clear();

        // Requête SQL: calcule du bénéfice par jour
        String sql = """
        SELECT
            DATE(v.date) AS day,
            COALESCE(SUM((v.price_sell_r - p.price_buy_u_TVA * v.qty)), 0) AS total_vente,
            (
                SELECT COALESCE(SUM((r.rec_price - p2.price_buy_u_TVA * r.rec_qt)), 0)
                FROM rec r
                JOIN (
                    SELECT prod_id, ((MAX(price_buy_u_TVA) - MIN(price_buy_u_TVA)) * 0.85 + MIN(price_buy_u_TVA)) AS price_buy_u_TVA
                    FROM product_info
                    GROUP BY prod_id
                ) p2 ON r.rec_id = p2.prod_id
                WHERE YEAR(r.rec_dateR) = ? AND MONTH(r.rec_dateR) = ? AND DAY(r.rec_dateR) = ?
            ) AS total_rec
        FROM vente v
        JOIN (
            SELECT prod_id, ((MAX(price_buy_u_TVA) - MIN(price_buy_u_TVA)) * 0.85 + MIN(price_buy_u_TVA)) AS price_buy_u_TVA
            FROM product_info
            GROUP BY prod_id
        ) p ON v.prod_id = p.prod_id
        WHERE YEAR(v.date) = ? AND MONTH(v.date) = ?
        GROUP BY DATE(v.date)
        ORDER BY DATE(v.date)
    """;

        connection = database.connectDb();
        XYChart.Series<String, Number> chart = new XYChart.Series<>();

        try {
            prepare = connection.prepareStatement(sql);
            prepare.setInt(1, selectedDate.getYear());        // pour total_rec
            prepare.setInt(2, selectedDate.getMonthValue());  // pour total_rec
            prepare.setInt(3, selectedDate.getDayOfMonth());  // pour total_rec

            prepare.setInt(4, selectedDate.getYear());        // pour total_vente
            prepare.setInt(5, selectedDate.getMonthValue());  // pour total_vente

            result = prepare.executeQuery();

            while (result.next()) {
                String day = result.getString("day");
                double totalVente = result.getDouble("total_vente");
                double totalRec = result.getDouble("total_rec");
                double profit = totalVente - totalRec;

                chart.getData().add(new XYChart.Data<>(day, profit));
            }

            chart4.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dashboardChart2() {
        admin_chart1.getData().clear();

        String sql = "SELECT " +
                "v.year, v.week, v.total_vente, IFNULL(r.total_rec, 0) AS total_achat, " +
                "(v.total_vente - IFNULL(r.total_rec, 0)) AS profit " +
                "FROM ( " +
                "  SELECT YEAR(date) AS year, WEEK(date, 1) AS week, SUM(price_sell_r) AS total_vente " +
                "  FROM vente " +
                "  GROUP BY year, week " +
                ") v " +
                "LEFT JOIN ( " +
                "  SELECT YEAR(rec_dateR) AS year, WEEK(rec_dateR, 1) AS week, SUM(rec_price) AS total_rec " +
                "  FROM rec " +
                "  GROUP BY year, week " +
                ") r ON v.year = r.year AND v.week = r.week " +
                "ORDER BY v.year, v.week";

        connection = database.connectDb();
        XYChart.Series<String, Number> chart = new XYChart.Series<>();
        chart.setName("Profit Hebdomadaire");

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                int year = result.getInt("year");
                int week = result.getInt("week");
                double profit = result.getDouble("profit");

                String label = year + "-S-" + String.format("%02d", week);
                chart.getData().add(new XYChart.Data<>(label, profit));
            }

            admin_chart1.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dashboardChart3() {
        admin_chart2.getData().clear();

        String sql = "SELECT " +
                "v.year, v.quarter, v.total_vente, IFNULL(r.total_rec, 0) AS total_achat, " +
                "(v.total_vente - IFNULL(r.total_rec, 0)) AS profit " +
                "FROM ( " +
                "  SELECT YEAR(date) AS year, QUARTER(date) AS quarter, SUM(price_sell_r) AS total_vente " +
                "  FROM vente " +
                "  GROUP BY year, quarter " +
                ") v " +
                "LEFT JOIN ( " +
                "  SELECT YEAR(rec_dateR) AS year, QUARTER(rec_dateR) AS quarter, SUM(rec_price) AS total_rec " +
                "  FROM rec " +
                "  GROUP BY year, quarter " +
                ") r ON v.year = r.year AND v.quarter = r.quarter " +
                "ORDER BY v.year, v.quarter";

        connection = database.connectDb();
        XYChart.Series<String, Number> chart = new XYChart.Series<>();
        chart.setName("Profit Trimestriel");

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                int year = result.getInt("year");
                int quarter = result.getInt("quarter");
                double profit = result.getDouble("profit");

                String label = year + "-T-" + quarter;
                chart.getData().add(new XYChart.Data<>(label, profit));
            }

            admin_chart2.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void afficherDateAvecSemaine() {


        LocalDateTime maintenant = LocalDateTime.now();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


        int numeroSemaine = maintenant.get(WeekFields.of(Locale.FRANCE).weekOfWeekBasedYear());


        int month = maintenant.getMonthValue();
        int trimester;
        if (month >= 1 && month <= 3) {
            trimester = 1;
        } else if (month >= 4 && month <= 6) {
            trimester = 2;
        } else if (month >= 7 && month <= 9) {
            trimester = 3;
        } else {
            trimester = 4;
        }


        String texteFinal = maintenant.format(formatter)
                + " | Semaine n°" + numeroSemaine
                + " | Trimestre : " + trimester;


        date.setText(texteFinal);
    }
    public Map<String, Double> calculateMonthlyProfit(Connection conn) throws SQLException {
        Map<String, Double> profitParMonth = new LinkedHashMap<>();

        // الشهر والسنة الحاليين
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        // Query المصاريف (charges) حسب الشهر الحالي
        String chargeQuery = """
        SELECT 
            SUM(price_charge) AS total_charge
        FROM charge
        WHERE YEAR(date) = ? AND MONTH(date) = ?
    """;

        // Query المبيعات - الشراء + bénéfice récupération
        String sql = """
        SELECT 
            YEAR(v.date) AS year,
            MONTH(v.date) AS month,
            COALESCE(SUM((v.price_sell_r - p.price_buy_u_TVA * v.qty)), 0) AS total_vente,
            (
                SELECT COALESCE(SUM((r.rec_price - p2.price_buy_u_TVA * r.rec_qt)), 0)
                FROM rec r
                JOIN (
                    SELECT prod_id, ((MAX(price_buy_u_TVA) - MIN(price_buy_u_TVA)) * 0.85 + MIN(price_buy_u_TVA)) AS price_buy_u_TVA
                    FROM product_info
                    GROUP BY prod_id
                ) p2 ON r.rec_id = p2.prod_id
                WHERE YEAR(r.rec_dateR) = YEAR(v.date)
                  AND MONTH(r.rec_dateR) = MONTH(v.date)
            ) AS benefice_rec
        FROM vente v
        JOIN (
            SELECT prod_id, ((MAX(price_buy_u_TVA) - MIN(price_buy_u_TVA)) * 0.85 + MIN(price_buy_u_TVA)) AS price_buy_u_TVA
            FROM product_info
            GROUP BY prod_id
        ) p ON v.prod_id = p.prod_id
        WHERE YEAR(v.date) = ? AND MONTH(v.date) = ?
        GROUP BY YEAR(v.date), MONTH(v.date)
    """;

        // نخزنو charges الشهرية
        double totalCharge = 0.0;
        try (PreparedStatement ps = conn.prepareStatement(chargeQuery)) {
            ps.setInt(1, year);
            ps.setInt(2, month);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalCharge = rs.getDouble("total_charge");
                }
            }
        }

        // نحسبو الربح الشهري: (vente brute + bénéfice récupérations) - charges
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int yr = rs.getInt("year");
                    int mn = rs.getInt("month");
                    double totalVente = rs.getDouble("total_vente");
                    double beneficeRec = rs.getDouble("benefice_rec");

                    double netProfit = totalVente - beneficeRec - totalCharge;

                    String key = yr + "-" + String.format("%02d", mn);
                    profitParMonth.put(key, netProfit);

                    System.out.println("Mois: " + key);
                    System.out.println("Vente brute: " + totalVente);
                    System.out.println("Bénéfice récupération: " + beneficeRec);
                    System.out.println("Charges: " + totalCharge);
                    System.out.println("Profit net: " + netProfit);
                }
            }
        }

        return profitParMonth;
    }
    public void showProfitBarChart(Map<String, Double> profitData) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Bénéfices Mensuels");

        for (Map.Entry<String, Double> entry : profitData.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        admin_barchart1.getData().clear();
        admin_barchart1.getData().add(series);
    }
    public void benefice(){
        try {
            connection = database.connectDb();
            Map<String, Double> data = calculateMonthlyProfit(connection);
            showProfitBarChart(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Double> calculateYearProfit(Connection conn) throws SQLException {
        Map<String, Double> profitParYear = new LinkedHashMap<>();

        // 1. Requête pour récupérer les charges par année
        String chargeQuery = """
        SELECT 
            YEAR(date) AS year,
            SUM(price_charge) AS total_charge
        FROM charge
        GROUP BY year
    """;

        // 2. Requête principale qui récupère la vente brute et le bénéfice des récupérations par année
        String venteQuery = """
        SELECT 
            YEAR(v.date) AS year,
            COALESCE(SUM((v.price_sell_r - p.price_buy_u_TVA * v.qty)), 0) AS total_vente,
            (
                SELECT COALESCE(SUM((r.rec_price - p2.price_buy_u_TVA * r.rec_qt)), 0)
                FROM rec r
                JOIN (
                    SELECT prod_id, ((MAX(price_buy_u_TVA) - MIN(price_buy_u_TVA)) * 0.85 + MIN(price_buy_u_TVA)) AS price_buy_u_TVA
                    FROM product_info
                    GROUP BY prod_id
                ) p2 ON r.rec_id = p2.prod_id
                WHERE YEAR(r.rec_dateR) = YEAR(v.date)
            ) AS benefice_rec
        FROM vente v
        JOIN (
            SELECT prod_id, ((MAX(price_buy_u_TVA) - MIN(price_buy_u_TVA)) * 0.85 + MIN(price_buy_u_TVA)) AS price_buy_u_TVA
            FROM product_info
            GROUP BY prod_id
        ) p ON v.prod_id = p.prod_id
        GROUP BY YEAR(v.date)
        ORDER BY YEAR(v.date)
    """;

        // 3. Map pour stocker les charges par année
        Map<String, Double> chargeParYear = new HashMap<>();

        try (PreparedStatement ps = conn.prepareStatement(chargeQuery);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String year = rs.getString("year");
                double totalCharge = rs.getDouble("total_charge");
                chargeParYear.put(year, totalCharge);
            }
        }

        // 4. Calcul du bénéfice net annuel
        try (PreparedStatement ps = conn.prepareStatement(venteQuery);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String year = rs.getString("year");
                double totalVente = rs.getDouble("total_vente");
                double beneficeRec = rs.getDouble("benefice_rec");
                double totalCharge = chargeParYear.getOrDefault(year, 0.0);

                double netProfit = totalVente - beneficeRec - totalCharge;

                System.out.println("Année: " + year);
                System.out.println("Vente brute: " + totalVente);
                System.out.println("Bénéfice récupération: " + beneficeRec);
                System.out.println("Charges: " + totalCharge);
                System.out.println("Profit net: " + netProfit);

                profitParYear.put(year, netProfit);
            }
        }

        return profitParYear;
    }
    public void showProfitBarChart2(Map<String, Double> profitData) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();


        for (Map.Entry<String, Double> entry : profitData.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        admin_barchart2.getData().clear();
        admin_barchart2.getData().add(series);
    }
    public void benefice2(){
        try {
            connection = database.connectDb();
            Map<String, Double> data = calculateYearProfit(connection);
            showProfitBarChart2(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void venteSearch() {

        FilteredList<productdata> filter = new FilteredList<>(salaryList, e -> true);

        admin_search1.textProperty().addListener((Observable, oldValue, newValue) -> {

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

        sortList.comparatorProperty().bind(admin_tableview1.comparatorProperty());
        admin_tableview1.setItems(sortList);
    }
    public void venteSearch2() {

        FilteredList<productdata> filter = new FilteredList<>(salaryList2, e -> true);

        admin_search2.textProperty().addListener((Observable, oldValue, newValue) -> {

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

        sortList.comparatorProperty().bind(admin_tableview2.comparatorProperty());
        admin_tableview2.setItems(sortList);
    }
    public void showCapital() {
        Connection connection = null;
        PreparedStatement getVirtual = null;
        PreparedStatement getPrice = null;
        ResultSet rsVirtual = null;
        ResultSet rsPrice = null;

        double totalCapital = 0.0;

        try {
            connection = database.connectDb();

            // نجيب كل المخزون من virtual_stock
            String queryVirtual = "SELECT prod_id, prod_marque, stock_rest FROM virtual_stock";
            getVirtual = connection.prepareStatement(queryVirtual);
            rsVirtual = getVirtual.executeQuery();

            while (rsVirtual.next()) {
                String prodId = rsVirtual.getString("prod_id");
                String prodMarque = rsVirtual.getString("prod_marque");
                double stockRest = rsVirtual.getDouble("stock_rest");

                // نجيب سعر الشراء من جدول product حسب prod_id و prod_marque
                String queryPrice = "SELECT price_buy_u_TVA FROM product_info WHERE prod_id = ? AND prod_marque = ?";
                getPrice = connection.prepareStatement(queryPrice);
                getPrice.setString(1, prodId);
                getPrice.setString(2, prodMarque);
                rsPrice = getPrice.executeQuery();

                if (rsPrice.next()) {
                    double priceBuy = rsPrice.getDouble("price_buy_u_TVA");
                    totalCapital += priceBuy * stockRest;
                }

                // نغلق الموارد الجزئية في كل دورة
                rsPrice.close();
                getPrice.close();
            }

            // نعرض النتيجة في الـLabel
            Capital.setText(String.format("%.2f DT", totalCapital));
            System.out.println("Total Capital: " + totalCapital);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsVirtual != null) rsVirtual.close();
                if (getVirtual != null) getVirtual.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }


    public void switchForm(ActionEvent event) {

        if (event.getSource() ==admin_btn1) {
            admin_form1.setVisible(true);

            admin_form2.setVisible(false);
            admin_form3.setVisible(false);
            form4.setVisible(false);




        } else if (event.getSource() == admin_btn2) {
            admin_form2.setVisible(true);

            admin_form1.setVisible(false);
            admin_form3.setVisible(false);
            form4.setVisible(false);
        }
        else if (event.getSource() == admin_btn3) {
            admin_form2.setVisible(false);

            admin_form1.setVisible(false);
            admin_form3.setVisible(true);
            form4.setVisible(false);

        }
        else if (event.getSource() == admin_btn4) {
            admin_form2.setVisible(false);

            admin_form1.setVisible(false);
            admin_form3.setVisible(false);
            form4.setVisible(true);

        }

    }


    private double x=0 ;
    private double y=0 ;
    public void logoutAdmin() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Est ce que T'est sure de se Deconnecter?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                System.out.println("OK click");
                logoutAdmin.getScene().getWindow().hide();
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
    public void initialize(URL location, ResourceBundle resources) {
        switchForm(new ActionEvent(admin_btn1, null));
      adminShowListData();
      adminShowListData2();
      adminComboData();
      adminParMois();
      adminParJour();
      dashboardChart2();
      dashboardChart4(LocalDate.now());
      afficherDateAvecSemaine();
      dashboardChart3();


      benefice();
      benefice2();
      adminParJour2();
    }


}
