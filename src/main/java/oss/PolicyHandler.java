package oss;

import oss.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverActivationCompleted_UpdateState(@Payload ActivationCompleted activationCompleted){

        if(activationCompleted.isMe()){
            Optional<Order> orderOptional = orderRepository.findById(activationCompleted.getOrderId());
            Order order = orderOptional.get();
            order.setState("ActivationCompleted");
            orderRepository.save(order);

            System.out.println("##### listener UpdateState : " + activationCompleted.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverScheduled_UpdateState(@Payload Scheduled scheduled){

        if(scheduled.isMe()){
            Optional<Order> orderOptional = orderRepository.findById(scheduled.getOrderId());
            Order order = orderOptional.get();
            order.setState("Scheduled");
            orderRepository.save(order);

            System.out.println("##### listener UpdateState : " + scheduled.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverFieldWorkCompleted_UpdateState(@Payload FieldWorkCompleted fieldWorkCompleted){

        if(fieldWorkCompleted.isMe()){
            Optional<Order> orderOptional = orderRepository.findById(fieldWorkCompleted.getOrderId());
            Order order = orderOptional.get();
            order.setState("FieldWorkCompleted");
            orderRepository.save(order);

            System.out.println("##### listener UpdateState : " + fieldWorkCompleted.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverFieldWorkFailed_UpdateState(@Payload FieldWorkFailed fieldWorkFailed){

        if(fieldWorkFailed.isMe()){
            Optional<Order> orderOptional = orderRepository.findById(fieldWorkFailed.getOrderId());
            Order order = orderOptional.get();
            order.setState("FieldWorkFailed");
            orderRepository.save(order);

            System.out.println("##### listener UpdateState : " + fieldWorkFailed.toJson());
        }
    }

}
