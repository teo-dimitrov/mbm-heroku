package com.example.mybusinessmanager_final_project.service;

import com.example.mybusinessmanager_final_project.model.binding.OrderAddBindingModel;
import com.example.mybusinessmanager_final_project.model.service.OrderAddServiceModel;
import com.example.mybusinessmanager_final_project.model.view.OrderDetailsView;
import com.example.mybusinessmanager_final_project.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    List<OrderViewModel> getAllOrders();

    OrderDetailsView findById(Long id, String currentUser);

    boolean isOwner(String username, Long id);

    OrderAddServiceModel addOrder(OrderAddBindingModel orderAddBindingModel,
                                  String ownerId);
    void deleteOrder(Long id);
}
