package edu.swarthmore.cs.cs71.shelved;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;


public class Server {
    JavaSparkContext jsc = new JavaSparkContext("spark://HOST:PORT", "Shelved");

}
