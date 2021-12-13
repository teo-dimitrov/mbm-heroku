package com.example.mybusinessmanager_final_project.web;

import com.example.mybusinessmanager_final_project.model.binding.OrderAddBindingModel;
import com.example.mybusinessmanager_final_project.model.binding.ReportEditBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;
import com.example.mybusinessmanager_final_project.model.service.OrderAddServiceModel;
import com.example.mybusinessmanager_final_project.model.view.ReportDetailsView;
import com.example.mybusinessmanager_final_project.service.OrderService;
import com.example.mybusinessmanager_final_project.service.impl.MBMUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/orders/all")
    public String allReports(Model model) {
        model.addAttribute("orders",
                orderService.getAllOrders());
        return "orders-all";
    }
    @GetMapping("/orders/{id}/order-details")
    public String showOrder(
            @PathVariable Long id, Model model,
            Principal principal) {
        model.addAttribute("order", this.orderService.findById(id, principal.getName()));
        return "order-details";
    }
    @GetMapping("/orders/add")
    public String getAddOrderPage(Model model) {

        if (!model.containsAttribute("orderAddBindingModel")) {
            model.
                    addAttribute("orderAddBindingModel", new OrderAddBindingModel());
        }
        return "orders-add";
    }
    @PostMapping("/orders/add")
    public String addReport(@Valid OrderAddBindingModel orderAddBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            @AuthenticationPrincipal MBMUser user) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel",
                            bindingResult);

            return "redirect:add";
        }
        OrderAddServiceModel savedOrderAddServiceModel = orderService
                .addOrder(orderAddBindingModel, user.getUserIdentifier());

        return "redirect:/orders/all";
    }
    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable Long id,
                               Principal principal) {
        orderService.deleteOrder(id);
        return "redirect:/orders/all";
    }
    @GetMapping("/orders/{id}/edit")
    public String editReport(@PathVariable Long id, Model model,
                             @AuthenticationPrincipal MBMUser currentUser) {

        // TODO: 11.12.2021 г.  

        return "redirect:/orders/{id}/order-details";
    }
}
