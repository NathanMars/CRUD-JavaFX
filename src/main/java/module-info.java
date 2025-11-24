module Application.crud {
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
    requires jbcrypt;
    requires org.flywaydb.core;

    opens Application to javafx.fxml;
    opens Controllers.General to javafx.fxml;
    opens Controllers.Registrations to javafx.fxml;
    opens Model.Entity to javafx.base, org.hibernate.orm.core;
    opens Connection to org.hibernate.orm.core;

    exports Application;
    exports Controllers.General;
    exports Controllers.Registrations;
    exports Model.Entity;
    exports Model.DAO;
}