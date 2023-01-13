package pl.markowski.kinoteatr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.markowski.kinoteatr.service.OrderService;


@Controller
@RequiredArgsConstructor
public class OrderController {

    static final class Routes {
        static final String ORDER_ROOT = "/order";
        static final String COMPLETE_ORDER = ORDER_ROOT+"/complete/{id}";
    }

    private final OrderService orderService;

    @GetMapping(Routes.COMPLETE_ORDER)
    public String completeOrder(@PathVariable("id") final long id, final Model model) {
        return orderService.completeOrder(id, model);
    }
}
