package org.example.hamdi;
import org.example.hamdi.Admin;
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

public class Api implements Initializable {
    public double capital;
    public Double showCapital2() {
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

            // نعرض النتيجة في الـLabe
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
         return totalCapital;
    }
    public Api() {
        // ici on appelle la fonction et on stocke son résultat dans la variable publique
        this.capital = showCapital2();
    }
    public double getVenteToday() {
        double totalVente = 0.0;

        LocalDate today = LocalDate.now(); // اليوم الحالي
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        String sql = """
        SELECT 
            IFNULL((SELECT SUM(price_sell_r) FROM vente WHERE YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ?), 0) AS total_vente,
            IFNULL((SELECT SUM(rec_price) FROM rec WHERE YEAR(rec_dateR) = ? AND MONTH(rec_dateR) = ? AND DAY(rec_dateR) = ?), 0) AS total_rec
    """;

        try (Connection connection = database.connectDb();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            // تمرير قيم السنة والشهر واليوم في كل SELECT
            pst.setInt(1, year);
            pst.setInt(2, month);
            pst.setInt(3, day);
            pst.setInt(4, year);
            pst.setInt(5, month);
            pst.setInt(6, day);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double totalRec = rs.getDouble("total_rec"); // المبلغ المسترجع
                totalVente = rs.getDouble("total_vente") - totalRec; // الربح اليوم فقط
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalVente;
    }
    double profitToday = getVenteToday();
    public double getProfitCurrentMonth() {
        double profit = 0.0;

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

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

        try (Connection connection = database.connectDb();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, year);
            pst.setInt(2, month);
            pst.setInt(3, year);
            pst.setInt(4, month);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                profit = rs.getDouble("profit_mensuel");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profit;
    }
    Double VenteMensuel = getProfitCurrentMonth();
    public double getProfitToday() {
        double profit = 0.0;

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

         String sql = """
       SELECT
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

        try (Connection connection = database.connectDb();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            // تمرير السنة والشهر واليوم لكل من vente و rec
            pst.setInt(1, year);
            pst.setInt(2, month);
            pst.setInt(3, day);
            pst.setInt(4, year);
            pst.setInt(5, month);
            pst.setInt(6, day);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                double totalVente = rs.getDouble("total_vente");
                double totalRec = rs.getDouble("total_rec");
                profit = totalVente - totalRec;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profit;
    }
    double BenToday = getProfitToday();
    public Double calculateMonthlyProfit2() {
        double netProfit = 0.0;

        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        // Query المصاريف (charges) للشهر الحالي
        String chargeQuery = """
        SELECT SUM(price_charge) AS total_charge
        FROM charge
        WHERE YEAR(date) = ? AND MONTH(date) = ?
    """;

        // Query المبيعات + bénéfice récupération
        String sql = """
        SELECT 
            COALESCE(SUM((v.price_sell_r - p.price_buy_u_TVA * v.qty)), 0) AS total_vente,
            (
                SELECT COALESCE(SUM((r.rec_price - p2.price_buy_u_TVA * r.rec_qt)), 0)
                FROM rec r
                JOIN (
                    SELECT prod_id, ((MAX(price_buy_u_TVA) - MIN(price_buy_u_TVA)) * 0.85 + MIN(price_buy_u_TVA)) AS price_buy_u_TVA
                    FROM product_info
                    GROUP BY prod_id
                ) p2 ON r.rec_id = p2.prod_id
                WHERE YEAR(r.rec_dateR) = ? AND MONTH(r.rec_dateR) = ?
            ) AS benefice_rec
        FROM vente v
        JOIN (
            SELECT prod_id, ((MAX(price_buy_u_TVA) - MIN(price_buy_u_TVA)) * 0.85 + MIN(price_buy_u_TVA)) AS price_buy_u_TVA
            FROM product_info
            GROUP BY prod_id
        ) p ON v.prod_id = p.prod_id
        WHERE YEAR(v.date) = ? AND MONTH(v.date) = ?
    """;

        try (Connection connection = database.connectDb()) {
            // حساب المصاريف الشهرية
            double totalCharge = 0.0;
            try (PreparedStatement psCharge = connection.prepareStatement(chargeQuery)) {
                psCharge.setInt(1, year);
                psCharge.setInt(2, month);

                try (ResultSet rsCharge = psCharge.executeQuery()) {
                    if (rsCharge.next()) {
                        totalCharge = rsCharge.getDouble("total_charge");
                    }
                }
            }

            // حساب الربح الشهري
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, year); // r.rec_dateR
                ps.setInt(2, month);
                ps.setInt(3, year); // v.date
                ps.setInt(4, month);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        double totalVente = rs.getDouble("total_vente");
                        double beneficeRec = rs.getDouble("benefice_rec");

                        netProfit = totalVente + beneficeRec - totalCharge;

                        System.out.println("Vente brute: " + totalVente);
                        System.out.println("Bénéfice récupération: " + beneficeRec);
                        System.out.println("Charges: " + totalCharge);
                        System.out.println("Profit net: " + netProfit);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return netProfit;
    }
Double BenMonthlyProfit = calculateMonthlyProfit2();

    // Destinataires
    String[] recipients = {"Sami.Baazaoui@esprit.tn"};
    // دالة الإرسال الرئيسية
    public void sendEmailReport(double capital, double benefice,double benefice3, String[] recipients, String type) {
        final String username = "baazaouisami136@gmail.com";
        // استعمل كلمة مرور التطبيقات من Gmail
        final String password = "enof hlkd pbdk qhvh";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(username,"BAAZAOUI AUTO PIECE", "UTF-8"));
            } catch (java.io.UnsupportedEncodingException e) {
                e.printStackTrace();
                // fallback إذا صار خطأ
                try {
                    message.setFrom(new InternetAddress(username));
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
            // إضافة جميع المستلمين
            InternetAddress[] recipientAddresses = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                recipientAddresses[i] = new InternetAddress(recipients[i]);
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddresses);

            message.setSubject("📊 Rapport AUTO PIECE BAAZAOUI - " + type);

            String body = "<html>\n" +
                    "<head>\n" +
                    "<style>\n" +
                    "    body {\n" +
                    "        font-family: 'Arial', sans-serif;\n" +
                    "        background-color: #f4f6f8;\n" +
                    "        margin: 0;\n" +
                    "        padding: 0;\n" +
                    "    }\n" +
                    "    .container {\n" +
                    "        width: 90%;\n" +
                    "        max-width: 600px;\n" +
                    "        margin: 20px auto;\n" +
                    "        background-color: #ffffff;\n" +
                    "        border-radius: 10px;\n" +
                    "        box-shadow: 0 4px 10px rgba(0,0,0,0.1);\n" +
                    "        overflow: hidden;\n" +
                    "        border-top: 6px solid #4CAF50;\n" +
                    "    }\n" +
                    "    .header {\n" +
                    "        background-color: #4CAF50;\n" +
                    "        color: white;\n" +
                    "        padding: 20px;\n" +
                    "        text-align: center;\n" +
                    "        font-size: 24px;\n" +
                    "    }\n" +
                    "    .content {\n" +
                    "        padding: 20px;\n" +
                    "    }\n" +
                    "    .card {\n" +
                    "        background-color: #f0f4f8;\n" +
                    "        border-left: 6px solid #4CAF50;\n" +
                    "        padding: 15px;\n" +
                    "        margin-bottom: 15px;\n" +
                    "        border-radius: 5px;\n" +
                    "    }\n" +
                    "    .card h3 {\n" +
                    "        margin: 0 0 10px 0;\n" +
                    "        font-size: 18px;\n" +
                    "        color: #333333;\n" +
                    "    }\n" +
                    "    .card p {\n" +
                    "        margin: 0;\n" +
                    "        font-size: 16px;\n" +
                    "        color: #555555;\n" +
                    "    }\n" +
                    "    .footer {\n" +
                    "        text-align: center;\n" +
                    "        padding: 15px;\n" +
                    "        font-size: 14px;\n" +
                    "        color: #999999;\n" +
                    "        background-color: #f4f6f8;\n" +
                    "    }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"header\">📊 Rapport BAAZAOUI AUTO PIECE  - " + type + "</div>\n" +
                    "    <div class=\"content\">\n" +
                    "        <div class=\"card\">\n" +
                    "            <h3>🏪 Capital Total AUTO PIECE</h3>\n" +
                    "            <p>" + String.format("%.2f DT", capital) + "</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"card\">\n" +
                    "            <h3>💰 Ventes du jour</h3>\n" +
                    "            <p>" + String.format("%.2f DT", benefice) + "</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"card\">\n" +
                    "            <h3>📈 Bénéfice du jour</h3>\n" +
                    "            <p>" + String.format("%.2f DT", benefice3) + "</p>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <div class=\"footer\">\n" +
                    "        Cordialement,<br>Votre application de gestion\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";



            message.setContent(body, "text/html; charset=utf-8");

            // إرسال البريد
            Transport.send(message);
            System.out.println("✅ Email " + type + " envoyé avec succès à " + java.util.Arrays.toString(recipients));

        } catch (MessagingException e) {
            System.out.println("❌ Erreur d'envoi: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void sendEmailReport2(double capital, double vente,double benefice2, String[] recipients, String type) {
        final String username = "baazaouisami136@gmail.com";
        // استعمل كلمة مرور التطبيقات من Gmail
        final String password = "enof hlkd pbdk qhvh";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(username,"BAAZAOUI AUTO PIECE", "UTF-8"));
            } catch (java.io.UnsupportedEncodingException e) {
                e.printStackTrace();
                // fallback إذا صار خطأ
                try {
                    message.setFrom(new InternetAddress(username));
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }

            // إضافة جميع المستلمين
            InternetAddress[] recipientAddresses = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                recipientAddresses[i] = new InternetAddress(recipients[i]);
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddresses);

            message.setSubject("📊 Rapport AUTO PIECE BAAZAOUI - " + type);

            String body = "<html>\n" +
                    "<head>\n" +
                    "<style>\n" +
                    "    body {\n" +
                    "        font-family: 'Arial', sans-serif;\n" +
                    "        background-color: #f4f6f8;\n" +
                    "        margin: 0;\n" +
                    "        padding: 0;\n" +
                    "    }\n" +
                    "    .container {\n" +
                    "        width: 90%;\n" +
                    "        max-width: 600px;\n" +
                    "        margin: 20px auto;\n" +
                    "        background-color: #ffffff;\n" +
                    "        border-radius: 10px;\n" +
                    "        box-shadow: 0 4px 10px rgba(0,0,0,0.1);\n" +
                    "        overflow: hidden;\n" +
                    "        border-top: 6px solid #4CAF50;\n" +
                    "    }\n" +
                    "    .header {\n" +
                    "        background-color: #4CAF50;\n" +
                    "        color: white;\n" +
                    "        padding: 20px;\n" +
                    "        text-align: center;\n" +
                    "        font-size: 24px;\n" +
                    "    }\n" +
                    "    .content {\n" +
                    "        padding: 20px;\n" +
                    "    }\n" +
                    "    .card {\n" +
                    "        background-color: #f0f4f8;\n" +
                    "        border-left: 6px solid #4CAF50;\n" +
                    "        padding: 15px;\n" +
                    "        margin-bottom: 15px;\n" +
                    "        border-radius: 5px;\n" +
                    "    }\n" +
                    "    .card h3 {\n" +
                    "        margin: 0 0 10px 0;\n" +
                    "        font-size: 18px;\n" +
                    "        color: #333333;\n" +
                    "    }\n" +
                    "    .card p {\n" +
                    "        margin: 0;\n" +
                    "        font-size: 16px;\n" +
                    "        color: #555555;\n" +
                    "    }\n" +
                    "    .footer {\n" +
                    "        text-align: center;\n" +
                    "        padding: 15px;\n" +
                    "        font-size: 14px;\n" +
                    "        color: #999999;\n" +
                    "        background-color: #f4f6f8;\n" +
                    "    }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"header\">📊 Rapport BAAZAOUI AUTO PIECE  - " + type + "</div>\n" +
                    "    <div class=\"content\">\n" +
                    "        <div class=\"card\">\n" +
                    "            <h3>🏪 Capital Total AUTO PIECE</h3>\n" +
                    "            <p>" + String.format("%.2f DT", capital) + "</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"card\">\n" +
                    "            <h3>💰 Ventes du jour</h3>\n" +
                    "            <p>" + String.format("%.2f DT", vente) + "</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"card\">\n" +
                    "            <h3>📈 Bénéfice du jour</h3>\n" +
                    "            <p>" + String.format("%.2f DT", benefice2) + "</p>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <div class=\"footer\">\n" +
                    "        Cordialement,<br>Votre application de gestion\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";


            message.setContent(body, "text/html; charset=utf-8");

            // إرسال البريد
            Transport.send(message);
            System.out.println("✅ Email " + type + " envoyé avec succès à " + java.util.Arrays.toString(recipients));

        } catch (MessagingException e) {
            System.out.println("❌ Erreur d'envoi: " + e.getMessage());
            e.printStackTrace();
            // fallback في حالة الخطأ

        }
    }


    // دالة للتجربة اليدوية


    // السcheduler التلقائي
    public void startAutoMailScheduler() {
        ZoneId zoneTunis = ZoneId.of("Africa/Tunis");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        System.out.println("⏰ Démarrage du scheduler...");

        // --- Envoi quotidien à 18:00 ---
        long dailyDelay = getInitialDelayForTime(1, 8, zoneTunis);
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("📧 Envoi du rapport quotidien...");

            // ⚡️ Recalculer les valeurs à chaque envoi
            double freshCapital = showCapital2();
            double freshVente = getVenteToday();
            double freshProfit = getProfitToday();

            // envoyer avec les valeurs actualisées
            sendEmailReport2(freshCapital, freshVente, freshProfit, recipients, "Rapport Quotidien(Daily)");

        }, dailyDelay, 24 * 60 * 60, TimeUnit.SECONDS);

        // --- Envoi mensuel le premier du mois à 09:00 ---
        long monthlyDelay = getInitialDelayForFirstDayOfMonth(15,37, zoneTunis);
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("📊 Envoi du rapport mensuel...");

            // ⚡️ Recalculer les valeurs à chaque envoi mensuel aussi
            double freshCapital = showCapital2();
            double freshVenteMois = getProfitCurrentMonth();
            double freshProfitMois = calculateMonthlyProfit2();

            sendEmailReport(freshCapital, freshVenteMois, freshProfitMois, recipients, "Rapport Mensuel(Monthly)");

        }, monthlyDelay, 30L * 24 * 60 * 60, TimeUnit.SECONDS);
    }

    // حساب التأخير لوقت محدد
    private long getInitialDelayForTime(int hour, int minute, ZoneId zone) {
        LocalDateTime now = LocalDateTime.now(zone);
        LocalDateTime nextRun = now.withHour(hour).withMinute(minute).withSecond(0).withNano(0);

        if (now.compareTo(nextRun) > 0) {
            nextRun = nextRun.plusDays(1);
        }

        long seconds = Duration.between(now, nextRun).getSeconds();
        System.out.println("⏰ Prochain envoi dans " + seconds + " secondes");
        return seconds;
    }

    // حساب التأخير لأول يوم من الشهر
    private long getInitialDelayForFirstDayOfMonth(int hour, int minute, ZoneId zone) {
        LocalDateTime now = LocalDateTime.now(zone);

        // نحدّد أول يوم من الشهر الجاي أو الحالي
        LocalDateTime nextRun = now.withDayOfMonth(1)
                .withHour(hour)
                .withMinute(minute)
                .withSecond(0)
                .withNano(0);

        // إذا الوقت الحالي عدى أول الشهر، نحركوه للشهر الجاي
        if (now.compareTo(nextRun) > 0) {
            nextRun = nextRun.plusMonths(1).withDayOfMonth(1);
        }

        // ✅ إذا اليوم الأول من الشهر يصادف يوم الأحد، نأجلو للنهار إلي بعدو
        if (nextRun.getDayOfWeek() == DayOfWeek.SUNDAY) {
            nextRun = nextRun.plusDays(1);
            System.out.println("📅 Le 1er est un dimanche → reporté au lundi !");
        }

        long seconds = Duration.between(now, nextRun).getSeconds();
        System.out.println("📆 Prochain rapport mensuel dans " + seconds + " secondes (" + nextRun + ")");
        return seconds;
    }
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(this::startAutoMailScheduler).start();

    }




}
