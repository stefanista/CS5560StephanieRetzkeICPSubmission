

import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by Mayanka on 09-Sep-15.
 */
object SparkWordCount {

  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir","C:\\winutils");

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]") //do .set("spark.executer.memory","4g").set("spark.driver.memory", "4g") if out of memory exception

    val sc=new SparkContext(sparkConf)

    val input=sc.textFile("input1", 10)

    val inputf = sc.wholeTextFiles("input",10).map(abs=>{
      abs._1
      abs._2
    }).flatMap(line=>{line.split(" ")}).map(word => (word,1))

    //val wc =inputf.map(word=>(word,1)) //.cache()

    val output=inputf.reduceByKey(_+_)

    output.saveAsTextFile("output")

    val o=output.collect()

    var s:String="Words:Count \n"
    o.foreach{case(word,count)=>{

      s+=word+" : "+count+"\n"

    }}

  }

}
