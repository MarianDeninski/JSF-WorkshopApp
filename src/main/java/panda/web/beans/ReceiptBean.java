package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.service.ReceiptServiceModel;
import panda.domain.models.service.UserServiceModel;
import panda.domain.models.view.PackageViewModel;
import panda.domain.models.view.ReceiptViewModel;
import panda.domain.models.view.UserViewModel;
import panda.service.PackageService;
import panda.service.ReceiptService;
import panda.service.ReceiptServiceImpl;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ReceiptBean {

   private UserService userService;
   private PackageService packageService;
   private ReceiptServiceImpl receiptService;
   private ModelMapper modelMapper;
   private ReceiptViewModel receipt;



   private List<ReceiptViewModel> allReceipts;
   private UserViewModel user;


   public ReceiptBean() {
   }

   @Inject
   public ReceiptBean(UserService userService, PackageService packageService,
                      ReceiptServiceImpl receiptService,
                      ModelMapper modelMapper) {
      this.userService = userService;
      this.packageService = packageService;
      this.receiptService = receiptService;
      this.modelMapper = modelMapper;
      this.init();
   }




   public ReceiptViewModel getReceipt(String id) {

      this.receipt = this.modelMapper.map(this.receiptService.getReceipt(id),ReceiptViewModel.class);
      return receipt;
   }



   private void init() {


     this.allReceipts = this.receiptService
              .allList()
              .stream()
              .map(e->this.modelMapper.map(e,ReceiptViewModel.class)).collect(Collectors.toList());

      String username = (String)((HttpSession) FacesContext
              .getCurrentInstance()
              .getExternalContext()
              .getSession(true)).getAttribute("username");

      this.user = this.modelMapper.map(this.userService.findUserByUsername(username),UserViewModel.class);


   }

   public List<ReceiptViewModel> getAllReceipts() {
      return allReceipts;
   }

   public void setAllReceipts(List<ReceiptViewModel> allReceipts) {
      this.allReceipts = allReceipts;
   }


   public UserViewModel getUser() {
      return user;
   }

   public void setUser(UserViewModel user) {
      this.user = user;
   }


   public void createReceipt(String id) throws IOException {

      ReceiptViewModel receipt = new ReceiptViewModel();
      receipt.setRecipient(this.user);
      PackageViewModel packageViewModel =  this.modelMapper.map(this.packageService.findById(id),PackageViewModel.class);
      receipt.setIssuedOn(LocalDateTime.now());
      receipt.setaPackage(packageViewModel);
      receipt.setFee(BigDecimal.valueOf(Double.parseDouble(String.format("%.2f",packageViewModel.getWeight()*2.67))));

      this.receiptService.save(this.modelMapper.map(receipt,ReceiptServiceModel.class));

      this.packageService.packageStatusChange(id);

      FacesContext.getCurrentInstance()
              .getExternalContext()
              .redirect("/faces/view/home.xhtml");


   }
}
