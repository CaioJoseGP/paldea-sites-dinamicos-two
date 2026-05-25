package com.jardim.paldea.controller;

import com.jardim.paldea.model.Plant;
import com.jardim.paldea.model.PlantCatalog;
import com.jardim.paldea.model.PlantForm;
import com.jardim.paldea.support.SessionHelper;
import com.jardim.paldea.support.VisitCookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlantController {

    private final PlantCatalog plantCatalog;

    public PlantController(PlantCatalog plantCatalog) {
        this.plantCatalog = plantCatalog;
    }

    @GetMapping("/plantas")
    public ModelAndView showCrudPage(@RequestParam(defaultValue = "") String searchId,
                                     HttpServletRequest request, HttpSession session) {
        if (!SessionHelper.hasUser(session)) {
            return new ModelAndView("redirect:/login");
        }

        return buildCrudPage(HttpStatus.OK, "Gerencie o catalogo da Paldea a partir dos formularios abaixo.",
                new PlantForm(), searchId, "", request);
    }

    @GetMapping("/plantas/buscar")
    public ModelAndView search(@RequestParam(defaultValue = "") String id,
                               HttpServletRequest request, HttpSession session) {
        if (!SessionHelper.hasUser(session)) {
            return new ModelAndView("redirect:/login");
        }

        long plantId = PlantForm.parseId(id);
        if (plantId == 0L) {
            return buildCrudPage(HttpStatus.BAD_REQUEST, "Informe um identificador numerico para buscar uma planta.",
                    new PlantForm(), id, "", request);
        }

        Plant plant = plantCatalog.findById(plantId);
        if (plant == null) {
            return buildCrudPage(HttpStatus.NOT_FOUND, "Nenhum cadastro foi encontrado para o ID " + plantId + ".",
                    new PlantForm(), id, "", request);
        }

        return buildCrudPage(HttpStatus.OK, "A planta " + plant.getNome() + " foi localizada com sucesso.",
                new PlantForm(), id, "", request);
    }

    @PostMapping("/plantas/inserir")
    public ModelAndView create(PlantForm plantForm, HttpServletRequest request, HttpSession session) {
        if (!SessionHelper.hasUser(session)) {
            return new ModelAndView("redirect:/login");
        }

        String validationMessage = plantForm.validatePlantData();
        if (validationMessage != null) {
            return buildCrudPage(HttpStatus.BAD_REQUEST, validationMessage, new PlantForm(), "", "", request);
        }

        Plant plant = plantCatalog.create(plantForm);
        return buildCrudPage(HttpStatus.OK, "A planta " + plant.getNome() + " foi cadastrada com o ID " + plant.getId() + ".",
                new PlantForm(), "", "", request);
    }

    @PostMapping("/plantas/atualizar")
    public ModelAndView update(PlantForm plantForm, HttpServletRequest request, HttpSession session) {
        if (!SessionHelper.hasUser(session)) {
            return new ModelAndView("redirect:/login");
        }

        long plantId = plantForm.idAsLong();
        if (plantId == 0L) {
            return buildCrudPage(HttpStatus.BAD_REQUEST, "Informe um ID valido para atualizar um cadastro existente.",
                    plantForm, "", "", request);
        }

        String validationMessage = plantForm.validatePlantData();
        if (validationMessage != null) {
            return buildCrudPage(HttpStatus.BAD_REQUEST, validationMessage, plantForm, "", "", request);
        }

        Plant plant = plantCatalog.update(plantId, plantForm);
        if (plant == null) {
            return buildCrudPage(HttpStatus.NOT_FOUND, "Nao existe planta cadastrada com o ID " + plantId + " para atualizacao.",
                    plantForm, "", "", request);
        }

        return buildCrudPage(HttpStatus.OK, "O cadastro da planta " + plant.getNome() + " foi atualizado.",
                plantForm, "", "", request);
    }

    @PostMapping("/plantas/apagar")
    public ModelAndView delete(@RequestParam(defaultValue = "") String id, HttpServletRequest request, HttpSession session) {
        if (!SessionHelper.hasUser(session)) {
            return new ModelAndView("redirect:/login");
        }

        long plantId = PlantForm.parseId(id);
        if (plantId == 0L) {
            return buildCrudPage(HttpStatus.BAD_REQUEST, "Informe um ID valido para remover um cadastro.",
                    new PlantForm(), "", id, request);
        }

        if (!plantCatalog.delete(plantId)) {
            return buildCrudPage(HttpStatus.NOT_FOUND, "Nao existe planta cadastrada com o ID " + plantId + " para exclusao.",
                    new PlantForm(), "", id, request);
        }

        return buildCrudPage(HttpStatus.OK, "O cadastro de ID " + plantId + " foi removido da vitrine administrativa.",
                new PlantForm(), "", id, request);
    }

    private ModelAndView buildCrudPage(HttpStatus status, String message, PlantForm plantForm, String searchId,
                                       String deleteId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("plantas");
        modelAndView.setStatus(status);
        modelAndView.addObject("plantForm", plantForm);
        modelAndView.addObject("searchId", searchId);
        modelAndView.addObject("deleteId", deleteId);
        modelAndView.addObject("plants", plantCatalog.findAll());
        modelAndView.addObject("feedbackMessage", message);
        modelAndView.addObject("feedbackTone", status == HttpStatus.OK ? "success" : "danger");
        modelAndView.addObject("totalVisitas", VisitCookie.current(request));
        return modelAndView;
    }
}
