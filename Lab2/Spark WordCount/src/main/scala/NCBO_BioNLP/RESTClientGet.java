package NCBO_BioNLP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class RESTClientGet {
    public static void main(String[] args)
	{

			String Bioconcept="BioConcept";
			String Inputfile= "input/ex.pmid";
			String Format="PubTator";
            ArrayList<String> medicalWords = new ArrayList<String>();

			try {

				//pmids
				BufferedReader fr= new BufferedReader(new FileReader(Inputfile));
				String pmid = "";
				Integer counter = 0;
                int line_ct = 0;
				while((pmid = fr.readLine()) != null)
				{
					URL url_Submit;
					url_Submit = new URL("https://www.ncbi.nlm.nih.gov/CBBresearch/Lu/Demo/RESTful/tmTool.cgi/" + Bioconcept + "/" + pmid + "/"+Format+"/");
					HttpURLConnection conn_Submit = (HttpURLConnection) url_Submit.openConnection();
					conn_Submit.setDoOutput(true);
					BufferedReader br_Submit = new BufferedReader(new InputStreamReader(conn_Submit.getInputStream()));
					String line="";
                    while ((line = br_Submit.readLine()) != null) {
                        String[] word_info = line.split("\t");
                        //System.out.println(Arrays.toString(word_info));
                        // Array must not be empty or contain 1 element of ""
                        if (word_info.length > 4 && !word_info[0].equals("")) {
                            if (line_ct < 2) {
                                System.out.println(line);
                            } else {
                                // Extract PMID and output the word and the genre associated with the word
                                System.out.println(word_info[3]);
                            }
                        }
                        line_ct++;
                    }
					conn_Submit.disconnect();
				}
				fr.close();
			}
			catch (MalformedURLException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}

    }
}