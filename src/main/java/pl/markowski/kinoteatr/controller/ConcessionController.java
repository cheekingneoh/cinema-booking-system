package pl.markowski.kinoteatr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.markowski.kinoteatr.model.Repertoire;
import pl.markowski.kinoteatr.model.Concession;
import pl.markowski.kinoteatr.service.ConcessionService;

@Controller
@RequiredArgsConstructor
public class ConcessionController {
    static final class Routes {
        static final String ROOT = "/concessions";
        static final String ADMIN = ROOT + "/admin";
        static final String CONCESSION_NAME = ADMIN + "/{concessionName}";
        static final String LIST = ROOT + "/list";
        static final String FORM = ROOT + "/showForm";
        static final String ADD = ROOT + "/add";
        static final String EDIT = ROOT + "/edit/{id}";
        static final String UPDATE = ROOT + "/update/{id}";
        static final String DELETE = ROOT + "/delete/{id}";
        static final String ADD_REPERTOIRE = ADMIN + "/newRepertoire";
        static final String NEW_REPERTOIRE = CONCESSION_NAME + "/newRepertoire";
        static final String UPDATE_REPERTOIRE_ID = CONCESSION_NAME + "/updateRepertoire/{repertoireId}";
        static final String UPDATE_REPERTOIRE = ADMIN + "/updateRepertoire";
        static final String DELETE_REPERTOIRE = ADMIN + "/deleteRepertoire/{repertoireId}";
    }

    private final ConcessionService concessionService;

    @GetMapping(Routes.LIST)
    public String getConcessions(final Model model) {
        return concessionService.getConcessions(model);
    }

    @GetMapping(Routes.FORM)
    public String showConcessionForm(final Concession concession) {
        return "add-concession";
    }

    @PostMapping(Routes.ADD)
    public String addConcession(@Validated final Concession concession, final BindingResult result, final Model model) {
        return concessionService.addConcession(concession, result, model);
    }

    @GetMapping(Routes.EDIT)
    public String showUpdateFormConcession(@PathVariable("id") final long id, final Model model) {
        return concessionService.showUpdateFormConcession(id, model);
    }

    @PostMapping(Routes.UPDATE)
    @Transactional
    public String updateConcession(@PathVariable("id") final long id, @Validated final Concession concession) {
        return concessionService.updateConcession(id, concession);
    }

    @GetMapping(Routes.DELETE)
    public String deleteConcession(@PathVariable("id") final long id, final Model model) {
        return concessionService.deleteConcession(id, model);
    }

    @GetMapping(Routes.NEW_REPERTOIRE)
    public String showConcessionRepertoireForm(@PathVariable("concessionName") final String concessionName,
            final Model model) {
        return concessionService.showConcessionRepertoireForm(concessionName, model);
    }

    @PostMapping(Routes.ADD_REPERTOIRE)
    @Transactional
    public String addConcessionRepertoire(@ModelAttribute("repertoire") final Repertoire repertoire,
            @ModelAttribute("concessionId") final Long concessionId, final BindingResult result) {
        return concessionService.addConcessionRepertoire(repertoire, concessionId, result);
    }

    @GetMapping(Routes.UPDATE_REPERTOIRE_ID)
    public String showUpdateConcessionRepertoireForm(@PathVariable("concessionName") final String concessionName,
            @PathVariable("repertoireId") final Long repertoireId, final Model model) {
        return concessionService.showUpdateConcessionRepertoireForm(concessionName, repertoireId, model);
    }

    @PostMapping(Routes.UPDATE_REPERTOIRE)
    @Transactional
    public String updateConcessionRepertoire(@ModelAttribute("repertoire") final Repertoire repertoire,
            @ModelAttribute("concessionId") final Long concessionId,
            @ModelAttribute("repertoireId") final Long repertoireId, final BindingResult result) {
        return concessionService.updateConcessionRepertoire(repertoire, repertoireId, result);
    }

    @GetMapping(Routes.DELETE_REPERTOIRE)
    @Transactional
    public String deleteConcessionRepertoire(@PathVariable("repertoireId") final Long repertoireId, final Model model) {
        return concessionService.deleteConcessionRepertoire(repertoireId, model);
    }
}
