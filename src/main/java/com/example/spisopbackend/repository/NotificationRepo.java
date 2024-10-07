package com.example.spisopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spisopbackend.model.notifications.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Integer> {

}
