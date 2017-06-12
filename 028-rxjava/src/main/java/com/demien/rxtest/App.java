package com.demien.rxtest;

import io.reactivex.Observable;


public class App {

    public static final String UsersURL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {
        String json = HttpReader.read(UsersURL);

        Observable<String> userObservable = UsersParser.getObservable(json);

        userObservable.subscribe(
                t -> System.out.println(t),
                e -> e.printStackTrace(),
                () -> System.out.println("done")
        );


        Observable<String> filteredObservable = userObservable
                .map(e -> e.toUpperCase())
                .filter(e -> e.indexOf("BIZ") > 0);
        filteredObservable.subscribe(e -> System.out.println(e));

        Observable.just(1, 2, 3, 4, 2, 1, 3)
                .distinct()
                .subscribe(e -> System.out.print(e));

        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Observable.fromArray(array).flatMap(e -> Observable.fromArray(e)).subscribe(e -> System.out.println(e.getClass().toString()));


        String s= "1234567";
        System.out.println(s.substring(0,4));

    }

}
