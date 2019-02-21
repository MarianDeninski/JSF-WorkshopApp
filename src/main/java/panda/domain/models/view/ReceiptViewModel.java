package panda.domain.models.view;

import panda.domain.entities.Package;
import panda.domain.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptViewModel {

    private String id;
    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private PackageViewModel aPackage;
    private UserViewModel recipient;


    public ReceiptViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public LocalDateTime getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    public PackageViewModel getaPackage() {
        return aPackage;
    }

    public void setaPackage(PackageViewModel aPackage) {
        this.aPackage = aPackage;
    }

    public UserViewModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserViewModel recipient) {
        this.recipient = recipient;
    }


}
