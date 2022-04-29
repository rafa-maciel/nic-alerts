package com.rmaciel.nicalerts.monitoring.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DeviceType {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
}
