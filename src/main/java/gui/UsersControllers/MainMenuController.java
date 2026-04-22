package gui.UsersControllers;

import entities.Users;
import Utils.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuController {

    // ── Sidebar nodes ──
    @FXML private Button btnAdmin;
    @FXML private Button btnIdea;
    @FXML private Button btnMission;
    @FXML private Button btnTasks;
    @FXML private Button btnLogout;
    @FXML private Text   txtAvatarInitials;
    @FXML private Text   txtUsername;
    @FXML private Text   txtRole;

    // ── Content-area nodes ──
    @FXML private VBox cardAdmin;
    @FXML private Text txtWelcome;
    @FXML private Text txtTopInitials;

    @FXML
    public void initialize() {
        Users user = SessionManager.getInstance().getCurrentUser();
        if (user == null) return; // shouldn't happen, but safe-guard

        boolean isAdmin = SessionManager.getInstance().isAdmin();

        // ── Populate user info in sidebar ──
        String name     = user.getUsername();
        String initials = name.length() >= 2
                ? (name.substring(0, 1) + name.substring(name.length() - 1)).toUpperCase()
                : name.toUpperCase();

        txtAvatarInitials.setText(initials);
        txtTopInitials.setText(initials);
        txtUsername.setText(name);
        txtRole.setText(user.getRole().replace("ROLE_", "").replace("_", " "));
        txtWelcome.setText("Welcome back, " + name + " 👋");

        // ── Hide Admin items for non-admin users ──
        btnAdmin.setVisible(isAdmin);
        btnAdmin.setManaged(isAdmin);
        cardAdmin.setVisible(isAdmin);
        cardAdmin.setManaged(isAdmin);
    }

    // ── Navigation helpers ──
    private void switchScene(String fxml) throws Exception {
        Stage stage = (Stage) btnIdea.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/" + fxml))));
    }

    @FXML public void goToAdmin()   throws Exception { switchScene("Users/Admin.fxml");   }
    @FXML public void goToIdea()    throws Exception { switchScene("TSK/Idea.fxml");     }
    @FXML public void goToMission() throws Exception { switchScene("TSK/Mission.fxml");  }
    @FXML public void goToTasks()   throws Exception { switchScene("TSK/Tasks.fxml");    }

    @FXML
    public void handleLogout() throws Exception {
        SessionManager.getInstance().logout();
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/Users/SignIn.fxml"))));
        stage.setTitle("Sign In — Soft UI Dashboard");
    }
}