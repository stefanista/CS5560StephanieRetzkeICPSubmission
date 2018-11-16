package openie

import java.io.PrintStream

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Mayanka on 27-Jun-16.
  */
object matcher {

  def main(args: Array[String]) {
    // Configuration
    // For Windows Users
    System.setProperty("hadoop.home.dir", "C:\\winutils")

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)




    // Turn off Info Logger for Console
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    //val i=0
    val subObjects = sc.textFile("SubObj.txt")
    val input = sc.textFile("data/sample").map(line => {
      //Getting OpenIE Form of the word using lda.CoreNLP
      //val line2=line.replaceAll("[,]"," ")
      val t=IndividualClass.returnTriplets(line)
      t
    }).collect()

    val out= new PrintStream("Test.txt")
    input.foreach(f=>{
      out.println(f)
    })
    out.close()
    //println(CoreNLP.returnTriplets("The dog has a lifespan of upto 10-12 years."))
    //println(input.collect().mkString("\n"))



  }

}
