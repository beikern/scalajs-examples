package example

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

trait JavaScriptContext {
  implicit def ctxCanvasState: CtxCanvasState
}

case class CtxCanvasState (canvas: Canvas, ctx2d: dom.CanvasRenderingContext2D)
