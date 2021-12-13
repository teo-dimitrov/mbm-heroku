package com.example.mybusinessmanager_final_project.service;

import com.example.mybusinessmanager_final_project.model.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
