package panda.domain.models.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceiptServiceModel {


    private String id;
    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private PackageServiceModel aPackage;
    private UserServiceModel recipient;


    public ReceiptServiceModel() {
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

    public PackageServiceModel getaPackage() {
        return aPackage;
    }

    public void setaPackage(PackageServiceModel aPackage) {
        this.aPackage = aPackage;
    }

    public UserServiceModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserServiceModel recipient) {
        this.recipient = recipient;
    }


}
