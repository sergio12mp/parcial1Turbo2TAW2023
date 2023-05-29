package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.CuentaclienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaClienteRepository extends JpaRepository<CuentaclienteEntity,Integer> {
}
