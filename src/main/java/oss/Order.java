package oss;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String customerName;
    private String address;
    private String phoneNumber;
    private Integer internetCount;
    private Integer tvCount;
    private String state;

    @PostPersist
    public void onPostPersist(){
        if(this.getState().equals("OrderRequest")) {
            OrderPlaced orderPlaced = new OrderPlaced();
            BeanUtils.copyProperties(this, orderPlaced);
            orderPlaced.publishAfterCommit();

            System.out.println("#################### OrderPlaced Event has been published ####################");
        }else {
            System.out.println("#################### Unknown Command is Called ####################");
        }
    }

    @PostUpdate
    public void onPostUpdate(){
        if(this.getState().equals("OrderCancel")){
            OrderCancelled orderCancelled = new OrderCancelled();
            BeanUtils.copyProperties(this, orderCancelled);
            orderCancelled.publishAfterCommit();

            System.out.println("#################### OrderCancel Event has been published ####################");
        }else{
            System.out.println("#################### Unknown Command is Called ####################");
        }
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Integer getInternetCount() {
        return internetCount;
    }
    public void setInternetCount(Integer internetCount) {
        this.internetCount = internetCount;
    }
    public Integer getTvCount() {
        return tvCount;
    }
    public void setTvCount(Integer tvCount) {
        this.tvCount = tvCount;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

}
