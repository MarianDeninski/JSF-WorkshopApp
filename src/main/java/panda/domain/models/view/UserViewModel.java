package panda.domain.models.view;

import panda.domain.entities.Package;
import panda.domain.entities.Receipt;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel {

    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
    private List<PackageViewModel> packages;
    private List<ReceiptViewModel> receipts;

    public UserViewModel() {

        this.packages = new ArrayList<>();
        this.receipts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<PackageViewModel> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageViewModel> packages) {
        this.packages = packages;
    }

    public List<ReceiptViewModel> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<ReceiptViewModel> receipts) {
        this.receipts = receipts;
    }


    @Override
    public String toString() {
        return this.getUsername();
    }
}
