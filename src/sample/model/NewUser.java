package sample.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class NewUser {

    private StringProperty fisrtname;
    private StringProperty middlename;
    private StringProperty lastname;
    private StringProperty username;
    private StringProperty password;
    private StringProperty confirmPassword;
    private ObjectProperty<LocalDate> birthdate;

    public NewUser() {
        this.fisrtname = new SimpleStringProperty("");
        this.middlename = new SimpleStringProperty("");
        this.lastname = new SimpleStringProperty("");
        this.username = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.confirmPassword = new SimpleStringProperty("");
        this.birthdate = new SimpleObjectProperty<>(LocalDate.now());
    }

    public void log() {
        System.out.println(this.fisrtname.getValue());
        System.out.println(this.middlename.getValue());
        System.out.println(this.lastname.getValue());
        System.out.println(this.username.getValue());
        System.out.println(this.password.getValue());
        System.out.println(this.confirmPassword.getValue());
        System.out.println(this.birthdate.getValue());
    }

    public final String getFirstName() {
        return this.fisrtname.getValue();
    }
    public StringProperty fisrtNameProperty() {
        return this.fisrtname;
    }

    public final String getMiddleName() {
        return this.middlename.getValue();
    }
    public StringProperty middleNameProperty() {
        return this.middlename;
    }

    public final String getLastname() {
        return lastname.getValue();
    }

    public StringProperty lastNameProperty() {
        return lastname;
    }

    public final String getUsername() {
        return username.getValue();
    }

    public StringProperty userNameProperty() {
        return username;
    }

    public final String getPassword() {
        return password.getValue();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public final String getConfirmPassword() {
        return confirmPassword.getValue();
    }

    public StringProperty confirmPasswordProperty() {
        return confirmPassword;
    }

    public final LocalDate getBirthDate() {
        return this.birthdate.getValue();
    }

    public ObjectProperty<LocalDate> birthdateProperty() {
        return this.birthdate;
    }

}
