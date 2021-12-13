package com.example.mybusinessmanager_final_project.service.impl;

import com.example.mybusinessmanager_final_project.model.entity.PictureEntity;
import com.example.mybusinessmanager_final_project.model.service.PictureServiceModel;
import com.example.mybusinessmanager_final_project.model.view.PictureViewModel;
import com.example.mybusinessmanager_final_project.repository.PictureRepository;
import com.example.mybusinessmanager_final_project.repository.ReportRepository;
import com.example.mybusinessmanager_final_project.repository.UserRepository;
import com.example.mybusinessmanager_final_project.service.PictureService;
import com.example.mybusinessmanager_final_project.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(ReportRepository reportRepository,
                              UserRepository userRepository,
                              PictureRepository pictureRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
    }


    @Transactional
    @Override
    public PictureViewModel addPicture(PictureServiceModel pictureServiceModel) {


        Objects.requireNonNull(pictureServiceModel.getAuthor());

        var report = reportRepository.
                findById(pictureServiceModel.getReportId()).
                orElseThrow(() -> new ObjectNotFoundException("report with id " + pictureServiceModel.getReportId() + " not found!"));

        var author = userRepository.
                findByUsername(pictureServiceModel.getAuthor()).
                orElseThrow(() -> new ObjectNotFoundException("User with username " + pictureServiceModel.getAuthor() + " not found!"));

        PictureEntity newPicture = new PictureEntity();
        newPicture
                .setTitle(pictureServiceModel.getTitle())
                .setUrl(pictureServiceModel.getUrl())
                .setPublicId(pictureServiceModel.getPublicId())
                .setApproved(false)
                .setCreated(Instant.now());

        newPicture
                .setReportEntity(report)
                .setAuthor(author);

        PictureEntity savedPicture = pictureRepository.save(newPicture);

        return mapAsPicture(savedPicture);


    }

    private PictureViewModel mapAsPicture(PictureEntity pictureEntity) {
        PictureViewModel pictureViewModel = new PictureViewModel();

        pictureViewModel.
                setTitle(pictureEntity.getTitle())
                .setUrl(pictureEntity.getUrl())
                .setPictureId(pictureEntity.getId())
                .setCanApprove(true)
                .setCanDelete(true)
                .setPublicId(pictureEntity.getPublicId())
                .setCreated(pictureEntity.getCreated())
                .setReportId(pictureEntity.getReportEntity().getId())
                .setAuthor(pictureEntity.getAuthor().getUsername());

        return pictureViewModel;
    }

    @Transactional
    @Override
    public List<PictureViewModel> getPictures(Long reportId) {
        var reportOpt = reportRepository.
                findById(reportId);

        if (reportOpt.isEmpty()) {
            throw new ObjectNotFoundException("Report with id " + reportId + " was not found!");
        }

        return reportOpt.
                get().
                getPictures().
                stream().
                map(this::mapAsPicture).
                collect(Collectors.toList());
    }

    @Override
    public void delete(String publicId) {
        pictureRepository.deleteAllByPublicId(publicId);
    }
}
