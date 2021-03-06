package com.standbystill.managementdaycare.controllers;

import com.standbystill.managementdaycare.entities.Address;
import com.standbystill.managementdaycare.entities.Parent;
import com.standbystill.managementdaycare.entities.Person;
import com.standbystill.managementdaycare.services.AddressCRUDService;
import com.standbystill.managementdaycare.services.ParentsCRUDService;
import com.standbystill.managementdaycare.services.PersonCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ParentsController {
    @Autowired
    ParentsCRUDService parentsCRUDService;
    @Autowired
    PersonCRUDService personCRUDService;
    @Autowired
    AddressCRUDService addressCRUDService;

    @GetMapping("/families/{idF}/parent/{idP}/view")
    public String showParentForFamily(@PathVariable("idF") int idF, @PathVariable("idP") int idP, Model model) {
        model.addAttribute("familyId", idF);
        model.addAttribute("personId", idP);
        model.addAttribute("parent", parentsCRUDService.findParentById(idP));
        model.addAttribute("person", personCRUDService.findPersonById(idP));
        model.addAttribute("address", addressCRUDService.findAddressById(idP));
        return "parent";
    }

    @GetMapping("/families/{idF}/parent/{idP}/updateCPR")
    public String getPersonToUpdate(@PathVariable("idF") int idF, @PathVariable("idP") int idP, Model model) {
        model.addAttribute("familyId", idF);
        model.addAttribute("personId", idP);
        model.addAttribute("person", personCRUDService.findPersonById(idP));
        return "updateParentCPR";
    }

    @PostMapping("/families/{idF}/parent/{idP}/updateCPR")
    public String updatePersonParent(@PathVariable("idF") int idF, @PathVariable("idP") int idP,
                                        @ModelAttribute Person person, Model model) {
        model.addAttribute("familyId", idF);
        model.addAttribute("personId", idP);
        model.addAttribute("person", personCRUDService.findPersonById(idP));
        boolean update = personCRUDService.updatePerson(person,idP);
        if (update) {
            return new StringBuilder().append("redirect:/families/").append(idF).append("/parent/").append(idP).append("/view").toString();
        } else {
            return "/error";
        }
    }

    @GetMapping("/families/{idF}/parent/{idP}/updateInfo")
    public String getParentToUpdate(@PathVariable("idF") int idF, @PathVariable("idP") int idP, Model model) {
        model.addAttribute("familyId", idF);
        model.addAttribute("personId", idP);
        model.addAttribute("parent", parentsCRUDService.findParentById(idP));
        return "updateParentInfo";
    }

    @PostMapping("/families/{idF}/parent/{idP}/updateInfo")
    public String updateParent(@PathVariable("idF") int idF, @PathVariable("idP") int idP,
                               @ModelAttribute Parent parent, Model model) {
        model.addAttribute("familyId", idF);
        model.addAttribute("personId", idP);
        model.addAttribute("parent", parentsCRUDService.findParentById(idP));
        boolean update = parentsCRUDService.updateParent(parent,idP);
        if (update) {
            return new StringBuilder().append("redirect:/families/").append(idF).append("/parent/").append(idP).append("/view").toString();
        } else {
            return "/error";
        }
    }

    @GetMapping("/families/{idF}/parent/{idP}/address/{idA}/updateAddress")
    public String getAddressToUpdate(@PathVariable("idF") int idF, @PathVariable("idA") int idA,
                                     @PathVariable("idP") int idP, Model model) {
        model.addAttribute("familyID", idF);
        model.addAttribute("addressId", idA);
        model.addAttribute("personId", idP);
        model.addAttribute("address", addressCRUDService.findAddressById(idA));
        return "updateParentAddress";
    }

    @PostMapping("/families/{idF}/parent/{idP}/address/{idA}/updateAddress")
    public String updateParentAddress(@PathVariable("idF") int idF, @PathVariable("idA") int idA,
                                     @PathVariable("idP") int idP, @ModelAttribute Address address, Model model) {
        model.addAttribute("familyID", idF);
        model.addAttribute("addressId", idA);
        model.addAttribute("personId", idP);
        model.addAttribute("address", addressCRUDService.findAddressById(idA));
        boolean update = addressCRUDService.updateAddress(address,idA);
        if (update) {
            return new StringBuilder().append("redirect:/families/").append(idF).append("/parent/").append(idP).append("/view").toString();
        } else {
            return "/error";
        }
    }

    @GetMapping("/families/{idF}/parent/{idP}/delete")
    public String deleteParent(@PathVariable("idF") int idF, @PathVariable("idP") int idP) {
        boolean delete = parentsCRUDService.deleteParent(idP);
        if (delete) {
            return "redirect:/families/"+idF;
        } else {
            return "/error";
        }
    }

    @GetMapping("/families/{idF}/address/parent")
    public String retrieveAddressModel(@PathVariable("idF") int idF, Model model) {
        model.addAttribute("familyID", idF);
        model.addAttribute("address", new Address());
        return "formAddressParent";
    }

    @PostMapping("/families/{idF}/address/parent")
    public String addAddress(@PathVariable("idF") int idF, @ModelAttribute Address address, Model model) {
        model.addAttribute("familyID", idF);
        int addressId = addressCRUDService.addAddress(address);
        model.addAttribute("address", address);
        model.addAttribute("addressId", addressId);
        return "resultAddressParent";
    }

    @GetMapping("/families/{idF}/address/{idA}/person/parent")
    public String retrievePersonModel(@PathVariable("idF") int idF, @PathVariable("idA") int idA, Model model) {
        model.addAttribute("familyID", idF);
        model.addAttribute("addressId", idA);
        model.addAttribute("person", new Person());
        return "formPersonParent";
    }

    @PostMapping("/families/{idF}/address/{idA}/person/parent")
    public String addPerson(@PathVariable("idF") int idF, @PathVariable("idA") int idA,
                            @ModelAttribute Person person, Model model) {
        model.addAttribute("familyID", idF);
        model.addAttribute("addressId", idA);
        int personId = personCRUDService.addPerson(person, idA);
        model.addAttribute("person", person);
        model.addAttribute("personId", personId);
        return "resultPersonParent";
    }

    @GetMapping("/families/{idF}/address/{idA}/person/{idP}/parent")
    public String retrieveParentModel(@PathVariable("idF") int idF, @PathVariable("idA") int idA,
                                      @PathVariable("idP") int idP, Model model) {
        model.addAttribute("familyID", idF);
        model.addAttribute("addressId", idA);
        model.addAttribute("personId", idP);
        model.addAttribute("parent", new Parent());
        return "formParent";
    }

    @PostMapping("/families/{idF}/address/{idA}/person/{idP}/parent")
    public String addParent(@PathVariable("idF") int idF, @PathVariable("idA") int idA, @PathVariable("idP") int idP,
                         @ModelAttribute Parent parent, Model model) {
        model.addAttribute("familyID", idF);
        model.addAttribute("addressId", idA);
        model.addAttribute("personId", idP);
        model.addAttribute("parent", parent);
        parentsCRUDService.addParent(parent,idF,idP);
        return "resultParent";
    }

}
