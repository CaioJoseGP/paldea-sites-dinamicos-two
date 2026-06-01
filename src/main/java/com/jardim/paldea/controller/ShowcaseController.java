package com.jardim.paldea.controller;

import com.jardim.paldea.model.PlantCatalog;
import com.jardim.paldea.support.SessionHelper;
import com.jardim.paldea.support.VisitCookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// Entrega 3 - get / post
@Controller
public class ShowcaseController {

    private final PlantCatalog plantCatalog;

    public ShowcaseController(PlantCatalog plantCatalog) {
        this.plantCatalog = plantCatalog;
    }

    @GetMapping("/catalogo")
    public ModelAndView showCatalog(@RequestParam(defaultValue = "ativa") String promocaoStatus,
                                    HttpServletRequest request, HttpSession session) {
        if (!SessionHelper.hasUser(session)) {
            return new ModelAndView("redirect:/login");
        }

        boolean promotionActive = !"inativa".equalsIgnoreCase(promocaoStatus);

        ModelAndView modelAndView = new ModelAndView("catalogo");
        modelAndView.addObject("promocaoAtiva", promotionActive);
        modelAndView.addObject("promocaoStatus", promotionActive ? "ativa" : "inativa");
        modelAndView.addObject("plants", plantCatalog.findAll());
        modelAndView.addObject("totalVisitas", VisitCookie.current(request));
        return modelAndView;
    }
}
