package pl.markowski.kinoteatr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pl.markowski.kinoteatr.model.Repertoire;
import pl.markowski.kinoteatr.repository.RepertoireRepository;
import pl.markowski.kinoteatr.repository.ConcessionRepository;

import pl.markowski.kinoteatr.model.Concession;
import pl.markowski.kinoteatr.service.ConcessionService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConcessionServiceImpl implements ConcessionService {

    private final ConcessionRepository concessionRepository;
    private final RepertoireRepository repertoireRepository;

    @Override
    public String getConcessions(final Model model) {
        final List<Concession> concessions = concessionRepository.findAll();
        model.addAttribute("concessions", concessions);
        return "concessionIndex";
    }

    @Override
    public String addConcession(final Concession concession, final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            return "add-concession";
        }
        concessionRepository.save(concession);
        log.info("A new concession has been added to the database " + concession.getName());
        return "redirect:/concessions/list";
    }

    @Override
    public String showUpdateFormConcession(final long id, final Model model) {
        final Concession concession = concessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect ID: " + id));
        model.addAttribute("concession", concession);
        return "update-concession";
    }

    @Override
    public String updateConcession(final long id, final Concession concession) {
        final Concession concessionFromDb = concessionRepository.getOne(id);
        concessionFromDb.setDescription(concession.getDescription());
        concessionFromDb.setImageUrl(concession.getImageUrl());
        concessionFromDb.setCategory(concession.getCategory());
        concessionFromDb.setStock(concession.getStock());
        concessionFromDb.setPrice(concession.getPrice());
        log.info("Concession data edited " + concession.getName());
        return "redirect:/concessions/list";
    }

    @Override
    public String deleteConcession(final long id, final Model model) {
        final List<Repertoire> repertoires = repertoireRepository.findByConcessionId(id);
        repertoires.forEach(r -> repertoireRepository.deleteById(r.getId()));
        final Concession concession = concessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect ID : " + id));
        concessionRepository.delete(concession);
        final List<Concession> concessions = concessionRepository.findAll();
        model.addAttribute("concessions", concessions);
        log.info("The concession has been removed " + concession.getName());
        return "concessionIndex";
    }

    @Override
    public String showConcessionRepertoireForm(String concessionName, Model model) {
        final Concession concessionRepertoire = concessionRepository.findByName(concessionName);
        model.addAttribute("concessionRepertoire", concessionRepertoire);
        model.addAttribute("repertoire", new Repertoire());
        return "concession-repertoire";
    }

    @Override
    public String addConcessionRepertoire(Repertoire repertoire, Long concessionId, BindingResult result) {
        repertoire.setConcession(concessionRepository.getOne(concessionId));
        repertoireRepository.save(repertoire);
        log.info("Added repertoire for the concession with ID " + concessionId);
        return "redirect:/concessions/list";
    }

    @Override
    public String showUpdateConcessionRepertoireForm(String concessionName, Long repertoireId, Model model) {
        final Repertoire repertoire = repertoireRepository.getOne(repertoireId);
        final Concession concessionRepertoire = concessionRepository.findByName(concessionName);
        model.addAttribute("concessionRepertoire", concessionRepertoire);
        model.addAttribute("repertoire", repertoire);
        return "concession-repertoire";
    }

    @Override
    public String updateConcessionRepertoire(Repertoire repertoire, Long repertoireId, BindingResult result) {
        final Repertoire repertoireFromDb = repertoireRepository.getOne(repertoireId);
        repertoireFromDb.setDate(repertoire.getDate());
        log.info("Updated repertoire data for " + repertoire.getConcession().getName());
        return "redirect:/concessions/list";
    }

    @Override
    public String deleteConcessionRepertoire(Long repertoireId, Model model) {
        repertoireRepository.deleteById(repertoireId);
        log.info("Removed repertoire with ID " + repertoireId);
        return "redirect:/concessions/list";
    }

}
