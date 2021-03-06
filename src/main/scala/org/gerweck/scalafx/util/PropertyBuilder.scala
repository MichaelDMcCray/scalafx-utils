package org.gerweck.scalafx.util

import scalafx.beans.property._

/** A property builder is something that knows how to build a JavaFX Property
  * for a given type of object.
  *
  * @author Sarah Gerweck <sarah@atscale.com>
  */
trait PropertyBuilder[A] {
  type Prop <: SimpleProperty[A]
  def makeNew(default: A): Prop
}

sealed trait PropertyBuilderLP {
  implicit def objectPropertyBuilder[A]: PropertyBuilder[A] = new PropertyBuilder[A] {
    type Prop = ObjectProperty[A]
    def makeNew(default: A) = ObjectProperty[A](default)
  }
}

object PropertyBuilder extends PropertyBuilderLP {
  implicit object IntPropertyBuilder extends PropertyBuilder[Int] {
    type Prop = IntegerProperty
    def makeNew(default: Int) = IntegerProperty(default)
  }
  implicit object LongPropertyBuilder extends PropertyBuilder[Long] {
    type Prop = LongProperty
    def makeNew(default: Long) = LongProperty(default)
  }
  implicit object FloatPropertyBuilder extends PropertyBuilder[Float] {
    type Prop = FloatProperty
    def makeNew(default: Float) = FloatProperty(default)
  }
  implicit object DoublePropertyBuilder extends PropertyBuilder[Double] {
    type Prop = DoubleProperty
    def makeNew(default: Double) = DoubleProperty(default)
  }
  implicit object BooleanPropertyBuilder extends PropertyBuilder[Boolean] {
    type Prop = BooleanProperty
    def makeNew(default: Boolean) = BooleanProperty(default)
  }
  implicit object StringPropertyBuilder extends PropertyBuilder[String] {
    type Prop = StringProperty
    def makeNew(default: String) = StringProperty(default)
  }

  def apply[A](default: A)(implicit builder: PropertyBuilder[A]): builder.Prop = {
    builder.makeNew(default)
  }
}
