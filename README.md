## factory-worker

factory-worker is a library designed to make building fixture data easier in Scala. It is inspired by, and named in reference to, [thoughtbot](http://www.thoughtbot.com/community)'s [factory_girl](https://github.com/thoughtbot/factory_girl).

### Install

Add factory-worker to your SBT dependencies:

    "com.benburton" %% "factory-worker" % "0.1"
    
Run sbt compile

    sbt compile

### Usage

To start using factory-worker, define an object extending Factory[T] with T being the type of object you want FactoryWorker to produce. This object should include method declarations whose names correspond to the content of the objects you'd like to build. For example, the following Factory[BlogPost] example builds an "unpublished" BlogPost:

    object BlogPostFactory extends Factory[BlogPost] {

      def unpublished: BlogPost {
        new BlogPost(published = false)
      }

    }


Once you have a Factory[T], construct a FactoryWorker with a reference to the Factory:

    val factoryWorker = FactoryWorker(Seq(BlogPostFactory))


You can then use the build method to construct objects of the provided type:

    val unpublishedPost = factoryWorker.build[BlogPost]("unpublished")


You might use a FactoryWorker in the context of, for example, a [specs2](http://etorreborre.github.io/specs2/) Specification as follows:

    class BlogPostSpec extends Specification with FactoryWorker[BlogPostFactory] {
      
      "BlogPost" should {
        
        "publish" should {
          
          "return BlogPost with some publishedAt" in {

            val unpublishedPost = FactoryWorker[BlogPost].build("unpublished")
            val publishedPost = unpublishedPost.publish

            publishedPost.publishedAt match {
              case Some(date: Date) => success
              case _ failure
            }

          }
        
        }
        
      }
      
    }
