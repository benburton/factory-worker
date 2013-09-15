## factory-worker

factory-worker is a library designed to make building fixture data easier in Scala. It is inspired by, and named in reference to, [thoughtbot](http://www.thoughtbot.com/community)'s [factory_girl](https://github.com/thoughtbot/factory_girl).

### Install

Add factory-worker to your SBT dependencies:

    "com.benburton" %% "factory-worker" % "0.1"
    
Run sbt compile

    sbt compile

### Usage

To start using factory-worker define a trait extending Factory[T] with the type of object you want to build and implement methods returning the objects you wish to build:

    trait BlogPostFactory extends Factory[BlogPost] {
      def unpublished: BlogPost {
        new BlogPost(published = false)
      }
    }


Then you can use the Factory in the context of, for example, a [specs2](http://etorreborre.github.io/specs2/) Specification as follows:

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