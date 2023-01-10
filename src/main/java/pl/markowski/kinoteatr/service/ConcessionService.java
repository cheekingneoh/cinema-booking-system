package pl.markowski.kinoteatr.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.markowski.kinoteatr.model.Repertoire;
import pl.markowski.kinoteatr.model.Concession;

public interface ConcessionService {

    String getConcessions(final Model model);

    String addConcession(final Concession concession, final BindingResult result, final Model model);

    String showUpdateFormConcession(final long id, final Model model);

    String updateConcession(final long id, final Concession concession);

    String deleteConcession(final long id, final Model model);

    String showConcessionRepertoireForm(final String concessionName, final Model model);

    String addConcessionRepertoire(final Repertoire repertoire, final Long concessionId, final BindingResult result);

    String showUpdateConcessionRepertoireForm(final String concessionName, final Long repertoireId, final Model model);

    String updateConcessionRepertoire(final Repertoire repertoire, final Long repertoireId, final BindingResult result);

    String deleteConcessionRepertoire(final Long repertoireId, final Model model);
}