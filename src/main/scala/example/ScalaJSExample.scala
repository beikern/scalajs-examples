package example

import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.JSApp
import scalatags.JsDom.all._

object ScalaJSExample extends JSApp{
  override def main() = {

    val target = dom.document.getElementById("divid")

    //Example 3

    val listings: Seq[String] = Seq(
      "Apple", "Apricot", "Banana", "Cherry",
      "Mango", "Mangosteen", "Mandarin",
      "Grape", "Grapefruit", "Guava"
    )
    val box = input (
      `type`:="text",
      placeholder:= "type to filter the list"
    ).render

    def renderListings(sequence: Seq[String]) =
      ul(
        for{
          fruit <- sequence
          if fruit.toLowerCase.startsWith(box.value.toLowerCase)
        } yield {
          val (first, last) = fruit.splitAt(box.value.length)
          li(
            span(
              backgroundColor:="yellow",
              first
            ),
            last)
        }
      ).render




    val output = div(renderListings(listings)).render

    box.onkeyup = (e: dom.Event) => {
      output.innerHTML=""
      output.appendChild(renderListings(listings))
    }


    target.appendChild(
      div(
        h1("Search box!"),
        p("Type here to filter the list of things below!"),
        div(box),
        output
    ).render
    )



    // Example 2
    /*
    val box = input (
      `type`:="text",
      placeholder:="Type here!!!"
    ).render

    val output = span.render

    box.onkeyup = (e: dom.Event) => {
      output.textContent = box.value.toUpperCase()
    }

    target.appendChild(
      div(
        h1("Capital Box!"),
        p(
          "Type here and have it capitalized!"
        ),
        div(box),
        div(output).apply(style:="margin: 8px")
      ).render
    )*/


    // Example 1
    /*val (animalA, animalB) = ("fox", "dog")

    target.appendChild(
      div(
        h1("Hello World!"),
        p(
          "The quick brown ", b(animalA),
          " jumps over the lazy ",
          i(animalB), "."
        )
      ).render
    )

    target.appendChild(
      button(
        `type`:="button",
        onclick:= {() => lulz},
        "thelulz"
      ).render
    )*/
  }

  def lulz = {
    println("lulz ejecutado")
    val x = dom.document.createElement("div").asInstanceOf[html.Div]
    x.appendChild(
      div(
        p(
          "for the lulz"
        )
      ).render
    )
    dom.document.body.appendChild(x)
  }
}

