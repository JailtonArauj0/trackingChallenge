package com.challenge.TrackingChallenge.repositories;

import com.challenge.TrackingChallenge.domain.Veiculo.Veiculo;
import com.challenge.TrackingChallenge.exception.CustomException;
import com.challenge.TrackingChallenge.exception.EntityNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VeiculoRepository {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SessionFactory sessionFactory;

    public Veiculo salvar(Veiculo veiculo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(veiculo);
            transaction.commit();
            return veiculo;
        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Erro ao salvar no banco de dados! " + he.getCause());
        } finally {
            session.close();
        }
    }

    public Veiculo listarPorId(long id) {
        Session session = sessionFactory.openSession();

        try {
            String sql = "FROM Veiculo WHERE id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            Veiculo veiculo = (Veiculo) query.uniqueResult();

            if(veiculo == null){
                throw new EntityNotFoundException("Veículo não encontrado para os parâmetros informados");
            }
            return veiculo;

        } catch (HibernateException he) {
            throw new HibernateException("Erro ao consultar no banco de dados!");
        } finally {
            session.close();
        }
    }

    public List<Veiculo> listarTodosPorIdCLiente(long idCliente) {
        Session session = sessionFactory.openSession();

        try {
            String sql = "FROM Veiculo WHERE idCliente = :idCliente";
            Query query = session.createQuery(sql);
            query.setParameter("idCliente", idCliente);
            List<Veiculo> veiculos = query.getResultList();

            if(veiculos == null || veiculos.isEmpty()){
                throw new EntityNotFoundException("Veículo não encontrado para os parâmetros informados");
            }
            return veiculos;

        } catch (HibernateException he) {
            throw new HibernateException("Erro ao consultar no banco de dados!");
        } finally {
            session.close();
        }
    }

    public Veiculo listarPorPlaca(String placa) {
        Session session = sessionFactory.openSession();

        try {
            String sql = "FROM Veiculo WHERE placa = :placa";
            Query query = session.createQuery(sql);
            query.setParameter("placa", placa);
            Veiculo veiculo = (Veiculo) query.uniqueResult();

            if(veiculo == null){
                throw new EntityNotFoundException("Veículo não encontrado para os parâmetros informados");
            }
            return veiculo;

        } catch (HibernateException he) {
            throw new HibernateException("Erro ao consultar no banco de dados!");
        } finally {
            session.close();
        }
    }

    public Veiculo listarPorChassi(String chassi) {
        Session session = sessionFactory.openSession();

        try {
            String sql = "FROM Veiculo WHERE chassi = :chassi";
            Query query = session.createQuery(sql);
            query.setParameter("chassi", chassi);
            Veiculo veiculo = (Veiculo) query.uniqueResult();

            if(veiculo == null){
                throw new EntityNotFoundException("Veículo não encontrado para os parâmetros informados");
            }
            return veiculo;

        } catch (HibernateException he) {
            throw new HibernateException("Erro ao consultar no banco de dados!");
        } finally {
            session.close();
        }
    }

    public Veiculo listarPorRenavam(String renavam) {
        Session session = sessionFactory.openSession();

        try {
            String sql = "FROM Veiculo WHERE renavam = :renavam";
            Query query = session.createQuery(sql);
            query.setParameter("renavam", renavam);
            Veiculo veiculo = (Veiculo) query.uniqueResult();

            if(veiculo == null){
                throw new EntityNotFoundException("Veículo não encontrado para os parâmetros informados");
            }
            return veiculo;

        } catch (HibernateException he) {
            throw new RuntimeException("Erro ao consultar no banco de dados!");
        } finally {
            session.close();
        }
    }
    public Veiculo listarPorParametros(String placa, String renavam, String chassi) {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder("FROM Veiculo WHERE 1 = 1");

        if (placa != null && !placa.isEmpty()) {
            sql.append(" AND placa = :placa");
        }
        if (renavam != null && !renavam.isEmpty()) {
            sql.append(" AND renavam = :renavam");
        }
        if (chassi != null && !chassi.isEmpty()) {
            sql.append(" AND chassi = :chassi");
        }

        try {
            Query query = session.createQuery(sql.toString());

            if (placa != null && !placa.isEmpty()) {
                query.setParameter("placa", placa);
            }
            if (renavam != null && !renavam.isEmpty()) {
                query.setParameter("renavam", renavam);
            }
            if (chassi != null && !chassi.isEmpty()) {
                query.setParameter("chassi", chassi);
            }

            Veiculo veiculo = (Veiculo) query.uniqueResult();

            if(veiculo == null){
                throw new EntityNotFoundException("Veículo não encontrado para os parâmetros informados");
            }
            return veiculo;

        } catch (HibernateException he) {
            throw new RuntimeException("Erro ao consultar no banco de dados!");
        } finally {
            session.close();
        }
    }

    public Veiculo atualizar(Veiculo veiculo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            if (this.listarPorId(veiculo.getId()) != null && clienteRepository.listarPorId(veiculo.getIdCliente()) != null){
                transaction = session.beginTransaction();
                Veiculo veiculoAtualizado = session.merge(veiculo);
                transaction.commit();
                return veiculoAtualizado;
            }
            throw new CustomException("Informe valores válidos para ID ou ID Cliente");
        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Erro ao atualizar no banco de dados!");
        } finally {
            session.close();
        }
    }

    public void deletar(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Veiculo veiculo = session.get(Veiculo.class, id);
            
            if (veiculo != null) {
                session.remove(veiculo);
                transaction.commit();
            } else {
                throw new EntityNotFoundException("Veículo não encontrado no banco de dados");
            }

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Erro ao deletar, tente novamente!");
        } finally {
            session.close();
        }
    }

}
