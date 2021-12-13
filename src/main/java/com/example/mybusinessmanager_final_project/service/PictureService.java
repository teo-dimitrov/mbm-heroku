package com.example.mybusinessmanager_final_project.service;

import com.example.mybusinessmanager_final_project.model.service.PictureServiceModel;
import com.example.mybusinessmanager_final_project.model.view.PictureViewModel;

import java.io.IOException;
import java.util.List;

public interface PictureService {

    PictureViewModel addPicture(PictureServiceModel pictureServiceModel) throws IOException;

    List<PictureViewModel> getPictures(Long reportId);

    void delete(String publicId);
}
