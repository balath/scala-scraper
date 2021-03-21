import java.io.File
import java.net.URL
import com.typesafe.scalalogging.Logger
import org.jsoup.Jsoup
import scala.jdk.CollectionConverters._
import scala.language.postfixOps
import scala.sys.process._

object NaivePdfScraper extends App{

  val logger = Logger("Downloads")
  val LINKS_SELECTOR = "a[href]"
  val baseUrl = "http://cs.brown.edu/people/rtamassi/gdhandbook/"
  val targetUrl = "http://cs.brown.edu/people/rtamassi/gdhandbook/"
  val localPath = "C:\\Learn\\libros-de-mates\\"  //Local path must exist
  val downloadableLinks = appendRootUrl(baseUrl, getLinks(targetUrl)) //Use this value when links are relative paths
  //val downloadableLinks = getLinks(targetUrl) //Use this value when links are absolute paths

  downloadFiles(downloadableLinks, localPath) //Main program

  def getLinks(url: String): List[String] =
  Jsoup.connect(url).get().select(LINKS_SELECTOR).asScala.toList.map(_.attr("href")).filter(_.endsWith(".pdf"))

  def appendRootUrl(rootUrl: String, links: List[String]): List[String] = links.map(link => s"$rootUrl$link")

  def downloadFiles(downloadableFileLinks: List[String], path: String): Unit = {
    for {
      link <- downloadableFileLinks
        filePath = s"$path/${link.reverse.takeWhile(!_.equals('/')).reverse}"
    } yield {
      logger.info(s"Downloading $link")
      new URL(link) #> new File(filePath) !!
    }
  }
}

