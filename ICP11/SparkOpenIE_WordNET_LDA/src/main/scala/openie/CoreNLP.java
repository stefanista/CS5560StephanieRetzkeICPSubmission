package openie;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.util.Quadruple;
import java.util.Collection;
import java.util.Iterator;
import java.util.Collection;

/**
 * Created by Mayanka on 27-Jun-16.
 */
public class CoreNLP {
    public static String returnTriplets(String sentence) {

        Document doc = new Document(sentence);
        String triplets="";
        for (Sentence sent : doc.sentences()) {  // Will iterate over two sentences

            //triplets+=sent+",";
            Iterator<Quadruple<String, String, String, Double>> openIETripletList=sent.openie().iterator();
            int noOfTriplets=0;
           // while(openIETripletList.hasNext()) {
                //triplets+= l.toString();
                // singleTriplet somehow check size and select only one
            if (openIETripletList.hasNext()) {
                Quadruple<String, String, String, Double> singleTriplet = openIETripletList.next();
                String subject = singleTriplet.first.toLowerCase();
                String predicate = singleTriplet.second.toLowerCase();
                String object = singleTriplet.third.toLowerCase();
                // subject;object;predicate;\n

                //triplets+=predicate+",Subject,Object,Func\n";
                //triplets+=subject+"\n"+object+"\n";
                //triplets += subject + "," + predicate + "," + object + ",Obj\n";
                triplets += subject + "," + predicate + "," + object;
                noOfTriplets++;
            }
           // }
            //System.out.println(triplets);
            //triplets+=","+noOfTriplets+"\n";

        }
        /**
         *  Triplet Results
         *  sentence, subject;predicate;object\n subject;predicate;object \n , No. Of Triplets
         */

        return triplets;

    }

}
