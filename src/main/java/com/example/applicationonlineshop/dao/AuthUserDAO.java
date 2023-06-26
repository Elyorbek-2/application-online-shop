package com.example.applicationonlineshop.dao;

import com.example.applicationonlineshop.entity.AuthUser;
import com.example.applicationonlineshop.enums.Status;
import jakarta.persistence.Query;
import lombok.NonNull;

import java.util.Optional;


public class AuthUserDAO extends BaseDAO<AuthUser, Integer> {

    public Optional<AuthUser> findByEmail(@NonNull String email) {
        try {
            begin();
            AuthUser user = entityManager.createQuery("select t from AuthUser t where t.email ilike :email", AuthUser.class)
                    .setParameter("email", email).getSingleResult();
            commit();
            return Optional.ofNullable(user);
        } catch (Exception e) {
//            e.printStackTrace();
            rollback();
            return Optional.empty();
        }
    }

    public Optional<AuthUser> emailAndActivationCode(String email, String activationCode) {
        try {
            begin();
            AuthUser authUser = entityManager.createQuery("select t from AuthUser t " +
                                    "where t.email=:email and t.activationCode=:activationCode",AuthUser.class)
                    .setParameter("email", email)
                    .setParameter("activationCode", activationCode).getSingleResult();
            commit();
            return Optional.ofNullable(authUser);
        } catch (Exception e) {
            rollback();
            return Optional.empty();
        }

    }

    public Optional<AuthUser> existsEmailAndActivateCode(String email, String activatedCode) {
        begin();
        AuthUser user = entityManager.find(AuthUser.class, email);
        if (user.getActivationCode().equals(activatedCode) && user.getEmail().equals(email)) {
            commit();
            return Optional.ofNullable(user);
        } else {
            rollback();
            return Optional.empty();
        }
    }

    public boolean editStatusUser(String email) {
        try {
            begin();
            AuthUser user = entityManager.find(AuthUser.class, email);
            user.setStatus(Status.ACTIVE);
            commit();
            return true;
        } catch (Exception e) {
            rollback();
            return false;
        }
    }

    /*public Optional<AuthUser> existsEmailAndActivationCode(String email, String activationCode) {
        try {
            begin();
            AuthUser authUser = entityManager.createQuery("select t from AuthUser t " +
                                    "where t.email ilike ?1 and  t.activationCode  ilike ?2",
                            AuthUser.class).setParameter(1, email)
                    .setParameter(2, activationCode).getSingleResult();
            commit();
            return Optional.ofNullable(authUser);
        } catch (Exception e) {
            rollback();
            return Optional.empty();
        }

    }*/

    public boolean updateUserStatus(String email) {
        try {
            begin();
            entityManager.createQuery("update AuthUser t set status='ACTIVE' where t.email=:email").
                    setParameter("email", email);
            commit();
            return true;
        } catch (Exception e) {
            rollback();
            return false;
        }

    }
}
