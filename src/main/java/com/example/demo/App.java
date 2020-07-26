package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);

        
        
//        String txtChck = "Simple text to test";

//        String txtChck = "<iframe>teste</iframe>";

//        String txtChck = "<title>Example document: %(title)</title>";
//        String txtChck = "<p>Hello, <b onclick=alert(1337)>World</b>!</p>\"";
//        String txtChck = "<style>body {background-color: lightblue;} </style>";
        String txtChck = "\" or \"=\"";
        //-------------------------------------------------------
        
        Cleanup cleanup = new Cleanup();
        
        cleanup.canonic(txtChck);
        
        cleanup.validate(txtChck);
        
        cleanup.sanit(txtChck);

        cleanup.encod(txtChck, "sql");

    }

}
