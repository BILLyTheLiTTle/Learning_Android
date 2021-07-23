package gr.sfhmmy.java.workshop;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    public String calculatingGreeting(int age, String greeting) {
        if(age<5){greeting = "Hello Kid";}
        else if (age<18){greeting = "Hello " + greeting; }
        else if (age < 60) { greeting = "Hello Mr/Ms " + greeting; }
        else {
            greeting = "Hello Grandma/Grandpa";
        }
        return greeting;
    }
}
