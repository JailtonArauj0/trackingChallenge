package com.challenge.TrackingChallenge.repositories;

import com.challenge.TrackingChallenge.domain.Usuario.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Usuario listarUsuario(String login){
        Session session = sessionFactory.openSession();
        Usuario usuario = null;
        try {
            String sql = "FROM Usuario WHERE login = :login";
            Query query = session.createQuery(sql);
            query.setParameter("login", login);
            usuario = (Usuario) query.uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usuario;
    }

    public Usuario salvarUsuario(Usuario usuario){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
            session.refresh(usuario);
            return usuario;
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
