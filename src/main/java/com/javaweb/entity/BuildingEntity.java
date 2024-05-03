package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ward")
    private String ward;

    @Column(name = "street")
    private String street;

    @Column(name = "district")
    private String district;

    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    private List<RentAreaEntity> rentArea = new ArrayList<>();

    public List<RentAreaEntity> getRentArea() {
        return rentArea;
    }
    public void setRentArea(List<RentAreaEntity> rentArea) {
        this.rentArea = rentArea;
    }

//    @OneToMany(mappedBy =  "building", fetch = FetchType.LAZY)
//    private List<AssignmentBuildingEntity>  assignmentBuildingEntities = new ArrayList<>();
//
//    public List<AssignmentBuildingEntity> getAssignmentBuildingEntities() {
//        return assignmentBuildingEntities;
//    }
//
//    public void setAssignmentBuildingEntities(List<AssignmentBuildingEntity> assignmentBuildingEntities) {
//        this.assignmentBuildingEntities = assignmentBuildingEntities;
//    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    @JoinTable(name= "assignmentbuilding",
        joinColumns = @JoinColumn(name = "buildingid",nullable = false),
        inverseJoinColumns = @JoinColumn(name = "staffid",nullable = false))
    private List<UserEntity> userEntities = new ArrayList<>();

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }
    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private Long level;

    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Column(name = "managername")
    private String managerName;
    @Column(name = "floorarea")
    private Long floorArea;

    @Column(name = "rentprice")
    private Long rentPrice;

    @Column(name = "servicefee")
    private String serviceFee;

    @Column(name = "brokeragefee")
    private String brokeragefee;

    @Column(name = "type")
    private String type;

    @Column(name = "managerphone")
    private String managerPhone;

    @Column(name = "structure")
    private String structure;

    @Column(name = "rentpricedescription")
    private String rentpricedescription;

    @Column(name = "carfee")
    private Long carfee;

    @Column(name = "motofee")
    private Long motofee;

    @Column(name = "overtimefee")
    private Long overtimefee;

    @Column(name = "waterfee")
    private Long waterfee;

    @Column(name = "electricityfee")
    private Long electricityfee;

    @Column(name = "deposit")
    private Long deposit;

    @Column(name = "payment")
    private Long payment;

    @Column(name = "renttime")
    private Long renttime;

    @Column(name = "decorationtime")
    private String decorationtime;

    @Column(name="avatar")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getRentpricedescription() {
        return rentpricedescription;
    }

    public void setRentpricedescription(String rentpricedescription) {
        this.rentpricedescription = rentpricedescription;
    }



    public String getDecorationtime() {
        return decorationtime;
    }

    public void setDecorationtime(String decorationtime) {
        this.decorationtime = decorationtime;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBrokeragefee() {
        return brokeragefee;
    }

    public void setBrokeragefee(String brokeragefee) {
        this.brokeragefee = brokeragefee;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getCarfee() {
        return carfee;
    }

    public void setCarfee(Long carfee) {
        this.carfee = carfee;
    }

    public Long getMotofee() {
        return motofee;
    }

    public void setMotofee(Long motofee) {
        this.motofee = motofee;
    }

    public Long getOvertimefee() {
        return overtimefee;
    }

    public void setOvertimefee(Long overtimefee) {
        this.overtimefee = overtimefee;
    }

    public Long getWaterfee() {
        return waterfee;
    }

    public void setWaterfee(Long waterfee) {
        this.waterfee = waterfee;
    }

    public Long getElectricityfee() {
        return electricityfee;
    }

    public void setElectricityfee(Long electricityfee) {
        this.electricityfee = electricityfee;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public Long getRenttime() {
        return renttime;
    }

    public void setRenttime(Long renttime) {
        this.renttime = renttime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }
    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public Long getFloorArea() {
        return floorArea;
    }
    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public Long getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }
    public String getServiceFee() {
        return serviceFee;
    }
    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }



}
