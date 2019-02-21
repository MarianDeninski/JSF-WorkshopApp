package panda.service;

import panda.domain.models.service.ReceiptServiceModel;

import java.util.List;

public interface ReceiptService {

    List<ReceiptServiceModel> allList();

    void save(ReceiptServiceModel receiptServiceModel);

    ReceiptServiceModel getReceipt(String id);


}
