package pl.markowski.kinoteatr.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.markowski.kinoteatr.model.Order;

public interface OrderService {

    String getOrders(final Model model);

    String completeOrder(long completedId, final Model model);

}
