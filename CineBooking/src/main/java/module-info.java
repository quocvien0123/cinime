module Cinebooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.jfoenix;
    requires javafx.media;
    requires jakarta.persistence;
    requires java.compiler;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires mysql.connector.j;

    opens model to javafx.fxml;
    exports model;
    exports controller;
    opens controller to javafx.fxml;


    exports run to javafx.graphics;
}