package com.benburton.factoryworker

class FactoryWorker(factories: Seq[Factory[_]]) {

  def build[T <: Any](name: String, arguments: AnyRef*) = {

    val method = factories.head
      .getClass
      .getDeclaredMethod(name)

    method.invoke(factories.head).asInstanceOf[T]
  }

}
