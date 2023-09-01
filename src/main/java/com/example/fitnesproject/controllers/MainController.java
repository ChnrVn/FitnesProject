package com.example.fitnesproject.controllers;

import com.example.fitnesproject.domain.FitnessMembership;
import com.example.fitnesproject.services.FitnessMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/")
public class MainController {
    private static final String SEARCH_STRING_ATTR = "search_string";
    private static final String MEMBERSHIP_LIST_ATTR = "membership_list";
    private static final String ID_PATH_VAR = "id";
    private static final String MEMBERSHIP_TO_EDIT_ATTR = "membership_to_edit";
    private static final String ID_SEARCH_ERROR_ATTR = "no_such_id";

    private final FitnessMembershipService fitnessMembershipService;

    @Autowired
    public MainController(FitnessMembershipService fitnessMembershipService) {
        this.fitnessMembershipService = fitnessMembershipService;
    }

    @GetMapping
    public String viewHomePage(Model model, @Param(SEARCH_STRING_ATTR) String keyword) {

        List<FitnessMembership> fitnessMembershipList = keyword == null ?
                fitnessMembershipService.getAll()
                : fitnessMembershipService.getFitnessMembershipsWithOwner(keyword);

        for (FitnessMembership fm : fitnessMembershipList){
            System.out.println(fm);
        }

        model.addAttribute(MEMBERSHIP_LIST_ATTR, fitnessMembershipList);
        model.addAttribute(SEARCH_STRING_ATTR, keyword);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteMemberShipById(@PathVariable(name = ID_PATH_VAR) Long id) {
        fitnessMembershipService.deleteByID(id);
        return "redirect:/";
    }


    @GetMapping("/edit")
    public String editMemberShipPage(Model model){
        model.addAttribute(MEMBERSHIP_TO_EDIT_ATTR, new FitnessMembership());
        return "edit";
    }

    @PostMapping("/edit")
    public String editMemberShipPost(Model model,
                                     @ModelAttribute(MEMBERSHIP_TO_EDIT_ATTR)
                                     FitnessMembership editingMemberShip){

        AtomicReference<Boolean> noMembershipFound = new AtomicReference<>(false);
        fitnessMembershipService
                .getFitnessMembershipByID(editingMemberShip.getId())
                .ifPresentOrElse(fitnessMembershipService::save,
                        () -> noMembershipFound.set(true));

        model.addAttribute(ID_SEARCH_ERROR_ATTR, noMembershipFound.get());

        return noMembershipFound.get() ?
                "redirect:/"
                : "edit";

    }

//    @GetMapping("edit/{id}")
//    public String editMemberShipById(@ModelAttribute(MEMBERSHIP_TO_EDIT_ATTR)
//                                         FitnessMembership editingMemberShip) {
//
//    }
}
