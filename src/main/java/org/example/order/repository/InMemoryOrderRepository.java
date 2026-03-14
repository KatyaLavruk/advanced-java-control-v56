package org.example.order.repository;

import org.example.order.domain.order;
import org.example.order.exception.RepositoryException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, order> map = new HashMap<>();

    @Override
    public void save(order order) throws RepositoryException {
        try {
            map.put(order.getId(), order);
        } catch (Exception e) {
            throw new RepositoryException("Cannot save order", e);
        }
    }

    @Override
    public Optional<order> findById(String id) {
        return Optional.ofNullable(map.get(id));
    }
}