package com.standbystill.managementdaycare.controllers;

import com.standbystill.managementdaycare.entities.Address;
import com.standbystill.managementdaycare.entities.Family;
import com.standbystill.managementdaycare.entities.Parent;
import com.standbystill.managementdaycare.services.AddressCRUDService;
import com.standbystill.managementdaycare.services.FamilyCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class FamilyController {
    @Autowired
    FamilyCRUDService familyCRUDService;
    @Autowired
    AddressCRUDService addressCRUDService;

    @GetMapping("/families/{id}")
    public String getFamily(@PathVariable("id") int id, Model model) {
        model.addAttribute("familyId", id);
        model.addAttribute("family", familyCRUDService.findFamilyById(id));
        model.addAttribute("parents", familyCRUDService.findParentsForFamily(id));
        model.addAttribute("children", familyCRUDService.findChildrenForFamily(id));
        model.addAttribute("address", addressCRUDService.findAddressById(id));
        return "family";
    }

    @GetMapping("/families/{idF}/updateInfo")
    public String getFamilyToUpdate(@PathVariable("idF") int idF, Model model) {
        model.addAttribute("familyId", idF);
        model.addAttribute("family", familyCRUDService.findFamilyById(idF));
        return "updateFamilyInfo";
    }

    @PostMapping("/families/{idF}/updateInfo")
    public String updateFamily(@PathVariable("idF") int idF, @ModelAttribute Family family, Model model) {
        model.addAttribute("familyId", idF);
        model.addAttribute("family", familyCRUDService.findFamilyById(idF));
        boolean update = familyCRUDService.updateFamily(family,idF);
        if (update) {
            return new StringBuilder().append("redirect:/families/").append(idF).toString();
        } else {
            return "/error";
        }
    }

    @GetMapping("/families/{idF}/address/{idA}/updateAddress")
    public String getAddressToUpdate(@PathVariable("idF") int idF, @PathVariable("idA") int idA, Model model) {
        model.addAttribute("familyID", idF);
        model.addAttribute("addressId", idA);
        model.addAttribute("address", addressCRUDService.findAddressById(idA));
        return "updateFamilyAddress";
    }

    @PostMapping("/families/{idF}/address/{idA}/updateAddress")
    public String updateFamilyAddress(@PathVariable("idF") int idF, @PathVariable("idA") int idA,
                                      @ModelAttribute Address address, Model model) {
        model.addAttribute("familyID", idF);
        model.addAttribute("addressId", idA);
        model.addAttribute("address", addressCRUDService.findAddressById(idA));
        boolean update = addressCRUDService.updateAddress(address,idA);
        if (update) {
            return new StringBuilder().append("redirect:/families/").append(idF).toString();
        } else {
            return "/error";
        }
    }

    @GetMapping("/families/{id}/delete")
    public String deleteFamily(@PathVariable("id") int id) {
        boolean delete = familyCRUDService.deleteFamily(id);
        if (delete) {
            return "redirect:/families";
        } else {
            return "error";
        }
    }

    @GetMapping("/families/address")
    public String retrieveAddressModel(Model model) {
        model.addAttribute("address", new Address());
        return "formAddressFamily";
    }

    @PostMapping("/families/address")
    public String addAddress(@ModelAttribute Address address, Model model) {
        int addressId = addressCRUDService.addAddress(address);
        model.addAttribute("address", address);
        model.addAttribute("addressId", addressId);
        return "resultAddressFamily";
    }

    @GetMapping("/families/address/{idA}")
    public String retrieveFamilyModel(@PathVariable("idA") int idA, Model model) {
        model.addAttribute("addressId", idA);
        model.addAttribute("family", new Family());
        return "formFamily";
    }

    @PostMapping("/families/address/{idA}")
    public String addFamily(@PathVariable("idA") int idA, @ModelAttribute Family family, Model model) {
        model.addAttribute("addressId", idA);
        int familyId = familyCRUDService.addFamily(family, idA);
        model.addAttribute("familyId", familyId);
        model.addAttribute("family", family);
        return "resultFamily";
    }

}
