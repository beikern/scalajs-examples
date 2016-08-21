package example

trait CanvasConfiguration {
  self: JavaScriptContext =>

  /*setup*/
  ctxCanvasState.canvas.width = ctxCanvasState.canvas.parentElement.clientWidth
  ctxCanvasState.canvas.height = 400

  val gradient = ctxCanvasState.ctx2d.createLinearGradient(
    ctxCanvasState.canvas.width / 2 - 100, 0, ctxCanvasState.canvas.width/ 2 + 100, 0
  )
  gradient.addColorStop(0,"red")
  gradient.addColorStop(0.5,"green")
  gradient.addColorStop(1,"blue")
  ctxCanvasState.ctx2d.fillStyle = gradient
  //renderer.fillStyle = "black"

  ctxCanvasState.ctx2d.textAlign = "center"
  ctxCanvasState.ctx2d.textBaseline = "middle"
}
