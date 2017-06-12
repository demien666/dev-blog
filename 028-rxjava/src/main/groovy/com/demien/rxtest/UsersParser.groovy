package com.demien.rxtest

import groovy.json.JsonSlurper
import io.reactivex.Observable

class UsersParser {

    static Observable getObservable(String json) {

        Observable result = Observable.create { subscriber ->

            try {
                def jsonSlurper = new JsonSlurper()
                def usersList = jsonSlurper.parseText(json)
                usersList.each({ e ->
                    def val = "[$e.email] $e.username"
                    subscriber.onNext((String) val)
                })
                subscriber.onComplete()
            } catch (Throwable ex) {
                subscriber.onError(ex)
            }
        }

        return result
    }

}
