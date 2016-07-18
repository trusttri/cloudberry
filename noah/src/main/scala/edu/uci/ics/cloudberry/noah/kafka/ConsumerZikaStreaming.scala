package edu.uci.ics.cloudberry.noah.kafka

import edu.uci.ics.cloudberry.noah.feed.{CmdLineAux, Config}
import org.kohsuke.args4j.CmdLineException

/**
  * Created by Monique on 7/18/2016.
  */

object ConsumerZikaStreaming {
  def main(args: Array[String]) {
    try {
      val config: Config = CmdLineAux.parseCmdLine(args)

      if (config.getKafkaServer.isEmpty || config.getKafkaId.isEmpty || config.getAxServer.isEmpty) {
        throw new CmdLineException("Should provide a server for both kafka and asterixDB(hostname:port) and a consumer ID")
      }

      val topics: Array[String] = Array(config.getTopicZikaStream)
      ConsumerKafka.run(config, topics, "ds_zika_streaming")
    }
    catch {
      case e: CmdLineException => {
        e.printStackTrace(System.err)
      }
    }
  }
}
