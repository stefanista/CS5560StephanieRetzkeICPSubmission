import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import java.io._

object RulesEngine {

  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir", "C:\\winutils")

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)

    val inputf = sc.textFile("Triplets.txt", 4)

    inverseof(inputf)
    symmetry(inputf)
    transtive(inputf)
    propertyAxiom(inputf)
    irreflexive(inputf)

  }

  //functions
  def inverseof(inputf : RDD[String]): Unit =
  {

    //gets each word
    val trip1 = inputf.map(line => line.split(",")).collect()
    val trip2 = trip1
    //sets up writer
    val pw = new PrintWriter(new File("rulesout/inverseOf.txt" ))

    //checks if subject1 is the same as object2 and vice versa
    //when the predicates are different
    trip1.foreach( t1 =>
    {
      trip2.foreach( t2 => {
        if (t1(0).equals(t2(2))  &&  t1(2).equals(t2(0)) && !t1(1).equals(t2(1)) )
          pw.write(t1(0) + "," + t1(1) + "," + t1(2) + " INVERSE TO " + t2(0) + "," + t2(1) + "," + t2(2) + "\n")
      })
    })
    pw.close()
  }


  def symmetry(inputf : RDD[String]): Unit =
  {

    val trip1 = inputf.map(line => line.split(",")).collect()
    val trip2 = trip1
    val pw = new PrintWriter(new File("rulesout/symmetry.txt" ))

    //checks if subject1 is equal to object2 and vice versa
    //when the predicates are the same
    trip1.foreach( t1 =>
    {
      trip2.foreach( t2 => {
        if (t1(0).equals(t2(2))  &&  t1(2).equals(t2(0)) && t1(1).equals(t2(1)) )
          pw.println(t1(0) + "," + t1(1) + "," + t1(2) + " SYMMETRIC TO " + t2(0) + "," + t2(1) + "," + t2(2) + "\n")
      })
    })
    pw.close()
  }


  def transtive(inputf : RDD[String]): Unit =
  {

    val trip1 = inputf.map(line => line.split(",")).collect()
    val trip2 = trip1
    val pw = new PrintWriter(new File("rulesout/transtive.txt" ))

    //checks if object1 is the same as subject2
    //the predicates are equal
    //and object1 is not the same as object2
    trip1.foreach( t1 =>
    {
      trip2.foreach( t2 => {
        if (t1(2).equals(t2(0))   && t1(1).equals(t2(1)) &&  !t1(2).equals(t2(2))) {
          println(t1(0) + "," + t1(1) + "," + t1(2) )
          println(t2(0) + "," + t2(1) + "," + t2(2) )
          println("Transtive :" )
          println(t1(0) + "," + t1(1) + "," + t2(2) )
        }
      })
    })
    pw.close()
  }


  def propertyAxiom(inputf : RDD[String]): Unit =
  {

    val trip1 = inputf.map(line => line.split(",")).collect()
    val trip2 = trip1
    val trip3 = trip1
    val pw = new PrintWriter(new File("rulesout/propertyaxiom.txt" ))

    //checks to see if object1 is the same as subject2
    //the predicates are the same
    //and object1 is not equal to object2
    trip1.foreach( t1 =>
    {
      trip2.foreach( t2 => {
        if (t1(2).equals(t2(0))   && t1(1).equals(t2(1)) &&  !t1(2).equals(t2(2))) {
          trip3.foreach(t3 => {
            if (t1(0).equals(t3(0)) && t2(2).equals(t3(2)) && !t1(1).equals(t3(1)) ) {
              println(t1(0) + "," + t1(1) + "," + t1(2) )
              println(t2(0) + "," + t2(1) + "," + t2(2) )
              println("property axiom :" )
              println(t3(0) + "," + t3(1) + "," + t3(2) )
            }
          }
          )
        }
      })
    })

    pw.close()
  }

  def irreflexive(inputf : RDD[String]): Unit =
  {

    val trip1 = inputf.map(line => line.split(",")).collect()
    val trip2 = trip1
    val pw = new PrintWriter(new File("rulesout/irreflexive.txt" ))

    //checks to see if object2 is the same as subject2
    //checks to see if subject1 is the same as subject2
    //and object1 is not equal to object2
    //and the predicates are not the same
    trip1.foreach( t1 =>
    {
      trip2.foreach( t2 => {
        if (t2(2).equals(t2(0)) && t1(0).equals(t2(0)) && !t1(2).equals(t2(2))  && t1(1).equals(t2(1)) ) {
          println(t1(0) + "," + t1(1) + "," + t1(2) )
          println(t2(0) + "," + t2(1) + "," + t2(2) )
        }
      })
    })

    pw.close()
  }
}