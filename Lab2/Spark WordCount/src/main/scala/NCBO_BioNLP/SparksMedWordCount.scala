package NCBO_BioNLP

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}


/**
  * Created by Mayanka on 27-Jun-16.
  */
object SparksMedWordCount {

  def main(args: Array[String]): Unit = {

    // For Windows Users
    System.setProperty("hadoop.home.dir", "C:\\winutils")
    // Configuration
    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    //val input=sc.textFile("data/abstracts",minPartitions = 10)




    val input=sc.textFile("input/input2",minPartitions = 2)

    val wc=input.flatMap(line=>{line.split(" ")}).map(word=>(word,1)).cache()

    val output=wc.reduceByKey(_+_)
    output.saveAsTextFile("output2")
    val o=output.collect()
    var s:String="Words:Count \n"
    o.foreach{
      case(word,count)=>{
        s+=word+" : "+count+"\n"
      }}



  }

}