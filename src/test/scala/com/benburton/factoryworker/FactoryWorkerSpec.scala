package com.benburton.factoryworker

import org.specs2.mutable.Specification
import java.util.Date

class FactoryWorkerSpec extends Specification {

  /** Example class to be instantiated **/
  case class BlogPost(published: Boolean, publishedAt: Option[Date] = None) {
    def publish(): BlogPost = BlogPost(published = true, publishedAt = Some(new Date()))
  }

  /** Example Factory to instantiate class **/
  object BlogPostFactory extends Factory[BlogPost] {

    def unpublished(): BlogPost = {
      BlogPost(published = false)
    }

  }

  "FactoryWorker" should {

    "build" should {

      "produce instance of appropriate class" in {
        val factoryWorker = FactoryWorker(Seq(BlogPostFactory))
        val unpublishedBlogPost = factoryWorker.build[BlogPost]("unpublished")
        unpublishedBlogPost.getClass.toString + "$" === BlogPost.getClass.toString
      }

    }

  }



}
