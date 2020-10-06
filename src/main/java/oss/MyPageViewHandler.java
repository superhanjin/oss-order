package oss;

import oss.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageViewHandler {


    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1 (@Payload OrderPlaced orderPlaced) {
        try {
            if (orderPlaced.isMe()) {
                // view 객체 생성
                MyPage myPage = new MyPage();
                // view 객체에 이벤트의 Value 를 set 함
                myPage.setOrderId(orderPlaced.getId());
                myPage.setCustomerName(orderPlaced.getCustomerName());
                myPage.setAddress(orderPlaced.getAddress());
                myPage.setPhoneNumber(orderPlaced.getPhoneNumber());
                myPage.setInternetCount(orderPlaced.getInternetCount());
                myPage.setTvCount(orderPlaced.getTvCount());
                myPage.setState("OrderPlaced");
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenActivationCompleted_then_UPDATE_1(@Payload ActivationCompleted activationCompleted) {
        try {
            if (activationCompleted.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByOrderId(activationCompleted.getOrderId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setEquipmentId(activationCompleted.getEquipmentId());
                    myPage.setState("ActivationCompleted");
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenScheduled_then_UPDATE_2(@Payload Scheduled scheduled) {
        try {
            if (scheduled.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByOrderId(scheduled.getOrderId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setVisitDateTime(scheduled.getVisitDateTime());
                    myPage.setState("Scheduled");
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenFieldWorkCompleted_then_UPDATE_3(@Payload FieldWorkCompleted fieldWorkCompleted) {
        try {
            if (fieldWorkCompleted.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByOrderId(fieldWorkCompleted.getOrderId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setState("FieldWorkCompleted");
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCancelled_then_UPDATE_4(@Payload OrderCancelled orderCancelled) {
        try {
            if (orderCancelled.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByOrderId(orderCancelled.getId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setState("OrderCancelled");
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenActivationCancelled_then_UPDATE_5(@Payload ActivationCancelled activationCancelled) {
        try {
            if (activationCancelled.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByOrderId(activationCancelled.getOrderId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setState("ActivationCancelled");
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenScheduleCancelled_then_UPDATE_6(@Payload ScheduleCancelled scheduleCancelled) {
        try {
            if (scheduleCancelled.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByOrderId(scheduleCancelled.getOrderId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setState("ScheduleCancelled");
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenFieldWorkFailed_then_UPDATE_7(@Payload FieldWorkFailed fieldWorkFailed) {
        try {
            if (fieldWorkFailed.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByOrderId(fieldWorkFailed.getOrderId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setState("FieldWorkFailed");
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}