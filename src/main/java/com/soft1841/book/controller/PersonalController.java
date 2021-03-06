package com.soft1841.book.controller;

import com.soft1841.book.entity.Admin;
import com.soft1841.book.service.AdminService;
import com.soft1841.book.utils.ServiceFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalController implements Initializable {

    @FXML
    private Label adminName;
    @FXML
    private TextField adminPassword;
    @FXML
    private ImageView adminImg;

    private AdminService adminService = ServiceFactory.getAdminServiceInstance();

    private Admin admin;

    public void setAdmin(Admin admin) {
        this.admin = admin;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                adminImg.setImage(new Image(admin.getAvatar()));
                adminName.setText(admin.getName());
                adminPassword.setText(admin.getPassword());
            }
        });
    }

    public void edit() {
        //激活密码框为可编辑状态，同时改变样式
        adminPassword.setEditable(true);
        adminPassword.getStyleClass().add("input-group");
        adminPassword.setOnMouseClicked(event -> {
            adminPassword.setText("");
        });
    }

    public void save() {
        //获取密码框的值
        String passString = adminPassword.getText().trim();
        //更新管理员密码
        admin.setPassword(passString);
        adminService.updateAdmin(admin);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setContentText("密码修改成功");
        alert.showAndWait();
    }
}
