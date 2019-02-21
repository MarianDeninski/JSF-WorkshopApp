package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Receipt;
import panda.domain.models.service.ReceiptServiceModel;
import panda.repository.ReceiptRepositoryImpl;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptServiceImpl implements ReceiptService {

   private ReceiptRepositoryImpl receiptRepository;
   private ModelMapper modelMapper;


    public ReceiptServiceImpl() {

    }

    @Inject
    public ReceiptServiceImpl(ReceiptRepositoryImpl receiptRepository,ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ReceiptServiceModel> allList() {

     List<ReceiptServiceModel> list = receiptRepository.findAll().stream()
               .map(e-> this.modelMapper.map(e,ReceiptServiceModel.class))
               .collect(Collectors.toList());

        return list;
    }

    @Override
    public void save(ReceiptServiceModel receiptServiceModel) {
        this.receiptRepository.save(this.modelMapper.map(receiptServiceModel,Receipt.class));

    }

    @Override
    public ReceiptServiceModel getReceipt(String id) {

        ReceiptServiceModel obj = this.modelMapper.map(this.receiptRepository.findById(id),ReceiptServiceModel.class);

        return obj;
    }
}
