package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.view.PackageViewModel;
import panda.repository.PackageRepository;
import panda.service.PackageService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class DeliveryPackageBean {

    private List<PackageViewModel> packages;
    private PackageViewModel packageViewModel;
    private PackageRepository packageRepository;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public DeliveryPackageBean() {

    }
    @Inject
    public DeliveryPackageBean(PackageService packageService, ModelMapper modelMapper, PackageRepository packageRepository) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.packageRepository = packageRepository;
        this.initPackages();
    }

    private void initPackages() {
        this.packages = this.packageService
                .findAllPackagesByStatus(Status.Delivered)
                .stream()
                .map(p -> {
                    PackageViewModel packageViewModel = this.modelMapper.map(p, PackageViewModel.class);
//                    packageViewModel.setRecipient(p.getRecipient().getUsername());

                    return packageViewModel;
                })
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageViewModel> packages) {
        this.packages = packages;
    }

    public PackageViewModel getPackageViewModel(String str) {
        this.packageViewModel = this.modelMapper.map(this.packageService.findById(str),PackageViewModel.class);
        return packageViewModel;
    }

    public void changeStatus(String id) throws IOException {

        if(!this.packageRepository.findById(id).getStatus().toString().equals("Delivered")){
            System.out.println(this.packageRepository.findById(id).getStatus());
            this.packageService.packageStatusChange(id);
        }

        System.out.println(this.packageRepository.findById(id).getStatus());
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/faces/view/packages/details.xhtml?id="+id);
    }
}
