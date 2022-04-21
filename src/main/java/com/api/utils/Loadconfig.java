package com.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Loadconfig
{
    public String HOST;
    public Integer staticWaitTime;

    public Loadconfig() {
        Properties prop = new Properties(); //to read properties file
        File f = new File("src/main/resources/Configfile.properties"); //
        System.out.println(f);

        try {
            FileInputStream fis = new FileInputStream(f); //
            prop.load(fis);                          //reading .properties file
            this.HOST = prop.get("HOST").toString(); //prop obj to read data from configfile properties
            this.staticWaitTime = Integer.valueOf(prop.get("SWAIT").toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)  {
        Loadconfig ldf = new Loadconfig();
        System.out.println(ldf.HOST);
        System.out.println(ldf.staticWaitTime);
    }

}