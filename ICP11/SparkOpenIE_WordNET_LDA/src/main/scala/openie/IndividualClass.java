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
public class IndividualClass {
    public static String returnTriplets(String sentence) throws FileNotFoundException {

        //take and read two files
        //medical category object with med and cat
        //then stick it in a list of these objects
        //then other list
        //close files
        //iterate through the lists to see if it contains the med word

        File subObj = new File("SubObj.txt");
        Scanner subjectObject = new Scanner(subObj);
        String[] subjObjList = new String[600];
        int soIter = 0;
        while (subjectObject.hasNextLine()) {

            if (subjectObject.hasNextLine()) {
                subjObjList[soIter] = subjectObject.nextLine();
                soIter++;
            }
        }
        File medWordC = new File("data/MedWordCategory");
        Scanner medWordCat = new Scanner(medWordC);
        String[] medWordList = new String[300];
        String[] categoryList = new String[300];
        int mwcIter = 0;
        while (medWordCat.hasNextLine()) {

            String[] temp = medWordCat.nextLine().split("\t");
            medWordList[mwcIter] = temp[0];
            categoryList[mwcIter] = temp[1];
            mwcIter++;
        }

        //now I have my whole files within my lists and I just got to do a simple comparison

        String triplets="";
        for (String subject: subjObjList) {
            for (int i = 0; i < medWordList.length; i++){

                if (subject != null && medWordList[i] != null && subject.contains(medWordList[i])){
                    //we got a match!!! so we need the subect and cat
                    triplets += subject + "," + categoryList[i] + "\n";
                    break;
                }
                //triplets+=subject+"\n"+object+"\n";
            }

        }

        return triplets;

    }

}
