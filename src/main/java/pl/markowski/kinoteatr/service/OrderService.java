package pl.markowski.kinoteatr.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.markowski.kinoteatr.model.Order;

public interface OrderService {

    String getOrder(final Model model);

    String addOrder(final Order order, final BindingResult result, final Model model);


}
