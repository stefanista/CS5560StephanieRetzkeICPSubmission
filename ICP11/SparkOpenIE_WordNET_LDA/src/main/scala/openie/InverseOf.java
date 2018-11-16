package openie;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.util.Quadruple;

import java.util.Iterator;
import java.io.*;
import java.io.File;
import java.util.Scanner;

/**
 * Created by Mayanka on 27-Jun-16.
 */
public class InverseOf {
    public static String returnTriplets(String sentence) throws FileNotFoundException {

        //take and read two files
        //medical category object with med and cat
        //then stick it in a list of these objects
        //then other list
        //close files
        //iterate through the lists to see if it contains the med word

        File subObj = new File("Triplets.txt");
        Scanner subjectObject = new Scanner(subObj);
        String[] subjObjList = new String[600];
        int soIter = 0;
        String subject,predicate,object = "";

        File medWordC = new File("Triplets.txt");
        Scanner medWordCat = new Scanner(medWordC);
        String[] medWordList = new String[300];
        String[] categoryList = new String[300];
        String subject2,predicate2,object2 = "";
        int mwcIter = 0;

        String triplets="";

        while (subjectObject.hasNextLine()) {

            if (subjectObject.hasNextLine()) {
                String[] temp = subjectObject.nextLine().split(",");
                subject = temp[0];
                predicate = temp[1];
                object = temp[2];
                soIter++;

                while (medWordCat.hasNextLine()) {

                    String[] temp2 = medWordCat.nextLine().split(",");
                    subject2 = temp2[0];
                    predicate2 = temp2[1];
                    object2 = temp2[2];

                    if (subject.equals(object2) && object.equals(subject2)){
                        triplets += "predicate1: " + predicate + " and Predicate 2 " + predicate2 + " Might be InverseOf " + "\n";
                    }

                    mwcIter++;
                }
            }
        }


        //now I have my whole files within my lists and I just got to do a simple comparison

        return triplets;

    }

}
