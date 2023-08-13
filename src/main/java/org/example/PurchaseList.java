package org.example;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "purchaselist")
public class PurchaseList {

    @EmbeddedId
    private PurchaseListKey Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_name", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "name")
            , referencedColumnName = "name")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_name", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "name")
            , referencedColumnName = "name")
    private Course course;

    private Integer price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchaseListKey getId() {
        return Id;
    }

    public void setId(PurchaseListKey id) {
        Id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
