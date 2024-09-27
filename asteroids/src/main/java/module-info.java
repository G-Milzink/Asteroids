module com.asteroids {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;

    opens com.asteroids to javafx.fxml;
    exports com.asteroids;
}
