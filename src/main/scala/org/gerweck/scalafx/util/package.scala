package org.gerweck.scalafx

import language.implicitConversions
import language.existentials

import scalafx.Includes._
import scalafx.beans.property._
import scalafx.beans.value._
import scalafx.event.subscriptions.Subscription
import scalafx.scene.Node
import scalafx.scene.control._
import scalafx.scene.input._
import scalafx.scene.layout.GridPane
import scalafx.scene.text.Text
import scalafx.util.StringConverter

import scalaz._

/** Various implicits and global utilities for ScalaFX work.
  *
  * @author Sarah Gerweck <sarah@atscale.com>
  */
package object util extends ObservableImplicits with LowPriorityImplicits {
  type Observable[A] = ObservableValue[A, _]
  type SimpleProperty[A] = Property[A, _]

  object TextDisplay {
    def apply(text: ObservableValue[String,String]) = {
      val t = new scalafx.scene.text.Text
      t.text <== text
      t
    }
  }

  implicit class RichGridPane(val inner: GridPane) extends AnyVal {
    def addToRow(ri: Int, children: Node*) = inner.addRow(ri, children map {_.delegate}: _*)
  }

  implicit class RichMenuItem(val inner: MenuItem) extends AnyVal {
    def withKey(char: Char): MenuItem = {
      val c: Char = char.toUpper
      inner.accelerator = new KeyCodeCombination(KeyCode(c.toString), KeyCombination.ShortcutDown)
      inner
    }
  }
}
