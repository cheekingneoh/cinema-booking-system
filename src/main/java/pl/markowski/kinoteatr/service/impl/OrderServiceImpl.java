package pl.markowski.kinoteatr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pl.markowski.kinoteatr.model.Concession;
import pl.markowski.kinoteatr.model.Order;
import pl.markowski.kinoteatr.repository.ConcessionRepository;
import pl.markowski.kinoteatr.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final ConcessionRepository concessionRepository;

    @Override
    public String getOrder(Model model) {
        return null;
    }

    @Override
    public String addOrder(Order order, BindingResult result, Model model) {
        return null;
    }
}
