package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "packages")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String description;
    private double weight;

    private String status;

    private String pickupAddress;
    private String deliveryAddress;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pickup_partner_id")
    private Partner pickupPartner;

    @ManyToOne
    @JoinColumn(name = "delivery_partner_id")
    private Partner deliveryPartner;

    @ManyToOne
    @JoinColumn(name = "source_hub_id")
    private Hub sourceHub;

    @ManyToOne
    @JoinColumn(name = "destination_hub_id")
    private Hub destinationHub;

    public Package() {
        this.createdAt = LocalDateTime.now();
    }

    public Package(Long id, String itemName, String description, double weight, String status,
                   String pickupAddress, String deliveryAddress, LocalDateTime createdAt,
                   User user, Partner pickupPartner, Partner deliveryPartner,
                   Hub sourceHub, Hub destinationHub) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.weight = weight;
        this.status = status;
        this.pickupAddress = pickupAddress;
        this.deliveryAddress = deliveryAddress;
        this.createdAt = createdAt;
        this.user = user;
        this.pickupPartner = pickupPartner;
        this.deliveryPartner = deliveryPartner;
        this.sourceHub = sourceHub;
        this.destinationHub = destinationHub;
    }

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    public String getStatus() {
        return status;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public Partner getPickupPartner() {
        return pickupPartner;
    }

    public Partner getDeliveryPartner() {
        return deliveryPartner;
    }

    public Hub getSourceHub() {
        return sourceHub;
    }

    public Hub getDestinationHub() {
        return destinationHub;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPickupPartner(Partner pickupPartner) {
        this.pickupPartner = pickupPartner;
    }

    public void setDeliveryPartner(Partner deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }

    public void setSourceHub(Hub sourceHub) {
        this.sourceHub = sourceHub;
    }

    public void setDestinationHub(Hub destinationHub) {
        this.destinationHub = destinationHub;
    }
}