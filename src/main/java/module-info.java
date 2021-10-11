module com.example.webscrap {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;

    opens com.example.webscrap to javafx.fxml;
    exports com.example.webscrap;
    exports com.example.webscrap.Crawlers;
    opens com.example.webscrap.Crawlers to javafx.fxml;
    exports com.example.webscrap.Controllers;
    opens com.example.webscrap.Controllers to javafx.fxml;
    exports com.example.webscrap.Models;
    opens com.example.webscrap.Models to javafx.fxml;
}