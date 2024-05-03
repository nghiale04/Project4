package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name="assignmentbuilding")
public class AssignmentBuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
}
