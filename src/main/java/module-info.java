module devtests.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires atlantafx.base;
    requires com.jfoenix;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens devtests.crud to javafx.fxml;
    opens Controllers to javafx.fxml;
    opens Model to javafx.base, org.hibernate.orm.core;
    opens Connection to org.hibernate.orm.core;

    exports devtests.crud;
    exports Controllers;
    exports Controllers.Registrations;
    opens Controllers.Registrations to javafx.fxml;
}