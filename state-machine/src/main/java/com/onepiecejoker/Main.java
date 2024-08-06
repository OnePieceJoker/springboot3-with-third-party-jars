package com.onepiecejoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;

import com.onepiecejoker.StatesAndEvents.Events;
import com.onepiecejoker.StatesAndEvents.States;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    private StateMachine<States, Events> stateMachine;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // stateMachine.sendEvent(StatesAndEvents.Events.E1);
        // stateMachine.sendEvent(StatesAndEvents.Events.E2);

        // stateMachine.sendEvent(MessageBuilder.withPayload(StatesAndEvents.Events.E1).build());
        // stateMachine.sendEvent(MessageBuilder.withPayload(StatesAndEvents.Events.E2).build());

        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(StatesAndEvents.Events.E1).build())).subscribe();
        // System.out.println("result: " + result);
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(StatesAndEvents.Events.E2).build())).subscribe();

    }
}