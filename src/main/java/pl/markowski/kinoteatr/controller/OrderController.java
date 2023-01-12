package pl.markowski.kinoteatr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.markowski.kinoteatr.model.Order;
import pl.markowski.kinoteatr.service.ConcessionService;
import pl.markowski.kinoteatr.service.OrderService;


@Controller
@RequiredArgsConstructor
public class OrderController {

    static final class Routes {
        static final String MOVIE_ROOT = "/movies/{movieName}";
        static final String SPECTACLE_ROOT = "/spectacles/{spectacleName}";
        static final String RESERVATION_ROOT = "/reservation";
        static final String REPERTOIRE_ROOT = "/{repertoireId}";
        static final String RESERVATION_ID_ROOT = "/{reservationId}";
        static final String ORDER_ROOT = "/order";
        static final String ORDER_CONCESSION = ORDER_ROOT+RESERVATION_ID_ROOT;
        static final String MOVIE_RESERVATION = MOVIE_ROOT + RESERVATION_ROOT;
        static final String SPECTACLE_RESERVATION = SPECTACLE_ROOT + RESERVATION_ROOT;
        static final String MOVIE_RESERVATION_ID = MOVIE_ROOT + RESERVATION_ROOT + REPERTOIRE_ROOT;
        static final String SPECTACLE_RESERVATION_ID = SPECTACLE_ROOT + RESERVATION_ROOT + REPERTOIRE_ROOT;
        static final String SEAT_RESERVATION = RESERVATION_ROOT + "/save/{repertoireId}";
    }

    private final OrderService orderService;

}
