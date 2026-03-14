package org.example.order.repository;

import org.example.order.domain.order;
import org.example.order.exception.RepositoryException;

import java.util.Optional;

public interface OrderRepository {

    void save(order order) throws RepositoryException;

    Optional<order> findById(String id);

}