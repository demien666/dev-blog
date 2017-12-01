package com.demien.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by dmytro.kovalskyi on 11/8/2017.
 */
public class Opt {

    class MessageStates {
        List<Integer> state;

        public List<Integer> getState() {
            return state;
        }

        public void setState(List<Integer> state) {
            this.state = state;
        }
    }

    class MessageObject {
        MessageStates messageStates;
        boolean setUp = false;

        public MessageStates getMessageStates() {
            return messageStates;
        }

        public void setMessageStates(MessageStates messageStates) {
            this.messageStates = messageStates;
            setUp = true;
        }

        public boolean isSetUp() {
            return setUp;
        }
    }

    class MessageWrapper {
        MessageObject messageObject;

        public MessageObject getMessageObject() {
            return messageObject;
        }

        public void setMessageObject(MessageObject messageObject) {
            this.messageObject = messageObject;
        }
    }

    public void run() {
        MessageWrapper wrapper = new MessageWrapper();
        MessageObject messageObject = new MessageObject();
        wrapper.setMessageObject(messageObject);

        MessageStates messageStates = new MessageStates();
        List<Integer> states = Arrays.asList(1,2,3,-1);
        messageStates.setState(states);

        messageObject.setMessageStates(messageStates);



        //MessageWrapper wrapper = null;
        Optional<MessageWrapper> optional = Optional.ofNullable(wrapper);
        Optional result=
        optional.map(MessageWrapper::getMessageObject)
                .filter(o->o.isSetUp())
                .map(o -> o.getMessageStates())
                .map(o->o.getState())
                .map(list->list.get(0))
                .filter(s->s==1);
        System.out.println(result);
    }


    public static void main(String[] args) {
        new Opt().run();
    }

}
