package wordnet

import org.apache.spark.{SparkConf, SparkContext}
import rita.RiWordNet

/**
  * Created by Mayanka on 26-06-2017.
  */
object WordNetSpark {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C:\\winutils")
    val conf = new SparkConf().setAppName("WordNetSpark").setMaster("local[*]").set("spark.driver.memory", "4g").set("spark.executor.memory", "4g")
    val sc = new SparkContext(conf)


    val data=sc.textFile("input/input1")

    val dd=data.map(line=>{
      val wordnet = new RiWordNet("C:\\Users\\steph\\Downloads\\WordNet-3.0\\WordNet-3.0")
      val wordSet=line.split(" ")
      val synarr=wordSet.map(word=>{
        if(wordnet.exists(word))
          (word,getSynoymns(wordnet,word))
        else
          (word,null)
      })
      synarr
    })

    dd.collect().foreach(linesyn=>{
      var counter: Int = 0
      linesyn.foreach(wordssyn=>{
        if(wordssyn._2 != null) {
          counter += 1
          println("Words:Count " + counter + "   " + wordssyn._1 + " : " + wordssyn._2.mkString(","))
        }
      })
    })
  }
  def getSynoymns(wordnet:RiWordNet,word:String): Array[String] ={
    // println(word)
    val pos=wordnet.getPos(word)
    // println(pos.mkString(" "))
    val syn=wordnet.getAllSynonyms(word, pos(0), 10)
    syn
  }
}
