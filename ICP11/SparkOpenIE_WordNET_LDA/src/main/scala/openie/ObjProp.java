package openie;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.util.Quadruple;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Mayanka on 27-Jun-16.
 */
public class ObjProp {
    public static String returnTriplets(String sentence) throws FileNotFoundException {

        //Change this to do the Object Properties

        File subObj = new File("Test.txt");
        Scanner indCategory = new Scanner(subObj);
        String[] individualList = new String[600];
        String[] categoryList = new String[600];
        int mwcIter = 0;
        while (indCategory.hasNextLine()) {

            String[] temp = indCategory.nextLine().split(",");
            individualList[mwcIter] = temp[0];
            categoryList[mwcIter] = temp[1];
            mwcIter++;
        }

        Document doc = new Document(sentence);
        String triplets="";
        for (Sentence sent : doc.sentences()) {  // Will iterate over two sentences

            Iterator<Quadruple<String, String, String, Double>> openIETripletList=sent.openie().iterator();
            int noOfTriplets=0;

            if (openIETripletList.hasNext()) {
                Quadruple<String, String, String, Double> singleTriplet = openIETripletList.next();
                String subject = singleTriplet.first;
                String predicate = singleTriplet.second;
                String object = singleTriplet.third;
                // subject;object;predicate;\n

                triplets += predicate;
                boolean gotACategory = false;
                for (int i = 0; i < individualList.length; i++){

                    if (subject != null && individualList[i] != null && subject.contains(individualList[i])){
                        //we got a match!!! so we need the subect and cat
                        triplets += "," + categoryList[i];
                        gotACategory = true;
                        break;
                    }
                }
                if (!gotACategory){
                    triplets += ",Misc";
                }
                gotACategory = false;
                for (int i = 0; i < individualList.length; i++){

                    if (subject != null && individualList[i] != null && object.contains(individualList[i])){
                        //we got a match!!! so we need the subect and cat
                        triplets += "," + categoryList[i];
                        gotACategory = true;
                        break;
                    }
                }
                if (!gotACategory){
                    triplets += ",Misc";
                }

                triplets+= ",Func" + "\n";
                noOfTriplets++;
            }
        }
        return triplets;

    }

}
