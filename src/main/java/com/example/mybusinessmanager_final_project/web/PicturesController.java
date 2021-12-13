package com.example.mybusinessmanager_final_project.web;

import com.example.mybusinessmanager_final_project.model.binding.PictureBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.PictureEntity;
import com.example.mybusinessmanager_final_project.model.service.PictureServiceModel;
import com.example.mybusinessmanager_final_project.model.view.PictureViewModel;
import com.example.mybusinessmanager_final_project.repository.PictureRepository;
import com.example.mybusinessmanager_final_project.service.CloudinaryImage;
import com.example.mybusinessmanager_final_project.service.CloudinaryService;
import com.example.mybusinessmanager_final_project.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PicturesController {

    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    public PicturesController(CloudinaryService cloudinaryService,
                              PictureRepository pictureRepository, PictureService pictureService, ModelMapper modelMapper) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }

    private PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        return new PictureEntity().
                setTitle(title).
                setUrl(uploaded.getUrl()).setPublicId(uploaded.getPublicId());
    }

    @PostMapping("/reports/{reportId}/report-details/pictures/add")
    public String newPicture(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long reportId,
            @ModelAttribute("pictureBindingModel") PictureBindingModel pictureBindingModel
    ) throws IOException {

        var picture = createPictureEntity(pictureBindingModel.getPicture(),
                pictureBindingModel.getTitle());


        PictureServiceModel pictureServiceModel =
                modelMapper.map(pictureBindingModel, PictureServiceModel.class);
        pictureServiceModel.setTitle(picture.getTitle())
                .setUrl(picture.getUrl()).setPublicId(picture.getPublicId());
        pictureServiceModel.setAuthor(principal.getUsername());
        pictureServiceModel.setReportId(reportId);

        PictureViewModel newPicture =
                pictureService.addPicture(pictureServiceModel);


        return "redirect:/reports/{reportId}/report-details";
    }

    @Transactional
    @DeleteMapping("/report/{reportId}/report-details/pictures/all/delete")
    public String delete(@RequestParam("public_id") String publicId) {
        if (cloudinaryService.delete(publicId)) {
            pictureRepository.deleteAllByPublicId(publicId);
        }
        return "redirect:/reports/{reportId}/report-details";
    }

    @GetMapping("/reports/{reportId}/report-details/pictures/all")
    public ResponseEntity<List<PictureViewModel>> getPictures(
            @PathVariable Long reportId,
            Principal principal, Model model,
            @ModelAttribute("pictureServiceModel") PictureServiceModel pictureServiceModel
    ) {
        List<PictureViewModel> pictures = pictureRepository.
                findAll().
                stream().
                map(this::asViewModel).
                collect(Collectors.toList());

        model.addAttribute("pictures", pictures);

        return ResponseEntity.ok(
                pictureService.getPictures(reportId));
    }


    private PictureViewModel asViewModel(PictureEntity picture) {
        return new PictureViewModel().
                setTitle(picture.getTitle()).
                setUrl(picture.getUrl()).
                setPublicId(picture.getPublicId()).
                setAuthor(picture.getAuthor().getUsername());

    }

    @ModelAttribute
    public PictureBindingModel pictureBindingModel() {
        return new PictureBindingModel();
    }
}