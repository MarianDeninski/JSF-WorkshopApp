package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.view.PackageViewModel;
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
public class ShippedPackagesBean {


    private List<PackageViewModel> packages;
    private PackageViewModel packageViewModel;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public ShippedPackagesBean() {

    }

    @Inject
    public ShippedPackagesBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.initPackages();


    }

    private void initPackages() {
        this.packages = this.packageService
                .findAllPackagesByStatus(Status.Shipped)
                .stream()
                .map(p -> {
                    PackageViewModel packageViewModel = this.modelMapper.map(p, PackageViewModel.class);

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
        this.packageService.packageStatusChange(id);

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/faces/view/admin/shipped.xhtml");
    }

}
