package com.webAvanzada.Tarea2_SpringBoot.controllers;

import com.webAvanzada.Tarea2_SpringBoot.entities.Familia;
import com.webAvanzada.Tarea2_SpringBoot.entities.SubFamilia;
import com.webAvanzada.Tarea2_SpringBoot.entities.UserInfo;
import com.webAvanzada.Tarea2_SpringBoot.entities.Usuario;
import com.webAvanzada.Tarea2_SpringBoot.services.FamiliaServices;
import com.webAvanzada.Tarea2_SpringBoot.services.SubFamiliaServices;
import com.webAvanzada.Tarea2_SpringBoot.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/managefam/{idUsuario}")
public class FamiliaController {

    @Autowired
    private FamiliaServices familiaServices;

    @Autowired
    private SubFamiliaServices subFamiliaServices;

    @Autowired
    private UsuarioServices usuarioServices;

    public static UserInfo userInfo = new UserInfo();

    //CRUD FAMILIA
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String createFamilia(Model model){
        model.addAttribute("usuario", userInfo.getIdUserInfo());

        return "createFamilia";
    }

    @RequestMapping(path = "/createFamilia", method = RequestMethod.POST)
    public String newFamilia(@RequestParam String nombreFamilia){
        Familia newFamilia = new Familia(nombreFamilia);
        familiaServices.createOrUpdateFamilia(newFamilia);
        return "redirect:/managefam/" + userInfo.getIdUserInfo() + "/";
    }

    @RequestMapping(value="/{idFamilia}", method=RequestMethod.GET)
    public String getFamilia(Model model, @PathVariable("idFamilia") int idFamilia){
        Familia familia = familiaServices.getFamiliaById(idFamilia);
        model.addAttribute("familia", familia);
        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "createFamilia";
    }

    @GetMapping("/showAllFamilias")
    public String showAllFamilias(Model model, @PathVariable("idUsuario") int idUsuario){
        List<Familia> familiaList = familiaServices.allFamilias();
        if(familiaList == null){
            model.addAttribute("familias", "No Familias Yet.");
        } else {
            model.addAttribute("familias", familiaList);
        }

        Usuario usuario = usuarioServices.getUsuario(idUsuario);
        userInfo.setIdUserInfo(usuario.getIdUsuario());
        userInfo.setNombreUserInfo(usuario.getUsername());

        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "listFamilia";
    }

    @PostMapping("/updateFamilia")
    public String updateFamilia(@RequestParam String nombreFamilia, @RequestParam int idFamilia){
        Familia familia = familiaServices.getFamiliaById(idFamilia);
        familia.setNombreFamilia(nombreFamilia);
        familiaServices.createOrUpdateFamilia(familia);
        return "redirect:/managefam/" + userInfo.getIdUserInfo() + "/showAllFamilias";
    }

    @GetMapping("/deleteFamilia/{idFamilia}")
    public String deleteFamilia(@PathVariable int idFamilia){
        familiaServices.deleteFamilia(idFamilia);
        return "redirect:/managefam/" + userInfo.getIdUserInfo() + "/showAllFamilias";
    }

    //CRUD SUB-FAMILIA

    @RequestMapping(value="/createSubFamilia", method=RequestMethod.GET)
    public String createSubFamilia(Model model, @PathVariable("idUsuario") int idUsuario){
        List<String> familiaList = familiaServices.allFamiliasNames();
        if(familiaList == null){
            model.addAttribute("familias", "No Familias Yet.");
        } else {
            model.addAttribute("familias", familiaList);
        }
        model.addAttribute("usuario", idUsuario);
        return "createSubFamilia";
    }

    @RequestMapping(path="/newSubFamilia", method=RequestMethod.POST)
    public String newSubFamilia(@RequestParam String nombreSubFamilia, @RequestParam String nombreFamilia){
        SubFamilia newSubFamilia = new SubFamilia(nombreFamilia, nombreSubFamilia);
        subFamiliaServices.createOrUpdateSubFamilia(newSubFamilia);
        return "redirect:/managefam/" + userInfo.getIdUserInfo() + "/createSubFamilia";
    }

    @RequestMapping(path="/updateSubFamilia", method=RequestMethod.POST)
    public String updateSubFamilia(@RequestParam int idSubFamilia, @RequestParam String nombreSubFamilia,
                                   @RequestParam String nombreFamilia){
        SubFamilia subFamilia = subFamiliaServices.getSubFamilia(idSubFamilia);
        subFamilia.setNameSubFamily(nombreSubFamilia);
        subFamilia.setFamilia(nombreFamilia);
        subFamiliaServices.createOrUpdateSubFamilia(subFamilia);
        return "redirect:/managefam/" + userInfo.getIdUserInfo() + "/showAllSubFamilias";
    }

    @RequestMapping(value="/getSubFamilia/{idSubFamilia}", method=RequestMethod.GET)
    public String getSubFamilia(Model model, @PathVariable("idSubFamilia") int idSubFamilia){
        SubFamilia subFamilia = subFamiliaServices.getSubFamilia(idSubFamilia);
        model.addAttribute("subFamilia", subFamilia);

        List<String> familiaList = familiaServices.allFamiliasNames();
        if(familiaList == null){
            model.addAttribute("familias", "No Familias Yet.");
        } else {
            model.addAttribute("familias", familiaList);
        }
        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "createSubFamilia";
    }

    @RequestMapping(value="/showAllSubFamilias", method=RequestMethod.GET)
    public String showAllSubFamilias(Model model){
        List<SubFamilia> subFamiliaList = subFamiliaServices.allSubFamilias();
        if(subFamiliaList == null){
            model.addAttribute("subFamilias", "No Sub-Familias Yet.");
        } else {
            model.addAttribute("subFamilias", subFamiliaList);
        }
        model.addAttribute("usuario", userInfo.getIdUserInfo());
        return "listSubFamilia";
    }

    @PostMapping("/updateSubFamilia/{idSubFamilia}")
    public String updateSubFamilia(@RequestParam String nombreSubFamilia, @RequestParam String nombreFamilia,
                                   @PathVariable("idSubFamilia") int idSubFamilia){
        SubFamilia subFamilia = subFamiliaServices.getSubFamilia(idSubFamilia);
        subFamilia.setFamilia(nombreFamilia);
        subFamilia.setNameSubFamily(nombreSubFamilia);
        subFamiliaServices.createOrUpdateSubFamilia(subFamilia);
        return "redirect:/managefam/" + userInfo.getIdUserInfo() + "/showAllSubFamilias";
    }

    @GetMapping("/deleteSubFamilia/{idSubFamilia}")
    public String deleteSubFamilia(@PathVariable("idSubFamilia") int idSubFamilia){
        subFamiliaServices.deleteSubFamilia(idSubFamilia);
        return "redirect:/managefam/" + userInfo.getIdUserInfo() + "/showAllSubFamilias";
    }
}
