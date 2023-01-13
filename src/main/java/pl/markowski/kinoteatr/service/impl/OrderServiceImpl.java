package pl.markowski.kinoteatr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.markowski.kinoteatr.model.Reservation;
import pl.markowski.kinoteatr.repository.ReservationRepository;
import pl.markowski.kinoteatr.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final ReservationRepository reservationRepository;

    @Override
    public String getOrders(final Model model) {
        final List<Reservation> reservations = reservationRepository.findByOrderCompleted(false);
        if(reservations.size()==0){
            model.addAttribute("reservations",null);
        }else{
            model.addAttribute("reservations",reservations);
        }
        return "view-orders";
    }

    @Override
    public String completeOrder(long completedId, final Model model) {
        final Reservation completeOrder = reservationRepository.findById(completedId).orElseThrow(() -> new IllegalArgumentException("Invalid Reservation ID: " + completedId));
        completeOrder.setOrderCompleted(true);
        reservationRepository.save(completeOrder);
        final List<Reservation> reservations = reservationRepository.findByOrderCompleted(false);
        if(reservations.size()==0){
            model.addAttribute("reservations",null);
        }else{
            model.addAttribute("reservations",reservations);
        }
        return "view-orders";
    }
}
