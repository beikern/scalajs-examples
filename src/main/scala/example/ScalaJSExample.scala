package example

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

import scala.scalajs.js

object ScalaJSExample extends js.JSApp
  with JavaScriptContextImpl
  with CanvasConfiguration {
  override def main(): Unit = {

    js.timers.setInterval(1000){
      render
    }
    // forma vieja -> setInterval(render _, 1000)

  }

  def render(implicit state: CtxCanvasState) = {

    val date = new js.Date()
    state.ctx2d.clearRect(
      0, 0, state.canvas.width, state.canvas.height
    )

    state.ctx2d.font = "75px sans-serif"
    state.ctx2d.fillText(
      Seq(
        date.getHours(),
        date.getMinutes(),
        date.getSeconds()
      ).mkString(":"),
      state.canvas.width / 2,
      state.canvas.height / 2
    )
  }
}


trait JavaScriptContextImpl extends JavaScriptContext {

  private val canvas: Canvas = dom.document.getElementById("canvas").asInstanceOf[Canvas]
  private val ctx2d: dom.CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  override implicit lazy val ctxCanvasState = CtxCanvasState(canvas, ctx2d)
}