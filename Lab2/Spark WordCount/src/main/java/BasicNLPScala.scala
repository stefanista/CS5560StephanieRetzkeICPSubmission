import org.apache.spark.{SparkConf, SparkContext}
import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations.{PartOfSpeechAnnotation, SentencesAnnotation, TextAnnotation, TokensAnnotation}
import edu.stanford.nlp.ling.CoreLabel
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import edu.stanford.nlp.util.CoreMap
import rita.RiWordNet

import scala.collection.JavaConverters._

object BasicNLPScala{
  def main(args: Array[String]): Unit = {

    // For Windows Users
    System.setProperty("hadoop.home.dir", "C:\\winutils")
    // Configuration
    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    //val input=sc.textFile("data/abstracts",minPartitions = 10)




    val input=sc.textFile("input/input1",minPartitions = 10)

    val wc=input.map(line=>{BasicNLP.returnpos(line).split(", ")}).map(word=>("NN",1)).cache()

    val output=wc.reduceByKey(_+_)
    //output.saveAsTextFile("output1")
    val o=output.collect()
    var s:String="Words:Count \n"
    o.foreach{
      case(word,count)=>{
        s+=word+" : "+count+"\n"
      }}



  }

}